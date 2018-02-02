package com.huishu.ZSServer.controller.company;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.dto.IndusCompanyDTO;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.entity.vo.CompanyVO;
import com.huishu.ZSServer.service.company.IndusCompanyService;
import com.huishu.ZSServer.service.openeyes.impl.OpeneyesServiceImpl;

/**
 * @author hhy
 * @date 2017年11月8日
 * @Parem
 * @return 
 * 精准招商模块--智能招商
 */
@Controller
@RequestMapping(value = "/apis/getcompany")
public class AccurateController extends BaseController{
	
	private static final Logger LOGGER = Logger.getLogger(AccurateController.class);
	
	@Autowired
	private IndusCompanyService service;
	@Autowired
	private OpeneyesServiceImpl openeyesServiceImpl;
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/search/"+page;
	}
	
	/**
	 * 根据公司名称查询公司信息
	 * @param companyName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findCompanyInfoByName.json")
	public AjaxResult findCompanyInfoByName(String companyName){
		if(StringUtil.isEmpty(companyName)){
			LOGGER.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		BaseInfo info = service.getCompanyInfo(companyName);
		return success(info);
	}
	
	/**
	 * 智能推送公司列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/listCompanyInfo.json")
	public AjaxResult listCompanyInfo(){
//		Iterable<IndusCompany> list = service.listCompany();
		Iterable<IndusCompanyDTO> list = service.listCompany();
		return success(list);
	}
	
	/**
	 * 模糊查询企业列表
	 * @param companyName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "listCompanyByName.json", method = RequestMethod.GET)
	public String listCompanyByName(String companyName,Model model){
		if(StringUtil.isEmpty(companyName)){
			model.addAttribute("message",MsgConstant.ILLEGAL_PARAM);
			return "/search/beamSearch";
		}
		try{
			OpeneyesDTO dto = new OpeneyesDTO();
			dto.setCname(companyName);
			dto.setUserId(getUserId());
			JSONObject list = openeyesServiceImpl.getSousuoCompanyList(dto);
			if(list.getJSONArray("data")==null){
				model.addAttribute("data",null);
			}
			else{
				List<CompanyVO> array = JSONArray.parseArray(list.getJSONArray("data").toJSONString(), CompanyVO.class);
				model.addAttribute("data",array);
			}
			model.addAttribute("companyName",companyName);
			return "/search/allCompany";
		} catch (Exception e) {
			LOGGER.error("模糊查询企业列表失败!", e);
			model.addAttribute("message",MsgConstant.SYSTEM_ERROR);
 			return "/search/beamSearch";
		}
	}
	
	/**
	 * 图片的上传和识别
	 * @param imageBase64   base64编码字符串
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "uploadImage.json", method = RequestMethod.POST)
	public AjaxResult uploadImage(String imageBase64){
		LOGGER.debug("上传图片的数据："+imageBase64);
		if(StringUtil.isEmpty(imageBase64)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try{
			return success(service.uploadImage(imageBase64));
		} catch (Exception e) {
			LOGGER.error("图片的上传和识别失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}

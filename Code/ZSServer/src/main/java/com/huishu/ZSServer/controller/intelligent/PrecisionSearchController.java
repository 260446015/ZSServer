package com.huishu.ZSServer.controller.intelligent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.huishu.ZSServer.entity.dto.LabelDTO;
import com.huishu.ZSServer.service.company.EnterPriseService;
import com.huishu.ZSServer.service.company.IndusCompanyDTOService;

/**
 * @author hhy
 * @date 2017年11月30日
 * @Parem
 * @return 
 * 精确筛选相关controller
 */
@Controller
@RequestMapping("/accurateScreening")
public class PrecisionSearchController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(PrecisionSearchController.class);
	@Autowired
	private EnterPriseService service;
	@Autowired
	private  IndusCompanyDTOService  iservice;
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/search/"+page;
	}
	@ResponseBody
	@RequestMapping(value="/getLabelInfoByIndustry.json",method=RequestMethod.GET)
	public AjaxResult getLabelInfoByIndustry(String industry){
		if(StringUtil.isEmpty(industry)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		List<String> list =service.getAareaByIndustry(industry);
		return success(list);
	}
	/**
	 * 精准筛选--搜企业
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCompanyInfoBySearch.json",method=RequestMethod.POST)
	public AjaxResult getCompanyInfoBySearch(@RequestBody LabelDTO  dto){
		if(dto==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String industry =dto.getIndustry();
		String[] money = dto.getRegister();
		String[] time = dto.getRegisterTime();
		String area = dto.getArea();
		if(area.equals("全部")&&industry.equals("全部")&&time[0].equals("全部")&&money[0].equals("全部")){
			List<IndusCompanyDTO> list = iservice.listCompany();
			return success(list);
		}else{
			if(area.equals("全部")){
				area = "";
			}
			if(industry.equals("全部")){
				industry = "";
			}
			
			List<IndusCompanyDTO> info = service.findCompanyList(industry,area,money,time);
			if(info!=null && info.size() != 0){
				JSONArray arr = new JSONArray();
				info.forEach(action->{
					JSONObject obj = new JSONObject();
					if(!action.getCreateTime().equals(action.getUpdateTime())){
						 obj.put("flag", true);
					}else{
						obj.put("flag", false);
						
					}
					obj.put("company", action.getCompany());
					obj.put("companyName", action.getCompanyName());
					obj.put("industry", action.getIndustry());
					obj.put("industryLabel", action.getIndustryLabel());
					obj.put("industryZero", action.getInduszero());
					arr.add(obj);
				});
				return success(arr);
			}
			return error("暂无数据");
		}
	}
	
}

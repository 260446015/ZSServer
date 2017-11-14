package com.huishu.ZSServer.controller.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.service.company.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年11月8日
 * @Parem
 * @return 
 * 精准招商模块--智能招商
 */
@RestController
@RequestMapping(value = "/apis/getcompany")
public class AccurateController extends BaseController{
	
	private static final Logger LOGGER = Logger.getLogger(AccurateController.class);
	
	@Autowired
	private IndusCompanyService service;
	
	/**
	 * 根据公司名称查询公司信息
	 * @param companyName
	 * @return
	 */
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
	@RequestMapping(value="/listCompanyInfo.json")
	public AjaxResult listCompanyInfo(){
		Iterable<IndusCompany> list = service.listCompany();
		return success(list);
	}
	
	/**
	 * 图片的上传和识别
	 * @param imageBase64   base64编码字符串
	 * @return
	 */
	@RequestMapping(value = "uploadImage.json", method = RequestMethod.POST)
	public AjaxResult uploadImage(@RequestParam String imageBase64){
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

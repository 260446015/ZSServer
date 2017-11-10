package com.huishu.ZSServer.controller.company;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.Company;
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
		List<Company> list = service.listCompany();
		return success(list);
	}
	
	/**
	 * 图片的上传与识别
	 * @return
	 */
	@RequestMapping(value = "uploadImage.json", method = RequestMethod.POST)
	public AjaxResult uploadImage(){
		return success(null);
	}
}

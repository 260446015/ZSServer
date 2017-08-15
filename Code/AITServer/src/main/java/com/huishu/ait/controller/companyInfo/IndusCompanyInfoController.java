package com.huishu.ait.controller.companyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.indusCompany.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年8月11日
 * 
 * 
 */
@Controller
@RequestMapping("/apis/indus")
public class IndusCompanyInfoController extends BaseController{
  @Autowired
  private IndusCompanyService service;
  /**
   * 根据产业名查询公司的信息
   * @param industry
   * @return
   */
  @ResponseBody
  @RequestMapping(name="/getCompanyInfoByIndustry.json",method=RequestMethod.POST)
  public AjaxResult getCompanyInfoByIndustry(@RequestBody String industry){
	  if(industry.isEmpty()){
		  	return error(MsgConstant.ILLEGAL_PARAM);
	  }	  
	  return success(service.findIndusInfoByIndustry(industry));
  }
  /**
   * 根据公司全名查询公司的详细信息
   * @param company
   * @return
   */
  @ResponseBody
  @RequestMapping(name="/findInfo.json",method=RequestMethod.GET)
  public AjaxResult  findInfo(@RequestBody String company){
	  if(company.isEmpty()){
		  	return error(MsgConstant.ILLEGAL_PARAM);
	  }	  
	  return success(service.findInfo(company));
  }
}

package com.huishu.ait.company.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.company.service.CompanyService;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;

@RestController
public class CompanyOrderController extends BaseController{

	@Resource
	private CompanyService cs;
	
	@RequestMapping(value="/findCompaniesDesc",method={RequestMethod.GET})
	public AjaxResult findCompanies(CompanyDTO dto){
		if(null == dto){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(cs.findCompaniesOder(dto));
	}
}

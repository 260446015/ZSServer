package com.huishu.ait.controller.company;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.service.company.CompanyService;
/**
 * 企业排行榜
 * @author yindawei 
 *
 */
@RestController
@RequestMapping("/company")
public class CompanyOrderController extends BaseController{

	@Resource
	private CompanyService cs;
	/**
	 * 查询企业排行
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/findCompaniesDesc.do")
	public AjaxResult findCompanies(CompanyDTO dto){
		if(null == dto){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(cs.findCompaniesOder(dto));
	}
}

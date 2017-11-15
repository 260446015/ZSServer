package com.huishu.ZSServer.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.service.company.CompanyService;

/**
 * 
 * @author yindawei 
 * @date 2017年10月31日下午2:16:21
 * @description 
 * @version
 */
@RestController
@RequestMapping(value="/apis/company")
public class CompnayController extends BaseController{
	@Autowired
	private CompanyService companyService;

	/**
	 * 查询企业列表（模板导入）
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="findCompanyList.json")
	public AjaxResult findCompanyList(@RequestBody CompanySearchDTO dto){
		if(dto.getMsg().length == 0){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(companyService.findCompanyList(dto));
	}
	
}

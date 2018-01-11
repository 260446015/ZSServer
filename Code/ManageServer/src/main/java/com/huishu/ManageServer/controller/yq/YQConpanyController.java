package com.huishu.ManageServer.controller.yq;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.service.second.company.CompanyService;

/**
 * @author hhy
 * @date 2018年1月10日
 * @Parem
 * @return 
 * 舆情企业信息管理
 */
@Controller
@RequestMapping("/apis/yq")
public class YQConpanyController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(YQConpanyController.class);
	@Autowired
	private CompanyService service;
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String findYQCompany(@PathVariable String page) {
		return "/yqinfo/" + page;
	}
	
	/**
	 * 分页查询舆情公司详情
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listCompany(@RequestBody AbstractDTO dto){
		try {
			Page<CompanyEntity> page = 	service.listCompany(dto);
			return successPage(page,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("列表查看舆情公司数据失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 * 删除一条舆情公司数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delCompany.json", method = RequestMethod.GET, params = "id")
	@ResponseBody
	public AjaxResult deleteCompany(String id){
		if(StringUtil.isEmpty(id)){
			LOGGER.error("删除舆情公司信息传参失误!");
			return error(MsgConstant.SYSTEM_ERROR);
		}
		boolean info = false;
		try {
			 info = service.deleteCompanyInfo(id);
			 if(info){
				 return success("删除成功！");
			 }else{
				 return error("删除失败！");
			 }
		} catch (Exception e) {
			LOGGER.error("删除舆情公司信息失败!",e);
			return error("删除失败！");
		}
	}
}

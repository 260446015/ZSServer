package com.huishu.ait.controller.headlines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.service.company.CompanyService;

/**
 * @author hhy
 * @date 2017年10月9日
 * @Parem
 * @return 
 * 企业排行的相关操作
 */
@Controller
@RequestMapping("/apis/enterprise")
public class EnterpriseRankController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(EnterpriseRankController.class);
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String showAccount(@PathVariable String page,Model model) {
		
		return "industry/"+page;
	}

	/**
	 * 查询企业排行的全部内容
	 * @param params
	 * @return
	 */
	
	@RequestMapping("/getCompanyInfo.json")
	public AjaxResult getCompanyInfo(String params){
		if(StringUtil.isEmpty(params)){
			LOGGER.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		Page<ArticleListDTO> list =companyService.findArticleList(param);
		return success(list);
	}
}

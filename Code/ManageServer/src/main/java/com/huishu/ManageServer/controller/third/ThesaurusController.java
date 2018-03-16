package com.huishu.ManageServer.controller.third;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.service.third.ThesaurusService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 
 */

@Controller
@RequestMapping("/apis/keyInfo")
public class ThesaurusController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(ThesaurusController.class);
	@Autowired
	private ThesaurusService service;
	
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}.html" }, method = RequestMethod.GET)
	public String findYQCompany(@PathVariable String page) {
		return "/thesaurus/" + page;
	}
	/**
	 *	获取所有的关键词集合
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listKeyWord.do", method = RequestMethod.GET)
	public AjaxResult listKeyWord(){
		try {
			JSONArray obj = service.findAllKeyWord();
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("列表查看舆情公司数据失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
}

package com.huishu.ZSServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.service.article.getArticleInfoService;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 
 */
@Controller
@RequestMapping("/apis/article")
public class ArticleInfoController extends 	BaseController{
	@Autowired
	private getArticleInfoService service;
	
	/**
	 * 根据文章id查看文章详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getArticleInfoById.json",method = RequestMethod.GET)
	@ResponseBody
	public  AjaxResult  getArticleInfoById(@RequestBody String id){
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(service.findArticleById(id));
	}
}

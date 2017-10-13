package com.huishu.ait.controller.article;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.service.article.ArticleService;

/**
 * @author hhy
 * @date 2017年9月26日
 * @Parem
 * @return 提供公用的文章操作的接口
 */
@Controller
@RequestMapping(value = "/art")
public class ArticleController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	private ArticleService service;

	/**
	 * 根据文章id删除文章内容
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteArticleById(String id) {
		if (StringUtil.isEmpty(id)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Boolean info = service.delArticleById(id);
		logger.info("根据文章id删除文章的结果是" + info);
		if (info) {
			return success("删除成功！");
		} else {
			return success("删除失败！");
		}
	}

	/**
	 * 根据文章id更新情感
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateEmotion", method = RequestMethod.GET)
	public AjaxResult updateEmotion( String id) {
		if (StringUtil.isEmpty(id)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Boolean info = service.updateEmotion(id);
		if (info) {
			return success("更新情感成功！");
		} else {
			return error("更新情感失败！");
		}

	}
	/**
	 * 根据id查看文章内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findInfo", method = RequestMethod.GET)
	public AjaxResult findArticleInfoById(String id,Map<String,Object>map) {
		if (StringUtil.isEmpty(id)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		AITInfo info = service.findArticleInfoById(id);
		map.put("message", info);
		return success(map);
	}
	
	/**
	 * 保存专家观点新增文章的功能
	 * @param ait
	 * @return
	 */
	@RequestMapping(value = "/saveArt.json", method = RequestMethod.POST)
	public String saveArt( AITInfo ait){
		service.saveArt(ait);
		return "industry/expertOpint";
	}
}

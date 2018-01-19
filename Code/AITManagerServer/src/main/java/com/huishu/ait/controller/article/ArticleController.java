package com.huishu.ait.controller.article;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.service.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public AjaxResult deleteArticleById(@RequestParam(value="ids[]") String[] ids) {
		if (StringUtil.isEmpty(ids.toString())) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Boolean info = service.delArticleById(ids);
		logger.info("根据文章id删除文章的结果是" + info);
		if (info) {
			return success("删除成功！");
		} else {
			return success("删除失败！");
		}
	}
	/**
	 * 根据文章id置顶文章
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/totop",method=RequestMethod.GET)
	public AjaxResult toTop(String id){
		if (StringUtil.isEmpty(id)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean info = service.toTop(id);
		logger.info("根据文章id置顶文章的结果是："+info);
		if(info){
			return success("置顶成功！");
		}else{			
			return success("已置顶,无需重复操作！");
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
	public ModelAndView findArticleInfoById(String id,ModelAndView model) {
		if (StringUtil.isEmpty(id) && getUserId()!=null) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject info = service.findArticleInfoById(id, getUserId());
		model.addObject("detail", info);
		model.setViewName("industry/detail");
		return model;
	}

	/**
	 * 根据id修改文章发布时间
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/modifyInfo.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult modifyInfo(String id,String time) {
		if (StringUtil.isEmpty(id) ||StringUtil.isEmpty(time)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = service.modifyInfo(id,time);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			logger.error("添加/修改用户失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
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
	
	/**
	 * @param requestParam
	 * @return 收藏专家观点
	 */
	@RequestMapping(value = "collectExpertOpinion.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult collectExpertOpinion(String id) {
		try {
			if (StringUtil.isEmpty(id) || getUserId() == null) {
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			JSONObject json = service.expertOpinionCollect(id, getUserId());
			return success(json);
		} catch (Exception e) {
			logger.error("收藏失败：", e.getMessage());
			return null;
		}
	}
	
	/**
	 * @param requestParam
	 * @return 取消收藏专家观点
	 */
	@RequestMapping(value = "cancelCollectExpertOpinion.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult cancelCollectExpertOpinion(String id) {
		try {
			if (StringUtil.isEmpty(id) || getUserId() == null) {
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			JSONObject json = service.cancelExpertOpinionCollect(id, getUserId());
			return success(json);
		} catch (Exception e) {
			logger.error("取消收藏失败：", e.getMessage());
			return null;
		}
	}
}

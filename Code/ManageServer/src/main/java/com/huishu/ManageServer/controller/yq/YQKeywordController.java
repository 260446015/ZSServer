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
import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.service.second.keyword.KeyInfoService;

/**
 * @author hhy
 * @date 2018年1月10日
 * @Parem
 * @return 
 * 产业标签关键词管理
 */
@Controller
@RequestMapping("/apis/keywordinfo")
public class YQKeywordController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger( YQKeywordController.class);
	
	@Autowired
	private KeyInfoService service;
	
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}.html" }, method = RequestMethod.GET)
	public String findKeywordInfo(@PathVariable String page) {
		return "/keyword/" + page;
	}
	/**
	 * 分页查询关键词
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listKeyWord.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listKeyWord(@RequestBody AbstractDTO dto){
		try {
			Page<KeyInfoEntity> page = 	service.ListKeyWordInfo(dto);
			return successPage(page,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("列表查看舆情公司数据失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 删除某一个关键词
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteKeyWord.json", method = RequestMethod.GET, params = "id")
	@ResponseBody
	public AjaxResult deleteKeyWord(String id){
		if(StringUtil.isEmpty(id)){
			LOGGER.error("删除关键词信息传参失误!");
			return error(MsgConstant.SYSTEM_ERROR);
		}
		boolean info = false;
		try {
			 info = service.deleteKeyWordInfo(id);
			 if(info){
				 return success("删除成功！");
			 }else{
				 return error("删除失败！");
			 }
		} catch (Exception e) {
			LOGGER.error("删除关键词信息失败!",e);
			return error("删除失败！");
		}
	}
	/**
	 * 更新或保存关键词
	 * @param ent
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdateKeyWord.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveOrUpdateKeyWord(@RequestBody KeyInfoEntity ent){
		boolean flag = service.saveOrUpdateKeyWord(ent);
		if(flag){
			return success("保存成功");
		}else{
			return error("保存失败！");
		}
	}
}

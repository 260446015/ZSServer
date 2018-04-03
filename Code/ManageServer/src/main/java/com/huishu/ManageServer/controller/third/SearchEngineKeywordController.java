package com.huishu.ManageServer.controller.third;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbThird.SearchEngineKeyword;
import com.huishu.ManageServer.service.third.SearchEngineKeywordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yindq
 * @since 2018-04-036
 */
@Controller
@RequestMapping("/apis/searchEngine")
public class SearchEngineKeywordController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(SearchEngineKeywordController.class);
	@Autowired
	public SearchEngineKeywordService keywordService;

	/**
	 * 获得所有的关键词
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findAllKeyWorld.json")
	public AjaxResult findAllKeyWorld(){
		try {
			return success(keywordService.findAllKeyWorld());
		} catch (Exception e) {
			LOGGER.error("获得所有的关键词失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 根据关键词搜索数据
	 * @param keyWorld
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findByKeyWorld.json")
	public AjaxResult findByKeyWorld(String keyWorld){
		try {
			List<String> list = keywordService.findByKeyWord(keyWorld);
			return success(list);
		} catch (Exception e) {
			LOGGER.error("根据关键词搜索数据失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 添加关键词，其他属性默认
	 * @param keyWorld
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addKeyWorld.json")
	public AjaxResult addKeyWorld(String keyWorld){
		try {
			boolean result=keywordService.addKeyWorld(keyWorld);
			if(result)
				return success(MsgConstant.OPERATION_SUCCESS);
			else
				return success(MsgConstant.OPERATION_ERROR);
		} catch (Exception e) {
			LOGGER.error("添加关键词失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
}

package com.huishu.ManageServer.controller.third;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.IndustrySummitDTO;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.addKeyWordDTO;
import com.huishu.ManageServer.es.entity.SummitInfo;
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
	public String findYQCompany(@PathVariable String page,String id ,Model model) {
		if("ThesaurusRelatedManage".equals(page)){
			if(StringUtil.isNotEmpty(id)){
				model.addAttribute("info", id);
			}
		}
		return "/thesaurus/" + page;
	}
	/**
	 *	根据id获取关联关系的信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findRelatedInfoById.json", method = RequestMethod.GET,params={"id"})
	public AjaxResult findRelatedInfoById( String id){
		try {
			if(StringUtil.isEmpty(id)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			JSONObject obj = service.findRelatedById(id);
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("根据id查看关键词以及关联关系!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 *	根据id删除关键词以及关联关系
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRelatedInfoById.json", method = RequestMethod.GET,params={"id"})
	public AjaxResult deleteRelatedInfoById( String id){
		try {
			if(StringUtil.isEmpty(id)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			boolean info = service.deleteRelatedById(id);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("根据id删除关键词以及关联关系失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	@ResponseBody
	@RequestMapping(value = "/listKeyWord.do", method = RequestMethod.GET)
	public AjaxResult listKeyWord(){
		try {
			JSONArray obj = service.findAllKeyWord();
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("列表查看舆情公司数据失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 * 关键词列表展示
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findKeyWordInfo.json", method = RequestMethod.POST)
	public AjaxResult findKeyWordInfo(@RequestBody TKeyWordDTO dto){
		try {
			if(StringUtil.isEmpty(dto.getType())){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			Page<ThesaurusEntity> page = service.findByPage(dto);
			return  successPage(page, page.getNumber()+1);
		} catch (Exception e) {
			LOGGER.error("获取关键词库列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 新增或者修改词
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpdateKeyWordData.json",method=RequestMethod.POST)
	public AjaxResult addOrUpdateKeyWordData(@RequestBody addKeyWordDTO dto){
		try {
			if(StringUtil.isEmpty(dto.getName())||StringUtil.isEmpty(dto.getType())){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			boolean info = service.saveOrUpdateInfo(dto);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("新增或者更新词失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
}

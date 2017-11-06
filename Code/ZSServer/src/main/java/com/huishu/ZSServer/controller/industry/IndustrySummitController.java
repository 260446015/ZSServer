package com.huishu.ZSServer.controller.industry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.es.entity.SummitInfo;
import com.huishu.ZSServer.service.indus.IndusSummitService;

/**
 * @author hhy
 * @date 2017年10月27日
 * @Parem
 * @return
 * 产业峰会接口文档
 */
@Controller
@RequestMapping("/apis/summit")
public class IndustrySummitController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(IndustrySummitController.class);
	@Autowired
	private IndusSummitService service ;
	
	/**
	 * 查看峰会列表
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AjaxResult ListIndustry(String[] msg) {
		if(msg.length==0){
			LOGGER.debug("差看峰会列表传参异常:"+msg.length);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String industry = 	msg[0];
		String indutryLabel = msg[1];
		String area = msg[2];
		JSONObject obj = new JSONObject();
		obj.put("industry", industry);
		obj.put("indutryLabel", indutryLabel);
		obj.put("area", area);
		Page<SummitInfo> page = service.getIndustryForPage(obj);
		return success(page);
	}
	
	/**
	 * 根据id查看峰会详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public AjaxResult getSummitInfoById(@RequestBody String  id){
		if(StringUtil.isEmpty(id)){
			LOGGER.debug("差看峰会详情异常:"+id);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		SummitInfo info = service.getSummitInfoById(id);
		return success(info);
	}
	
	/**
	 * 关注 峰会
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public AjaxResult insertSummitInfoById(UserSummitInfo info){
		if(info == null){
			LOGGER.debug("关注 峰会 详情 异常:"+info.getAid());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		
		boolean a = service.insertSummitInfoById(info);
		
		return success(a);
	}
	
	/**
	 * 取消 关注 峰会
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public AjaxResult deleteSummitInfoById(@RequestBody Long  id){
		if(id==null||id==0){
			LOGGER.debug("删除峰会详情异常:"+id);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean info = service.deleteSummitInfoById(id);
		return success(info);
	}
	
	
}

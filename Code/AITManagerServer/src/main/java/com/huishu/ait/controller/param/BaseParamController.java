package com.huishu.ait.controller.param;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.Param;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.param.ParamService;

/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 本方法主要针对公用参数的添加与删除
 */
@Controller
@RequestMapping(value = "/apis/param")
public class BaseParamController extends BaseController {

	@Autowired
	private ParamService service;

	private static Logger LOGGER = LoggerFactory.getLogger(BaseParamController.class);

	// 首次进入今日头条，将会给予提示
	@ResponseBody
	@RequestMapping(value = "/getInsertParam.json", method = RequestMethod.POST)
	public AjaxResult getInsertParam(@RequestBody Param param) {
		if (param == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long userId = getUserId();

		boolean b = false;
		String msg = param.getMsg();
		List<Param> params = new ArrayList<>();
		JSONObject obj = JSONObject.parseObject(msg);
		for (String key : obj.keySet()) {
			Param p = new Param();
			p.setIndustryInfo(key);
			p.setUid(userId);
			String industryLagel = obj.getString(key).replace("[", "").replace("]", "").replace("\"", "");
			p.setIndustryLagel(industryLagel);
			params.add(p);
		}
		try {
			b = service.getInsertParam(params, userId);
		} catch (Exception e) {
			LOGGER.error("存储今日头条爱好条件出错", e);
			return error("存储今日头条爱好条件出错");
		}
		if (!b) {
			return error("保存失败！");
		} else {
			return success(service.findByUid(userId));
		}
	}

	/**
	 * 根据当前的用户id 查询所有的参数信息--用于页面跳转时使用
	 * 
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getParamById.json")
	public AjaxResult getParamById() {
		Long userId = getUserId();
		if (userId == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(service.findByUid(userId));
	}

}

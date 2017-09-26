package com.huishu.ait.controller.Investmentmodule.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.TreeNode.TreeNode;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.IndicatorDTO;
import com.huishu.ait.service.indicator.IndicatorService;

/**
 * @author hhy
 * @date 2017年9月5日
 * @Parem
 * @return
 * 
 */
@Controller
@RequestMapping("/apis")
public class IndicatorController extends BaseController {

	@Autowired
	private IndicatorService service;

	/**
	 * 获取四级产业分类 /apis/getIndicator.json
	 * 
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getIndicator.json", method = RequestMethod.POST)
	public AjaxResult getIndicator(IndicatorDTO dto) {

		List<TreeNode> jsonArray = service.getIndicatorList(dto);
		return success(jsonArray);
	}

	/**
	 * 根据指标获取公司名录 /apis/getBusinessByIndicator.json
	 * 
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBusinessByIndicator.json", method = RequestMethod.POST)
	public AjaxResult getBusinessByIndicator(@RequestBody IndicatorDTO dto) {
		if (dto == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		dto = chack(dto);
		String[] msg = dto.getMsg();

		JSONArray jsonArray = service.getBusinessByIndicator(dto, msg.length);
		return success(jsonArray);
	}

	/**
	 * @param dto
	 * @return
	 */
	private IndicatorDTO chack(IndicatorDTO dto) {
		String[] msg = dto.getMsg();
		if (msg.length == 1) {
			dto.setFirstIndicator(msg[0]);
		} else if (msg.length == 2) {
			dto.setFirstIndicator(msg[0]);
			dto.setSecondIndicator(msg[1]);
		} else if (msg.length == 3) {
			dto.setFirstIndicator(msg[0]);
			dto.setSecondIndicator(msg[1]);
			dto.setThirdIndicator(msg[2]);
		} else {
			dto.setFirstIndicator(msg[0]);
			dto.setSecondIndicator(msg[1]);
			dto.setThirdIndicator(msg[2]);
			dto.setFourIndicator(msg[3]);
		}
		return dto;
	}
}

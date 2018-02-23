package com.huishu.ZSServer.controller.label;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.LabelDTO;
import com.huishu.ZSServer.service.label.GetLabelService;

/**
 * @author hhy
 * @date 2018年2月8日
 * @Parem
 * @return 
 * 
 */
@Controller
@RequestMapping("/apis/getlabel")
public class GetLabelController extends BaseController {
	
	private Logger LOGGER = LoggerFactory.getLogger(GetLabelController.class);
	@Autowired
	private GetLabelService service;
	/**
	 * 
	 * @param dto type = one 产业峰会 && type = two 融资速递
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getLabel.json",method=RequestMethod.POST)
	public AjaxResult getLabel(@RequestBody LabelDTO dto){
		if(StringUtil.isEmpty(dto.getType())||StringUtil.isEmpty(dto.getIndustry())){
			LOGGER.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj = new JSONObject();
		obj.put("type",dto.getType() );
		JSONObject jec = new JSONObject();
		JSONArray arr = new JSONArray();
		if(dto.getIndustry().equals("全部")){
			jec.put("industry", "物联网");
			jec = new  JSONObject();
			jec.put("industry", "人工智能");
			arr.add(jec);
			jec = new  JSONObject();
			jec.put("industry", "大数据");
			arr.add(jec);
			jec = new  JSONObject();
			jec.put("industry", "生物医药");
			arr.add(jec);
		}else{
			jec.put("industry",dto.getIndustry() );
			arr.add(jec);
		}
		obj.put("info", arr);
		
		return success(service.getLabelInfo(obj));
	}
}

package com.huishu.ait.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.Label;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanySearchDTO;
import com.huishu.ait.service.user.LabelService;

/**
 * 招商需求池--企业标签
 * 
 * @author yindq
 * @create 2017年9月22日
 */
@RestController
@RequestMapping(value = "/apis/label")
public class LabelController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(LabelController.class);
    @Autowired
    private LabelService labelService;
    
    /**
     * 获取当前园区需求池中企业标签
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "getLabel.json", method = RequestMethod.GET)
	public AjaxResult getLabel() {
		try {
			return success(labelService.getLabel(getUserPark()));
		} catch (Exception e) {
			LOGGER.error("getLabel失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
     * 获取我的需求池中企业标签
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "getMyLabel.json", method = RequestMethod.GET)
	public AjaxResult getMyLabel() {
		try {
			return success(labelService.getMyLabel(getUserId()));
		} catch (Exception e) {
			LOGGER.error("getMyLabel失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 添加我的需求池中企业标签
	 * @Label label
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addMyLabel.json", method = RequestMethod.GET)
	public AjaxResult addMyLabel(String name) {
		if(StringUtil.isEmpty(name)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Label label = new Label();
			label.setLabel(name);
			label.setUserId(getUserId());
			label.setPark(getUserPark());
			return labelService.addLabel(label);
		} catch (Exception e) {
			LOGGER.error("getMyLabel失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 删除我的需求池中企业标签
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "dropMyLabel.json", method = RequestMethod.POST)
	public AjaxResult dropMyLabel(@RequestBody CompanySearchDTO dto) {
		String[] msg = dto.getMsg();
		if(msg==null||msg.length==0){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return labelService.dropLabel(msg);
		} catch (Exception e) {
			LOGGER.error("getMyLabel失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}

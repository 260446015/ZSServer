package com.huishu.ait.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.headlines.HeadlinesController;
import com.huishu.ait.entity.Specialist;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.Specialist.impl.SpecialistService;

/**
 * @author yxq
 *	专家信息的controller层
 */
@RestController
@RequestMapping(value = "/Specialist")
public class SpecialistController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(SpecialistController.class);
	@Autowired
	private SpecialistService specialistService;
	
	@RequestMapping(value = "/getSpecialist.json")
	public AjaxResult getSpecialist(){
		try {
			List<Specialist> findAll = specialistService.findAll();
			return success(findAll);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
}

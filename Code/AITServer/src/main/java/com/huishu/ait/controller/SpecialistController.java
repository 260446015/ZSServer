package com.huishu.ait.controller;

import java.util.List;

import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.entity.Specialist;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.Specialist.SpecialService;

/**
 * @author yxq
 *	专家信息的controller层
 */
@RestController
public class SpecialistController extends BaseController {
	
	@Autowired
	private SpecialService specialService;
	
	@RequestMapping(value = "getSpecialist.do")
	public AjaxResult getSpecialist(){
		Object data = null;
		List<Specialist> findAll = specialService.findAll();
		return success(data);
	}
	
}

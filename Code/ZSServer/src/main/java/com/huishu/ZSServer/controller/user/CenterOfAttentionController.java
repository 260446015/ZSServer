package com.huishu.ZSServer.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.service.user.CenterOfAttentionService;

/**
 * 关注中心的代码
 * 
 * @author yindq
 * @date 2017年12月19日
 */
@RestController
@RequestMapping("/apis/follow")
public class CenterOfAttentionController extends BaseController{

	private Logger LOGGER = LoggerFactory.getLogger(CenterOfAttentionController.class);

	@Autowired
	private CenterOfAttentionService centerOfAttentionService;
	
	/**
	 * 查看我所关注的机构
	 * @param industry
	 * @return
	 */
	@RequestMapping(value = "findOrganizationList.json", method = RequestMethod.GET)
	public AjaxResult findOrganizationList(String industry,Integer pageNum) {
		if(StringUtil.isEmpty(industry)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if(pageNum==null||pageNum<0){
			pageNum=0;
		}
		try {
			Page<Institutional> page = centerOfAttentionService.findOrganizationList(industry,getUserId(),pageNum);
			return successPage(page,pageNum+1);
		} catch (Exception e) {
			LOGGER.error("findOrganizationList失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
}

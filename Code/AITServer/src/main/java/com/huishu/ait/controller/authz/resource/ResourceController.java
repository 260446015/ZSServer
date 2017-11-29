package com.huishu.ait.controller.authz.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.skyeye.SkyeyeService;

/**
 * 
 * @author yindawei
 * @date 2017年9月13日上午9:17:56
 * @description 本地访问天眼查的Controller,先获取token,再生成签名
 * @version
 */
@RestController
@RequestMapping("/apis/oauth")
public class ResourceController extends BaseController {

	@Autowired
	private SkyeyeService skyeyeService;

	@RequestMapping(value = "/loginOpenEye.json", method = RequestMethod.GET)
	public void loginOpenEye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String redirectUri = skyeyeService.loginOpenEye(getCurrentShiroUser().getLoginName());
		response.sendRedirect(redirectUri);
	}

	@RequestMapping(value = "/getChangeInfo.json", method = RequestMethod.GET)
	public AjaxResult getChangeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		skyeyeService.getChangeInfo();
		return success("");
	}

	@RequestMapping(value = "/getCompanyDetail.json", method = RequestMethod.GET)
	public void getCompanyDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter(ConstantKey.DEFAULT_NAME_PARAM);
		if (StringUtil.isEmpty(name)) {
			throw new Exception("name can not be null");
		}
		response.sendRedirect(skyeyeService.getCompanyDetail(getCurrentShiroUser().getLoginName(), name));
	}

	/**
	 * 获取关注企业分组
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAttentionGroup.json", method = RequestMethod.GET)
	public AjaxResult getAttentionGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject obj = skyeyeService.getAttentionGroup(getCurrentShiroUser().getLoginName());
		return success(obj.get("data"));
	}

	/**
	 * 获取用户关注企业分组下的所有企业信息
	 */
	@RequestMapping(value = "/getCompanyByGroup.json", method = RequestMethod.GET)
	public AjaxResult getCompanyByGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tags = request.getParameter(ConstantKey.DEFAULT_TAGS_PARAM);
		String ps = request.getParameter(ConstantKey.DEFAULT_PS_PARAM);
		String pn = request.getParameter(ConstantKey.DEFAULT_PN_PARAM);
		if (StringUtil.isEmpty(tags) || StringUtil.isEmpty(ps) || StringUtil.isEmpty(pn)) {
			throw new Exception("tags,ps,pn can not be null");
		}
		JSONObject obj = skyeyeService.getCompanyByGroup(getCurrentShiroUser().getLoginName(), tags, ps, pn);
		return success(obj.get("data"));
	}

	/**
	 * 获取用户查询记录
	 */
	@RequestMapping(value = "/getSearchTrack.json", method = RequestMethod.GET)
	public AjaxResult getSearchTrack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject returnData = skyeyeService.getSearchTrack(getCurrentShiroUser().getLoginName());
		return success(returnData);
	}

	/**
	 * 获取列表信息
	 */
	@RequestMapping(value = "/getSearchList.json", method = RequestMethod.GET)
	public void getSearchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyword = request.getParameter(ConstantKey.DEFAULT_NAME_PARAM);
		if (StringUtil.isEmpty(keyword)) {
			throw new Exception("name can not be null");
		}
		String redirectUri = skyeyeService.getSearchList(getCurrentShiroUser().getLoginName(), keyword);
		response.sendRedirect(redirectUri);
	}

}
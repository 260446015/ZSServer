package com.huishu.ait.controller.authz.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.service.skyeye.SkyeyeService;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author yindawei
 * @date 2017年9月11日下午4:40:32
 * @description 客户端获取token
 * @version
 */
@Controller
@RequestMapping("/apis/oauth")
public class ClientController {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private SkyeyeService skyeyeService;

	/*
	 * grant_type：表示使用的授权模式，必选项，此处的值固定为"authorization_code"
	 * code：表示上一步获得的授权码，必选项。 redirect_uri：表示重定向URI，必选项，且必须与A步骤中的该参数值保持一致
	 * client_id：表示客户端ID，必选项
	 */
	/**
	 * 获得令牌
	 * 
	 * @return oauth_callback?code=?
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping(value = "/oauth_callback", method = RequestMethod.GET)
	public void getTokenLogin(HttpServletRequest request, Model model) {
		String code = request.getParameter(ConstantKey.DEFAULT_CODE_PARAM);
		try {
			skyeyeService.getToken(code);
		} catch (Exception ex) {
			logger.error("getToken OAuthSystemException : " + ex.getMessage());
			model.addAttribute("errorMsg", ex.getMessage());
		}
	}

}

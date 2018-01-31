package com.huishu.aitanalysis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.huishu.aitanalysis.controller.abs.AbstractController;
import com.huishu.aitanalysis.service.analysis.AnalysisService;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return
 * 
 */
@Controller
@RequestMapping(value = "/analysis")
public class AnalysisController extends AbstractController {
	@Autowired
	private AnalysisService analysisService;

	/*@RequestMapping(value = "/put")
	public void analysisConstant(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// return "HelloWorld!";
		log.info("------------------------------");

		String dataJson = request.getParameter("data");
		log.info("data---------->" + dataJson);

		analysisService.analysis(dataJson);
	}*/

	@RequestMapping(value = "/getCompanyName.json")
	@ResponseBody
	public String getCompanyName(HttpServletRequest request, HttpServletResponse response) {
		log.info("------------------------------");

		String dataJson = request.getParameter("data");
		log.info("data---------->" + dataJson);
		JSONObject json = null;
		try {
			json = Analysis.getInitCompanyAbbr(dataJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}
}

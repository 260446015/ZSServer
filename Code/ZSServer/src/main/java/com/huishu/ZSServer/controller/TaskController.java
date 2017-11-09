package com.huishu.ZSServer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAnnals;
import com.huishu.ZSServer.repository.company.FinancingRepository;
import com.huishu.ZSServer.repository.garden.CompanyAnnalsRepository;

/**
 * 定时任务
 * 
 * @author yindq
 * @date 2017年11月2日
 */
@Component
@EnableScheduling
@Controller
@RequestMapping(value = "/data")
public class TaskController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private FinancingRepository financingRepository;
	@Autowired
	private CompanyAnnalsRepository annalsRepository;
	
	/**
	 * 查询所有企业的融资情况
	 */
	@RequestMapping(value = "/getCompanyFinancing.json", method = RequestMethod.GET)
	public void getCompanyFinancing(){
		Iterable<Company> findAll = financingRepository.findAll();
		for (Company company : findAll) {
			if(StringUtil.isEmpty(company.getInvest())){
				LOGGER.info("正在查询"+company.getCompanyName()+"的融资情况");
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("name",company.getCompanyName()));
				JSONObject jsonObject = HttpUtils.sendGet(KeyConstan.URL.RONGZI, params);
				JSONObject result = jsonObject.getJSONObject("result");
				if(result==null) continue;
				JSONObject row = result.getJSONObject("page").getJSONArray("rows").getJSONObject(0);
				company.setInvest(row.getString("round"));
				company.setFinancingAmount(row.getString("money"));
				company.setInvestor(row.getString("organizationName"));
				Date date = new Date(row.getLong("date"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				company.setFinancingDate(sdf.format(date));
				financingRepository.save(company);
			}
		}
	}
	
	/**
	 * 查询所有企业的年报，主要为了年产值与年税收
	 */
	@RequestMapping(value = "/getCompanyAnnals.json", method = RequestMethod.GET)
	public void getCompanyAnnals(){
		Iterable<Company> findAll = financingRepository.findAll();
		for (Company company : findAll) {
			LOGGER.info("正在查询"+company.getCompanyName()+"的企业年报");
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name",company.getCompanyName()));
			JSONObject jsonObject = HttpUtils.sendGet(KeyConstan.URL.NIANBAO, params);
			JSONObject result = jsonObject.getJSONObject("result");
			if(result==null) continue;
			JSONArray items = result.getJSONArray("items");
			for (Object object : items) {
				JSONObject item=(JSONObject)object;
				JSONObject baseInfo = item.getJSONObject("baseInfo");
				if((baseInfo.getString("totalSales").equals("不公示")||StringUtil.isEmpty(baseInfo.getString("totalSales")))
						&&(baseInfo.getString("totalTax").equals("不公示"))||StringUtil.isEmpty(baseInfo.getString("totalTax"))) 
					continue;
				CompanyAnnals annals = new CompanyAnnals();
				annals.setCompanyName(company.getCompanyName());
				annals.setIndustry(company.getIndustry());
				annals.setIndustryLabel(company.getIndustryLabel());
				annals.setOutputValue(baseInfo.getString("totalSales"));
				annals.setPark(company.getPark());
				annals.setTaxRevenue(baseInfo.getString("totalTax"));
				annals.setYear(baseInfo.getString("reportYear"));
				annalsRepository.save(annals);
			}
		}
	}
}


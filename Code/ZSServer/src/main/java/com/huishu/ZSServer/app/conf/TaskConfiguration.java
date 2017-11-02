package com.huishu.ZSServer.app.conf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

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
@Configuration  
@Component
@EnableScheduling
public class TaskConfiguration {
	@Autowired
	private FinancingRepository financingRepository;
	@Autowired
	private CompanyAnnalsRepository annalsRepository;
	
	/**
	 * 查询所有企业的融资情况
	 */
	public void getCompanyFinancing(){
		Iterable<Company> findAll = financingRepository.findAll();
		for (Company company : findAll) {
			if(StringUtil.isEmpty(company.getInvest())){
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("name",company.getCompanyName()));
				JSONObject jsonObject = HttpUtils.sendGet(KeyConstan.URL.RONGZI, params);
				JSONObject result = (JSONObject)jsonObject.get("result");
				if(result==null) continue;
				JSONObject page = result.getJSONObject("page");
				JSONArray rows = page.getJSONArray("rows");
				JSONObject row = rows.getJSONObject(0);
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
	public void getCompanyAnnals(){
		Iterable<Company> findAll = financingRepository.findAll();
		for (Company company : findAll) {
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name",company.getCompanyName()));
			JSONObject jsonObject = HttpUtils.sendGet(KeyConstan.URL.NIANBAO, params);
			JSONObject result = jsonObject.getJSONObject("result");
			if(result==null) continue;
			JSONArray items = result.getJSONArray("items");
			for (Object object : items) {
				JSONObject item=(JSONObject)object;
				JSONObject baseInfo = item.getJSONObject("baseInfo");
				if(baseInfo.getString("totalSales").equals("企业选择不公示")&&baseInfo.getString("totalTax").equals("企业选择不公示")) 
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


package com.huishu.ZSServer.service.company.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.repository.company.IndusCompanyRepository;
import com.huishu.ZSServer.repository.openeyes.BaseInfoRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.company.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 智能招商
 */
@Service
public class IndusCompanyServiceImpl extends AbstractService implements IndusCompanyService {

	private static final Logger LOGGER = Logger.getLogger(IndusCompanyServiceImpl.class);

	@Autowired
	private IndusCompanyRepository repository;

	@Autowired
	private BaseInfoRepository rep;

	@Override
	public BaseInfo getCompanyInfo(String companyName) {
		Map<String, Object> map = new HashMap<String, Object>();
		IndusCompany com = repository.findByCompanyName(companyName);
		if (com == null) {
			LOGGER.debug(MsgConstant.SYSTEM_ERROR);
			return null;
		}
		List<BaseInfo> list = rep.findByName(com.getCompany());
		if (list.size() != 0) {
			BaseInfo baseInfo = list.get(0);
			com.setIndustry(baseInfo.getIndustry());

			repository.save(com);
			return baseInfo;
		} else {
			map.put("name", com.getCompany());
			JSONObject obj = getOpenEyesTarget(KeyConstan.URL.BASEINFO, map, KeyConstan.From.CUSTOM);
			JSONObject object = obj.getJSONObject("result");
			BaseInfo info = object.parseObject(object.toJSONString(), BaseInfo.class);
			BaseInfo save = rep.save(info);
			if (save == null) {
				LOGGER.debug("智能招商查看公司详情,调取天眼查接口返回数据保存数据库失败！");
				return null;
			}
			com.setIndustry(info.getIndustry());
			repository.save(com);
			return save;
		}
	}

	@Override
	public List<IndusCompany> listCompany() {
		// 获取总数
		int count = repository.getCount();
		
		int i = (int)(Math.random()*count);
		while(i>(count-10)){
			i = (int)(Math.random()*count+1);
		}
		List<IndusCompany> list =  repository.getCompanyInfo(i);
		return list;
	}

	@Override
	public String uploadImage(String imageBase64) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("src_img ", imageBase64));
		JSONObject object = HttpUtils.sendPost(KeyConstan.DISTINGUISH, params);
		String imgMsg = object.getString("data");
		String[] split = imgMsg.split("\r\n");
		String company = "";
		for (String string : split) {
			if (string.indexOf("公司") != -1 || string.indexOf("办司") != -1 || string.indexOf("有限") != -1 || string.indexOf("集团") != -1) {
				String replaceAll = string.replaceAll("[\\p{Punct}\\p{Space}A-Za-z0-9]+", "");
				company = replaceAll.substring(0, replaceAll.length()-2);
				break;
			}
		}
		return company;
	}
	/**根据别名查看具体的公司信息*/
	@Override
	public IndusCompany findCompanyInfoByCompanyName(String companyName) {
		
		return repository.findByCompanyName(companyName);
	}
}

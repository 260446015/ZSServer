package com.huishu.ZSServer.service.company.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.entity.Enterprise;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.repository.company.EnterPriseRepository;
import com.huishu.ZSServer.repository.openeyes.BaseInfoRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.company.EnterPriseService;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 关于公司的相关操作
 */
@Service
public class EnterPriseServiceImpl extends AbstractService implements EnterPriseService {
	
	@Autowired
	private EnterPriseRepository rep;
	@Autowired
	private BaseInfoRepository repository;
	@Override
	public List<String> findCompanyName(String area, String industry) {
		int count = rep.getCount(area, industry);
		int i = (int)(Math.random()*count);
		while(i>43){
			i = (int)(Math.random()*count+1);
		}
		List<String> list = rep.getCompanyNameByIndustryAndArea(area, industry,i);
		return list;
	}

	/**
	 * 查看公司全部信息
	 */
	@Override
	public JSONObject getCompanyInfoByCompany(String company) {
		Enterprise enter = null;
		JSONObject obj = new JSONObject();
		try {
			enter = rep.findByCompany(company);
			if(enter != null){
				obj.put("boss",enter.getBoss());
				obj.put("name", enter.getCompany());
				obj.put("address", enter.getAddress());
				obj.put("state", enter.getEngageState());
				obj.put("time", enter.getRegisterTime());
				obj.put("money", enter.getRegisterCapital());
				obj.put("industry", enter.getIndustry());
			}else{
				List<BaseInfo> list = repository.findByName(company);
				if (list.size() != 0) {
					BaseInfo baseInfo = list.get(0);
				}else{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", company);
					JSONObject obj1 = null;
					try {
						obj1 = getOpenEyesTarget(KeyConstan.URL.BASEINFO, map, KeyConstan.From.CUSTOM);
						
						JSONObject object = obj1.getJSONObject("result");
						if(object.isEmpty()){
							JSONObject object1 = obj1.getJSONObject("data");
							if(object1.isEmpty()){
								return null;
							}else{
								BaseInfo info = object.parseObject(object.toJSONString(), BaseInfo.class);
								BaseInfo save = repository.save(info);
								obj.put("boss",save.getLegalPersonName());
								obj.put("name", save.getName());
								obj.put("address", save.getRegLocation());
								obj.put("state", save.getRegStatus());
								obj.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date(save.getEstiblishTime())));
								obj.put("money",  save.getRegCapital());
								obj.put("industry", save.getIndustry());
							}
						}else{
							BaseInfo info = object.parseObject(object.toJSONString(), BaseInfo.class);
							BaseInfo save = repository.save(info);
							obj.put("boss",save.getLegalPersonName());
							obj.put("name", save.getName());
							obj.put("address", save.getRegLocation());
							obj.put("state", save.getRegStatus());
							obj.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date(save.getEstiblishTime())));
							obj.put("money",  save.getRegCapital());
							obj.put("industry", save.getIndustry());
						}
					
					} catch (Exception e) {
						return null;
					}
				}
				
			}
		} catch (Exception e) {
			return null;
		}
		
		return obj;
	}

}

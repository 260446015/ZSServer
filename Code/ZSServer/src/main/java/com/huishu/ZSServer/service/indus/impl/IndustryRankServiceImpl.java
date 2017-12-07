package com.huishu.ZSServer.service.indus.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.Data;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.IndustryRank;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.repository.company.IndusRankRepository;
import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
import com.huishu.ZSServer.service.indus.IndustryRankService;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 产业热度产业排行
 */
@Service
public class IndustryRankServiceImpl  implements IndustryRankService {

	private Logger LOGGER = LoggerFactory.getLogger(IndustryRankServiceImpl.class);
	@Autowired
	private IndusRankRepository rep;
	@Autowired
	private InstitutionalRepostitory isr;
	@Override
	public List<IndustryRank> findIndustryRank(String industry) {
		if(StringUtil.isEmpty(industry)){
			LOGGER.info("根据产业查询产业热度城市排行失败："+industry);
			return null;
		}
		return rep.getTop10ByIndustry(industry);
	}
	
	@Override
	public JSONObject findMapInfo(String industry) {
		List<Data> dlist= new ArrayList<Data>();
		
		if(StringUtil.isEmpty(industry)){
			LOGGER.info("根据产业查询城市地图数据失败："+industry);
			return null;
		}
		List<Object[]> list = isr.findByIndustry(industry);
		
		List<IndustryRank> li = rep.findByIndustry(industry);
		JSONObject obj = new JSONObject();
		if(list.size()==0){
			LOGGER.info("查询产业地图城市数据失败");	
			if(li.size()==0){
				return null;
			}else{
				JSONArray json = new JSONArray();
				li.forEach(action->{
					Data dd = new Data();
					dd.setName(action.getArea());
					dd.setValue(action.getCount());
					json.add(dd);
				});
				obj.put("hot", json);
				obj.put("map",new JSONArray());
				return null;
			}
		}
		list.forEach(action->{
			JSONArray json = new JSONArray();
			Data dd = new Data();
			dd.setName(action[0].toString());
			dd.setValue( Long.parseLong( action[1].toString()));
			dlist.add(dd);
			json.add(dd);
			obj.put("map",json);
		});
		if(li.size()==0){
			
		}else{
			JSONArray arr = new JSONArray();
			li.forEach(action->{
				Data dd = new Data();
				dd.setName(action.getArea());
				dd.setValue(action.getCount());
				arr.add(dd);
			});
			obj.put("hot", arr);
		}
		
		return obj;
	}

}

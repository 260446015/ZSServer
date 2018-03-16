package com.huishu.ManageServer.service.third.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.repository.third.KeyWordRelatedRepository;
import com.huishu.ManageServer.repository.third.ThesaurusRepository;
import com.huishu.ManageServer.service.third.ThesaurusService;
import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.KeyWordRelatedEntity;
/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 
 */
@Service
public class ThesaurusServiceImpl implements ThesaurusService {
	private static final Logger LOGGER = Logger.getLogger(ThesaurusServiceImpl.class);
	
	@Autowired
	private  ThesaurusRepository rep;
	
	@Autowired
	private KeyWordRelatedRepository krp;
	
	@TargetDataSource(name="third")
	@Override
	public JSONArray findAllKeyWord() {
		JSONArray obj = new JSONArray();
		Iterable<ThesaurusEntity> all = rep.findAll();
		all.forEach(action->{
			JSONObject o = new JSONObject();
			JSONArray arr = new JSONArray();
			Long id = action.getId();
			String keyword = action.getKeyword();//关键词
			String type = action.getType();//词类型
			List<KeyWordRelatedEntity> list = krp.findByWordId(id);
			list.forEach(act->{
				JSONObject oj = new JSONObject();
				String related = act.getRelated();//关联关系
				Long wordId =act.getRelateId();//关联词id
				if(wordId!=0){
					ThesaurusEntity one = rep.findOne(wordId);
					oj.put("keyName", one.getKeyword());
					oj.put("relatedName",related );
					oj.put("keyType", one.getType());
					arr.add(oj);
				}
			});
			o.put("keyword", keyword);			
			o.put("type", type);
			o.put("relate", arr);
			obj.add(o);
		});
		return obj;
	}
}

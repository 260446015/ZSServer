package com.huishu.ZSServer.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.merchantKey.itemModel.KeywordModel;
import com.huishu.ZSServer.common.util.DateUtils;
import com.huishu.ZSServer.entity.KeyWordEntity;
import com.huishu.ZSServer.service.indus.IndustryInfoService;
import com.huishu.ZSServer.service.keyword.KeyWordService;

/**
 * @author hhy
 * @date 2017年12月19日
 * @Parem
 * @return 
 * 获取关键词云定时任务
 * 6小时执行一次
 */
@Component
public class KeyWordTask {
	private static final Logger log = LoggerFactory.getLogger(KeyWordTask.class);
	private static final String time="近1周,近1个月,近6个月,近1年";
	private static final String industry = "人工智能,大数据,物联网,生物医药";
	@Autowired
	protected ElasticsearchTemplate template;
	@Autowired
	private IndustryInfoService service;
	@Autowired
	private KeyWordService kservice;
	/**
	 * 关键词云
	 */
	@Scheduled(fixedDelay = 1000 * 60 *60*2)
	public void getKeyWord(){
		log.info("==========定时任务开启===========");
		//第一步 获取所有的关于关键词的条件
		List<String> time1 = Arrays.asList(time.split(","));
		List<String> asList = Arrays.asList(industry.split(","));
		time1.forEach(str->{
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			DateUtils.initTime(obj, str);
			asList.forEach(action->{
			JSONObject obj1 = new JSONObject();
			obj1.put("value", action);
			array.add(obj1);
			});
			obj.put("industryLabel", array);
			obj.put("dimension","产业头条");
			//第二步，获取关键词
//			JSONArray arr = service.getKeyWordList(obj);
			List<KeywordModel> list1 = service.fiindKeyWordList(obj);
			List<KeyWordEntity> list = kservice.findKeyWordList(str);
			if(list1 != null){
			 if(list.size() == 0){
				List<KeyWordEntity> li = new ArrayList<KeyWordEntity>();
				list1.forEach(json->{
					try {
						KeyWordEntity key = new KeyWordEntity();
						key.setTime(str);
						key.setKey(json.getName());
						key.setValue(json.getValue());
						li.add(key);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				boolean info = kservice.saveKeyWord(li);
				log.info("保存的结果是：》》》》》》》》》"+info);
			}else{
				List<KeyWordEntity> li = new ArrayList<KeyWordEntity>();
				for(int i=0;i<list.size();i++){
					KeyWordEntity ent = list.get(i);
					KeywordModel object = list1.get(i);
					ent.setKey( object.getName());
					ent.setValue( object.getValue());
					li.add(ent);
				}
				boolean info = kservice.saveKeyWord(li);
				log.info("保存的结果是：》》》》》》》》》"+info);
				}
			}
		});
		log.info("词云更新/保存成功");
	}
}

package com.huishu.ManageServer.task;

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
import com.huishu.ManageServer.common.util.DateUtils;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.KeyWordEntity;
import com.huishu.ManageServer.entity.dbFirst.KeywordArticle;
import com.huishu.ManageServer.service.industry.info.IndustryInfoService;
import com.huishu.ManageServer.service.keyword.KeyArticleService;
import com.huishu.ManageServer.service.keyword.KeyWordService;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年12月19日
 * @Parem
 * @return 获取关键词云定时任务 6小时执行一次
 */
@Component
public class KeyWordTask {
	private static final Logger log = LoggerFactory.getLogger(KeyWordTask.class);
	private static final String time = "近1周,近1个月,近6个月,近1年";
	private static final String industry = "人工智能,大数据,物联网,生物医药";
	@Autowired
	protected ElasticsearchTemplate template;
	@Autowired
	private IndustryInfoService service;
	@Autowired
	private KeyWordService kservice;
	@Autowired
	private KeyArticleService keyservice;
	/**
	 * 关键词云
	 * 每天晚上十一点五十更新
	 */
	@Scheduled(cron="0 50 23  * * ?")
	public void getKeyWord() {
		log.info("==========定时任务开启===========");
		// 第一步 获取所有的关于关键词的条件
		List<String> time1 = Arrays.asList(time.split(","));
		List<String> asList = Arrays.asList(industry.split(","));
		time1.forEach(str -> {
			System.out.println(str);
		
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			DateUtils.initTime(obj, str);
			asList.forEach(action -> {
				JSONObject obj1 = new JSONObject();
				obj1.put("value", action);
				array.add(obj1);
			});
			obj.put("industryLabel", array);
			obj.put("dimension", "产业头条");
			// 第二步，获取关键词
			List<KeywordModel> list1 = service.fiindKeyWordList(obj);
			List<KeyWordEntity> list = kservice.findKeyWordList(str);
			if (list1 != null) {
				if (list.size() == 0) {
					List<KeyWordEntity> li = new ArrayList<KeyWordEntity>();
					list1.forEach(json -> {
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
					log.info("保存的结果是：》》》》》》》》》" + info);
				} else {
					List<KeyWordEntity> li = new ArrayList<KeyWordEntity>();
					for (int i = 0; i < list.size(); i++) {
						KeyWordEntity ent = list.get(i);
						KeywordModel object = list1.get(i);
						ent.setKey(object.getName());
						ent.setValue(object.getValue());
						li.add(ent);
					}
					boolean info = kservice.saveKeyWord(li);
					log.info("保存的结果是：》》》》》》》》》" + info);
				}
				
				
			}else{
				if(list.size() != 0){
					boolean info = kservice.deleteData(list);
					if(info){
						list.forEach( act ->{
							boolean flag = keyservice.deleteByKid(act.getId());
						});
					}
				}
			}
			
		});
		log.info("词云更新/保存成功");
		log.info("==============词云方法更新完毕=====================");		
		log.info("==============根据词云获取文章方法开启=====================");		
		//根据词云查询文章
		List<KeyWordEntity> list = kservice.findAllKeyWord();
		if(list.size() != 0){
			list.forEach(action->{
				List<KeywordArticle> info = new ArrayList<KeywordArticle>();
				Long id = action.getId();
				String key = action.getKey();
				String time2 = action.getTime();
				JSONObject obj1 = new JSONObject();
				DateUtils.initTime(obj1, time2);
				obj1.put("keyWord",key);
				String dimension = StringUtil.getIndustryInfo(key);
				if(StringUtil.isNotEmpty(dimension)){
					obj1.put("dimension","产业头条");
					JSONArray arr = new JSONArray();
					JSONObject obj = new JSONObject();
					obj.put("value", dimension);
					arr.add(obj);
					obj1.put("industryLabel", arr);
				}else{
					JSONArray arr = new JSONArray();
					JSONObject obj = new JSONObject();
					obj.put("value", "大数据");
					arr.add(obj);
					obj = new JSONObject();
					obj.put("value", "人工智能");
					arr.add(obj);
					obj = new JSONObject();
					obj.put("value", "物联网");
					arr.add(obj);
					obj = new JSONObject();
					obj.put("value", "生物医药");
					arr.add(obj);
					obj1.put("industryLabel", arr);
					obj1.put("dimension","产业头条");
				}
				JSONArray json = service.getArticleListByKeyWord(obj1);
				if(json.size()>0){
					try {
						boolean flag = keyservice.deleteByKid(id);
						log.info("删除的结果为:",flag);
					} catch (Exception e) {
						log.info("根据关键词id删除文章信息",e);
					}
					for(int i=0;i<json.size();i++){
						KeywordArticle entry = new KeywordArticle();
						JSONObject obj = json.getJSONObject(i);
						entry.setAid(obj.getString("id"));
						entry.setIndustryLabel(obj.getString("industryLabel"));
						entry.setKid(id);
						entry.setTitle(obj.getString("title"));
						entry.setArticleLink(obj.getString("articleLink"));
						info.add(entry);
					}
					boolean result = keyservice.saveInfo(info);
					if(result){
						log.info("当前保存成功");
					}else{
						log.info("保存当前文章列表失败");
					}
				}
			});
		}
		log.info("==============根据词云保存文章方法结束=====================");	
	}
}

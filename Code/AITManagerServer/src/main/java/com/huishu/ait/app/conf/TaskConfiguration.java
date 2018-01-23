package com.huishu.ait.app.conf;

/**
 * 定时任务
 *
 * @author yindq
 * @date 2018/1/19
 */

import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Configuration
@Component
@EnableScheduling
public class TaskConfiguration {

	private static Logger LOGGER = LoggerFactory.getLogger(TaskConfiguration.class);

	@Autowired
	private BaseElasticsearch baseElasticsearch;
	/**
	 * 检查高峰论坛发布时间
	 */
	public void checkReleaseTime(){
		LOGGER.info("定时任务触发");
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", "高峰论坛"));
		Sort sort = new Sort(Direction.DESC, "publishDate");
		PageRequest pageRequest = new PageRequest(0,500, sort);
		Page<AITInfo> pageList = baseElasticsearch.search(bq,pageRequest);
		for (AITInfo ait : pageList) {
			String title1 = ait.getTitle();
			String content = ait.getContent();
			int titleIndex = title1.indexOf("201");
			if(titleIndex>=0){
				String titleYear = title1.substring(titleIndex,titleIndex+4< title1.length() ? titleIndex + 4 : title1.length());
				int k = content.indexOf(titleYear);
				if(k<0) {
					LOGGER.info("对象{}时间{}替换成{}",ait.getId(),ait.getPublishYear(),titleYear);
					ait.setPublishTime(titleYear+"-01-01");
					ait.setPublishYear(titleYear);
					ait.setPublishDate(titleYear+"-01-01 00:00:00");
					baseElasticsearch.save(ait);
					continue;
				}
				getAndChange(content,titleYear,ait);
			}else {
				int i = content.indexOf("201");
				if(i>=0) {
					//获取所有年份，取年份出现次数最多的，时间最小的
					List<String[]> list = new ArrayList<String[]>();
					int n = 0;
					for(;n<content.length();){
						n = content.indexOf("201", n + 1);
						if (n >= 0) {
							String time = content.substring(n, n + 10 < content.length() ? n + 10 : content.length());
							String[] array = getTimeArray(time);
							list.add(array);
						}else {
							break;
						}
					}
					Map<String, Integer> map = new HashMap<>();
					for(String[] times : list){
						Integer integer = map.get(times[0]);
						if(integer==null){
							map.put(times[0],1);
						}else{
							map.put(times[0],map.get(times[0])+1);
						}
					}
					Iterator ite=map.entrySet().iterator();
					List listSort=new ArrayList();
					while(ite.hasNext()){
						Map.Entry entry =(Map.Entry)ite.next();
						listSort.add(entry.getValue());
					}
					Collections.sort(listSort);
					Iterator ite2=map.entrySet().iterator();
					while(ite2.hasNext()){
						Map.Entry entry =(Map.Entry)ite2.next();
						Integer value = Integer.parseInt(entry.getValue().toString());
						if(value == Integer.parseInt(listSort.get(listSort.size()-1).toString())){
							String maxYear = entry.getKey().toString();
							getAndChange(content,maxYear,ait);
							break;
						}
					}
				}else{
					LOGGER.info("对象{}截取碎片里面没有时间",ait.getId());
				}
			}
		}
		LOGGER.info("定时任务结束");
	}

	/**
	 * 根据时间获取年月日数组
	 * @param time
	 * @return
	 */
	private String[] getTimeArray(String time){
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(time);
		String string = m.replaceAll(" ").trim();
		String[] strArr = string.split(" ");
		String year =strArr[0];
		String mouth= "01";
		String day = "01";
		if(year.length()==4){
			if (strArr.length>1){
				if(strArr[1].length()>0){
					mouth=strArr[1];
					if (mouth.length()==1){
						mouth="0"+mouth;
					}if(mouth.compareTo("12")>0){
						mouth="12";
					}
				}
			}
			if (strArr.length>2){
				if(strArr[2].length()>0) {
					day = strArr[2];
					if (day.length() == 1) {
						day = "0" + day;
					}if(day.compareTo("29")>0){
						day="29";
					}
				}
			}
		}else {
			try{
				year =strArr[0].substring(0,4);
			}catch (Exception e){
				year = "2017";
			}
			try{
				mouth=strArr[0].substring(4,6);
			}catch (Exception e){
				mouth = "01";
			}
			try{
				day=strArr[0].substring(6,8);
			}catch (Exception e){
				day = "01";
			}
		}
		String[] result={year,mouth,day};
		return result;
	}

	/**
	 * 根据关键年份从关键语句中获取发布时间
	 * @param substring   截取的相关碎片
	 * @param maxYear    关键年份
	 * @param ait     操作对象
	 */
	private void getAndChange(String substring,String maxYear,AITInfo ait){
		int i = substring.indexOf(maxYear);
		if(i>=0) {
			String time = substring.substring(i, i + 10 < substring.length() ? i + 10 : substring.length());
			String[] array = getTimeArray(time);
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());
			if((ait.getPublishTime()).compareTo(array[0]+"-"+array[1]+"-"+array[2])<=0){
				LOGGER.info("对象{}不用改变",ait.getId());
				return;
			}
			date.get(Calendar.MONTH);
			LOGGER.info("对象{}时间{}替换成{}",ait.getId(),ait.getPublishTime(),array[0]+"-"+array[1]+"-"+array[2]);
			ait.setPublishTime(array[0]+"-"+array[1]+"-"+array[2]);
			ait.setPublishYear(array[0]);
			ait.setPublishDate(array[0]+"-"+array[1]+"-"+array[2]+" 00:00:00");
			baseElasticsearch.save(ait);
		}else {
			LOGGER.info("对象{}里面没有计算到的年份",ait.getId());
		}
	}
}

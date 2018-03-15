package com.huishu.aitanalysis.service.analysis;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.forget.articleCategory.POMPArticleCategory;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.es.entity.Index2;
import com.huishu.aitanalysis.es.entity.Index3;
import com.huishu.aitanalysis.service.index.IIndexService;
import com.huishu.aitanalysis.service.indus.IndustryInfoService;
import com.huishu.aitanalysis.service.park.ParkAnalysisService;
import com.huishu.aitanalysis.util.Util;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 */
@Service
public class AnalysisServiceImpl extends AbstractPostService  implements AnalysisService{
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AnalysisServiceImpl.class);
	private Logger log = Logger.getLogger("analysis");
	
	/**
	 * 分析入es库
	 */
	@SuppressWarnings("unused")
	@Override
	public void analysis(String dataJson,IIndexService indexService,IndustryInfoService indusService,ParkAnalysisService  service) {
		Index index = null;
		Index2 index2 = null;
		Index3 index3 = null;
		JSONObject jsonObject = JSONObject.parseObject(dataJson);
		String title = Util.getTitle(jsonObject);
		String articleLink = Util.getArticleLink(jsonObject);
		String dimension2 = Util.getDimension(jsonObject);
		//去重
		boolean info = indexService.filterByDimensionAndJSON(dimension2,jsonObject);
		log.info("去重的结果为:》》》》"+info);
		if(!info){
			log.info("数据存在重复的可能====="+info);
		}else{
			//关键词
			String keyWord = Util.getKeyWord(jsonObject);
			if(StringUtil.isEmpty(keyWord)){
				String dimension = Util.getDimension(jsonObject);
				if(dimension.equals("高峰论坛")){
						index = getIndex(jsonObject);
						index2 = getIndex3(jsonObject,indusService);
						log.info("1.0高峰论坛es库分析出的数据为：》》》》"+index);
						log.info("2.0高峰论坛es库分析出的数据为：》》》》"+index2);
				} else if(dimension.equals("融资快讯")){
					index3 = getIndex4(jsonObject,indusService);
					log.info("融资快讯分析出的数据为：》》》》"+index2);
				}else{	
					index = getIndex(jsonObject);			
				}
			}else{
				index = getIndex1(jsonObject,service);
			}
			if(index==null){
				log.info("index索引信息为空，不能入库");
			}else{
				String content = index.getContent();
				if(content.length()>4000){
					log.info("文章长度大于4000字[" + content.length() + "]");
					//截取前4000字，存到es中。
					content = content.substring(0, 4000);
					index.setContent(content);
				}
				//分析情感
				sentimentNew(index);
				if(index.getDimension().equals("高峰论坛")){
					checkReleaseTime(index);
				}
				//保存数据
				long s = System.currentTimeMillis();
				indexService.index(index, log);
				long e = System.currentTimeMillis();
				long ecs = e - s;
				log.info("中文索引到es用时[" + ecs + "ms]");
			}
			if(index2== null){
				log.info("index2索引信息为空，不能入库");
			}else{
				String content = index2.getContent();
				if(content.length()>6000){
					log.info("文章长度大于10000字[" + content.length() + "]");
					//截取前4000字，存到es中。
					content = content.substring(0, 6000);
					index.setContent(content);
				}
				//分析情感
				sentEmotion(index2);
				log.info("分析情感结束");
				if(index2.getDimension().equals("高峰论坛")){
					checkReleaseTime(index2);
				}
				//获取产业分类
				
				//保存数据
				long s = System.currentTimeMillis();
//				indexService.indexInfo(index2, log);
				long e = System.currentTimeMillis();
				long ecs = e - s;
				log.info("中文索引到es用时[" + ecs + "ms]");
				
			}
			if(index3 == null){
				log.info("index3的索引信息为空，不能入库");
			}else{
				//分析情感
				//保存数据
				long s = System.currentTimeMillis();
//				indexService.savaIndex3(index3,log);
				long e = System.currentTimeMillis();
				long ecs = e - s;
				log.info("中文索引到es用时[" + ecs + "ms]");
			}
			
		}
		
		}
	
	

	/**
	 * 情感分析新版本
	 */
	private void sentimentNew(Index index) {

		String content = index.getContent();
		String title = index.getTitle();
		String articleCategory = POMPArticleCategory.articleCategory(title, content);

		if ("negative".equals(articleCategory)) {
			// 负面
			index.setEmotion(articleCategory);
		} else if ("positive".equals(articleCategory)) {
			// 正面
			index.setEmotion(articleCategory);
		} else {
			// 中性
			index.setEmotion(articleCategory);
		}
	}
	/**
	 * 情感分析新版本
	 */
	private void sentEmotion(Index2 index) {

		String content = index.getContent();
		String title = index.getTitle();
		String articleCategory = POMPArticleCategory.articleCategory(title, content);

		if ("negative".equals(articleCategory)) {
			// 负面
			index.setEmotion(articleCategory);
		} else if ("positive".equals(articleCategory)) {
			// 正面
			index.setEmotion(articleCategory);
		} else {
			// 中性
			index.setEmotion(articleCategory);
		}
	}

	/**
	 *  检查高峰论坛发布时间
	 */
	public void checkReleaseTime(Index ait){
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

	/**
	 * 根据关键年份从关键语句中获取发布时间
	 * @param substring   截取的相关碎片
	 * @param maxYear    关键年份
	 * @param ait     操作对象
	 */
	private void getAndChange(String substring,String maxYear,Index ait){
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
		}else {
			LOGGER.info("对象{}里面没有计算到的年份",ait.getId());
		}
	}

	/**
	 *  检查高峰论坛发布时间
	 */
	public void checkReleaseTime(Index2 ait){
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

	/**
	 * 根据关键年份从关键语句中获取发布时间
	 * @param substring   截取的相关碎片
	 * @param maxYear    关键年份
	 * @param ait     操作对象
	 */
	private void getAndChange(String substring,String maxYear,Index2 ait){
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
		}else {
			LOGGER.info("对象{}里面没有计算到的年份",ait.getId());
		}
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
					}else if(mouth.length()>2){
						day="12";
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
					}else if(day.length()>2){
						day="29";
					}else if(day.compareTo("29")>0){
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

}

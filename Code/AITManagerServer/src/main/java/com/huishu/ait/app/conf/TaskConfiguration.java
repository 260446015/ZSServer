package com.huishu.ait.app.conf;

/**
 * 定时任务
 *
 * @author yindq
 * @date 2018/1/19
 */

import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import com.huishu.ait.service.ExpertOpinion.impl.ExpertOpinionServiceImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
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
import java.util.Date;
import java.util.Map;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bq.must(QueryBuilders.termQuery("publishTime", sdf.format(new Date())));
		Sort sort = new Sort(Direction.DESC, "publishDate");
		PageRequest pageRequest = new PageRequest(0,500, sort);
		Page<AITInfo> pageList = baseElasticsearch.search(bq,pageRequest);
		for (AITInfo ait : pageList) {
			String title1 = ait.getTitle();
			if(title1.indexOf("2018")==-1){
				String content = ait.getContent();
				String substring = "";
				int titleIndex = title1.indexOf("201");
				String titleYear = title1.substring(titleIndex,titleIndex+4< title1.length() ? titleIndex + 4 : title1.length());
				int k = content.indexOf(titleYear);
				if(k==-1) {
					LOGGER.info("对象{}时间{}替换成{}",ait.getId(),ait.getPublishYear(),titleYear);
					ait.setPublishTime(titleYear+"-01-01");
					ait.setPublishYear(titleYear);
					ait.setPublishDate(titleYear+"-01-01 00:00:00");
					baseElasticsearch.save(ait);
					continue;
				}
				if(content.indexOf("日期")!=-1){
					int indexOf = content.indexOf("日期");
					substring += content.substring(indexOf, indexOf + 1000 < content.length() ? indexOf + 1000 : content.length());
				}
				if(content.indexOf("日程")!=-1){
					int indexOf = content.indexOf("日程");
					substring += content.substring(indexOf, indexOf + 1000 < content.length() ? indexOf + 1000 : content.length());
				}
				if(content.indexOf("时间")!=-1){
					int indexOf = content.indexOf("时间");
					substring += content.substring(indexOf, indexOf + 1000 < content.length() ? indexOf + 1000 : content.length());
				}
				if(content.indexOf("展会已结束")!=-1){
					int indexOf = content.indexOf("展会已结束");
					String over= content.substring(indexOf, indexOf + 100 < content.length() ? indexOf + 100 : content.length());
					int indexOf2 = over.indexOf("至");
					substring+=over.substring(indexOf2,over.length());
				}
				if(substring.length()==0){
					baseElasticsearch.delete(ait.getId());
					continue;
				}
				if(titleIndex!=-1){
					int i = substring.indexOf(titleYear);
					if(i!=-1) {
						String time = substring.substring(i, i + 10 < substring.length() ? i + 10 : substring.length());
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
									}
								}
							}
							if (strArr.length>2){
								if(strArr[2].length()>0) {
									day = strArr[2];
									if (day.length() == 1) {
										day = "0" + day;
									}
								}
							}
						}else {
							year =strArr[0].substring(0,4);
							mouth=strArr[0].substring(4,6);
							day=strArr[0].substring(6,8);
						}
						LOGGER.info("对象{}时间{}替换成{}",ait.getId(),ait.getPublishTime(),year+"-"+mouth+"-"+day);
						ait.setPublishTime(year+"-"+mouth+"-"+day);
						ait.setPublishYear(year);
						ait.setPublishDate(year+"-"+mouth+"-"+day+" 00:00:00");
						baseElasticsearch.save(ait);
					}else {
						baseElasticsearch.delete(ait.getId());
					}
				}else {
					LOGGER.info("{}是标题没有年份的，稍后处理",ait.getId());
				}
			}
		}
		LOGGER.info("定时任务结束");
	}
}

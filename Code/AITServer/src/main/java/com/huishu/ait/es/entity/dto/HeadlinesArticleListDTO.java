package com.huishu.ait.es.entity.dto;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年7月31日
 * @Parem
 * @return 
 * 
 */
@Document(indexName = INDEX, type = TYPE)
public class HeadlinesArticleListDTO {
	
	
	private  String _id;
	private  String title;
	private  String content;
	private  String source;
	private  String publishDate;
	private  String articleLink;
	private  String sourceLink;
	/**
	 * 排名
	 */
	private int rank;
	/**声量*/
	private Long volume;
	
	/**总阅读量*/
	private Double totalHitCount;
	
	/**热度*/
	private Double hot;
	
	/**情感值*/
	private Double emotionVal;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public Double getTotalHitCount() {
		return totalHitCount;
	}
	public void setTotalHitCount(Double totalHitCount) {
		this.totalHitCount = totalHitCount;
	}
	public Double getHot() {
		return hot;
	}
	public void setHot(Double hot) {
		this.hot = hot;
	}
	public Double getEmotionVal() {
		return emotionVal;
	}
	public void setEmotionVal(Double emotionVal) {
		this.emotionVal = emotionVal;
	}
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		this._id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getArticleLink() {
		return articleLink;
	}
	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	
}

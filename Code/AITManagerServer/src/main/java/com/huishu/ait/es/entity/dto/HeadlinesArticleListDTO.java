package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

/**
 * @author hhy
 * @date 2017年7月31日
 * @Parem
 * @return 产业头条文章内容dto
 */
@Document(indexName = INDEX, type = TYPE)
public class HeadlinesArticleListDTO {

	private String _id;
	private String title;
	private String summary;
	private String content;
	private String source;
	private String publishTime;
	private String articleLink;
	private String sourceLink;
	/**
	 * 排名
	 */
	private int rank;

	/** 回复量 */
	private int replyCount;

	/** 支持量 */
	private int supportCount;

	/** 点击量 */
	private int hitCount;

	/** 热度 */
	private Double hot;

	/** 公司名录集合 */
	// private Set<String> bus;
	private List<String> bus;

	// public Set<String> getBus() {
	// return bus;
	// }
	//
	// public void setBus(Set<String> bus) {
	// this.bus = bus;
	// }

	public List<String> getBus() {
		return bus;
	}

	public void setBus(List<String> bus) {
		this.bus = bus;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(int supportCount) {
		this.supportCount = supportCount;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public Double getHot() {
		return hot;
	}

	public void setHot(Double hot) {
		this.hot = hot;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
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

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
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

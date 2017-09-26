package com.huishu.ait.common.export;

/**
 * 
 * @author yindawei
 * @date 2017年9月4日下午6:30:29
 * @description 下载用户收藏所对应的实体类
 * @version
 */
public class News {

	/**
	 * 标题
	 */
	private String Title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发布时间
	 */
	private String publishTime;
	/**
	 * 情报采集
	 */
	private String source;
	/**
	 * 情报原址
	 */
	private String sourceLink;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

}

package com.huishu.ManageServer.entity.vo;


import com.huishu.ManageServer.entity.dbFirst.FilePdfImg;

import java.io.Serializable;
import java.util.List;

/**
 * pdf展示类
 *
 * @author yindq
 * @date 2018/2/1
 */
public class PdfVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/** 主键id */
	private Long id;
	/** 文件标签 */
	private String label;
	/** 文件名 */
	private String name;
	/** 文件访问地址 */
	private String url;
	/** 图片类型文件地址 */
	private List<FilePdfImg> imageUrl;
	/** 文件对应时间 */
	private String data;
	/** 创建时间 */
	private String createTime;
	/** 下载量 */
	private Integer downloads;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<FilePdfImg> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<FilePdfImg> imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
}

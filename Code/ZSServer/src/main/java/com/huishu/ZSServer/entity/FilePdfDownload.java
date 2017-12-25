package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * PDF文件存储实体类
 * 
 * @author yindq
 * @date 2017年11月10日
 */
@Entity
@Table(name = "t_file_download")
public class FilePdfDownload implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 文件ID */
	private Long fileId;
	/** 最后下载时间 */
	private String downloadTime;
	/** 下载量 */
	private Integer downloads;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

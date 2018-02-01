package com.huishu.ZSServer.entity.report;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * PDF文件对应图片
 *
 * @author yindq
 * @date 2017年11月10日
 */
@Entity
@Table(name = "t_file_pdf_img")
public class FilePdfImg implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 图片路径 */
	private String imageUrl;
	/** 所属pdf */
	private Long pdfId;
	/** 排序 */
	private Integer sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getPdfId() {
		return pdfId;
	}

	public void setPdfId(Long pdfId) {
		this.pdfId = pdfId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

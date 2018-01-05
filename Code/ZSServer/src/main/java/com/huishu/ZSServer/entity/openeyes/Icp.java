package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日下午6:14:41
 * @description 网站备案实体
 * @version
 */
@Table(name = "t_icp")
@Entity
public class Icp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451880168593931968L;
	@Id
	private String id;
	/** 网站 */
	private String webSite;
	/** 检查时间 */
	private String examineDate;
	/** 公司类型 */
	private String companyType;
	/** 网站名称 */
	private String webName;
	/** 许可证 */
	private String liscense;
	/** 公司名称 */
	private String companyName;
	/** 域名 */
	private String ym;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getExamineDate() {
		return examineDate;
	}

	public void setExamineDate(String examineDate) {
		this.examineDate = examineDate;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getLiscense() {
		return liscense;
	}

	public void setLiscense(String liscense) {
		this.liscense = liscense;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

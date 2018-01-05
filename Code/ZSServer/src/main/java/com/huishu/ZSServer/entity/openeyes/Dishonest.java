package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 失信人
 * 
 * @author yindawei
 * @date 2017年11月8日下午6:05:08
 * @description
 * @version
 */
@Table(name = "t_dishonest")
@Entity
public class Dishonest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199813784814575168L;
	@Id
	private Long id;
	/** 失信人名 */
	private String iname;
	/** 案号 */
	private String casecode;
	/** 身份证号／组织机构代码 */
	private String cardnum;
	/** 法定代表人 */
	private String businessentity;
	/** 省份 */
	private String areaname;
	/** 执行法院 */
	private String courtname;
	/** 案号 */
	private String gistid;
	/** 立案时间 */
	private String regdate;
	/** 做出执行依据单位 */
	private String gistunit;
	/** 法律生效文书确定的义务 */
	private String duty;
	/** 被执行人的履行情况 */
	private String performance;
	/** 发布时间 */
	private String publishdate;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getCasecode() {
		return casecode;
	}

	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getBusinessentity() {
		return businessentity;
	}

	public void setBusinessentity(String businessentity) {
		this.businessentity = businessentity;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getCourtname() {
		return courtname;
	}

	public void setCourtname(String courtname) {
		this.courtname = courtname;
	}

	public String getGistid() {
		return gistid;
	}

	public void setGistid(String gistid) {
		this.gistid = gistid;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getGistunit() {
		return gistunit;
	}

	public void setGistunit(String gistunit) {
		this.gistunit = gistunit;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

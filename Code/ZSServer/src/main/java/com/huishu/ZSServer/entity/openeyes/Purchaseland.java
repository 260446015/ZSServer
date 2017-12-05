package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午5:30:09
 * @description 购地信息实体
 * @version
 */
@Table(name = "t_purchaseland")
@Entity
public class Purchaseland implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2084730207044372654L;

	@Id
	private Long id;
	/** ⾏政区 */
	private String adminRegion;
	/** 受让⼈ */
	private String assignee;
	/** 创建⽇期 */
	private Long createTime;
	/** 成交价款（万元） */
	private String dealPrice;
	/** 电⼦监管号 */
	private String elecSupervisorNo;
	/** 约定竣⼯时间 */
	private Long endTime;
	/** 链接 */
	private String linkUrl;
	/** 宗地位置 */
	private String location;
	/** 最⼤容积率 */
	private String maxVolume;
	/** 最⼩容积率 */
	private String minVolume;
	/** 上级公司 */
	private String parentCompany;
	/** ⼟地⽤途 */
	private String purpose;
	/** 签订⽇期 */
	private Long signedDate;
	/** 约定动⼯时间 */
	private Long startTime;
	/** 供应⽅式 */
	private String supplyWay;
	/** 供地总⾯积(公顷) */
	private String totalArea;
	/** 更新⽇期 */
	private Long updateTime;
	/** 所属公司 */
	private String cname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminRegion() {
		return adminRegion;
	}

	public void setAdminRegion(String adminRegion) {
		this.adminRegion = adminRegion;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}

	public String getElecSupervisorNo() {
		return elecSupervisorNo;
	}

	public void setElecSupervisorNo(String elecSupervisorNo) {
		this.elecSupervisorNo = elecSupervisorNo;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(String maxVolume) {
		this.maxVolume = maxVolume;
	}

	public String getMinVolume() {
		return minVolume;
	}

	public void setMinVolume(String minVolume) {
		this.minVolume = minVolume;
	}

	public String getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Long getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Long signedDate) {
		this.signedDate = signedDate;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public String getSupplyWay() {
		return supplyWay;
	}

	public void setSupplyWay(String supplyWay) {
		this.supplyWay = supplyWay;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

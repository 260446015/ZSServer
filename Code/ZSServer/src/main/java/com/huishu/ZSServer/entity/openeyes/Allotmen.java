package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 【web版】配股情况（股票）
 * 
 * @author yindawei
 * @date 2017年12月5日上午11:10:08
 * @description
 * @version
 */
@Table(name = "t_allotmen")
@Entity
public class Allotmen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3372993826177465258L;
	@Id
	private String id;
	/** ⽅案进度 */
	private String progress;
	/** 除权⽇ */
	private String exDate;
	/** 实际配股⽐例 */
	private String proportion;
	/** 实际募集资⾦净额 */
	private String actualRaise;
	/** 证监会核准公告⽇ */
	private String pubDate;
	/** 董事会公告⽇ */
	private String dDate;
	/** 股权登记⽇ */
	private String registerDate;
	/** 配股上市⽇ */
	private String issueDate;
	/** 发审委公告⽇ */
	private String announceDate;
	/** 每股配股价格 */
	private String price;
	/** 预案配股⽐例上限 */
	private String proportionalLimit;
	/** 配股号 */
	private String issueCode;
	/** 配股简称 */
	private String name;
	/** 配股年份 */
	private String year;
	/** 股东⼤会公告⽇ */
	private String saDate;
	/** 缴款起⽌⽇ */
	private String sDate;
	/** 预案募资⾦额上限 */
	private String raiseCeiling;
	/** 所属公司 */
	private String cname;

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getActualRaise() {
		return actualRaise;
	}

	public void setActualRaise(String actualRaise) {
		this.actualRaise = actualRaise;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(String announceDate) {
		this.announceDate = announceDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProportionalLimit() {
		return proportionalLimit;
	}

	public void setProportionalLimit(String proportionalLimit) {
		this.proportionalLimit = proportionalLimit;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSaDate() {
		return saDate;
	}

	public void setSaDate(String saDate) {
		this.saDate = saDate;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getRaiseCeiling() {
		return raiseCeiling;
	}

	public void setRaiseCeiling(String raiseCeiling) {
		this.raiseCeiling = raiseCeiling;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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

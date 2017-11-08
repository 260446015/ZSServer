package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日上午9:22:07
 * @description 融资历史的实体类
 * @version
 */
@Table(name = "t_historyrongzi")
@Entity
public class HistoryRongZi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449646276882424851L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/** 公司名 */
	private String companyName;
	/** 融资日期 */
	private Long date;
	/** 投资公司 */
	private String investorName;
	/** 是否删除 0 未删除 1 已删除 */
	private Integer isDeleted;
	/** 未用到 */
	private String money;
	/** 新闻标题 */
	private String newsTitle;
	/** 新闻url */
	private String newsUrl;
	/** 投资公司 */
	private String organizationName;
	/** 投资公司 */
	private String rongziMap;
	/** 轮次 */
	private String round;
	/** 投资比例 */
	private String share;
	/** 未用到 */
	private Integer sourceWeb;
	/** 投资公司 */
	private String tzrIds;
	/** 估值 */
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRongziMap() {
		return rongziMap;
	}

	public void setRongziMap(String rongziMap) {
		this.rongziMap = rongziMap;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public Integer getSourceWeb() {
		return sourceWeb;
	}

	public void setSourceWeb(Integer sourceWeb) {
		this.sourceWeb = sourceWeb;
	}

	public String getTzrIds() {
		return tzrIds;
	}

	public void setTzrIds(String tzrIds) {
		this.tzrIds = tzrIds;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

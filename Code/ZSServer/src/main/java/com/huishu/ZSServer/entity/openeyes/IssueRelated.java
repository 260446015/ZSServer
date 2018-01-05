package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月5日上午10:22:08
 * @description 发⾏相关（股票）
 * @version
 */
@Table(name = "t_issuerelated")
@Entity
public class IssueRelated implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6284189381394265540L;

	/***/
	@Id
	private String id;
	/** 预计募资 */
	private String expectedToRaise;
	/** 历史沿⾰ */
	private String history;
	/** 发⾏数量 */
	private String issueNumber;
	/** 发⾏价格 */
	private String issuePrice;
	/** 发⾏市盈率 */
	private String ipoRatio;
	/** 发⾏中签率 */
	private String rate;
	/** 上市⽇期 */
	private String listingDate;
	/** 实际募资 */
	private String actualRaised;
	/** 成⽴⽇期 */
	private String issueDate;
	/** ⾸⽇开盘价 */
	private String openingPrice;
	/** 所属公司 */
	private String cname;
	/** （主承销商） 字段说明 */
	private String mainUnderwriter;
	/** （上市保荐⼈） 字段说明 */
	private String listingSponsor;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getExpectedToRaise() {
		return expectedToRaise;
	}

	public void setExpectedToRaise(String expectedToRaise) {
		this.expectedToRaise = expectedToRaise;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getIssuePrice() {
		return issuePrice;
	}

	public void setIssuePrice(String issuePrice) {
		this.issuePrice = issuePrice;
	}

	public String getIpoRatio() {
		return ipoRatio;
	}

	public void setIpoRatio(String ipoRatio) {
		this.ipoRatio = ipoRatio;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getListingDate() {
		return listingDate;
	}

	public void setListingDate(String listingDate) {
		this.listingDate = listingDate;
	}

	public String getActualRaised() {
		return actualRaised;
	}

	public void setActualRaised(String actualRaised) {
		this.actualRaised = actualRaised;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(String openingPrice) {
		this.openingPrice = openingPrice;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public JSONObject getMainUnderwriter() {
		return JSONObject.parseObject(this.mainUnderwriter);
	}

	public void setMainUnderwriter(String mainUnderwriter) {
		this.mainUnderwriter = mainUnderwriter;
	}

	public JSONObject getListingSponsor() {
		return JSONObject.parseObject(this.listingSponsor);
	}

	public void setListingSponsor(String listingSponsor) {
		this.listingSponsor = listingSponsor;
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

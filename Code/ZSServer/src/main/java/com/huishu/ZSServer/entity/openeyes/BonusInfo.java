package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月5日上午11:01:30
 * @description 【web版】分红情况（股票）
 * @version
 */
@Table(name = "t_bonusinfo")
@Entity
public class BonusInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5370630455423861383L;
	@Id
	private String id;
	/** ⽅案进度 */
	private String progress;
	/** 股东⼤会⽇期 */
	private String shareholderDate;
	/** A股除权除息⽇ */
	private String acuxiDate;
	/** 股利⽀付率(%) */
	private String payment;
	/** A股派息⽇ */
	private String adividendDate;
	/** 实施⽇期 */
	private String implementationDate;
	/** A股股权登记⽇ */
	private String asharesDate;
	/** 分红率(%) */
	private String dividendRate;
	/** 董事会⽇期 */
	private String boardDate;
	/** 分红⽅案说明 */
	private String introduction;
	/** 所属公司 */
	private String cname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getShareholderDate() {
		return shareholderDate;
	}

	public void setShareholderDate(String shareholderDate) {
		this.shareholderDate = shareholderDate;
	}

	public String getAcuxiDate() {
		return acuxiDate;
	}

	public void setAcuxiDate(String acuxiDate) {
		this.acuxiDate = acuxiDate;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getAdividendDate() {
		return adividendDate;
	}

	public void setAdividendDate(String adividendDate) {
		this.adividendDate = adividendDate;
	}

	public String getImplementationDate() {
		return implementationDate;
	}

	public void setImplementationDate(String implementationDate) {
		this.implementationDate = implementationDate;
	}

	public String getAsharesDate() {
		return asharesDate;
	}

	public void setAsharesDate(String asharesDate) {
		this.asharesDate = asharesDate;
	}

	public String getDividendRate() {
		return dividendRate;
	}

	public void setDividendRate(String dividendRate) {
		this.dividendRate = dividendRate;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

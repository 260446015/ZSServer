package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午5:11:30
 * @description 债券信息实体
 * @version
 */
@Table(name = "t_bond")
@Entity
public class Bond implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7829496487443813860L;

	@Id
	private Long id;
	/** 债券名称 */
	private String bondName;
	/** 债券代码 */
	private String bondNum;
	/** 债劵期限 */
	private String bondTimeLimit;
	/** 债劵摘牌⽇ */
	private Long bondStopTime;
	/** 债券类型 */
	private String bondType;
	/** 计息⽅式 */
	private String calInterestType;
	/** 债项评级 */
	private String debtRating;
	/** 创建⽇期 */
	private Long createTime;
	/** 托管机构 */
	private String escrowAgent;
	/** ⾏权类型 */
	private String exeRightType;
	/** ⾯值 */
	private String faceValue;
	/** 流通范围 */
	private String flowRange;
	/** 发⾏价格(元) */
	private String issuedPrice;
	/** 付息频率 */
	private String payInterestHZ;
	/** 计划发⾏量(亿) */
	private String planIssuedQuantity;
	/** 债劵到期⽇ */
	private String publishExpireTime;
	/** 发⾏⼈ */
	private String publisherName;
	/** 债劵发⾏⽇ */
	private String publishTime;
	/** 实际发⾏量(亿) */
	private String realIssuedQuantity;
	/** 备注 */
	private String remark;
	/** 债券起息⽇ */
	private String startCalInterestTime;
	/** 上市交易⽇ */
	private String bondTradeTime;
	/** 信⽤评级机构 */
	private String creditRatingGov;
	/** 参考利率 */
	private String refInterestRate;
	/** 票⾯利率(%) */
	private String faceInterestRate;
	/** 利差(BP) */
	private String interestDiff;
	/** ⾏权⽇期 */
	private String exeRightTime;
	/** 所属公司 */
	private String cname;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBondName() {
		return bondName;
	}

	public void setBondName(String bondName) {
		this.bondName = bondName;
	}

	public String getBondNum() {
		return bondNum;
	}

	public void setBondNum(String bondNum) {
		this.bondNum = bondNum;
	}

	public String getBondTimeLimit() {
		return bondTimeLimit;
	}

	public void setBondTimeLimit(String bondTimeLimit) {
		this.bondTimeLimit = bondTimeLimit;
	}

	public Long getBondStopTime() {
		return bondStopTime;
	}

	public void setBondStopTime(Long bondStopTime) {
		this.bondStopTime = bondStopTime;
	}

	public String getBondType() {
		return bondType;
	}

	public void setBondType(String bondType) {
		this.bondType = bondType;
	}

	public String getCalInterestType() {
		return calInterestType;
	}

	public void setCalInterestType(String calInterestType) {
		this.calInterestType = calInterestType;
	}

	public String getDebtRating() {
		return debtRating;
	}

	public void setDebtRating(String debtRating) {
		this.debtRating = debtRating;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getEscrowAgent() {
		return escrowAgent;
	}

	public void setEscrowAgent(String escrowAgent) {
		this.escrowAgent = escrowAgent;
	}

	public String getExeRightType() {
		return exeRightType;
	}

	public void setExeRightType(String exeRightType) {
		this.exeRightType = exeRightType;
	}

	public String getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

	public String getFlowRange() {
		return flowRange;
	}

	public void setFlowRange(String flowRange) {
		this.flowRange = flowRange;
	}

	public String getIssuedPrice() {
		return issuedPrice;
	}

	public void setIssuedPrice(String issuedPrice) {
		this.issuedPrice = issuedPrice;
	}

	public String getPayInterestHZ() {
		return payInterestHZ;
	}

	public void setPayInterestHZ(String payInterestHZ) {
		this.payInterestHZ = payInterestHZ;
	}

	public String getPlanIssuedQuantity() {
		return planIssuedQuantity;
	}

	public void setPlanIssuedQuantity(String planIssuedQuantity) {
		this.planIssuedQuantity = planIssuedQuantity;
	}

	public String getPublishExpireTime() {
		return publishExpireTime;
	}

	public void setPublishExpireTime(String publishExpireTime) {
		this.publishExpireTime = publishExpireTime;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getRealIssuedQuantity() {
		return realIssuedQuantity;
	}

	public void setRealIssuedQuantity(String realIssuedQuantity) {
		this.realIssuedQuantity = realIssuedQuantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartCalInterestTime() {
		return startCalInterestTime;
	}

	public void setStartCalInterestTime(String startCalInterestTime) {
		this.startCalInterestTime = startCalInterestTime;
	}

	public String getBondTradeTime() {
		return bondTradeTime;
	}

	public void setBondTradeTime(String bondTradeTime) {
		this.bondTradeTime = bondTradeTime;
	}

	public String getCreditRatingGov() {
		return creditRatingGov;
	}

	public void setCreditRatingGov(String creditRatingGov) {
		this.creditRatingGov = creditRatingGov;
	}

	public String getRefInterestRate() {
		return refInterestRate;
	}

	public void setRefInterestRate(String refInterestRate) {
		this.refInterestRate = refInterestRate;
	}

	public String getFaceInterestRate() {
		return faceInterestRate;
	}

	public void setFaceInterestRate(String faceInterestRate) {
		this.faceInterestRate = faceInterestRate;
	}

	public String getInterestDiff() {
		return interestDiff;
	}

	public void setInterestDiff(String interestDiff) {
		this.interestDiff = interestDiff;
	}

	public String getExeRightTime() {
		return exeRightTime;
	}

	public void setExeRightTime(String exeRightTime) {
		this.exeRightTime = exeRightTime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

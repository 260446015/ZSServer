package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月5日上午9:40:34
 * @description 参股控股（股票）
 * @version
 */
@Table(name = "t_holdingcompany")
@Entity
public class HoldingCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5760304448270666045L;
	@Id
	private Long id;
	/** 参股关系 */
	private String relationship;
	/** 是否报表合并 */
	private String reportMerge;
	/** 被参股公司 净利润(元) */
	private String profit;
	/** 对应公司id */
	private String graphId;
	/** 公司名 */
	private String name;
	/** 参股⽐例（%） */
	private String participationRatio;
	/** 类型 1公司 2 ⼈ */
	private Integer cType;
	/** 投资⾦额（万元） */
	private String investmentAmount;
	/** 所属公司 */
	private String cname;
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

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getReportMerge() {
		return reportMerge;
	}

	public void setReportMerge(String reportMerge) {
		this.reportMerge = reportMerge;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getGraphId() {
		return graphId;
	}

	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParticipationRatio() {
		return participationRatio;
	}

	public void setParticipationRatio(String participationRatio) {
		this.participationRatio = participationRatio;
	}

	public Integer getcType() {
		return cType;
	}

	public void setcType(Integer cType) {
		this.cType = cType;
	}

	public String getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
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

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
 * @date 2017年11月8日下午6:15:53
 * @description
 * @version
 */
@Table(name = "t_riskinfo")
@Entity
public class RiskInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1790165272185521873L;
	@Id
	private Long id;
	/** 风险数据 internalList 自身 externalList 周边 */
	private String riskType;
	/** 标题 */
	private String title;
	/** 公司id */
	private Long companyId;
	/**
	 * 1-严重违法，2-失信人，3-失信公司，4-被执行人，5-被执行公司，6-行政处罚，7-经营异常，8-法律诉讼，9-股权出质，10-动产抵押
	 */
	private Integer type;
	/** 公司名称 */
	private String companyName;
	/** 风险数量 */
	private Integer riskCount;
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

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getRiskCount() {
		return riskCount;
	}

	public void setRiskCount(Integer riskCount) {
		this.riskCount = riskCount;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

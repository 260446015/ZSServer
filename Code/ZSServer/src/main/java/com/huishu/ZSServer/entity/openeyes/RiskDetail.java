package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/***
 * 风险信息
 * 
 * @author yindawei
 * @date 2017年11月8日下午6:44:12
 * @description
 * @version
 */
@Table(name = "t_riskdetail")
@Entity
public class RiskDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7100934975763185747L;
	@Id
	private String id;
	/***/
	private String iname;
	/***/
	private String gistid;
	/***/
	private String areaname;
	/***/
	private String cardnum;
	/***/
	private String courtname;
	/***/
	private Integer type;
	/***/
	private Long publishdate;
	/***/
	private String gistunit;
	/***/
	private String duty;
	/***/
	private String performance;
	/***/
	private Long regdate;
	/***/
	private String disrupttypename;
	/***/
	private String casecode;
	/***/
	private String companyName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getGistid() {
		return gistid;
	}

	public void setGistid(String gistid) {
		this.gistid = gistid;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getCourtname() {
		return courtname;
	}

	public void setCourtname(String courtname) {
		this.courtname = courtname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Long publishdate) {
		this.publishdate = publishdate;
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

	public Long getRegdate() {
		return regdate;
	}

	public void setRegdate(Long regdate) {
		this.regdate = regdate;
	}

	public String getDisrupttypename() {
		return disrupttypename;
	}

	public void setDisrupttypename(String disrupttypename) {
		this.disrupttypename = disrupttypename;
	}

	public String getCasecode() {
		return casecode;
	}

	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

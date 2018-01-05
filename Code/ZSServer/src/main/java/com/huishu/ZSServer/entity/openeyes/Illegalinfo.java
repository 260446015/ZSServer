package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 严重违法
 * 
 * @author yindawei
 * @date 2017年11月8日下午5:38:06
 * @description
 * @version
 */
@Table(name = "t_illegalinfo")
@Entity
public class Illegalinfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4193992973544875193L;
	@Id
	private String id;
	/** 列入原因 */
	private String putReason;
	/** 列入日期 */
	private Long putDate;
	/** 决定列入部门(作出决定机关) */
	private String putDepartment;
	/** 移除原因 */
	private String removeReason;
	/** 决定移除部门 */
	private String removeDepartment;
	/** 所属公司 */
	private String companyName;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPutReason() {
		return putReason;
	}

	public void setPutReason(String putReason) {
		this.putReason = putReason;
	}

	public Long getPutDate() {
		return putDate;
	}

	public void setPutDate(Long putDate) {
		this.putDate = putDate;
	}

	public String getPutDepartment() {
		return putDepartment;
	}

	public void setPutDepartment(String putDepartment) {
		this.putDepartment = putDepartment;
	}

	public String getRemoveReason() {
		return removeReason;
	}

	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}

	public String getRemoveDepartment() {
		return removeDepartment;
	}

	public void setRemoveDepartment(String removeDepartment) {
		this.removeDepartment = removeDepartment;
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

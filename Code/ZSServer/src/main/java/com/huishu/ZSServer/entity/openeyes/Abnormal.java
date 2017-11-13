package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 经营异常
 * 
 * @author yindawei
 * @date 2017年11月8日下午5:14:42
 * @description
 * @version
 */
@Table(name = "t_abnormal")
@Entity
public class Abnormal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5440685114239805113L;
	@Id
	private String id;
	/** 列入经营异常名录原因 */
	private String putReason;
	/** 列入日期 */
	private String putDate;
	/** 列入部门 */
	private String putDepartment;
	/** 移出原因 */
	private String removeReason;
	/** 移出日期 */
	private String removeDate;
	/** 移出部门 */
	private String removeDepartment;
	/** 创建时间 */
	private String createTime;
	/** 所属公司 */
	private String companyName;

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

	public String getPutDate() {
		return putDate;
	}

	public void setPutDate(String putDate) {
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

	public String getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(String removeDate) {
		this.removeDate = removeDate;
	}

	public String getRemoveDepartment() {
		return removeDepartment;
	}

	public void setRemoveDepartment(String removeDepartment) {
		this.removeDepartment = removeDepartment;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

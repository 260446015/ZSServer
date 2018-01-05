package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日下午4:39:27
 * @description 商标信息实体
 * @version
 */
@Table(name = "t_shangbiao")
@Entity
public class ShangBiao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5500955250560193679L;
	@Id
	private Long id;
	/** 类别 */
	private String intCls;
	/** 商标图片链接 */
	private String tmPic;
	/** 状态 */
	private String status;
	/** 注册号 */
	private String regNo;
	/** 申请人 */
	private String applicantCn;
	/** 商标名称 */
	private String tmName;
	/** 商标状态 */
	private String category;
	/** 申请日期 */
	private Long appDate;
	/** 公司id */
	private Long companyId;
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

	public String getIntCls() {
		return intCls;
	}

	public void setIntCls(String intCls) {
		this.intCls = intCls;
	}

	public String getTmPic() {
		return tmPic;
	}

	public void setTmPic(String tmPic) {
		this.tmPic = tmPic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getApplicantCn() {
		return applicantCn;
	}

	public void setApplicantCn(String applicantCn) {
		this.applicantCn = applicantCn;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getAppDate() {
		return appDate;
	}

	public void setAppDate(Long appDate) {
		this.appDate = appDate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日下午5:22:22
 * @description 专利实体类
 * @version
 */
@Table(name = "t_patents")
@Entity
public class Patents implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131460997688235555L;
	/**
	 * 唯一标识id
	 */
	@Id
	private String uuid;
	/** 主分类号 */
	private String mainCatNum;
	/** 申请公布号 */
	private String applicationPublishNum;
	/** 代理机构 */
	private String agency;
	/** 发明人 */
	private String inventor;
	/** 代理人 */
	private String agent;
	/** 专利信息id */
	private String pid;
	/** 申请公布日 */
	private String applicationPublishTime;
	/** 图片路径 */
	private String imgUrl;
	/** 申请号/专利号 */
	private String patentNum;
	/** 全部分类号 */
	private String allCatNum;
	/** 专利名称 */
	private String patentName;
	/** 摘要 */
	@Column(length=1000)
	private String abstracts;
	/** 地址 */
	private String address;
	/** 申请日 */
	private String applicationTime;
	/** 专利类型 */
	private String patentType;
	/** 申请人 */
	private String applicantName;
	/** 创建时间（毫秒） */
	private String createTime;
	/** 修改时间（毫秒） */
	private String updateTime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMainCatNum() {
		return mainCatNum;
	}

	public void setMainCatNum(String mainCatNum) {
		this.mainCatNum = mainCatNum;
	}

	public String getApplicationPublishNum() {
		return applicationPublishNum;
	}

	public void setApplicationPublishNum(String applicationPublishNum) {
		this.applicationPublishNum = applicationPublishNum;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getApplicationPublishTime() {
		return applicationPublishTime;
	}

	public void setApplicationPublishTime(String applicationPublishTime) {
		this.applicationPublishTime = applicationPublishTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPatentNum() {
		return patentNum;
	}

	public void setPatentNum(String patentNum) {
		this.patentNum = patentNum;
	}

	public String getAllCatNum() {
		return allCatNum;
	}

	public void setAllCatNum(String allCatNum) {
		this.allCatNum = allCatNum;
	}

	public String getPatentName() {
		return patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(String applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getPatentType() {
		return patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日下午6:03:26
 * @description 著作权实体类
 * @version
 */
@Table(name = "t_copyreg")
@Entity
public class CopyReg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6520358575512484732L;
	/** 登记号 */
	@Id
	private String regnum;
	/** 分类号 */
	private String catnum;
	/** 全称 */
	private String fullname;
	/** 简称 */
	private String simplename;
	/** 版本号 */
	private String version;
	/** 著作权人 */
	private String authorNationality;
	/** 首次发表日期 */
	private Long publishtime;
	/** 登记日期 */
	private Long regtime;
	/** 所属公司 */
	private String companyName;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegnum() {
		return regnum;
	}

	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}

	public String getCatnum() {
		return catnum;
	}

	public void setCatnum(String catnum) {
		this.catnum = catnum;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSimplename() {
		return simplename;
	}

	public void setSimplename(String simplename) {
		this.simplename = simplename;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthorNationality() {
		return authorNationality;
	}

	public void setAuthorNationality(String authorNationality) {
		this.authorNationality = authorNationality;
	}

	public Long getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Long publishtime) {
		this.publishtime = publishtime;
	}

	public Long getRegtime() {
		return regtime;
	}

	public void setRegtime(Long regtime) {
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

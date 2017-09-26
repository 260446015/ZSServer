package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author yindawei
 * @date 2017年9月13日下午5:38:20
 * @description 对应天眼查用户搜索记录的类
 * @version
 */
@Entity
@Table(name = "t_search_track")
public class SearchTrack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1998399707891732397L;
	@Id
	private Integer id;
	@Column(name = "company_graphid")
	private String companyGraphId;
	private Integer deleted;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "search_count")
	private Integer searchCount;
	@Column(name = "update_time")
	private String updateTime;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "org_id")
	private Integer orgId;
	@Column(name = "human_name")
	private String humanName;
	@Column(name = "human_graphid")
	private String humanGraphId;
	private String username;

	public String getCompanyGraphId() {
		return companyGraphId;
	}

	public void setCompanyGraphId(String companyGraphId) {
		this.companyGraphId = companyGraphId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public Integer getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public String getHumanGraphId() {
		return humanGraphId;
	}

	public void setHumanGraphId(String humanGraphId) {
		this.humanGraphId = humanGraphId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

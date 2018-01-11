package com.huishu.ManageServer.entity.dbFirst;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户操作日志
 * 
 * @author yindq
 * @date 2018/1/11
 */
@Entity
@Table(name = "t_user_logo")
public class UserLogo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2016035236962210036L;

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** 用户id */
	@Column(name = "user_id")
	private Long userId;

	/** 用户登录时间 */
	@Column(name = "login_time")
	private String loginTime;
	
	/** 用户操作时间 */
	@Column(name = "operation_time")
	private String operationTime;
	
	/** 查询公司 */
	@Column(name = "search_company")
	private String searchCompany;
	
	/** 查询公司次数 */
	@Column(name = "search_count")
	private Integer searchCount;
	
	/** 用户操作日志类型(0:登录,1:操作) */
	@Column(name = "logo_type")
	private Integer logoType;
	
	public Integer getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getSearchCompany() {
		return searchCompany;
	}

	public void setSearchCompany(String searchCompany) {
		this.searchCompany = searchCompany;
	}

	public Integer getLogoType() {
		return logoType;
	}

	public void setLogoType(Integer logoType) {
		this.logoType = logoType;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

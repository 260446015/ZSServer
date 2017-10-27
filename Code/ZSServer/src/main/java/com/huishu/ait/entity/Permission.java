package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 权限表
 * 
 * @author yindq
 * @date 2017年8月8日
 */
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

	private static final long serialVersionUID = 6131980643765892077L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	/**
	 * 权限名称
	 */
	@Column(name = "permission_name")
	private String permissionName;

	/**
	 * 权限中文描述
	 */
	@Column(name = "permission_describe")
	private String permissionDescribe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionDescribe() {
		return permissionDescribe;
	}

	public void setPermissionDescribe(String permissionDescribe) {
		this.permissionDescribe = permissionDescribe;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

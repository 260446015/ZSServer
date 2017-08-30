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
 * 用户权限
 * @author yindq
 * @date 2017年8月8日
 */
@Entity
@Table(name = "t_user_permission")
public class UserPermission implements Serializable {

	private static final long serialVersionUID = -4143346559497698848L;
	
	/**id*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	/**会员等级*/
	@Column(name = "user_level", nullable = false)
	private Integer userLevel;
	
	/**权限id*/
	@Column(name = "permission_id", nullable = false)
	private Long permissionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

package com.huishu.ZSServer.entity.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 会员等级所对应权限
 * 
 * @author yindq
 * @date 2017年12月12日
 */
@Entity
@Table(name = "t_user_permission")
public class UserPermission implements Serializable {

	private static final long serialVersionUID = 4645964956792326167L;

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	/** 会员等级 */
//	@Column(name = "user_level", nullable = false)
//	private Integer userLevel;

	private String permissionName;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@ManyToMany(mappedBy = "permissions",fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Integer getUserLevel() {
//		return userLevel;
//	}

//	public void setUserLevel(Integer userLevel) {
//		this.userLevel = userLevel;
//	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

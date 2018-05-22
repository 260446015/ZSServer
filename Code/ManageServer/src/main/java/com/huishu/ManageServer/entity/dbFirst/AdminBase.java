package com.huishu.ManageServer.entity.dbFirst;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 管理员实体类
 * 
 * @author yindq
 * @date 2018/2/2
 */
@Entity
@Table(name = "t_admin_base")
public class AdminBase implements Serializable {

	private static final long serialVersionUID = 3643928066531343869L;

	/** 用户id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	/** 账号 */
	@Column(name = "user_account", nullable = false, unique = true)
	private String userAccount;

	/** 密码 */
	@Column(nullable = false)
	private String password;

	/** 盐值 */
	@Column(nullable = false)
	private String salt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

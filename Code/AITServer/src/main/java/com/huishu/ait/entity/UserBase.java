package com.huishu.ait.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户实体类
 * 
 * @author yindq
 * @date 2017年8月8日
 */
@Entity
@Table(name = "t_user_base")
public class UserBase implements Serializable {

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

	/** 真实姓名 */
	@Column(name = "real_name")
	private String realName;

	/** 手机号 */
	@Column
	private String telphone;

	/** 邮箱号 */
	@Column(name = "user_email")
	private String userEmail;

	/** 所属职务 */
	@Column(name = "user_job")
	private String userJob;

	/** 部门 */
	@Column(name = "user_department")
	private String userDepartment;

	/** 所属单位 */
	@Column(name = "user_comp")
	private String userComp;

	/** 所属园区 */
	@Column(name = "user_park")
	private String userPark;

	/** 创建时间 */
	@Column(name = "create_time")
	private String createTime;

	/** 会员开始时间 */
	@Column(name = "start_time")
	private String startTime;

	/** 会员到期时间 */
	@Column(name = "expire_time")
	private String expireTime;

	/** 用户类型 */
	@Column(name = "user_type")
	private String userType;

	/** 名片路径 */
	@Column(name = "image_url")
	private String imageUrl;

	/** 会员等级(0:试用,1:正式，9:试用申请转正式) */
	@Column(name = "user_level")
	private Integer userLevel;

	/** 是否预警(0:正常,1:预警) */
	@Column(name = "is_warn")
	private Integer isWarn;

	/** 是否审核(0:待审核,1:已审核) */
	@Column(name = "is_check")
	private Integer isCheck;

	/** 用户权限，不存数据库 */
	@Transient
	private List<Long> permissions;

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public Integer getIsWarn() {
		return isWarn;
	}

	public void setIsWarn(Integer isWarn) {
		this.isWarn = isWarn;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserDepartment() {
		return userDepartment;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}

	public String getUserComp() {
		return userComp;
	}

	public void setUserComp(String userComp) {
		this.userComp = userComp;
	}

	public String getUserPark() {
		return userPark;
	}

	public void setUserPark(String userPark) {
		this.userPark = userPark;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Long> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Long> permissions) {
		this.permissions = permissions;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

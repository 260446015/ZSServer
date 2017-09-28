package com.huishu.ait.entity;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：企业分组的实体类
 */
@Entity
@Table(name = "t_company_group")
public class CompanyGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -888515836041527788L;
	@Id
	@GeneratedValue
	@Column(name = "group_id", nullable = false)
	private Long groupid;
	// 分组名称
	@Column(name = "group_name")
	private String groupName;

	/**
	 * 传递用户id
	 */
	@Column(name = "userid")
	private Long userId;

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

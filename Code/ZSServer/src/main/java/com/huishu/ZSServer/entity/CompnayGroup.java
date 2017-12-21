package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 企业分组
 * 
 * @author yindq
 * @date 2017年12月21日
 */
@Table(name="t_company_group")
@Entity
public class CompnayGroup implements Serializable{

	private static final long serialVersionUID = 5371635225748376066L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**用户id*/
	private Long userId;
	/**企业分组名*/
	private String groupName;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

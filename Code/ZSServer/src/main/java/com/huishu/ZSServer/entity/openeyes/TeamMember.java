package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日上午9:48:16
 * @description 核心团队实体
 * @version
 */
@Table(name = "t_teammember")
@Entity
public class TeamMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4649927437598467218L;
	@Id
	private Long id;
	/** 公司名 */
	private String companyName;
	/** 创建日期 */
	private Long createTime;
	/** 人物介绍 */
	@Column(name = "sdesc")
	private String desc;
	/** logo来源 */
	private String icon;
	/** 天眼查logo路径 */
	private String iconOssPath;
	/** 是否删除 0未删除 */
	private Integer isDeleted;
	/** 姓名 */
	private String name;
	/** 人物标签 */
	private String title;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOssPath() {
		return iconOssPath;
	}

	public void setIconOssPath(String iconOssPath) {
		this.iconOssPath = iconOssPath;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

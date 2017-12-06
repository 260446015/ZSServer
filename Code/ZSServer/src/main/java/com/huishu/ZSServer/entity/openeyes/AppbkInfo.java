package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午6:04:31
 * @description 产品信息
 * @version
 */
@Table(name = "t_appbkinfo")
@Entity
public class AppbkInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411343628618893439L;

	@Id
	private String id;
	/** 领域 */
	private String classes;
	/** 产品简称 */
	private String filterName;
	/** 图标 */
	private String icon;
	/** 产品分类 */
	private String type;
	/** 描述 */
	private String brief;
	/** 产品名 */
	private String name;
	/** 所属公司 */
	private String cname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

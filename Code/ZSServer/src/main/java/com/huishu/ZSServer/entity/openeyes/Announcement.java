package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月5日上午9:53:55
 * @description 上市公告（股票）
 * @version
 */
@Table(name = "t_announcement")
@Entity
public class Announcement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5370888684471465741L;
	@Id
	private String id;
	/** 发布⽇期 */
	private String time;
	/** 标题 */
	private String title;
	/** 股票号 */
	private String code;
	/** 公司 */
	private String companyName;
	/** 股票名 */
	private String name;
	/** 所属公司 */
	private String cname;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

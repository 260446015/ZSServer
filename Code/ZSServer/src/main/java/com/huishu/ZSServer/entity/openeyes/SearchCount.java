package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/***
 * 用来映射查询广西项目查询天眼查接口的实体类
 * 
 * @author yindawei
 * @date 2017年11月16日下午4:17:05
 * @description
 * @version
 */
@Table(name = "t_search_count")
@Entity
public class SearchCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3725298032617999071L;
	@Id
	private String id;
	/** 第一次查询时间 */
	private Long startTime;
	/** 最后一次查询时间 */
	private Long lastTime;
	/** 查询日期 */
	private String today;
	/** 当日查询总量 */
	private Integer total;
	/** 来源 */
	private String fromType;
	/** 访问接口 */
	private String spec;
	/** 所属用户 */
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getLastTime() {
		return lastTime;
	}

	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

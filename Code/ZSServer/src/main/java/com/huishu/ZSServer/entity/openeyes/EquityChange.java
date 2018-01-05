package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月5日上午10:53:27
 * @description 【web版】股本变动（股票）
 * @version
 */
@Table(name = "t_equitychange")
@Entity
public class EquityChange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908693421194853871L;
	@Id
	private String id;
	/** 变动原因 */
	private String changeReason;
	/** 变动后限售A股(万股) */
	private String afterLimit;
	/** 变动⽇期 */
	private String changeDate;
	/** 变动后A股总股本(万股) */
	private String afterAll;
	/** 变动后流通A股(万股) */
	private String afterNoLimit;
	/** 所属公司 */
	private String cname;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getAfterLimit() {
		return afterLimit;
	}

	public void setAfterLimit(String afterLimit) {
		this.afterLimit = afterLimit;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getAfterAll() {
		return afterAll;
	}

	public void setAfterAll(String afterAll) {
		this.afterAll = afterAll;
	}

	public String getAfterNoLimit() {
		return afterNoLimit;
	}

	public void setAfterNoLimit(String afterNoLimit) {
		this.afterNoLimit = afterNoLimit;
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

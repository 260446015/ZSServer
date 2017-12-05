package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日上午10:48:52
 * @description 认缴资金
 * @version
 */
@Table(name = "t_capital")
@Entity
public class Capital implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9157591968015818933L;
	@Id
	private Long id;

	/** 认缴资金 */
	private String amomon;
	/** 认缴时间 */
	private String time;
	/** 占股比例 */
	private String percent;
	/** 认缴形式 */
	private String paymet;
	@ManyToOne(targetEntity = Holder.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Holder holder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmomon() {
		return amomon;
	}

	public void setAmomon(String amomon) {
		this.amomon = amomon;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getPaymet() {
		return paymet;
	}

	public void setPaymet(String paymet) {
		this.paymet = paymet;
	}

	public Holder getHolder() {
		return holder;
	}

	public void setHolder(Holder holder) {
		this.holder = holder;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

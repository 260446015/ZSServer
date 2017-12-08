package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日上午10:25:44
 * @description 股东信息
 * @version
 */
@Table(name = "t_holder")
@Entity
public class Holder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3148518697204856426L;

	@Id
	private Long id;
	/** 人或公司id */
	private Integer toco;
	/** 金额 */
	private Double amount;
	/** 人或公司名 */
	private String name;
	/** logo */
	private String logo;
	/** 1公司2人 */
	private Integer type;
	/**
	 * 认缴 amomon String 认缴⾦额 time String 认缴时间 percent String 占股⽐例 paymet String
	 * 认缴形式
	 * 
	 */
	private String capital;
	
	private String cname;
	

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getToco() {
		return toco;
	}

	public void setToco(Integer toco) {
		this.toco = toco;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public JSONArray getCapital() throws JSONException {
		String str = this.capital;
		org.json.JSONArray arr = new org.json.JSONArray(str);
		JSONArray parseArray = JSONArray.parseArray(arr.toString());
		return parseArray;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

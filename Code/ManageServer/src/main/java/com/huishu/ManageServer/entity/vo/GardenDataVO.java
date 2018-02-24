package com.huishu.ManageServer.entity.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 园区数据展示DTO
 * 
 * @author yindq
 * @create 2017年9月7日
 */
public class GardenDataVO implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 园区名字 */
	private String parkName;
	/** 地域 */
	private String area;
	/** 会员数量 */
	private Integer accountCount;
	/** 未审核会员数量 */
	private Integer checkAccountCount;
	/** 过期会员数量 */
	private Integer expireAccountCount;
	/** 未过期会员数量 */
	private Integer normalAccountCount;
	/** 即将过期会员数量 */
	private Integer dueAccountCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getAccountCount() {
		return accountCount;
	}

	public void setAccountCount(Integer accountCount) {
		this.accountCount = accountCount;
	}

	public Integer getCheckAccountCount() {
		return checkAccountCount;
	}

	public void setCheckAccountCount(Integer checkAccountCount) {
		this.checkAccountCount = checkAccountCount;
	}

	public Integer getExpireAccountCount() {
		return expireAccountCount;
	}

	public void setExpireAccountCount(Integer expireAccountCount) {
		this.expireAccountCount = expireAccountCount;
	}

	public Integer getNormalAccountCount() {
		return normalAccountCount;
	}

	public void setNormalAccountCount(Integer normalAccountCount) {
		this.normalAccountCount = normalAccountCount;
	}

	public Integer getDueAccountCount() {
		return dueAccountCount;
	}

	public void setDueAccountCount(Integer dueAccountCount) {
		this.dueAccountCount = dueAccountCount;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

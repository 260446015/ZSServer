package com.huishu.ZSServer.entity.garden;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月2日下午2:54:01
 * @description 地图中点击省份传递的数据
 * @version
 */
@Table(name = "t_garden_map")
@Entity
public class GardenMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5492477343314332149L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 所属省份 */
	private String province;
	/** 一级产业 */
	private String industry;
	/** 年产gdp */
	private Double gdp;
	/** 哪一年 */
	private Integer year;
	/**返回echarts产业数量*/
	@Transient
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Double getGdp() {
		return gdp;
	}

	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

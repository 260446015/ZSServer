package com.huishu.ManageServer.entity.dbFirst;

import java.io.Serializable;
import java.util.List;

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
 * @cretionTime 2017-07-29
 * @description 用户关注园区映射表
 *
 */
@Entity
@Table(name = "t_user_garden_attention")
public class GardenUser implements Serializable {

	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 205427153812219075L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String gardenName;
	private Long userId;
	private String province;
	private String industryType;
	private String address;
	private String description;
	private String attentionDate;
	private Double gdp;
	private Double gardenSquare;
	/** 园区网址 */
	private String gardenWebsite;
	/** 园区等级 */
	private String gardenLevel;
	/** 园区数量 */
	@Transient
	private List<Company> cList;

	private int enterCount;

	public int getEnterCount() {
		return enterCount;
	}

	public void setEnterCount(int enterCount) {
		this.enterCount = enterCount;
	}

	public List<Company> getcList() {
		return cList;
	}

	public void setcList(List<Company> cList) {
		this.cList = cList;
	}

	public String getGardenLevel() {
		return gardenLevel;
	}

	public void setGardenLevel(String gardenLevel) {
		this.gardenLevel = gardenLevel;
	}

	public Double getGardenSquare() {
		return gardenSquare;
	}

	public void setGardenSquare(Double gardenSquare) {
		this.gardenSquare = gardenSquare;
	}

	public Double getGdp() {
		return gdp;
	}

	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}

	public String getEnterCompany() {
		return enterCompany;
	}

	public void setEnterCompany(String enterCompany) {
		this.enterCompany = enterCompany;
	}

	private String gardenPicture;
	private Long gardenId;
	private String enterCompany;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGardenName() {
		return gardenName;
	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttentionDate() {
		return attentionDate;
	}

	public void setAttentionDate(String attentionDate) {
		this.attentionDate = attentionDate;
	}

	public String getGardenPicture() {
		return gardenPicture;
	}

	public void setGardenPicture(String gardenPicture) {
		this.gardenPicture = gardenPicture;
	}

	public Long getGardenId() {
		return gardenId;
	}

	public void setGardenId(Long gardenId) {
		this.gardenId = gardenId;
	}

	public String getGardenWebsite() {
		return gardenWebsite;
	}

	public void setGardenWebsite(String gardenWebsite) {
		this.gardenWebsite = gardenWebsite;
	}

	/*
	 * @Transient private Integer enterCount;
	 * 
	 * public Integer getEnterCount() { String[] str =
	 * (StringUtil.isEmpty(this.getEnterCompany()) ? "" :
	 * this.getEnterCompany()).split("、"); if (str[0].equals("")) { return 0; }
	 * return str.length; }
	 */

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

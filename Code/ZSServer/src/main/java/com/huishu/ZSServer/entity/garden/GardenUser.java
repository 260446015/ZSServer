package com.huishu.ZSServer.entity.garden;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

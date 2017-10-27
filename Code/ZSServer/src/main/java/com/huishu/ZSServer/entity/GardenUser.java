package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int id;
	@Column(name = "gardenname")
	private String gardenName;
	@Column(name = "userid")
	private int userId;

	private String area;
	@Column(name = "industrytype")
	private String industryType;
	private String address;
	private String description;

	@Column(name = "attentiondate")
	private String attentionDate;

	@Column(name = "garden_picture")
	private String gardenPicture;
	@Column(name = "garden_id")
	private String gardenId;

	/**
	 * 
	 * @return 关联园区名称
	 */
	public String getGardenName() {
		return gardenName;
	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	/**
	 * 
	 * @return 关联用户id
	 */
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return 中间表主键
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getGardenId() {
		return gardenId;
	}

	public void setGardenId(String gardenId) {
		this.gardenId = gardenId;
	}

}

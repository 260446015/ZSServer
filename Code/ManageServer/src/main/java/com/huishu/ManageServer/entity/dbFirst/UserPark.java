package com.huishu.ManageServer.entity.dbFirst;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户园区
 *
 * @author yindq
 * @date 2018/1/15
 */
@Entity
@Table(name = "t_user_park")
public class UserPark implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6720985639295709537L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 园区名称 */
	@Column
	private String name;
	/** 所属地 */
	@Column
	private String area;
	/** 详细地址 */
	@Column
	private String address;
	/** 产业 */
	@Column
	private String industry;
	/** 负责人 */
	@Column
	private String boss;
	/** 电话 */
	@Column
	private String phone;
	/** 职位 */
	@Column
	private String position;
	/** 简介 */
	@Column
	private String introduction;
	@Column
	private String logo;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}

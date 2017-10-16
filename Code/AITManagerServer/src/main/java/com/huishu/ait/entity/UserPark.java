package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户园区
 * 
 * @author yindq
 * @create 2017年9月8日
 */
@Entity
@Table(name = "t_user_park")
public class UserPark implements Serializable {
	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 5360652382519203287L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 园区名称 */
	private String name;
	/** 所属地 */
	private String area;
	/** 详细地址 */
	private String address;
	/** 产业 */
	private String industry;
	/** 负责人 */
	private String boss;
	/** 电话 */
	private String phone;
	/** 职位 */
	private String position;
	/** 简介 */
	private String introduction;
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

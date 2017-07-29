package com.huishu.ait.entity;

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
@Table(name="t_user_garden_attention")
public class GardenUser implements Serializable{

	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="gardenname")
	private String gardenName;
	@Column(name="userid")
	private int userId;
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
	

}

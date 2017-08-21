package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yindawei
 * 园区实体类
 */
@Entity
@Table(name="t_garden")
public class Garden implements Serializable {

	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 5360652382519203287L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private String address;
	private String area;
	@Column(name="industrytype")
	private String industryType;
	@Column(name="garden_type")
	private String gardenType;
	@Column(name="garden_picture")
	private String gardenPicture;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getGardenType() {
		return gardenType;
	}
	public void setGardenType(String gardenType) {
		this.gardenType = gardenType;
	}
    public String getGardenPicture() {
        return gardenPicture;
    }
    public void setGardenPicture(String gardenPicture) {
        this.gardenPicture = gardenPicture;
    }
}

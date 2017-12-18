package com.huishu.ZSServer.entity.garden;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;

/**
 * 
 * @author yindawei
 * @date 2017年10月30日上午9:44:50
 * @description 园区的实体类
 * @version
 */
@Table(name = "t_garden_data")
@Entity
public class GardenData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 818596933683250090L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 园区名称 */
	private String gardenName;
	/** 园区省份 */
	private String province;
	/** 园区产值 */
	private Double gdp;
	/** 园区网址 */
	private String gardenWebsite;
	/** 园区地址 */
	private String address;
	/** 园区等级 */
	private String gardenLevel;
	/** 成立时间 */
	private String establishDate;
	/** 园区面积 */
	private Double gardenSquare;
	/** 园区介绍 */
	private String gardenIntroduce;
	/** 园区图片 */
	private String gardenPicture;
	/** 入驻企业 */
	private String enterCompany;
	/** 园区优势 */
	private String gardenSuperiority;
	/** 园区产业 */
	private String industryType;
	/** 是否被关注标识 */
	@Transient
	private Boolean flag;

	private int enterCount;

	public int getEnterCount() {
		return enterCount;
	}

	public void setEnterCount(int enterCount) {
		this.enterCount = enterCount;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Double getGdp() {
		return gdp;
	}

	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}

	public String getGardenWebsite() {
		return gardenWebsite;
	}

	public void setGardenWebsite(String gardenWebsite) {
		this.gardenWebsite = gardenWebsite;
	}

	public String getAddress() {
		if (address == null || StringUtil.isEmpty(address) || address.equals("NULL")) {
			return "暂无";
		} else
			return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGardenLevel() {
		return gardenLevel;
	}

	public void setGardenLevel(String gardenLevel) {
		this.gardenLevel = gardenLevel;
	}

	public String getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}

	public Double getGardenSquare() {
		return gardenSquare;
	}

	public void setGardenSquare(Double gardenSquare) {
		this.gardenSquare = gardenSquare;
	}

	public String getGardenIntroduce() {
		if (gardenSuperiority == null || StringUtil.isEmpty(gardenSuperiority) || gardenSuperiority.equals("NULL")) {
			return "暂无";
		} else {
			return gardenIntroduce;
		}

	}

	public void setGardenIntroduce(String gardenIntroduce) {
		this.gardenIntroduce = gardenIntroduce;
	}

	public String getGardenPicture() {
		if (gardenPicture == null || StringUtil.isEmpty(gardenPicture) || gardenPicture.equals("NULL")) {
			return KeyConstan.IP_PORT + "fileserver/img/list_img.jpg";
		} else
			return gardenPicture;
	}

	public void setGardenPicture(String gardenPicture) {
		this.gardenPicture = gardenPicture;
	}

	public String getGardenSuperiority() {
		if (gardenSuperiority == null || StringUtil.isEmpty(gardenSuperiority) || gardenSuperiority.equals("NULL")) {
			return "暂无";
		} else {
			return gardenSuperiority;
		}

	}

	public void setGardenSuperiority(String gardenSuperiority) {
		this.gardenSuperiority = gardenSuperiority;
	}

	public String getEnterCompany() {
		return enterCompany;
	}

	public void setEnterCompany(String enterCompany) {
		this.enterCompany = enterCompany;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
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

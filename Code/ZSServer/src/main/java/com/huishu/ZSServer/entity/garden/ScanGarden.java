package com.huishu.ZSServer.entity.garden;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月11日下午4:28:41
 * @description 用作维护园区是否扫描过企业的表
 * @version
 */
@Table(name = "t_scan_garden")
@Entity
public class ScanGarden implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414376728846713492L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long gardenId;
	private String gardenName;
	private int dr;
	private String scanDate;
	private int scanCount;

	public int getScanCount() {
		return scanCount;
	}

	public void setScanCount(int scanCount) {
		this.scanCount = scanCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGardenId() {
		return gardenId;
	}

	public void setGardenId(Long gardenId) {
		this.gardenId = gardenId;
	}

	public String getGardenName() {
		return gardenName;
	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate() {
		this.scanDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}

	public ScanGarden() {
		super();
		setScanDate();
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

package com.huishu.ait.entity.dto;

import java.io.Serializable;

/**
 * 查询园区的DTO
 * 
 * @author yindawei
 * @version 1.0
 * @createDate 2017-7-29
 */
public class GardenDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3484787975765165683L;
	/**
	 * 
	 */
	private Integer id;
	private String name;
	private String description;
	private String address;
	private String area;
	private String industryType;
	private Integer userId;
	private String serarchName;
	private String[] msg;

	/**
	 * 
	 * @return 园区id
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return 园区名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return 园区描述
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return 园区详细地址
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return 园区地理位置
	 */
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 
	 * @return 园区产业类型
	 */
	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	/**
	 * 
	 * @return 获取关注的用户id
	 */
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return 获取前台搜索框中的内容
	 */
	public String getSerarchName() {
		return serarchName;
	}

	public void setSerarchName(String serarchName) {
		this.serarchName = serarchName;
	}

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

}

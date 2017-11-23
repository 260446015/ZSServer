package com.huishu.ZSServer.entity.garden;

import com.huishu.ZSServer.entity.dto.AbstractDTO;

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
	private Long id;
	private String name;
	private String description;
	private String address;
	private String province;
	private String industryType;
	private String industry;
	private Long userId;
	private String serarchName;
	private String[] msg;
	private Integer[] year;

	public Integer[] getYear() {
		return year;
	}

	public void setYear(Integer[] year) {
		this.year = year;
	}

	/**
	 * 
	 * @return 园区id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	 * @return 园区省份
	 */
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	

}

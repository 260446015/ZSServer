package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日上午11:36:19
 * @description 投资案例对应的实体
 * @version
 */
@Table(name = "t_touzi")
@Entity
public class TouZi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5314851299760848589L;
	@Id
	private Long id;
	@Column(name = "company_id")
	private Long company_id;
	/** 公司名 */
	private String company;
	/** 公司id */
	@Column(name = "graph_id")
	private Long graph_id;
	/** 地区 */
	private String location;
	/** 业务范围 */
	private String yewu;
	/** 行业 */
	private String hangye1;
	/** 天眼查logo路径 */
	private String iconOssPath;
	/** 投资时间 */
	private Long tzdate;
	/** 是否删除 0未删除 */
	private Integer isDeleted;
	/** 产品名 */
	private String product;
	/** 投资金额 */
	private String money;
	/** 轮次 */
	private String lunci;
	/** 投资公司 */
	private String rongzi_map;
	/** 投资公司 */
	private String organization_name;
	/** logo来源 */
	private String icon;
	/** 所属公司名称 */
	private String companyName;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getGraph_id() {
		return graph_id;
	}

	public void setGraph_id(Long graph_id) {
		this.graph_id = graph_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getYewu() {
		return yewu;
	}

	public void setYewu(String yewu) {
		this.yewu = yewu;
	}

	public String getHangye1() {
		return hangye1;
	}

	public void setHangye1(String hangye1) {
		this.hangye1 = hangye1;
	}

	public String getIconOssPath() {
		return iconOssPath;
	}

	public void setIconOssPath(String iconOssPath) {
		this.iconOssPath = iconOssPath;
	}

	public Long getTzdate() {
		return tzdate;
	}

	public void setTzdate(Long tzdate) {
		this.tzdate = tzdate;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLunci() {
		return lunci;
	}

	public void setLunci(String lunci) {
		this.lunci = lunci;
	}

	public String getRongzi_map() {
		return rongzi_map;
	}

	public void setRongzi_map(String rongzi_map) {
		this.rongzi_map = rongzi_map;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

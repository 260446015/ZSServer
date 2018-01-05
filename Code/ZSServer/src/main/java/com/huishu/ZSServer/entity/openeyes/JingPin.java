package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日下午3:53:23
 * @description 竞品信息实体
 * @version
 */
@Table(name = "t_jingpin")
@Entity
public class JingPin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5456550794818867291L;
	@Id
	private String id;
	/** 公司名 */
	private String companyName;
	/** 时间 */
	private Long date;
	/** 行业 */
	private String hangye;
	/** logo来源 */
	private String icon;
	/** 天眼查logo路径 */
	private String iconOssPath;
	/** 是否删除 0未删除 */
	private Integer isDeleted;
	/** 竞品名 */
	private String jingpinProduct;
	/** 地区 */
	private String location;
	/** 产品 */
	private String product;
	/** 轮次 */
	private String round;
	/** 投资时间 */
	private Long setupDate;
	/** 估值 */
	private String value;
	/** 业务范围 */
	private String yewu;
	/** 所属公司 */
	private String cname;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getHangye() {
		return hangye;
	}

	public void setHangye(String hangye) {
		this.hangye = hangye;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOssPath() {
		return iconOssPath;
	}

	public void setIconOssPath(String iconOssPath) {
		this.iconOssPath = iconOssPath;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getJingpinProduct() {
		return jingpinProduct;
	}

	public void setJingpinProduct(String jingpinProduct) {
		this.jingpinProduct = jingpinProduct;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Long getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Long setupDate) {
		this.setupDate = setupDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getYewu() {
		return yewu;
	}

	public void setYewu(String yewu) {
		this.yewu = yewu;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

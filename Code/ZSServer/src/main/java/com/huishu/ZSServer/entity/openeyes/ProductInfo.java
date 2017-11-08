package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月6日上午10:31:00
 * @description 企业业务实体类
 * @version
 */
@Table(name = "t_productinfo")
@Entity
public class ProductInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5629608594374026048L;
	@Id
	private Long id;
	/** 公司名 */
	private String companyName;
	/** 创建日期 */
	private Long createTime;
	/** 数据来源 */
	private String detailUrl;
	/** 行业 */
	private String hangye;
	/** 天眼查logo路径 */
	private String logoOssPath;
	/** logo来源 */
	private String logo;
	/** 是否删除 0未删除 */
	private Integer isDeleted;
	/** 产品名 */
	private String product;
	/** 产品id */
	private Long productId;
	/** 业务范围 */
	private String yewu;

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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getHangye() {
		return hangye;
	}

	public void setHangye(String hangye) {
		this.hangye = hangye;
	}

	public String getLogoOssPath() {
		return logoOssPath;
	}

	public void setLogoOssPath(String logoOssPath) {
		this.logoOssPath = logoOssPath;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

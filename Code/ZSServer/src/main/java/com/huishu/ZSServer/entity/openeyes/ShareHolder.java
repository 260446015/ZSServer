package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * ⼗⼤股东（⼗⼤流通股东）（股票）
 * 
 * @author yindawei
 * @date 2017年12月5日上午10:03:38
 * @description
 * @version
 */
@Table(name="t_shareholder")
@Entity
public class ShareHolder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5973107062573628328L;
	@Id
	private String id;
	/** 对应公司id */
	private String graphId;
	/** 类型 1公司 2 ⼈ */
	private String cType;
	/** 占股本⽐例（%） */
	private String proportion;
	/** app专⽤ */
	private String mtenPercent;
	/** app专⽤ */
	private String mtenTotal;
	/** 累计占股本⽐例（%） */
	private String tenPercent;
	/** 总股数 */
	private String tenTotal;
	/** 实际增减持（%） */
	private String actual;
	/** 公司名 */
	private String name;
	/** 排序 */
	private String sorting;
	/** 持股变化 （万股） */
	private String holdingChange;
	/** 持股数量 */
	private String holdingNum;
	/** 股票类型 */
	private String shareType;
	/** 持股变化 */
	private String compareChange;
	/** 发布⽇期 */
	private Long publishDate;
	/** 类型 */
	private Integer type;
	/** 所属公司 */
	private String cname;

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

	public String getGraphId() {
		return graphId;
	}

	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}

	public String getcType() {
		return cType;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getMtenPercent() {
		return mtenPercent;
	}

	public void setMtenPercent(String mtenPercent) {
		this.mtenPercent = mtenPercent;
	}

	public String getMtenTotal() {
		return mtenTotal;
	}

	public void setMtenTotal(String mtenTotal) {
		this.mtenTotal = mtenTotal;
	}

	public String getTenPercent() {
		return tenPercent;
	}

	public void setTenPercent(String tenPercent) {
		this.tenPercent = tenPercent;
	}

	public String getTenTotal() {
		return tenTotal;
	}

	public void setTenTotal(String tenTotal) {
		this.tenTotal = tenTotal;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	public String getHoldingChange() {
		return holdingChange;
	}

	public void setHoldingChange(String holdingChange) {
		this.holdingChange = holdingChange;
	}

	public String getHoldingNum() {
		return holdingNum;
	}

	public void setHoldingNum(String holdingNum) {
		this.holdingNum = holdingNum;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public String getCompareChange() {
		return compareChange;
	}

	public void setCompareChange(String compareChange) {
		this.compareChange = compareChange;
	}

	public Long getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

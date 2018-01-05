package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月5日上午10:43:29
 * @description 【web版】股本结构（股票）
 * @version
 */
@Table(name = "t_sharestructure")
@Entity
public class ShareStructure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5055358549776942063L;
	@Id
	private String id;
	/** 时间 */
	private Long pubDate;
	/** 变动原因 */
	private String changeReason;
	/** 总股本(万股) */
	private String shareAll;
	/** 流通A股 */
	private String noLimitShare;
	/** 限售A股 */
	private String limitShare;
	/** A股总股本 */
	private String ashareAll;
	/** 流通H股 */
	private String hnoLimitShare;
	/** H股总股本 */
	private String hshareAll;
	/** 限售H股 */
	private String hlimitShare;
	/** 所属公司 */
	private String cname;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getPubDate() {
		return pubDate;
	}

	public void setPubDate(Long pubDate) {
		this.pubDate = pubDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getShareAll() {
		return shareAll;
	}

	public void setShareAll(String shareAll) {
		this.shareAll = shareAll;
	}

	public String getNoLimitShare() {
		return noLimitShare;
	}

	public void setNoLimitShare(String noLimitShare) {
		this.noLimitShare = noLimitShare;
	}

	public String getLimitShare() {
		return limitShare;
	}

	public void setLimitShare(String limitShare) {
		this.limitShare = limitShare;
	}

	public String getAshareAll() {
		return ashareAll;
	}

	public void setAshareAll(String ashareAll) {
		this.ashareAll = ashareAll;
	}

	public String getHnoLimitShare() {
		return hnoLimitShare;
	}

	public void setHnoLimitShare(String hnoLimitShare) {
		this.hnoLimitShare = hnoLimitShare;
	}

	public String getHshareAll() {
		return hshareAll;
	}

	public void setHshareAll(String hshareAll) {
		this.hshareAll = hshareAll;
	}

	public String getHlimitShare() {
		return hlimitShare;
	}

	public void setHlimitShare(String hlimitShare) {
		this.hlimitShare = hlimitShare;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}

package com.huishu.ManageServer.entity.dbFirst.h5;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 会议日程
 *
 * @author yindq
 * @date 2018/2/26
 */
@Entity
@Table(name = "t_html_schedule")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 日期 */
	private Integer date;
	/** 地点 */
	private String place;
	/** 主办方 */
	private String sponsor;
	/** 所属段落ID */
	@Column(name="paragraph_id")
	private Long paragraphId;

	public Long getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(Long paragraphId) {
		this.paragraphId = paragraphId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
}

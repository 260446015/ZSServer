package com.huishu.ManageServer.entity.dbFirst.h5;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 月度报告
 *
 * @author yindq
 * @date 2018/2/7
 */
@Entity
@Table(name = "t_html_monthly_report")
public class MonthlyReport implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 报告名字 */
	private String name;
	/** 报告时间段 */
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}

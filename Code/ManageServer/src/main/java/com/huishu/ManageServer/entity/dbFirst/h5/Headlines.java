package com.huishu.ManageServer.entity.dbFirst.h5;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 大标题
 *
 * @author yindq
 * @date 2018/2/7
 */
@Entity
@Table(name = "t_html_headlines")
public class Headlines implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 大标题名字 */
	private String name;
	/** 大标题排序 */
	private Integer sort;
	/** 大标题logo样式 */
	private String logoClass;
	/** 所属h5 */
	private Long reportId;
	/** 标题父类ID */
	private Long parentId;

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

	public String getLogoClass() {
		return logoClass;
	}

	public void setLogoClass(String logoClass) {
		this.logoClass = logoClass;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}

package com.huishu.ManageServer.entity.dbFirst.h5;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 段落
 *
 * @author yindq
 * @date 2018/2/7
 */
@Entity
@Table(name = "t_html_paragraph")
public class Paragraph implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 人物 */
	private String people;
	/** 公司 */
	private String company;
	/** 段落内文本数据 */
	private String text;
	/** 段落内图片数据 */
	@Column(name="key_word")
	private String keyWord;
	/** 段落排序 */
	private Integer sort;
	/** 所属标题ID */
	@Column(name="headlines_id")
	private Long headlinesId;
	/** 所属h5 */
	@Column(name="report_id")
	private Long reportId;

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getHeadlinesId() {
		return headlinesId;
	}

	public void setHeadlinesId(Long headlinesId) {
		this.headlinesId = headlinesId;
	}
}

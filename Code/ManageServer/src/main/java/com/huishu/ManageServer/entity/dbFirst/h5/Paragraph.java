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
	/** 段落内文本数据 */
	private String text;
	/** 段落内图片数据 */
	private String img;
	/** 段落排序 */
	private Integer sort;
	/** 所属标题ID */
	@Column(name="headlines_id")
	private Long headlinesId;

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getHeadlinesId() {
		return headlinesId;
	}

	public void setHeadlinesId(Long headlinesId) {
		this.headlinesId = headlinesId;
	}
}

package com.huishu.ManageServer.entity.vo;

import com.huishu.ManageServer.entity.dbFirst.h5.Headlines;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 大标题
 *
 * @author yindq
 * @date 2018/2/7
 */
public class HeadlinesVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键id */
	private Long id;
	/** 大标题名字 */
	private String name;
	/** 二级标题集合 */
	private List<Headlines> children;

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

	public List<Headlines> getChildren() {
		return children;
	}

	public void setChildren(List<Headlines> children) {
		this.children = children;
	}
}

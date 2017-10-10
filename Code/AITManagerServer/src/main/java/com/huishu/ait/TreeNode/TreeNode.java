package com.huishu.ait.TreeNode;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年9月13日
 * @Parem
 * @return
 * 
 */
public class TreeNode {
	private Integer id;
	private String name;
	private Integer parentid;
	private List<TreeNode> children = new ArrayList<TreeNode>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
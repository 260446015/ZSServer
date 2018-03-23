package com.huishu.ManageServer.entity.dto.dbThird;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年3月21日
 * @Parem
 * @return 
 * 插入或更新词
 */
public class addKeyWordDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private String name;
	private String type;
	private String descrip;
	private List<Serizal> msg;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Serizal> getMsg() {
		return msg;
	}
	public void setMsg(List<Serizal> msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}

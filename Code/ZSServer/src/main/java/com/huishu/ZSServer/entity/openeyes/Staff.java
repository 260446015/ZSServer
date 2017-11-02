package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月1日下午5:14:09
 * @description 查询主要人员的pojo
 * @version
 */
@Entity
@Table(name = "t_staff")
public class Staff implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3183958805814208557L;

	/** 对应表id */
	@Id
	private Long id;
	/** 人或公司id */
	private Integer toco;
	/** 人物logo */
	private String logo;
	/** 人或公司名 */
	private String name;
	/** 职位 */
	private String typeJoin;
	/** 1 公司 2 人 */
	private Integer type;
	/** 所属公司 */
	private String cname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getToco() {
		return toco;
	}

	public void setToco(Integer toco) {
		this.toco = toco;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeJoin() {
		return typeJoin;
	}

	public void setTypeJoin(String typeJoin) {
		this.typeJoin = typeJoin;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

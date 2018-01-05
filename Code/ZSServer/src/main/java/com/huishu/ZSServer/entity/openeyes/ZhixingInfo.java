package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午6:38:49
 * @description 被执行人
 * @version
 */
@Table(name = "t_zhixinginfo")
@Entity
public class ZhixingInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2955017121468606961L;
	@Id
	private String id;
	/** 被执⾏⼈名称 */
	private String pname;
	/** 执⾏法院 */
	private String execCourtName;
	/** 身份证号／组织机构代码 */
	private String partyCardNum;
	/** 案号 */
	private String caseCode;
	/** ⽴案时间 */
	private Long caseCreateTime;
	/** 执⾏标的 */
	private String execMoney;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getExecCourtName() {
		return execCourtName;
	}

	public void setExecCourtName(String execCourtName) {
		this.execCourtName = execCourtName;
	}

	public String getPartyCardNum() {
		return partyCardNum;
	}

	public void setPartyCardNum(String partyCardNum) {
		this.partyCardNum = partyCardNum;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Long getCaseCreateTime() {
		return caseCreateTime;
	}

	public void setCaseCreateTime(Long caseCreateTime) {
		this.caseCreateTime = caseCreateTime;
	}

	public String getExecMoney() {
		return execMoney;
	}

	public void setExecMoney(String execMoney) {
		this.execMoney = execMoney;
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

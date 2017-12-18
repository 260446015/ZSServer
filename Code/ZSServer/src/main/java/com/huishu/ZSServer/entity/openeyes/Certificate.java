package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 资质证书
 * @author yindawei 
 * @date 2017年12月18日上午11:15:28
 * @description 
 * @version
 */
@Table(name="t_certificate")
@Entity
public class Certificate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2380868144267921574L;

	@Id
	private String id;
	/** 证书编号 */
	private String certNo;
	/** 证书类型 */
	private String certificateName;
	/** 发证⽇期 */
	private String startDate;
	/** 截⽌⽇期 */
	private String endDate;
	/** 详情 */
	private String detail;
	/** 所属公司 */
	private String cname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public JSONArray getDetail() throws JSONException {
		String str = this.detail;
		org.json.JSONArray arr = new org.json.JSONArray(str);
		JSONArray parseArray = JSONArray.parseArray(arr.toString());
		return parseArray;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

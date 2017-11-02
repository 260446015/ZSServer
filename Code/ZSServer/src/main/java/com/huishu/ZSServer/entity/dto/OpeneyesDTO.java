package com.huishu.ZSServer.entity.dto;

import java.util.Map;

public class OpeneyesDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7595780555781721642L;

	/** 访问的网址 */
	private String spec;
	/** 访问请求的参数 */
	private Map<String, String> params;
	/** 访问的公司名称 */
	private String cname;

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}

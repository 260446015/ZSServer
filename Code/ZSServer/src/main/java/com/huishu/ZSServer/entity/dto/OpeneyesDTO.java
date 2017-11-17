package com.huishu.ZSServer.entity.dto;

import java.util.Map;

import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;

public class OpeneyesDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7595780555781721642L;

	/** 访问的网址 */
	private String spec;
	/** 访问请求的参数 */
	private Map<String, Object> params;
	/** 访问的公司名称 */
	private String cname;
	/** 查询人名 */
	private String humanName;
	/** 查询关键字 */
	private String word;
	/** 来源 */
	private String from;
	/** 公司id */
	private Long id;

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getFrom() {
		if (StringUtil.isEmpty(from))
			from = KeyConstan.From.CUSTOM;
		return from;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFrom(String from) {
		if (StringUtil.isEmpty(from))
			from = KeyConstan.From.CUSTOM;
		this.from = from;
	}

}

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
	/** 区别是否要调用查询竞品信息 */
	private Boolean flag;
	/***/
	private String type;
	/** 用户id */
	private Long userId;
	/**接口方法*/
	private String[] methods;
	/**导出方式*/
	private String exportType;

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public String[] getMethods() {
		return methods;
	}

	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setFrom(String from) {
		if (StringUtil.isEmpty(from))
			from = KeyConstan.From.CUSTOM;
		this.from = from;
	}



}

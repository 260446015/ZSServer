package com.huishu.ManageServer.entity.dto.dbThird;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author hhy
 * @date 2018年3月28日
 * @Parem
 * @return 
 * 
 */
public class AttributeInfo {
	private String attributeName;
	private String attributeValue;
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
}

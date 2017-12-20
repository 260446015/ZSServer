package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import com.huishu.ZSServer.entity.openeyes.BaseInfo;

public class BaseInfoCustom extends BaseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7228436882378331852L;

	private boolean isAttation;

	public boolean getIsAttation() {
		return isAttation;
	}

	public void setIsAttation(boolean isAttation) {
		this.isAttation = isAttation;
	}

}

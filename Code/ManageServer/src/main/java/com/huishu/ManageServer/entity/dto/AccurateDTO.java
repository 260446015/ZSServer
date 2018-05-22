package com.huishu.ManageServer.entity.dto;

import org.springframework.data.domain.PageRequest;

public class AccurateDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8074927426236576820L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public PageRequest getPageRequest() {
		return super.getPageRequest();
		
	}
	
	
}

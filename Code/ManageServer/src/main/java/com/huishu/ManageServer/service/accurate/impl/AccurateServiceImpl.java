package com.huishu.ManageServer.service.accurate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.repository.datajpa.first.IndusCompanyRepository;
import com.huishu.ManageServer.service.accurate.AccurateService;

public class AccurateServiceImpl implements AccurateService{

	@Autowired
	private IndusCompanyRepository IndusCompanyRepository;
	@Override
	public Page<IndusCompany> findAllIndusCompany(AccurateDTO dto) {
		PageRequest pageRequest = dto.getPageRequest();
		return IndusCompanyRepository.findAll(pageRequest);
		
	}

}

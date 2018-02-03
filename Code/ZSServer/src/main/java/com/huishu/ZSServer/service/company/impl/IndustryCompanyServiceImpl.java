package com.huishu.ZSServer.service.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.repository.company.IndustryCompanyRepository;
import com.huishu.ZSServer.service.company.IndustryCompanyService;

/**
 * @author hhy
 * @date 2018年2月3日
 * @Parem
 * @return 
 * 智能推荐
 */
@Service
public class IndustryCompanyServiceImpl implements IndustryCompanyService {
	@Autowired
	private IndustryCompanyRepository rep;
	
	@Override
	public List<IndusCompany> listCompany() {
		return (List<IndusCompany>) rep.findAll();
	}

}

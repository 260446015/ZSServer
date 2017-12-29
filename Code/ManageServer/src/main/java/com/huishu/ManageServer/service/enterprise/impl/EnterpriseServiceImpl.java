package com.huishu.ManageServer.service.enterprise.impl;

import com.huishu.ManageServer.repository.datajpa.first.EnterPriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.service.enterprise.EnterpriseService;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 
 */
@Service
@Transactional("firstTransactionManager")
public class EnterpriseServiceImpl implements EnterpriseService{

	@Autowired
	private EnterPriseRepository enterPriseRepository;

	@Override
	public Enterprise findOneByName(String name) {
		enterPriseRepository.findByCompany(name);
		return null;
	}
}

package com.huishu.ManageServer.service.enterprise.impl;

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

	@Override
	public Enterprise findOneByName(String name) {
		return null;
	}

}

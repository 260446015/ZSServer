package com.huishu.ManageServer.service.company.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.repository.datajpa.second.CompanyRepository;
import com.huishu.ManageServer.service.company.CompanyService;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 关于公司的相关操作
 */
@Service
@Transactional("secondTransactionManager")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository rep;
	/**
	 * 获取所有公司的信息
	 */
	@Override
	public List<CompanyEntity> findAllCompany() {
		List<CompanyEntity> list = new ArrayList<CompanyEntity>();
		 CompanyEntity ent = rep.findOne((long) 2388);
		 list.add(ent);
		return list;
	}
	
}

package com.huishu.ManageServer.service.second.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.repository.second.CompanyRepository;
import com.huishu.ManageServer.service.second.company.CompanyService;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 关于公司的相关操作
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository rep;
	/**
	 * 获取所有公司的信息
	 */
	@Override
	@TargetDataSource(name="second")
	public List<CompanyEntity> findAllCompany() {
		return rep.getInfo();
	}
	
}

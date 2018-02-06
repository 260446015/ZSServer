package com.huishu.ManageServer.service.enterprise.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.repository.first.IndusCompanyRepository;
import com.huishu.ManageServer.service.enterprise.IndustryCompanyService;

/**
 * @author hhy
 * @date 2018年2月5日
 * @Parem
 * @return 
 * 
 */
@Service
public class IndustryCompanyServiceImpl implements IndustryCompanyService {
	@Autowired
	private IndusCompanyRepository rep;

	//删除原本的数据
	@Override
	public boolean deleteAll() {
				Iterable<IndusCompany> list = rep.findAll();
				try {
					rep.delete(list);
					return true;
				} catch (Exception e) {
					
					return false;
				}
	}
	
	@Override
	public boolean saveListCompany(List<Enterprise> list) {
		List<IndusCompany> ll = new ArrayList<IndusCompany>();
		list.forEach(action->{
			IndusCompany ent = new IndusCompany();
			ent.setId(action.getId());
			ent.setCompany(action.getCompany());
			ent.setCompanyName(action.getCompanyName());
			ent.setIndustry(action.getIndustry());
			ent.setIndustryLabel(action.getIndustryLabel());
			ent.setInduszero(action.getIndustryZero());
			ent.setCreateTime(action.getCreateTime());
			ent.setUpdateTime(action.getUpdateTime());
			ll.add(ent);
		});
		try {
			rep.save(ll);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

package com.huishu.ManageServer.service.accurate.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.repository.first.IndusCompanyRepository;
import com.huishu.ManageServer.service.accurate.AccurateService;

@Service
public class AccurateServiceImpl implements AccurateService {

	private static Logger log = LoggerFactory.getLogger(AccurateServiceImpl.class);
	
	@Autowired
	private IndusCompanyRepository IndusCompanyRepository;

	@Override
	public Page<IndusCompany> findAllIndusCompany(AccurateDTO dto) {
		PageRequest pageRequest = dto.getPageRequest();
		int count = IndusCompanyRepository.getCount();
		List<IndusCompany> findIndus = IndusCompanyRepository.findIndus(dto.getPageNum()*dto.getPageSize(), dto.getPageSize());
		return new PageImpl<>(findIndus, pageRequest, count);

	}

	@Override
	public boolean saveIndusCompany(IndusCompany indus) {
		IndusCompany save = null;
		try {
			save = IndusCompanyRepository.save(indus);
		} catch (Exception e) {
			log.info("保存indusCompany失败",e.getMessage());
			return false;
		}
		return true;
		
	}

	@Override
	public boolean delIndusCompany(String id) {
		try {
			long _id = Long.parseLong(id);
			IndusCompanyRepository.delete(_id);
		} catch (NumberFormatException e) {
			log.info("id输入不正确",e.getMessage());
			return false;
		} catch (Exception e){
			log.info("数据库出现异常",e.getMessage());
			return false;
		}
		return true;
		
	}
	
	

}

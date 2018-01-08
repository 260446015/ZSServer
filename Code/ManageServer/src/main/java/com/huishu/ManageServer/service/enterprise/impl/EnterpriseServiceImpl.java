package com.huishu.ManageServer.service.enterprise.impl;

import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.repository.first.EnterPriseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.service.enterprise.EnterpriseService;

import java.util.List;

/**
 * 企业库service
 *
 * @author yindq
 * @date 2017/12/29
 */
@Service
//@Transactional("firstTransactionManager")
public class EnterpriseServiceImpl implements EnterpriseService{

	@Autowired
	private EnterPriseRepository enterPriseRepository;

	@Override
	public Page<Enterprise> listEnterprise(AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<Enterprise> list = enterPriseRepository.findPage(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = enterPriseRepository.count();
		Page<Enterprise> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	@Override
	public Boolean saveEnterprise(Enterprise enter) {
		Enterprise save = enterPriseRepository.save(enter);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public Boolean dropEnterprise(Long id) {
		enterPriseRepository.delete(id);
		return true;
	}

	@Override
	public Enterprise findById(Long id) {
		return enterPriseRepository.findOne(id);
	}

}

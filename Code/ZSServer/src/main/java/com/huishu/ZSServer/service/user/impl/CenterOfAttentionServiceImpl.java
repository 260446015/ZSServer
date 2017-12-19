package com.huishu.ZSServer.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.PageConstant;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
import com.huishu.ZSServer.repository.user.UserInstitutionalRepository;
import com.huishu.ZSServer.service.user.CenterOfAttentionService;

@Service
public class CenterOfAttentionServiceImpl implements CenterOfAttentionService {
	
	@Autowired
	private InstitutionalRepostitory institutionalRepostitory;
	@Autowired
	private UserInstitutionalRepository userInstitutionalRepository;
	
	@Override
	public Page<Institutional> findOrganizationList(String industry, Long userId,Integer pageNum) {
		List<Institutional> list = institutionalRepostitory.findByIdAndIndustry(userId, industry, pageNum*PageConstant.FIVE, PageConstant.FIVE);
		Integer total = institutionalRepostitory.findCountByIdAndIndustry(userId, industry);
		PageRequest request = new PageRequest(pageNum,PageConstant.FIVE);
		Page<Institutional> impl = new PageImpl<>(list, request, total);
		return impl;
	}

}

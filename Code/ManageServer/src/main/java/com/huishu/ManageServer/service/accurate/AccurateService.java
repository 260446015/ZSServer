package com.huishu.ManageServer.service.accurate;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.entity.dto.AccurateDTO;

@Service
@Transactional("firstTransactionManager")
public interface AccurateService {

	Page<IndusCompany> findAllIndusCompany(AccurateDTO dto);
}

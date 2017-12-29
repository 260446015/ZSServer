package com.huishu.ManageServer.service.accurate;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.entity.dto.AccurateDTO;

public interface AccurateService {

	Page<IndusCompany> findAllIndusCompany(AccurateDTO dto);

	boolean saveIndusCompany(IndusCompany indus);

	boolean delIndusCompany(String id);
}

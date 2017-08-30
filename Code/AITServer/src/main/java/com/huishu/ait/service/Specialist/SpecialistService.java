package com.huishu.ait.service.Specialist;


import org.springframework.data.domain.Page;

import com.huishu.ait.entity.Specialist;
import com.huishu.ait.es.entity.ExpertOpinionDTO;

public interface SpecialistService {

	Page<Specialist> findAllOrderById(ExpertOpinionDTO dto);

}

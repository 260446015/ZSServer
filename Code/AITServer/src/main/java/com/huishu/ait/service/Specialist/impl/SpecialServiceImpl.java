package com.huishu.ait.service.Specialist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Specialist;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.repository.Specialist.SpecialistRepository;
import com.huishu.ait.service.Specialist.SpecialistService;


/**
 * @author yxq
 *
 */
@Service
public class SpecialServiceImpl implements SpecialistService {
	@Autowired
	private SpecialistRepository specialRepository;   
	
	@Override
	public Page<Specialist> findAllOrderById(ExpertOpinionDTO dto) {
		Sort sort = new Sort(Direction.ASC, "id");
		PageRequest request = new PageRequest(dto.getPageNumber(), dto.getPageSize(),sort);
		Page<Specialist> page = specialRepository.findAll(request);
		return page;
	}

}

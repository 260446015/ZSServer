package com.huishu.ait.service.Specialist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Specialist;
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
	public List<Specialist> findAll() {
		
		return (List<Specialist>) specialRepository.findAll();
	}

	@Override
	public Specialist getSpecialistById(String id) {
		return specialRepository.findOne(Long.parseLong(id));
	}

	

}

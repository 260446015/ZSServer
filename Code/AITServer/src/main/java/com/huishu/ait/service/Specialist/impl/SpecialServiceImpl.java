package com.huishu.ait.service.Specialist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Specialist;
import com.huishu.ait.repository.Specialist.SpecialistRepository;


/**
 * @author yxq
 *
 */
@Service
public class SpecialServiceImpl implements SpecialistService {
	@Autowired
	private SpecialistRepository   specialRepository;   
	
	@Override
	public List<Specialist> findAll() {
		
		return (List<Specialist>) specialRepository.findAll();
	}

	

}

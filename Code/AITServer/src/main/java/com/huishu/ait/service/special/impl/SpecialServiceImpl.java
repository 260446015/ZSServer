package com.huishu.ait.service.special.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Specialist;
import com.huishu.ait.repository.SpecialRepository;
import com.huishu.ait.service.special.SpecialService;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return 
 * 
 */
@Service
public class SpecialServiceImpl implements SpecialService {
	@Autowired
	private SpecialRepository   specialRepository;   
	
	@Override
	public List<Specialist> findAll() {
		
		return (List<Specialist>) specialRepository.findAll();
	}

}

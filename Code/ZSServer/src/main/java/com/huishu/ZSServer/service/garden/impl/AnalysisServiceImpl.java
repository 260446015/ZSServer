package com.huishu.ZSServer.service.garden.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.repository.FinancingRepository;
import com.huishu.ZSServer.service.garden.AnalysisService;

@Service
public class AnalysisServiceImpl implements AnalysisService {
	@Autowired
	private FinancingRepository financingRepository;

	@Override
	public List<JSONObject> getFinancingDistribution(String park) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<Object[]> repository = financingRepository.countAboutfinancingRepository(park);
		for (Object[] objects : repository) {
			JSONObject object = new JSONObject();
			object.put("count", objects[0]);
			object.put("invest", objects[1]);
			list.add(object);
		}
		return list;
	}
	
	@Override
	public Page<Company> getCompanyList(CompanySearchDTO dto) {
		Sort sort = new Sort(Direction.DESC, "financingDate");
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(),sort);
		return financingRepository.findByInvestAndPark(dto.getInvest(),dto.getPark(), pageRequest);
	}

	@Override
	public List<JSONObject> getValueDistribution(String park, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Company> getTopCompany(String park, String industry) {
		// TODO Auto-generated method stub
		return null;
	}
}

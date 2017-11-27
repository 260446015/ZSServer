package com.huishu.ZSServer.service.garden.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAnnals;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.repository.company.FinancingRepository;
import com.huishu.ZSServer.repository.garden.CompanyAnnalsRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.garden.AnalysisService;

@Service
public class AnalysisServiceImpl extends AbstractService<CompanyAnnals> implements AnalysisService {
	@Autowired
	private FinancingRepository financingRepository;
	@Autowired
	private CompanyAnnalsRepository annalsRepository;

	@Override
	public List<JSONObject> getFinancingDistribution(String park) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<Object[]> repository = financingRepository.countAboutfinancingRepository(park);
		for (Object[] objects : repository) {
			JSONObject object = new JSONObject();
			object.put("value", objects[0]);
			object.put("name", objects[1]);
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
	public List<JSONObject> getValueDistribution(String park, String type,String industry) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		if(type.equals("年税收")){
			type="tax_revenue";
		}else{
			type="output_value";
		}
		List<Object[]> year = annalsRepository.countByYear(park, type,industry);
		for (Object[] objects : year) {
			JSONObject object = new JSONObject();
			object.put("count", objects[0]);
			object.put("year", objects[1]);
			list.add(object);
		}
		return list;
	}

	@Override
	public Page<CompanyAnnals> getTopCompany(String park, String industry) {
		PageRequest pageRequest = new PageRequest(0, 5,new Sort(Direction.DESC, "outputValue"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("park", park);
		params.put("industry", industry);
		return annalsRepository.findAll(getSpec(params), pageRequest);
	}
}

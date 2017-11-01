package com.huishu.ZSServer.service.financing.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.huishu.ZSServer.common.util.ConstansKey;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.repository.FinancingRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.financing.FinancingService;

@Service
public class FinancingServiceImpl extends AbstractService<Company> implements FinancingService {
	@Autowired
	private FinancingRepository financingRepository;

	@Override
	public Page<Company> getCompanyList(CompanySearchDTO dto) {
		Sort sort;
		if(dto.getSort().equals("按时间")){
			sort = new Sort(Direction.DESC, "financingDate");
		}else{
			sort = new Sort(Direction.DESC, "financingAmount");
		}
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(),sort);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", dto.getArea());
		params.put("industry", dto.getIndustry());
		params.put("invest", dto.getInvest());
		return financingRepository.findAll(getSpec(params), pageRequest);
	}

	@Override
	public Page<AITInfo> getFinancingDynamic() {
		Sort sort = new Sort(Direction.DESC, "publishDate");
		PageRequest pageRequest = new PageRequest(0,10,sort);
		Map<String, Object> params = new HashMap<>();
		params.put("dimension", ConstansKey.RONGZIDONGTAI);
		return getAitinfo(params, pageRequest);
	}

	@Override
	public List<Company> getFinancingCompany(List<String> industry) {
		return financingRepository.findFinancingCompany(industry);
	}

	@Override
	public List<JSONObject> getHistogram(String type) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<String> industries = Arrays.asList("人工智能","大数据","物联网","生物技术");
		for (String industry : industries) {
			JSONObject object = new JSONObject();
			List<Object[]> counts=null;
			if(type.equals("week")){
				counts = financingRepository.countByWeek(industry);
			}else if(type.equals("month")){
				counts = financingRepository.countByMonth(industry);
			}else if(type.equals("season")){
				counts = financingRepository.countBySeason(industry);
			}else if(type.equals("year")){
				counts = financingRepository.countByYear(industry);
			}
			object.put("industry", industry);
			object.put("counts", counts);
			list.add(object);
		}
		return list;
	}

}

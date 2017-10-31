package com.huishu.ait.service.indusCompany.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ait.entity.Enterprise;
import com.huishu.ait.entity.IndusCompany;
import com.huishu.ait.entity.dto.EnterpriseDTO;
import com.huishu.ait.repository.companyInfo.EnterPriseRepository;
import com.huishu.ait.repository.companyInfo.IndustryCompanyRepository;
import com.huishu.ait.service.indusCompany.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年8月11日
 * @Parem
 * @return 智能企业相关实现service方法
 */
@Service
public class IndusCompanyServiceImpl implements IndusCompanyService {

	@Autowired
	private IndustryCompanyRepository repository;

	@Autowired
	private EnterPriseRepository erepository;

	/**
	 * 根据产业名查询公司信息
	 */
	@Override
	public JSONArray findIndusInfoByIndustry() {
		JSONArray json = new JSONArray();
		
		Iterable<IndusCompany> findAll = repository.findAll();
		
		while(json.size()<10){
			int a  = (int) (Math.random()*69+1);
	
			findAll.forEach( action->{
				if(a == action.getId()){
					json.add(action);							
				}
				
			});
			
		}
		
		return json;
	}

	/**
	 * 根据公司全名查询公司信息
	 */
	@Override
	public JSONObject findInfo(String company) {
		JSONObject json = new JSONObject();
		List<EnterpriseDTO> dto = erepository.findByCompany(company);
		for (EnterpriseDTO enterpriseDTO : dto) {
			json.put("data", enterpriseDTO);
		}
		// boolean add = json.add(dto);

		return json;
	}

}

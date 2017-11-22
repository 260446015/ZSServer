package com.huishu.ZSServer.service.company.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.repository.company.EnterPriseRepository;
import com.huishu.ZSServer.service.company.EnterPriseService;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 关于公司的相关操作
 */
@Service
public class EnterPriseServiceImpl implements EnterPriseService {
	
	@Autowired
	private EnterPriseRepository rep;
	
	@Override
	public List<String> findCompanyName(String area, String industry) {
		List<String> li = new ArrayList<String>();
		List<String> list = rep.getCompanyNameByIndustryAndArea(area, industry);
		for (int i = 0; i < 8; i++) {
			li.add(list.get(i));
		}
		return li;
	}

}

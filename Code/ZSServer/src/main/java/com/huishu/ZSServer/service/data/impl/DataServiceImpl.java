package com.huishu.ZSServer.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
import com.huishu.ZSServer.service.data.DataService;

/**
 * @author hhy
 * @date 2017年11月15日
 * @Parem
 * @return 
 * 
 */
@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private InstitutionalRepostitory repository;
	/**
	 * 转换机构库数据
	 */
	@Override
	public Institutional transformData1(String value) {
		String[] split = value.split("---");
		Institutional info = new Institutional();
		info.setLaboratoryName(split[0]);
		info.setInstitutionalCategory(split[1]);
		info.setIndustry(split[2]);
		info.setArea(split[3]);
		info.setSupportUnit(split[4]);
		info.setAcademicLeader(split[5]);
		info.setUrl(split[6]);
		return info;
	}

	/**
	 * 保存数据到数据库
	 */
	@Override
	public boolean addData1(Institutional info) {
		Institutional save = repository.save(info);
		if(save != null){
			return true;
		}else{
			return false;
		}
	}

}

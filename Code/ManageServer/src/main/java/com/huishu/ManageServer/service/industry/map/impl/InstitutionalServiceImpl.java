package com.huishu.ManageServer.service.industry.map.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.entity.dbFirst.Institutional;
import com.huishu.ManageServer.repository.first.InstitutionalRepostitory;
import com.huishu.ManageServer.service.industry.map.InstitutionalService;

/**
 * @author hhy
 * @date 2018年2月7日
 * @Parem
 * @return 
 * 国家重点实验室的相关操作
 */
@Service
public class InstitutionalServiceImpl implements InstitutionalService {
	
	@Autowired
	private InstitutionalRepostitory rep;
	
	@Override
	public List<Institutional> getInstitutionListByIndustry(String industry) {
		if(industry.equals("全部")){
			return (List<Institutional>) rep.findAll();
		}
		return rep.findByIndustry(industry);
	}

	
	@Override
	public boolean deleteInstitutionalInfoById(String id) {
		try {
			long pid = Long.parseLong(id);
			rep.delete(pid);
			return true;
		} catch (Exception e) {

			return false;
		}
	}


	@Override
	public boolean saveOrUpdateInstitutionInfo(Institutional ent) {
		try {
			Institutional save = rep.save(ent);
			if(save==null){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

}

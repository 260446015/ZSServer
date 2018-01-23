package com.huishu.aitanalysis.service.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.entity.GardenData;
import com.huishu.aitanalysis.repository.search.GardenDataRepository;

@Service
public class ParkAnalysisServiceImpl implements ParkAnalysisService {
	@Autowired
	private GardenDataRepository res;
//	private boolean n = false;
	@Override
	public boolean isParkName(String keyword) {
		GardenData data  = null;
		try {
			data = res.findByGardenName(keyword);
			if(StringUtil.isNotEmpty(data.getGardenName())){
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		/*
		Iterable<GardenData> all = res.findAll();
		
		all.forEach(info->{
			if(keyword.equals( info.getGardenName())){
				n = true;
			}
		});
		
		return n;*/
	}

}

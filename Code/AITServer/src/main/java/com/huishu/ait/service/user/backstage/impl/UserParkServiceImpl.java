package com.huishu.ait.service.user.backstage.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDataDTO;
import com.huishu.ait.entity.dto.GardenSearchDTO;
import com.huishu.ait.repository.user.UserParkRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.user.backstage.UserParkService;
@Service
public class UserParkServiceImpl extends AbstractService implements UserParkService {
	
	@Autowired
	private UserParkRepository userParkRepository;
	
	@Override
	public List<GardenDataDTO> getGardenList(GardenSearchDTO searchModel) {
		List<GardenDataDTO> listData = new ArrayList<GardenDataDTO>();
		String[] msg = searchModel.getMsg();
		searchModel.setArea(msg[0]);
		searchModel.setType(msg[1]);
		searchModel.setDay(msg[2]);
		String[] times = analysisDate(searchModel.getDay());
		Integer count = userParkRepository.findGardenListCount(searchModel.getArea(),searchModel.getSearch());
		searchModel.setTotalSize(count);
		List<Object[]> list = userParkRepository.findGardenList(searchModel.getArea(), searchModel.getPageFrom(), searchModel.getPageSize(),searchModel.getSearch());
		for (Object[] str : list) {
			GardenDataDTO dto = new GardenDataDTO();
			dto.setId((Long)str[0]);
			dto.setArea((String)str[2]);
			dto.setParkName((String)str[1]);
			Integer accountCount = userParkRepository.findAccountCount((String)str[0], searchModel.getType(),times[0], times[1]);
			Integer checkAccountCount = userParkRepository.findCheckAccountCount((String)str[0], searchModel.getType(),times[0], times[1]);
			Integer expireAccountCount = userParkRepository.findExpireAccountCount((String)str[0], searchModel.getType(),times[0], times[1]);
			Integer normalAccountCount = userParkRepository.findNormalAccountCount((String)str[0], searchModel.getType(),times[0], times[1]);
			Integer dueAccountCount = userParkRepository.findDueAccountCount((String)str[0], searchModel.getType(),times[0], times[1]);
			dto.setAccountCount(accountCount);
			dto.setCheckAccountCount(checkAccountCount);
			dto.setDueAccountCount(dueAccountCount);
			dto.setExpireAccountCount(expireAccountCount);
			dto.setNormalAccountCount(normalAccountCount);
			listData.add(dto);
		}
		return listData;
	}
	
	@Override
	public AjaxResult addGarden(UserPark userPark) {
		AjaxResult result = new AjaxResult();
		UserPark save = userParkRepository.save(userPark);
		if (save == null) {
			return result.setSuccess(false).setMessage("添加失败，请稍后再试");
		}
		return result.setSuccess(true).setMessage("添加成功");
	}

}

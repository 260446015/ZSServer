package com.huishu.ait.service.user.backstage.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.AccountDataDTO;
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
			dto.setId(Long.valueOf(str[0].toString()));
			dto.setArea(str[2].toString());
			dto.setParkName(str[1].toString());
			Integer accountCount = userParkRepository.findAccountCount(str[0].toString(), searchModel.getType(),times[0], times[1]);
			Integer checkAccountCount = userParkRepository.findCheckAccountCount(str[0].toString(), searchModel.getType(),times[0], times[1]);
			Integer expireAccountCount = userParkRepository.findExpireAccountCount(str[0].toString(), searchModel.getType(),times[0], times[1]);
			Integer normalAccountCount = userParkRepository.findNormalAccountCount(str[0].toString(), searchModel.getType(),times[0], times[1]);
			Integer dueAccountCount = userParkRepository.findDueAccountCount(str[0].toString(), searchModel.getType(),times[0], times[1]);
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

	@Override
	public UserPark findParkInformation(Long id) {
		return userParkRepository.findOne(id);
	}

	@Override
	public List<AccountDataDTO> findParkAccount(SearchModel searchModel) {
		List<AccountDataDTO> listData = new ArrayList<AccountDataDTO>();
		UserPark park = userParkRepository.findOne(searchModel.getUserId());
		Integer count = userParkRepository.findParkAccountCount(park.getName());
		searchModel.setTotalSize(count);
		List<Object[]> list = userParkRepository.findParkAccount(park.getName(),searchModel.getPageFrom(), searchModel.getPageSize());
		for (Object[] objects : list) {
			AccountDataDTO dto = new AccountDataDTO();
			dto.setId(Long.valueOf(objects[0].toString()));
			dto.setAccount(objects[1].toString());
			dto.setName(objects[2].toString());
			dto.setTime(objects[3].toString());
			listData.add(dto);
		}
		return listData;
	}

}

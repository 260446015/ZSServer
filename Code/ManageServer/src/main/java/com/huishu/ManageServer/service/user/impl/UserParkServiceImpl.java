package com.huishu.ManageServer.service.user.impl;

import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dbFirst.UserPark;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.entity.vo.GardenDataVO;
import com.huishu.ManageServer.repository.first.UserParkRepository;
import com.huishu.ManageServer.repository.first.UserRepository;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.service.user.UserParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserParkServiceImpl extends AbstractService implements UserParkService {

	@Autowired
	private UserParkRepository userParkRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<GardenDataVO> getGardenList(AbstractDTO dto) {
		List<GardenDataVO> listData = new ArrayList<GardenDataVO>();
		List<UserPark> list = userParkRepository.findGardenList(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		for (UserPark park : list) {
			GardenDataVO vo = new GardenDataVO();
			vo.setId(park.getId());
			vo.setArea(park.getArea());
			vo.setParkName(park.getName());
			Integer accountCount = userParkRepository.findAccountCount(park.getName());
			Integer checkAccountCount = userParkRepository.findCheckAccountCount(park.getName());
			Integer expireAccountCount = userParkRepository.findExpireAccountCount(park.getName());
			Integer normalAccountCount = userParkRepository.findNormalAccountCount(park.getName());
			Integer dueAccountCount = userParkRepository.findDueAccountCount(park.getName());
			vo.setAccountCount(accountCount);
			vo.setCheckAccountCount(checkAccountCount);
			vo.setDueAccountCount(dueAccountCount);
			vo.setExpireAccountCount(expireAccountCount);
			vo.setNormalAccountCount(normalAccountCount);
			listData.add(vo);
		}
		long count = userParkRepository.count();
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		Page<GardenDataVO> impl = new PageImpl<GardenDataVO>(listData, request, count);
		return impl;
	}

	@Override
	public Boolean addGarden(UserPark userPark) {
		UserPark save = userParkRepository.save(userPark);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public UserPark findParkInformation(Long id) {
		return userParkRepository.findOne(id);
	}

	@Override
	public Page<UserBase> findParkAccount(AccurateDTO dto) {
		UserPark park = userParkRepository.findOne(dto.getId());
		List<UserBase> list = userParkRepository.findParkAccount(park.getName());
		long count = userRepository.countByUserPark(park.getName());
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		Page<UserBase> impl = new PageImpl<UserBase>(list, request, count);
		return impl;
	}

}

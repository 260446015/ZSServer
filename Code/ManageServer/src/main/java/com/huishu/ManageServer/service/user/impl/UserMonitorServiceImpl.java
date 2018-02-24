package com.huishu.ManageServer.service.user.impl;

import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.vo.UserLogoVO;
import com.huishu.ManageServer.repository.first.UserLogoRepository;
import com.huishu.ManageServer.service.user.UserMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户日志监控实现类
 *
 * @author yindq
 * @date 2018/1/11
 */
@Service
public class UserMonitorServiceImpl implements UserMonitorService {

	@Autowired
	private UserLogoRepository userLogoRepository;

	@Override
	public Page<UserLogoVO> listLogo(Integer logoType,AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<Object[]> list = userLogoRepository.findPage(logoType,dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		List<UserLogoVO> arrayList = new ArrayList<>();
		for (Object[] objects : list){
			UserLogoVO logoVO = new UserLogoVO();
			logoVO.setUserId(objects[0]==null?null:Long.valueOf(objects[0].toString()));
			logoVO.setUserAccount(objects[1]==null?null:objects[1].toString());
			logoVO.setRealName(objects[2]==null?null:objects[2].toString());
			logoVO.setTelphone(objects[3]==null?null:objects[3].toString());
			logoVO.setUserEmail(objects[4]==null?null:objects[4].toString());
			logoVO.setLoginTime(objects[5]==null?null:objects[5].toString());
			logoVO.setOperationTime(objects[6]==null?null:objects[6].toString());
			logoVO.setSearchCompany(objects[7]==null?null:objects[7].toString());
			logoVO.setSearchCount(objects[8]==null?null:Integer.valueOf(objects[8].toString()));
			arrayList.add(logoVO);
		}
		long count = userLogoRepository.countByLogoType(logoType);
		Page<UserLogoVO> impl = new PageImpl<>(arrayList, request, count);
		return impl;
	}
}

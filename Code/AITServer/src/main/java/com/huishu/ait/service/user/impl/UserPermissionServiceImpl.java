package com.huishu.ait.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.repository.user.UserPermissionRepository;
import com.huishu.ait.service.user.UserPermissionService;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

	@Autowired
	private UserPermissionRepository userPermissionRepository;
	
	@Override
	public List<Long> getPermissionIdsByUserLevel(Integer userLevel) {
		return userPermissionRepository.findPermissionIdByUserLevel(userLevel);
	}

}

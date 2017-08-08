package com.huishu.ait.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Permission;
import com.huishu.ait.repository.user.PermissionRepository;
import com.huishu.ait.service.user.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public Permission getPermissionByPermissionId(Long permissionId) {
		return permissionRepository.findOne(permissionId);
	}

}

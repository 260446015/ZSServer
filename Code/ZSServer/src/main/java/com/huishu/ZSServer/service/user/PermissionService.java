package com.huishu.ZSServer.service.user;

import com.huishu.ZSServer.entity.user.Permission;

/**
 * 权限service
 * 
 * @author yindq
 * @date 2017年8月8日
 */
public interface PermissionService {
	/**
	 * 通过权限ID查找权限信息
	 * 
	 * @param permissionId
	 * @return
	 */
	Permission getPermissionByPermissionId(Long permissionId);
}

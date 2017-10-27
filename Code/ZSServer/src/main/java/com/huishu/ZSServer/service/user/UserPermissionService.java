package com.huishu.ZSServer.service.user;

import java.util.List;

/**
 * 用户权限service
 * 
 * @author yindq
 * @date 2017年8月8日
 */
public interface UserPermissionService {
	/**
	 * 通过用户ID查找改用户所拥有的权限
	 * 
	 * @param id
	 * @return
	 */
	List<Long> getPermissionIdsByUserId(Long id);
}

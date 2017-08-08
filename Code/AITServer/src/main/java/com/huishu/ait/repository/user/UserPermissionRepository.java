package com.huishu.ait.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.UserPermission;

/**
 * 用户权限持久化类
 * @author yindq
 * @date 2017年8月8日
 */
public interface UserPermissionRepository extends CrudRepository<UserPermission,Long>{

	/**
	 * 通过用户ID查找改用户所拥有的权限
	 * @param userId
	 * @return
	 */
	@Query("select permissionId from UserPermission where userId = ?1")
	List<Long> findPermissionIdByAdminId(Long userId);
}
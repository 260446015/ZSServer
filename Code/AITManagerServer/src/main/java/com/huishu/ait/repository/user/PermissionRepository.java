package com.huishu.ait.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.Permission;

/**
 * 权限持久化类
 * 
 * @author yindq
 * @date 2017年8月8日
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {

}
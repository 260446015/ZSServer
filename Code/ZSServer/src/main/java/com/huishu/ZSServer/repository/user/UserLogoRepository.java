package com.huishu.ZSServer.repository.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.user.UserLogo;

/**
 * 用户-日志
 * 
 * @author yindq
 * @date 2018年1月10日
 */
@Repository
public interface UserLogoRepository extends CrudRepository<UserLogo, Long> {
	UserLogo findByUserIdAndSearchCompany(Long userId,String searchCompany);
	List<UserLogo> findByUserId(Long userId);
}
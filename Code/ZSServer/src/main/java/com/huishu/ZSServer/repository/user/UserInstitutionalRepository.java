package com.huishu.ZSServer.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.UserInstitutionalEntity;

/**
 * @author hhy
 * @date 2017年11月22日
 * @Parem
 * @return 
 * 
 */
public interface UserInstitutionalRepository extends CrudRepository<UserInstitutionalEntity, Long> {
	UserInstitutionalEntity findByInsIdAndUserId(Long insId, Long userId);
	/**
	 * 删除该用户关注的机构
	 * @param insId
	 * @param userId
	 * @return
	 */
	void deleteByInsIdAndUserId(Long insId, Long userId);
}

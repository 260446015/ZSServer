package com.huishu.ZSServer.repository.user;

import java.util.List;

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
	/**
	 * 
	 * @param insId
	 * @param userId
	 * @return
	 */
	UserInstitutionalEntity findByInsIdAndUserId(Long insId, Long userId);

}

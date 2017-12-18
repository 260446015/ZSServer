package com.huishu.ZSServer.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.UserLabel;

/**
 * @author hhy
 * @date 2017年12月16日
 * @Parem
 * @return 
 * 
 */
public interface UserLabelRepository extends CrudRepository<UserLabel,Long> {

	/**
	 * @param uid
	 * @return
	 */
	UserLabel findByUid(Long uid);
	
}

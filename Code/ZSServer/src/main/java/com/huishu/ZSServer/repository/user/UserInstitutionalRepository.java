package com.huishu.ZSServer.repository.user;

import org.springframework.data.jpa.repository.Query;
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
	 * @param id
	 * @param name
	 * @return
	 */
	@Query(value="select * from  t_user_institutional  where t_ist_id = ?1 and t_us_name = ?2",nativeQuery = true)
	UserInstitutionalEntity getInfoByInsIdAndName(Long id, String name);

}

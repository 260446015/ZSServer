package com.huishu.ZSServer.repository.summit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.UserSummitInfo;


/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 
 */
@Repository
public interface SummitRepository extends CrudRepository<UserSummitInfo, Long>{

	/**
	 * 验证该信息是否已入库
	 * @param aid
	 * @param uid
	 * @return
	 */
	UserSummitInfo findByAidAndUid(String aid, Long uid);

}

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

}

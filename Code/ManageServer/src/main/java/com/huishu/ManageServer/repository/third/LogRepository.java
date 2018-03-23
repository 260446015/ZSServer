package com.huishu.ManageServer.repository.third;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.Log;

/**
 * @author hhy
 * @date 2018年3月23日
 * @Parem
 * @return 
 * 
 */
@TargetDataSource(name="third")
@Repository
public interface LogRepository extends CrudRepository<Log, Long> {

}

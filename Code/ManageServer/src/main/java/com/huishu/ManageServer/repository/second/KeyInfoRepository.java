package com.huishu.ManageServer.repository.second;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 */
@TargetDataSource(name="second")
public interface KeyInfoRepository  extends CrudRepository<KeyInfoEntity, Long> {

}

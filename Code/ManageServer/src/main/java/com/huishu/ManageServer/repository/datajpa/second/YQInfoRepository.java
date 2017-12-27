package com.huishu.ManageServer.repository.datajpa.second;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.entity.dbSecond.YQInfoEntity;

/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 舆情相关信息接口
 */
@Repository
public interface YQInfoRepository extends CrudRepository<YQInfoEntity , Long>{

}

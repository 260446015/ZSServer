package com.huishu.ZSServer.es.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.es.entity.SummitInfo;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 */
@Repository
public interface SummitElasticsearch extends CrudRepository<SummitInfo, String>{

}

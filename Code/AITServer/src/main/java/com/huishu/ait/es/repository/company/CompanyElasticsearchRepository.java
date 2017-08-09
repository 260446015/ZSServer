package com.huishu.ait.es.repository.company;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.CompanyElastic;


/**
 * 
 * @author yindawei 
 * @date 2017年8月9日下午3:59:42
 * @description
 * @version
 */
public interface CompanyElasticsearchRepository extends ElasticsearchCrudRepository<CompanyElastic, String>{

}

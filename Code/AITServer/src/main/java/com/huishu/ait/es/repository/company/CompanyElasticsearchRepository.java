package com.huishu.ait.es.repository.company;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.Company;

public interface CompanyElasticsearchRepository extends ElasticsearchCrudRepository<Company, String>{

}

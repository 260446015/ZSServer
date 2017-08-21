package com.huishu.ait.es.repository.warning;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.WarningInformation;

/** 
 * 企业信息变更
 * @author yindq
 * @date 2017年8月21日
 */
public interface WarningInformationRepository extends ElasticsearchCrudRepository<WarningInformation, String>{
}

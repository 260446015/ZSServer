package com.huishu.ZSServer.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.es.entity.Essay2;

/**
 * 查看文章详情
 * 
 * @author yindq
 * @date 2017年12月6日
 */
@Repository
public interface EssayElasticsearch2 extends ElasticsearchRepository<Essay2, String> {
}



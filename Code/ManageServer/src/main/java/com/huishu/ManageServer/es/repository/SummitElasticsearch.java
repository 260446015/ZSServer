package com.huishu.ManageServer.es.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.es.entity.SummitInfo;


/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 */
@Repository
public interface SummitElasticsearch extends ElasticsearchRepository<SummitInfo, String>{

	/**
	 * @param address
	 * @param industry
	 * @param pageRequest
	 * @return
	 */
	Page<SummitInfo> findByAddressLikeAndIdustryTwiceLike(String address, String idustryTwice, Pageable page);

}

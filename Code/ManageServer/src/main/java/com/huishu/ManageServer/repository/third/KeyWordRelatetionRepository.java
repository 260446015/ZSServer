package com.huishu.ManageServer.repository.third;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.RelatedWordEntity;

/**
 * @author hhy
 * @date 2018年4月10日
 * @Parem
 * @return 
 * 
 */
@Repository
public interface KeyWordRelatetionRepository extends CrudRepository<RelatedWordEntity, Long> {

	/**
	 * @param relatedWord
	 * @return
	 */
	RelatedWordEntity findByRelatetion(String relatedWord);

}

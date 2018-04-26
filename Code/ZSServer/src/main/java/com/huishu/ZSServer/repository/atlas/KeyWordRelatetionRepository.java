package com.huishu.ZSServer.repository.atlas;

import com.huishu.ZSServer.entity.atlas.RelatedWordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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

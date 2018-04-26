package com.huishu.ZSServer.repository.atlas;

import com.huishu.ZSServer.entity.atlas.KeywordTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hhy
 * @date 2018年4月3日
 * @Parem
 * @return 
 * 
 */
@Repository
public interface KeywordTypeRepository extends CrudRepository<KeywordTypeEntity, Long> {

	/**
	 * @param typeWord
	 */
	KeywordTypeEntity findByTypeWord(String typeWord);

}

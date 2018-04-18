package com.huishu.ManageServer.repository.third;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.KeywordTypeEntity;

/**
 * @author hhy
 * @date 2018年4月3日
 * @Parem
 * @return 
 * 
 */
@Repository
@TargetDataSource(name="third")
public interface KeywordTypeRepository extends CrudRepository<KeywordTypeEntity, Long> {

	/**
	 * @param typeWord
	 */
	KeywordTypeEntity findByTypeWord(String typeWord);

}

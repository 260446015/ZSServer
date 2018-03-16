package com.huishu.ManageServer.repository.third;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.KeyWordRelatedEntity;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 词库之间关联关系
 */
@TargetDataSource(name="third")
public interface KeyWordRelatedRepository extends CrudRepository<KeyWordRelatedEntity,Long> {

	/**
	 * @param id
	 * @return
	 */
	List<KeyWordRelatedEntity> findByWordId(Long id);

}

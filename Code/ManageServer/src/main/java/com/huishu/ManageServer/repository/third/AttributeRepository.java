package com.huishu.ManageServer.repository.third;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.AttributeEntity;
import com.huishu.ManageServer.entity.dto.dbThird.AttributeInfo;

/**
 * @author hhy
 * @date 2018年3月27日
 * @Parem
 * @return 
 * 
 */
@TargetDataSource(name="third")
@Repository
public interface AttributeRepository extends CrudRepository<AttributeEntity, Long> {

	/**
	 * @param id
	 * @return
	 */
	List<AttributeEntity> findByWordId(Long wordId);

	/**
	 * 
	 * @param wordId
	 * @return
	 */
	List<AttributeEntity> getByWordId(Long wordId);

}

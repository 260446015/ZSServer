package com.huishu.ManageServer.repository.third;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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
@Repository
public interface KeyWordRelatedRepository extends JpaRepository<KeyWordRelatedEntity,Long> ,JpaSpecificationExecutor<KeyWordRelatedEntity>{

	/**
	 * @param id
	 * @return
	 */
	List<KeyWordRelatedEntity> findByWordId(Long id);

	/**
	 * @param _id
	 */
	@Query(value="delete from t_word_related where t_word_id =?1",nativeQuery=true)
	void  removeInfoByWordId(long _id);

	/**
	 * @param id
	 * @param options
	 * @return
	 */
	KeyWordRelatedEntity findByWordIdAndRelateId(Long id, Long options);

}

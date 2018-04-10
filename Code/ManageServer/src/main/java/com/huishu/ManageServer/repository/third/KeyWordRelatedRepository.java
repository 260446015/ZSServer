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
@Repository
@TargetDataSource(name="third")
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
	

	/**
	 * 获取id的集合
	 * @param integer 
	 * @param number 
	 * @return
	 */
	@Query(value="select t_word_id  from  t_word_related  GROUP BY t_word_id ORDER BY count(t_word_id) desc limit ?1,?2",nativeQuery=true)
	List<Long> getKeyWordIdDESC(Long number, Integer integer);

	/**
	 * 获取总数id
	 * @return
	 */
	@Query(value="SELECT count(t.t_word_id) from (select count(*),t_word_id  from  t_word_related  GROUP BY t_word_id ) t ",nativeQuery=true)
	Long getKeyWordIdCountInfo();
}

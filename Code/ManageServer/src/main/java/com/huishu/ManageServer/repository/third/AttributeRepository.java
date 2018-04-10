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
@Repository
@TargetDataSource(name="third")
public interface AttributeRepository extends CrudRepository<AttributeEntity, Long> {

	/**
	 * @param id
	 * @return
	 */
	List<AttributeEntity> findByWordId(Long wordId);

	/**
	 * 获取id的集合
	 * @param integer 
	 * @param number 
	 * @return
	 */
	@Query(value="select t_word_id  from  t_word_attribute  GROUP BY t_word_id ORDER BY count(t_word_id) desc limit ?1,?2",nativeQuery=true)
	List<Long> getKeyWordId(Long number, Integer integer);

	/**
	 * 获取总数id
	 * @return
	 */
	@Query(value="SELECT count(t.t_word_id) from (select count(*),t_word_id  from  t_word_attribute  GROUP BY t_word_id ) t ",nativeQuery=true)
	Long getKeyWordIdCount();
	

}

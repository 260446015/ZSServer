package com.huishu.ManageServer.repository.third;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.KeywordInfoEntity;

/**
 * @author hhy
 * @date 2018年4月8日
 * @Parem
 * @return 
 * 
 */
@Repository
@TargetDataSource(name="third")
public interface KeyWordInfoRepository extends CrudRepository<KeywordInfoEntity, Long> {

	/**
	 * 根据类型id 分页 查看  所有数据 
	 * @param typeId
	 * @param number
	 * @param pageSize
	 * @return
	 */
	@Query(value="select t_word_id  from t_word_info  WHERE t_type_id = ?1  ORDER BY t_insert_time desc  limit ?2,?3 ",nativeQuery = true)
	List<Long> getKeyWordListDESCByType(Long typeId, Long number, Integer pageSize);

	@Query(value="select t_word_id  from t_word_info  WHERE t_type_id = ?1  ORDER BY t_insert_time limit ?2,?3 ",nativeQuery = true)
	List<Long> getKeyWordListByType(Long typeId, Long number, Integer pageSize);

	/**
	 * 获取计数id
	 * @param typeId
	 * @return
	 */
	@Query(value="select COUNT(id) from t_word_info  WHERE t_type_id = ?1",nativeQuery = true)
	Long getCount(Long typeId);

	/**
	 * @param typeId
	 * @return
	 */
	
	@Query(value="select t_word_id from t_word_info  WHERE t_type_id = ?1",nativeQuery = true)
	List<Integer> getWordIdByTypeId(Long typeId);

	/**
	 * @param id
	 * @return
	 */
	
	@Query(value="select t_word_number from t_word_info  WHERE t_word_id = ?1",nativeQuery = true)
	String getKeywordNumberByWordId(Long wordId);

	/**
	 * @param id
	 */
	KeywordInfoEntity findByWordId(Long wordId);
	
}

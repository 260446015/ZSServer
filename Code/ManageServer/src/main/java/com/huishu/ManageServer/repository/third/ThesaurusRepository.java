package com.huishu.ManageServer.repository.third;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 */
@TargetDataSource(name="third")
@Repository
public interface ThesaurusRepository  extends JpaRepository<ThesaurusEntity,Long>,JpaSpecificationExecutor<ThesaurusEntity> {

	/**
	 * 根据类型查看关键词信息
	 * @param type
	 * @param pageNumber
	 * @return
	 */
	Page<ThesaurusEntity> findByType(String type, Pageable page);

	/**
	 * @param type
	 * @return
	 */
	@Query(value="select count(*) from t_word where t_word_type =?1",nativeQuery = true)
	int countByType(String type);

	/**
	 * @return
	 */
	@Query(value="select max(id) from (select id from t_word GROUP BY t_word_type ORDER BY id DESC ) t ",nativeQuery = true)
	Long getKeyWordId();
	
	@Query(value="select max(id) from (select id from t_word where t_word_type = ?1  ORDER BY id DESC ) t ",nativeQuery = true)
	Long getMaxKeyWordIdByType(String type);
	
	@Query(value="select t_type_id from t_word where t_word_type = ?1 GROUP BY t_type_id",nativeQuery = true)
	Long getTypeIdByType(String type);
	
	@Query(value="select max(id) from t_word ",nativeQuery = true)
	Long getMaxId();

	/**
	 * @param keyword
	 */
	ThesaurusEntity findByKeyword(String keyword);

	/**
	 * 获取数据
	 * @param number
	 * @param pageSize
	 */
	@Query(value="select * from t_word  LIMIT ?1,?2",nativeQuery = true)
	List<ThesaurusEntity> getKeyInfoList(Long number, Integer pageSize);

	/**
	 * 根据类型获取数据
	 * @param type
	 * @param number
	 * @param pageSize
	 */
	@Query(value="select * from t_word WHERE t_word_type = ?1 LIMIT ?2,?3",nativeQuery = true)
	List<ThesaurusEntity> getKeyWordListByType(String type, Long number, Integer pageSize);

}

package com.huishu.ZSServer.repository.atlas;

import com.huishu.ZSServer.entity.atlas.ThesaurusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 */

@Repository
public interface ThesaurusRepository extends JpaRepository<ThesaurusEntity,Long>{

	

	/**
	 * @return
	 */
	@Query(value="select max(id) from (select id from t_word GROUP BY t_word_type ORDER BY id DESC ) t ",nativeQuery = true)
	Long getKeyWordId();
	
	
	@Query(value="select max(id) from t_word ",nativeQuery = true)
	Long getMaxId();

	/**
	 * @param keyword
	 */
	ThesaurusEntity findByKeyword(String keyword);

	/**
	 * 根据添加时间顺序获取数据
	 * @param number
	 * @param pageSize
	 */
	@Query(value="select * from t_word ORDER BY t_insert_time LIMIT ?1,?2",nativeQuery = true)
	List<ThesaurusEntity> getKeyInfoList(Long number, Integer pageSize);


	/**
	 * 根据添加时间倒序
	 * @param number
	 * @param pageSize
	 * @return
	 */
	@Query(value="select * from t_word ORDER BY t_insert_time  desc   LIMIT ?1,?2",nativeQuery = true)
	List<ThesaurusEntity> getKeyWordListDESC(Long number, Integer pageSize);


	/**
	 * @param ll 
	 * @param i
	 * @param it
	 * @return
	 */
	@Query(value="select * from t_word  where id not in(?1) LIMIT ?2,?3",nativeQuery = true)
	List<ThesaurusEntity> getKeyInfoList(List<Integer> ll, int i, int it);



}

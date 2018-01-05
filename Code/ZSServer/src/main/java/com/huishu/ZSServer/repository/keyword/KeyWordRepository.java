package com.huishu.ZSServer.repository.keyword;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.KeyWordEntity;

/**
 * @author hhy
 * @date 2017年12月19日
 * @Parem
 * @return 
 * 
 */
public interface KeyWordRepository extends CrudRepository<KeyWordEntity, Long>{

	/**
	 * @param str
	 * @return
	 * 根据时间查询关键词
	 * 
	 */
	List<KeyWordEntity> findByTime(String str);

	/**
	 * @param time
	 * @return
	 * 获取Top10词云
	 */
	@Query(value="SELECT * FROM t_key_value  WHERE time = ?1 limit 0,12" , nativeQuery = true)
	List<KeyWordEntity> getByTime(String time);

	/**
	 * @param time
	 * @param keyWord
	 * @return
	 */
	KeyWordEntity findByTimeAndKey(String time, String keyWord);

}

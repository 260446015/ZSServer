package com.huishu.ManageServer.repository.second;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 */
public interface KeyInfoRepository  extends CrudRepository<KeyInfoEntity, Long> {
	
	@Query(value=" select keyword  from li_indus_word",nativeQuery=true)
	List<String> getKeyWordList();

	/**
	 * 获取关键词信息
	 * @param keyname
	 * @return
	 */
	@Query(value=" select * from li_indus_word where keyword = ?1 ",nativeQuery=true)
	List<KeyInfoEntity> getKeywordInfo(String keyname);
}

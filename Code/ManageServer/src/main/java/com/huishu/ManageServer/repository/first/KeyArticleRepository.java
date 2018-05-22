package com.huishu.ManageServer.repository.first;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbFirst.KeywordArticle;


/**
 * @author hhy
 * @date 2018年1月4日
 * @Parem
 * @return 
 * 关键词文章接口
 */
public interface KeyArticleRepository extends CrudRepository< KeywordArticle, Long> {

	/**
	 * @param id
	 * @return
	 */
	List<KeywordArticle> findBykid(Long id);

}

package com.huishu.ait.repository.expertOpinionDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.huishu.ait.entity.UserCollection;

/**
 * @author yxq
 * @date 2017年7月31日
 * @功能描述：专家观点详情接口
 */
public interface UserCollectionRepository extends CrudRepository<UserCollection, Long> {
	// 根据文章id从数据库中查询文章详情
	UserCollection findByArticleIdAndUserId(String articleId, Long userId);
	
	// 通过分页插件查询t_user_collection表中的数据
	Page<UserCollection> findByUserIdAndLanmuLike(Long userId, String lanmu, Pageable pageable);

}

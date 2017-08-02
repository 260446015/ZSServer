package com.huishu.ait.repository.expertOpinionDetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ait.entity.ExpertOpinionDetail;

/**
 * @author yxq
 * @date 2017年7月31日
 * @功能描述：专家观点详情接口
 */
public interface ExpertOpinionDetailRepository extends CrudRepository<ExpertOpinionDetail, String> {
	//根据文章id从数据库中查询文章详情
	@Query("from ExpertOpinionDetail where articleId=?")
	ExpertOpinionDetail findExpertOpinionDetailByArticleId(String articleId);

}

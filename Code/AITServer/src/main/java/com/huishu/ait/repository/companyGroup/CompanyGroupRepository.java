package com.huishu.ait.repository.companyGroup;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.entity.CompanyGroup;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：企业分组
 */
@Transactional
public interface CompanyGroupRepository extends CrudRepository<CompanyGroup, Integer>{
	@Query("from CompanyGroup where groupName = ? and userId = ?")
	CompanyGroup findGroupByName(String groupName,Long userId);

	void deleteByGroupNameAndUserId(String groupName,Long userId);

	/**
	 * 通过用户id查询当前用户所创建的企业分组
	 * @param userId
	 * @return
	 */
	List<CompanyGroup> findByUserId(Long userId);
	
	CompanyGroup findByGroupNameAndUserId(String groupName,Long userId);
	
	

}

package com.huishu.ait.repository.companyGroup;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.CompanyGroup;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：企业分组
 */
public interface CompanyGroupRepository extends CrudRepository<CompanyGroup, Integer>{
	@Query("from CompanyGroup where groupName = ?")
	CompanyGroup findGroupByName(String groupName);

}

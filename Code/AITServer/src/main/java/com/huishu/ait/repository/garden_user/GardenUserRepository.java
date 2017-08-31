package com.huishu.ait.repository.garden_user;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.entity.GardenUser;
@Transactional
public interface GardenUserRepository extends CrudRepository<GardenUser, Integer>,JpaSpecificationExecutor<GardenUser>{

	@Query(value="select gu.gardenName from GardenUser gu where gu.userId=?")
	public List<String> findGardensCondition(int userid);
	
	/**
	 * 根据用户id，区域，产业类型 获取园区列表
	 * @param userId
	 * @param area
	 * @param industryType
	 * @param pageable
	 * @return
	 */
	Page<GardenUser> findByUserIdAndAreaAndIndustryType(Integer userId, String area, String industryType, Pageable pageable);
	Page<GardenUser> findByUserIdAndArea(Integer userId, String area, Pageable pageable);
	Page<GardenUser> findByUserIdAndIndustryType(Integer userId, String industryType, Pageable pageable);
	Page<GardenUser> findByUserId(Integer userId, Pageable pageable);
	Integer deleteByGardenName(String gardenName);
	GardenUser findByGardenName(String gardenName);
	GardenUser findByGardenNameAndUserId(String gardenName,Integer userId);
}

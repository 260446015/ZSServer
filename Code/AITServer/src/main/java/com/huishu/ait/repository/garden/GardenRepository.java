package com.huishu.ait.repository.garden;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.Garden;

/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return 
 * 
 */
public interface GardenRepository extends CrudRepository<Garden, Long>{
	
	public List<Garden> findByAreaAndIndustryType(String area,String industryType);

	public List<Garden> findByNameLike(String searchName);
	
}

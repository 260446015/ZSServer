package com.huishu.ait.repository.garden;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	Page<Garden> findByAreaAndIndustryType(String area,String industryType,Pageable pageable);

	Page<Garden> findByNameLike(String searchName,Pageable pagealbe);
	
}

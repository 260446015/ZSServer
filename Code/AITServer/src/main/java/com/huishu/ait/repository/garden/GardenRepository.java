package com.huishu.ait.repository.garden;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

	@Query(value="select id,name,description,address,area,industryType from Garden g where g.name like CONCAT('%',:keyName,'%')")
	Page<Garden> findByNameLike(@Param("keyName")String name,Pageable pagealbe);
	
}

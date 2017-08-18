package com.huishu.ait.repository.garden;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.GardenData;

/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return 
 * 
 */
public interface GardenRepository extends CrudRepository<GardenData, Integer>{
	
	Page<GardenData> findByAreaLikeAndLeadingIndustryLike(String area,String leadingIndustry,Pageable pageable);

	/**
	 * 按照地域查询园区列表
	 * @param area
	 * @return
	 */
	List<GardenData> findGardensByArea(String area);

//	@Query(value="select id,name,description,address,area,industryType from Garden g where g.name like CONCAT('%',:keyName,'%')")
//	Page<Garden> findByNameLike(@Param("keyName")String name,Pageable pagealbe);
	
}

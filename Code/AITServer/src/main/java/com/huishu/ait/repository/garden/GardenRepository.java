package com.huishu.ait.repository.garden;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.GardenData;

/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public interface GardenRepository extends CrudRepository<GardenData, Integer> {

	List<GardenData> findByAreaLikeAndIndustryLike(String area, String industry);

	/**
	 * 按照地域查询园区列表
	 * 
	 * @param area
	 * @return
	 */
	List<GardenData> findByAddressLikeAndIndustryLike(String area, String industry);

	GardenData findByGardenName(String gardenName);
	
	@Query(value="select gardenName from GardenData")
	List<String> findNames();

}

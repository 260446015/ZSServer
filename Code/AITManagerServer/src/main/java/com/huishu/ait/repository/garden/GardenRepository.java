package com.huishu.ait.repository.garden;

import java.util.List;

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

	List<GardenData> findByAreaLikeAndIndustryLikeOrderByIdDesc(String area, String industry);

}

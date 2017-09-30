package com.huishu.ait.repository.garden;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.GardenData;

/**
 * 
 * 
 * @author yindq
 * @create 2017年9月30日
 */
public interface GardenRepository extends CrudRepository<GardenData, Integer> {

	List<GardenData> findByAreaLikeAndGardenNameLikeAndIndustryLikeOrderByIdDesc(String area,String gardenName,String industry);

}

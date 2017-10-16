package com.huishu.ait.repository.garden;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.GardenData;

/**
 * 
 * 
 * @author yindq
 * @create 2017年9月30日
 */
public interface GardenRepository extends CrudRepository<GardenData, Integer> {

	GardenData findByGardenName(String gardenName);

}

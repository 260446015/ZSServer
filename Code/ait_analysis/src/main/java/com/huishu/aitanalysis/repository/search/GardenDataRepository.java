package com.huishu.aitanalysis.repository.search;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.aitanalysis.entity.GardenData;
@Repository
public interface GardenDataRepository extends CrudRepository<GardenData, String> {

	GardenData findByGardenName(String gardenName);

}

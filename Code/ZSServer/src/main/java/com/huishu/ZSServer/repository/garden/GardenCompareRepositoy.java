package com.huishu.ZSServer.repository.garden;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.GardenCompare;

public interface GardenCompareRepositoy extends CrudRepository<GardenCompare, Long>,JpaSpecificationExecutor<GardenCompare>{

	@Query(value="select count(industry) as industryCount,industry from t_company_data where park = ? GROUP BY industry ",nativeQuery=true)
	List<Object[]> getCompareEcharts(String park);
	
	List<GardenCompare> findByUserId(Long userId);

	List<GardenCompare> findByUserIdAndGardenId(Long userId, Long gardenId);
}

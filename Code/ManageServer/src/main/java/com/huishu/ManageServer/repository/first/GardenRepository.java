package com.huishu.ManageServer.repository.first;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbFirst.GardenData;


/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public interface GardenRepository extends CrudRepository<GardenData, Long>, JpaSpecificationExecutor<GardenData> {

	@Query(value = "select province,count(*) from t_garden_data where industry_type like ?1 GROUP by province", nativeQuery = true)
	List<Object[]> getGardenIndustryCount(String industry);

	List<GardenData> findByProvinceLike(String province);

	@Query(value = "select province from t_garden_data group by province", nativeQuery = true)
	List<String> findArea();

	Page<GardenData> findByProvinceLikeAndIndustryTypeLike(String province,String industry,Pageable page);

	List<GardenData> findByIndustryTypeLike(String string);
	
	GardenData findByGardenName(String gardenName);
	
	
}

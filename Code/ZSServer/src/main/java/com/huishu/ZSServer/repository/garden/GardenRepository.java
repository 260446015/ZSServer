package com.huishu.ZSServer.repository.garden;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.GardenData;

/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public interface GardenRepository extends CrudRepository<GardenData, Long>, JpaSpecificationExecutor<GardenData> {

	@Query(nativeQuery = true, value = "select area,sum(gdp) as gdp from t_garden_data group by area")
	List<GardenData> findGardenGdp();

}

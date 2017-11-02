package com.huishu.ZSServer.repository.garden;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.garden.GardenData;

/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public interface GardenRepository extends CrudRepository<GardenData, Long>, JpaSpecificationExecutor<GardenData> {

}

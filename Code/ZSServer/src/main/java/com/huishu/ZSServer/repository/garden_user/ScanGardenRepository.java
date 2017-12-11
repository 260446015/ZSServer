package com.huishu.ZSServer.repository.garden_user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.garden.ScanGarden;

public interface ScanGardenRepository extends CrudRepository<ScanGarden, Long> {

	List<ScanGarden> findByGardenIdAndDr(Long gardenId, int dr);

}

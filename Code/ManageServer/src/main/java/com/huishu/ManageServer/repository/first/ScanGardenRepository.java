package com.huishu.ManageServer.repository.first;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ManageServer.entity.dbFirst.ScanGarden;


public interface ScanGardenRepository extends PagingAndSortingRepository<ScanGarden, Long> {

	Page<ScanGarden> findByGardenName(String serarchName, Pageable pageRequest);


}

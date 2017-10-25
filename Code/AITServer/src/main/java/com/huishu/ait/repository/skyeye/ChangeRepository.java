package com.huishu.ait.repository.skyeye;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ait.entity.ChangeInfo;

public interface ChangeRepository extends PagingAndSortingRepository<ChangeInfo, String> {

	List<ChangeInfo> findByParkAndDr(String park, Integer dr);

	Page<ChangeInfo> findByPark(String park, Pageable pageable);

	@Query(value="select id from ChangeInfo where company = ?")
	List<Integer> findChangeId(String company);
}

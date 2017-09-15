package com.huishu.ait.repository.skyeye;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ait.entity.ChangeInfo;

public interface ChangeRepository extends PagingAndSortingRepository<ChangeInfo, Integer>{

	List<ChangeInfo> findByParkAndDr(String park,Integer dr);
}

package com.huishu.ait.repository.Specialist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ait.entity.Specialist;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return
 * 
 */
public interface SpecialistRepository extends PagingAndSortingRepository<Specialist, Long> {

	Specialist getSpecialistByName(String name);

	Page<Specialist> findAllOrderById(Pageable pageable);

}

package com.huishu.ait.repository.Specialist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.Specialist;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return 
 * 
 */
public interface SpecialistRepository extends CrudRepository<Specialist, Long>  {

	Specialist getSpecialistByName(String name);
	
	Page<Specialist> findSpecialistOrderById(Pageable pageable);

}

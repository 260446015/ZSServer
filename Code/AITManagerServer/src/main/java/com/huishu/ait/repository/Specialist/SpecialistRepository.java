package com.huishu.ait.repository.Specialist;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ait.entity.Specialist;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return
 * 专家的相关接口文档
 */
public interface SpecialistRepository extends PagingAndSortingRepository<Specialist, Long> {

	Specialist getSpecialistByName(String name);

	Page<Specialist> findAllOrderById(Pageable pageable);

	/**
	 * 根据产业和产业分类查询专家信息
	 * @param string
	 * @param string2
	 * @return
	 */
	List<Specialist> findByIndustryAndIndustryLabel(String industry, String industryLabel);

	/**
	 * 根据产业查询专家信息
	 * @param string
	 * @return
	 */
	List<Specialist> findByIndustry(String industry);

	
	
}

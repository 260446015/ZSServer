package com.huishu.ZSServer.repository.instituton;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.Institutional;

/**
 * @author hhy
 * @date 2017年11月15日
 * @Parem
 * @return 
 * 
 */
public interface InstitutionalRepostitory extends CrudRepository<Institutional, Long>{
	
	/**
	 * 获取满足条件的国家实验室
	 * @param area
	 * @param industry
	 * @return
	 */
	@Query(value="select * from  t_institutional_repository where area = ?1 and t_industry = ?2",nativeQuery = true)
	List<Institutional> findByAreaAndIndustry(String area, String industry);

	/**
	 * @param industry
	 * @return
	 * 获取当前产业下实验室数量
	 */
	@Query(value="select area ,count(*) from t_institutional_repository where t_industry = ?1 group by area",nativeQuery = true)
	List<Object[]> findByIndustry(String industry);
}

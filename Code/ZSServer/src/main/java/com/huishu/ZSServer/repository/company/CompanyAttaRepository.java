package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.CompanyAttation;

public interface CompanyAttaRepository extends CrudRepository<CompanyAttation, Long> {

	CompanyAttation findByCompanyIdAndUserId(Long companyId, Long userId);

	@Query(value="select industry from t_company_attation a LEFT JOIN t_baseinfo c on a.company_id=c.company_id where a.user_id=? GROUP BY industry" , nativeQuery = true)
	List<String> getCompnayIndustry(Long userId);
	
	@Query(value="select base from t_company_attation a LEFT JOIN t_baseinfo c on a.company_id=c.company_id where a.user_id=? GROUP BY base" , nativeQuery = true)
	List<String> getCompnayArea(Long userId);
	
	@Query(value="select c.company_id,c.name,c.base,c.legal_person_name,c.reg_capital,c.estiblish_time from t_company_attation a LEFT JOIN t_baseinfo c on a.company_id=c.company_id where a.user_id=?1 and c.industry like ?2 and c.estiblish_time between ?3 and ?4 and c.base like ?5 and a.company_group like ?6" , nativeQuery = true)
	List<Object[]> findCompnayList(Long userId,String industry,Long time1,Long time2,String area,String group);
	/**
	 * 删除该用户关注的机构
	 * @param insId
	 * @param userId
	 * @return
	 */
	void deleteByCompanyIdAndUserId(Long companyId, Long userId);
}

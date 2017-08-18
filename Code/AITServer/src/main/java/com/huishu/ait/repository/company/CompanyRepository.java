package com.huishu.ait.repository.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.dto.CompanyDTO;

public interface CompanyRepository extends CrudRepository<Company, Long> {


//	@Query(value="from Company c where c.industry like CONCAT('%',:keyName,'%') and (c.registerCapital between :start and :end) and c.park = :park")
	Page<Company> findByIndustryAndParkAndRegisterCapitalBetween(String industry, String park,double start,double end,Pageable pageable);

//	@Query(value="from Company  c  join CompanyGroupMiddle  m on c.cid=m.companyId where c.iudustry=? and m.groupId=?  ", nativeQuery = true)
	@Query(value="select * from t_company c INNER JOIN t_company_group_middle m on c.cid=m.company_id where c.industry=?  AND m.group_id=? and c.register_capital between ? and ?;", nativeQuery = true)
	List<Company> selectCompanysByDto(String industry,Long groupId,double start,double end);

}

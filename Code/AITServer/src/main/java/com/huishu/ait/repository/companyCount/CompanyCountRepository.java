package com.huishu.ait.repository.companyCount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.CompanyCount;
/**
 * 公司搜索维度搜索记录
 * @author jdz
 */
public interface CompanyCountRepository extends CrudRepository<CompanyCount, Integer>{
//public interface CompanyCountRepository extends JpaRepository<CompanyCount, Integer>, JpaSpecificationExecutor<CompanyCount>{

    /**
     * 根据企业被搜索次数获取企业列表，用作填充搜索框
     * @param pageable
     * @return
     */
    @Modifying
    @Query(value = "select * from t_company_count order by search_count desc")
    Page<CompanyCount> findAll(Pageable pageable);
    
    /**
     * 根据公司名称获取信息
     * @param companyName
     * @return
     */
    @Query(value = "select * from t_company_count where company_name = ?")
    public CompanyCount findByCompanyName(String companyName);
}

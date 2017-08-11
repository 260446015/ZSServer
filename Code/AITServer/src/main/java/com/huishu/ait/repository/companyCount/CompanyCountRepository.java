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

    /**
     * 向数据库中添加一条新公司信息，并将其被搜索数量设定为1
     * @param companyName
     * @param search
     * @return
     */
    @Modifying
    @Query(value = "insert into t_company_count(company_name,search_count) values(?1,?2)")
    int addCompanyCount(String companyName, int searchCount);
    
    /**
     * 根据企业被搜索次数获取企业列表，用作填充搜索框
     * @param pageable
     * @return
     */
    @Modifying
    @Query(value = "select * from t_company_count order by search_count desc")
    public Page<CompanyCount> findAllByOrderBySearchCountAtDesc(Pageable pageable);
    
    /**
     * 将 根据公司名称 点击量+1 
     * @param companyName
     * @return
     */
    @Modifying
    @Query(value = "update t_company_count set search_count=search_count+1 where company_name=1?")
    public int updateCompanyCount(String companyName);
    
    /**
     * 根据公司名称获取信息
     * @param companyName
     * @return
     */
    @Modifying
    @Query(value = "select * from t_company_count where company_name = 1?")
    public CompanyCount findByCompanyName(String companyName);
}

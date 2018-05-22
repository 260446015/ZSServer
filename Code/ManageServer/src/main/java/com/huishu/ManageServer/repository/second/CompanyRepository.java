package com.huishu.ManageServer.repository.second;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;


/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 
 */
@TargetDataSource(name="second")
public interface CompanyRepository  extends CrudRepository<CompanyEntity, Long>{
	
	@Query(value="select * from li_keywords ",nativeQuery=true)
	List<CompanyEntity> getInfo();
	
	/**
	 * @param i
	 * @param pageSize
	 * @return
	 */
	@Query(value="select * from li_keywords limit ?,?",nativeQuery=true)
	List<CompanyEntity> findPage(int i, Integer pageSize);
}

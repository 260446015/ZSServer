package com.huishu.ManageServer.repository.second;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbSecond.YQInfoEntity;

/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 舆情相关信息接口
 */
public interface YQInfoRepository extends CrudRepository<YQInfoEntity , Long>{

	/**
	 * @param kid
	 * @return
	 * 根据kid获取全部信息
	 */
	@Query(value="SELECT * FROM li_public_opinion where k_id = ?1",nativeQuery=true)
	List<YQInfoEntity> findByKid(Long kid);

}

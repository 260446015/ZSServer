package com.huishu.ait.repository.garden;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ait.entity.Garden;

/**
 * @author ydw
 * @date 2017年7月29日
 * @Parem
 * @return 
 * 
 */
public interface GardenRepository extends CrudRepository<Garden, Long>{
	
	@Query(value="select id,name,description,address,area,industryType from Garden g where g.area=? and g.industryType=?")
	public List<Garden> findGardensList(String area,String industryType);
	
}

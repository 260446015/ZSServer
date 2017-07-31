package com.huishu.ait.repository.garden_user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.GardenUser;

public interface GardenUserRepository extends CrudRepository<GardenUser, Long>{

	@Query(value="select gu.gardenName from GardenUser gu where gu.userId=?")
	public List<String> findGardensCondition(int userid);
}

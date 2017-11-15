package com.huishu.ZSServer.repository.garden_user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.garden.GardenUser;

public interface GardenUserRepository extends CrudRepository<GardenUser, Long>, JpaSpecificationExecutor<GardenUser> {

	@Query(value = "select garden_name from t_user_garden_attention where user_id = ?", nativeQuery = true)
	List<String> findGardenNames(Long userId);

}

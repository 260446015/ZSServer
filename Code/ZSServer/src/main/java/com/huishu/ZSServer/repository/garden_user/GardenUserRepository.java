package com.huishu.ZSServer.repository.garden_user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.garden.GardenUser;

public interface GardenUserRepository extends CrudRepository<GardenUser, Long>, JpaSpecificationExecutor<GardenUser> {

	@Query(value = "select garden_name from t_user_garden_attention where user_id = ?", nativeQuery = true)
	List<String> findGardenNames(Long userId);

	GardenUser findByGardenNameAndUserId(String gardenName, Long userId);

	@Query(value = "select province from t_user_garden_attention where user_id = 1 GROUP BY province", nativeQuery = true)
	List<String> findArea();

	Page<GardenUser> findByProvinceLikeAndIndustryTypeLike(String province, String industry, Pageable page);

	List<GardenUser> findByGardenIdIn(Long[] arrId);

}

package com.huishu.ZSServer.repository.company;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.TelContect;

public interface TelContectRepository extends CrudRepository<TelContect, Long>, JpaSpecificationExecutor<TelContect> {

	@Query(value = "select * from t_telcontect where user_id = ?1 and cname=?2 and name=?3", nativeQuery = true)
	TelContect telContact(Long userId, String cname, String name);

}

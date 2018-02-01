package com.huishu.ManageServer.repository.first;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbFirst.GardenUser;


public interface GardenUserRepository extends CrudRepository<GardenUser, Long>, JpaSpecificationExecutor<GardenUser> {

	List<GardenUser> findByUserId(Long userId);

}

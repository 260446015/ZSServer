package com.huishu.ZSServer.repository.garden_user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.garden.GardenUser;

public interface GardenUserRepository extends CrudRepository<GardenUser, Long>, JpaSpecificationExecutor<GardenUser>{

}

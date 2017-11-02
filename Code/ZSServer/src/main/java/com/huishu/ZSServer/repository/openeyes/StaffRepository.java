package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long>{

	List<Staff> findByCname(String cname);
	
}

package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.CompnayGroup;

public interface CompnayGroupRepository extends CrudRepository<CompnayGroup, Long>{
	List<CompnayGroup> findByUserId(Long userId);
	CompnayGroup findByUserIdAndGroupName(Long userId,String groupName);
}

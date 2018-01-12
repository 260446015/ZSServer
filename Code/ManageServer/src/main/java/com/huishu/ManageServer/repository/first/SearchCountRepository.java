package com.huishu.ManageServer.repository.first;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbFirst.SearchCount;


public interface SearchCountRepository extends CrudRepository<SearchCount, String>{

	List<SearchCount> findByUserIdIn(Collection<Long> c);

	List<SearchCount> findByUserId(Long id);
}

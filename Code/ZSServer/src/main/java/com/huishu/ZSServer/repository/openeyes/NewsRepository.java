package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.News;

public interface NewsRepository extends CrudRepository<News, String>{

	List<News> findByCompanyName(String cname);

}

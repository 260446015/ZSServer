package com.huishu.ManageServer.repository.third;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 */
@TargetDataSource(name="third")
@Repository
public interface ThesaurusRepository  extends JpaRepository<ThesaurusEntity,Long>,JpaSpecificationExecutor<ThesaurusEntity> {

	/**
	 * 根据类型查看关键词信息
	 * @param type
	 * @param pageNumber
	 * @return
	 */
	Page<ThesaurusEntity> findByType(String type, Pageable page);


}

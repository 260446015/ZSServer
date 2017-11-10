package com.huishu.ZSServer.repository.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.FilePdf;

/**
 * PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
@Repository
public interface FilePdfRepository extends CrudRepository<FilePdf, Long> {
	Page<FilePdf> findByDimension(String dimension,Pageable page);

}

package com.huishu.ait.repository.expertOpinionDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.FilePdf;

/**
 * PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
public interface FilePdfRepository extends CrudRepository<FilePdf, Long> {
	Page<FilePdf> findByDimension(String dimension,Pageable page);

}

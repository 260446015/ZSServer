package com.huishu.ZSServer.repository.report;

import com.huishu.ZSServer.entity.report.FilePdf;
import com.huishu.ZSServer.entity.report.FilePdfImg;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
@Repository
public interface FilePdfImgRepository extends CrudRepository<FilePdfImg, Long> {

	/**
	 * 查看该pdf的图片列表
	 * @param pdfId
	 * @return
	 */
	List<FilePdfImg> findByPdfId(Long pdfId);
}

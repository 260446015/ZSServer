package com.huishu.ZSServer.repository.report;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.report.FilePdf;

/**
 * PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
@Repository
public interface FilePdfRepository extends CrudRepository<FilePdf, Long> {
	
	/**
	 * 查询该类型PDF的数量
	 * @param fileType
	 * @param data
	 * @return
	 */
	@Query(value="select count(1) from t_file_pdf WHERE file_type=?1 and date_format(data,'%Y')=?2", nativeQuery = true)
	Integer findExpertReportCount(String fileType,String data);
	
	/**
	 * 分页查询PDF列表
	 * @param fileType
	 * @param data
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value="select * from t_file_pdf WHERE file_type=?1 and date_format(data,'%Y')=?2 order by create_time desc limit ?3,?4", nativeQuery = true)
	List<FilePdf> findExpertReport(String fileType,String data,Integer pageFrom,Integer pageSize);
}

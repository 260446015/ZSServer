package com.huishu.ZSServer.repository.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.FilePdf;
import com.huishu.ZSServer.entity.FilePdfDownload;

/**
 * 下载的PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
@Repository
public interface FileDownloadRepository extends CrudRepository<FilePdfDownload, Long> {
	List<FilePdfDownload> findByUserId(Long userId);
	
	/**
	 * 查询该类型PDF的数量
	 * @param dimension
	 * @param fileType
	 * @param data
	 * @return
	 */
	@Query(value="select count(1) from t_file_download WHERE dimension=?1 and file_type=?2 and date_format(data,'%Y')=?3", nativeQuery = true)
	Integer findExpertReportCount(String dimension,String fileType,String data);
	
	/**
	 * 分页查询PDF列表
	 * @param dimension
	 * @param fileType
	 * @param data
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value="select * from t_file_download WHERE dimension=?1 and file_type=?2 and date_format(data,'%Y')=?3 order by create_time desc limit ?4,?5", nativeQuery = true)
	ArrayList<FilePdf> findExpertReport(String dimension,String fileType,String data,Integer pageFrom,Integer pageSize);
}

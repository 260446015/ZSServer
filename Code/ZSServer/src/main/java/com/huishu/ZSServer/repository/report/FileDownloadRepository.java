package com.huishu.ZSServer.repository.report;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
	
	FilePdfDownload findByUserIdAndFileId(Long userId,Long fileId);
	
	/**
	 * 查询该类型PDF的数量
	 * @param fileType
	 * @param data
	 * @return
	 */
	@Query(value="select count(1) from t_file_download WHERE user_id=?1 and file_type=?2 and date_format(file_data,'%Y')=?3", nativeQuery = true)
	Integer findExpertReportCount(Long userId,String fileType,String data);
	
	/**
	 * 分页查询PDF列表
	 * @param fileType
	 * @param data
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value="select p.id,p.label,p.name,p.data from t_file_download d left join t_file_pdf p on d.file_id=p.id WHERE d.user_id=?1 and p.file_type=?2 and date_format(p.data,'%Y')=?3 order by p.create_time desc limit ?4,?5", nativeQuery = true)
	List<Object[]> findExpertReport(Long userId,String fileType,String data,Integer pageFrom,Integer pageSize);
}

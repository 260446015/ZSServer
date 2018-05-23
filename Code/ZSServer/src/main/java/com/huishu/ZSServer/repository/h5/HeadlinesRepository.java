package com.huishu.ZSServer.repository.h5;


import com.huishu.ZSServer.entity.h5.Headlines;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 大标题
 * @author yindq
 * @date 2018/2/7
 */
@Repository
public interface HeadlinesRepository extends CrudRepository<Headlines, Long> {
	/**
	 * 查找报告二级标题
	 * @param reportId
	 * @param parentName
	 * @return
	 */
	List<Headlines> findByReportIdAndParentNameOrderBySort(Long reportId, String parentName);

	/**
	 * 获取标题信息
	 * @param reportId
	 * @param name
	 * @return
	 */
	Headlines findByReportIdAndName(Long reportId, String name);
	void deleteByReportId(Long reportId);
}

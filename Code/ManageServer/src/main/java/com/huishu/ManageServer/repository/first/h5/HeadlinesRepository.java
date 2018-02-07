package com.huishu.ManageServer.repository.first.h5;

import com.huishu.ManageServer.entity.dbFirst.h5.Headlines;
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
	List<Headlines> findByReportIdAndParentIdOrderBySort(Long reportId,Long parentId);
	Headlines findByReportIdAndName(Long reportId,String name);
}

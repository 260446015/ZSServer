package com.huishu.ManageServer.repository.first.h5;

import com.huishu.ManageServer.entity.dbFirst.h5.Headlines;
import com.huishu.ManageServer.entity.dbFirst.h5.Paragraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 段落
 * @author yindq
 * @date 2018/2/7
 */
@Repository
public interface ParagraphRepository extends CrudRepository<Paragraph, Long> {
	List<Paragraph> findByHeadlinesIdOrderBySort(Long headlinesId);
}

package com.huishu.ZSServer.repository.h5;


import com.huishu.ZSServer.entity.h5.Paragraph;
import org.springframework.data.jpa.repository.Query;
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
	List<Paragraph> findByHeadlinesId(Long headlinesId);
	List<Paragraph> findByHeadlinesIdAndReportId(Long headlinesId, Long reportId);
	List<Paragraph> findByHeadlinesIdAndReportIdAndKeyWord(Long headlinesId, Long reportId, String keyWord);
	@Query(value="select key_word from t_html_paragraph where headlines_id=?1 group by key_word",nativeQuery=true)
	List<String> findByHeadlinesIdGroupByKeyWord(Long headlinesId);
	@Query(value="select text from t_html_paragraph where headlines_id=?1 and key_word = ?2",nativeQuery=true)
	List<String> findByHeadlinesIdAndKeyWord(Long headlinesId, String keyWord);
	void deleteByReportId(Long reportId);
	void deleteByReportIdAndHeadlinesId(Long reportId, Long headlinesId);
}

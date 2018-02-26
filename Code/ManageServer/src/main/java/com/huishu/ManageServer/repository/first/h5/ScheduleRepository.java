package com.huishu.ManageServer.repository.first.h5;

import com.huishu.ManageServer.entity.dbFirst.h5.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会议日程
 * @author yindq
 * @date 2018/2/26
 */
@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
	List<Schedule> findByParagraphId(Long paragraphId);

}

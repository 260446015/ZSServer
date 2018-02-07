package com.huishu.ManageServer.repository.first.h5;

import com.huishu.ManageServer.entity.dbFirst.h5.MonthlyReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 月度报告
 * @author yindq
 * @date 2018/2/7
 */
@Repository
public interface MonthlyReportRepository extends CrudRepository<MonthlyReport, Long> {
}

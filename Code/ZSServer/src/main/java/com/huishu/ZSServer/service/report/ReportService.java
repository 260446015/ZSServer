package com.huishu.ZSServer.service.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.huishu.ZSServer.entity.FilePdf;

public interface ReportService {
	/**
	 * 查询招商报告PDF列表
	 * @param request
	 * @return
	 */
	Page<FilePdf> getExpertReport(PageRequest request);
}

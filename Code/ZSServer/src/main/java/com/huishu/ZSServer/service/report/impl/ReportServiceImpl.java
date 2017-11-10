package com.huishu.ZSServer.service.report.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.entity.FilePdf;
import com.huishu.ZSServer.repository.report.FilePdfRepository;
import com.huishu.ZSServer.service.report.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private FilePdfRepository filePdfRepository;
	
	@Override
	public Page<FilePdf> getExpertReport(PageRequest request) {
		return filePdfRepository.findByDimension("招商报告",request);
	}

}

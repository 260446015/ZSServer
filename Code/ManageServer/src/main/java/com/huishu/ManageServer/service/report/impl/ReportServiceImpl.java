package com.huishu.ManageServer.service.report.impl;

import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.repository.first.FilePdfImgRepository;
import com.huishu.ManageServer.repository.first.FilePdfRepository;
import com.huishu.ManageServer.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private FilePdfRepository filePdfRepository;
	@Autowired
	private FilePdfImgRepository filePdfImgRepository;
	
	@Override
	public Page<FilePdf> getExpertReport(AbstractDTO dto) {
		Pageable pageable = new PageRequest(dto.getPageNum(), dto.getPageSize(), Sort.Direction.DESC, "createTime");
		return filePdfRepository.findAll(null,pageable);
	}

	@Override
	public Boolean dropReport(Long id) {
		filePdfRepository.delete(id);
		return true;
	}

	@Override
	public Boolean saveReport(FilePdf dto) {
		FilePdf one = filePdfRepository.findOne(dto.getId());
		one.setData(dto.getData());
		one.setFileType(dto.getFileType());
		one.setLabel(dto.getLabel());
		one.setName(dto.getName());
		one.setUrl(dto.getUrl());
		FilePdf save = filePdfRepository.save(one);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public FilePdf getReportById(Long id) {
		return filePdfRepository.findOne(id);
	}

}

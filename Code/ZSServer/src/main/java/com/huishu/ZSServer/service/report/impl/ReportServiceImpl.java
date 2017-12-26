package com.huishu.ZSServer.service.report.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.PageConstant;
import com.huishu.ZSServer.entity.FilePdf;
import com.huishu.ZSServer.entity.FilePdfDownload;
import com.huishu.ZSServer.entity.dto.ReportSearchDTO;
import com.huishu.ZSServer.repository.report.FileDownloadRepository;
import com.huishu.ZSServer.repository.report.FilePdfRepository;
import com.huishu.ZSServer.service.report.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	private static final Logger LOGGER = Logger.getLogger(ReportServiceImpl.class);

	@Autowired
	private FilePdfRepository filePdfRepository;
	@Autowired
	private FileDownloadRepository fileDownloadRepository;
	
	@Override
	public Set<Integer> getScreeningItem() {
		Iterable<FilePdf> all = filePdfRepository.findAll();
		Set<Integer> year = new TreeSet<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (FilePdf filePdf : all) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
				Date date = sdf.parse(filePdf.getData());
				calendar.setTime(date);
				year.add(calendar.get(Calendar.YEAR));
			} catch (Exception e) {
				LOGGER.error(filePdf.getData()+"转化日期失败", e);
			}
		}
		return year;
	}
	
	@Override
	public Page<FilePdf> getExpertReport(ReportSearchDTO dto) {
		List<FilePdf> list = filePdfRepository.findExpertReport(dto.getType(), dto.getYear(), dto.getPageNumber()*PageConstant.EIGHT,PageConstant.EIGHT);
		Integer count = filePdfRepository.findExpertReportCount(dto.getType(), dto.getYear());
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), PageConstant.EIGHT,new Sort(Direction.DESC, "createTime"));
		Page<FilePdf> impl = new PageImpl<FilePdf>(list,pageRequest,count);
		return impl;
	}

	@Override
	public Set<Integer> getUserScreeningItem(Long userId) {
		List<FilePdfDownload> list = fileDownloadRepository.findByUserId(userId);
		Set<Integer> year = new TreeSet<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (FilePdfDownload filePdf : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
				Date date = sdf.parse(filePdf.getFileData());
				calendar.setTime(date);
				year.add(calendar.get(Calendar.YEAR));
			} catch (Exception e) {
				LOGGER.error(filePdf.getFileData()+"转化日期失败", e);
			}
		}
		return year;
	}

	@Override
	public Page<FilePdf> getUserExpertReport(Long userId,ReportSearchDTO dto) {
		List<Object[]> list = fileDownloadRepository.findExpertReport(userId, dto.getType(), dto.getYear(), dto.getPageNumber()*PageConstant.EIGHT,PageConstant.EIGHT);
		List<FilePdf> result = new ArrayList<FilePdf>();
		for (Object[] objects : list) {
			FilePdf pdf = new FilePdf();
			pdf.setId(Long.valueOf(objects[0].toString()));
			pdf.setLabel(objects[1].toString());
			pdf.setName(objects[2].toString());
			pdf.setData(objects[3].toString());
			result.add(pdf);
		}
		Integer count = fileDownloadRepository.findExpertReportCount(userId, dto.getType(), dto.getYear());
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), PageConstant.EIGHT,new Sort(Direction.DESC, "createTime"));
		Page<FilePdf> impl = new PageImpl<FilePdf>(result,pageRequest,count);
		return impl;
	}

	@Override
	public FilePdf getReportContent(Long id) {
		return filePdfRepository.findOne(id);
	}

}

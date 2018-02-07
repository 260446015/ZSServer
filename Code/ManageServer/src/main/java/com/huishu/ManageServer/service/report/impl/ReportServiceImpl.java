package com.huishu.ManageServer.service.report.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dbFirst.h5.Headlines;
import com.huishu.ManageServer.entity.dbFirst.h5.MonthlyReport;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.HtmlAddDTO;
import com.huishu.ManageServer.repository.first.FilePdfRepository;
import com.huishu.ManageServer.repository.first.h5.HeadlinesRepository;
import com.huishu.ManageServer.repository.first.h5.MonthlyReportRepository;
import com.huishu.ManageServer.repository.first.h5.ParagraphRepository;
import com.huishu.ManageServer.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private FilePdfRepository filePdfRepository;
	@Autowired
	private HeadlinesRepository headlinesRepository;
	@Autowired
	private ParagraphRepository paragraphRepository;
	@Autowired
	private MonthlyReportRepository monthlyReportRepository;
	
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

	@Override
	public Object getHtmlData(Long id,String type) {
		if(type.equals("首页")){
			return monthlyReportRepository.findOne(id);
		}else if(type.equals("目录")){
			return headlinesRepository.findByReportIdAndParentIdOrderBySort(id,null);
		}else{
			Headlines headlines = headlinesRepository.findByReportIdAndName(id, type);
			List<Headlines> list = headlinesRepository.findByReportIdAndParentIdOrderBySort(null, headlines.getId());
			if(list.size()==0){
				return paragraphRepository.findByHeadlinesIdOrderBySort(headlines.getId());
			}else{
				return list;
			}
		}
	}

	@Override
	public Boolean addHtmlData(HtmlAddDTO dto) {
		MonthlyReport report = new MonthlyReport();
		report.setName(dto.getName());
		report.setTime(dto.getTime());
		MonthlyReport save = monthlyReportRepository.save(report);
		if(save==null){
			return false;
		}
		JSONObject[] arr = dto.getArr();
		for (int i = 0; i < arr.length; i++) {
			Headlines headlines = new Headlines();
			headlines.setLogoClass(arr[i].getString("logoClass"));
			headlines.setName(arr[i].getString("name"));
			headlines.setReportId(save.getId());
			headlines.setSort(arr[i].getInteger("sort"));
			headlinesRepository.save(headlines);
		}
		JSONObject[] arr2 = dto.getArr2();
		for (int i = 0; i < arr2.length; i++) {
			Headlines parent = headlinesRepository.findByReportIdAndName(save.getId(), arr2[i].getString("parentId"));
			if (parent!=null){
				Headlines headlines = new Headlines();
				headlines.setLogoClass(arr[i].getString("logoClass"));
				headlines.setName(arr[i].getString("name"));
				headlines.setReportId(save.getId());
				headlines.setSort(arr[i].getInteger("sort"));
				headlines.setParentId(parent.getId());
				headlinesRepository.save(headlines);
			}
		}
		return true;
	}

}

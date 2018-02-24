package com.huishu.ManageServer.service.report.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dbFirst.h5.Headlines;
import com.huishu.ManageServer.entity.dbFirst.h5.MonthlyReport;
import com.huishu.ManageServer.entity.dbFirst.h5.Paragraph;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.HtmlAddDTO;
import com.huishu.ManageServer.entity.dto.ParagraphAddDTO;
import com.huishu.ManageServer.entity.vo.HeadlinesVO;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		if(StringUtil.isEmpty(type)){
			return monthlyReportRepository.findOne(id);
		}else if(type.equals("focus")||type.equals("dynamic")){
			return headlinesRepository.findByReportIdAndParentNameOrderBySort(id,type);
		}else if(type.equals("keyWord")||type.equals("chain")||type.equals("recommend")||type.equals("industry")){
			Headlines headlines = headlinesRepository.findByReportIdAndName(0L, type);
			return paragraphRepository.findByHeadlinesIdOrderBySort(headlines.getId());
		}else{
			return null;
		}
	}

	@Override
	public JSONObject getHtmlData(Long id) {
		/*JSONObject object = new JSONObject();
		object.put("info",monthlyReportRepository.findOne(id));
		List<HeadlinesVO> list = findHtmlHeadlines(id);
		JSONArray array = new JSONArray();
		for (HeadlinesVO vo:list){
			JSONObject obj = new JSONObject();
			obj.put("id",vo.getId());
			obj.put("name",vo.getName());
			if(vo.getChildren()==null||vo.getChildren().size()==0){
				obj.put("level",1);
				obj.put("content",paragraphRepository.findByHeadlinesIdOrderBySort(vo.getId()));
			}else {
				obj.put("level",2);
				List<Headlines> headlinesList = headlinesRepository.findByReportIdAndParentIdOrderBySort(id, vo.getId());
				JSONArray jsonArray = new JSONArray();
				for (Headlines headlines:headlinesList){
					JSONObject child = new JSONObject();
					child.put("id",headlines.getId());
					child.put("name",headlines.getName());
					child.put("content",paragraphRepository.findByHeadlinesIdOrderBySort(headlines.getId()));
					jsonArray.add(child);
				}
				obj.put("children",jsonArray);
			}
			array.add(obj);
		}
		object.put("arr",array);
		return object;*/
		return null;
	}

	@Override
	public Page<MonthlyReport> getHtmlReport(AbstractDTO dto) {
		Pageable pageable = new PageRequest(dto.getPageNum(), dto.getPageSize(), Sort.Direction.DESC, "createTime");
		return monthlyReportRepository.findAll(null,pageable);
	}

	@Override
	public Long addHtmlData(HtmlAddDTO dto) {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MonthlyReport report = new MonthlyReport();
		report.setName(dto.getName());
		report.setTime(dto.getTime());
		report.setCreateTime(sdf.format(new Date()));
		MonthlyReport save = monthlyReportRepository.save(report);
		if(save==null){
			return null;
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
				headlinesRepository.save(headlines);
			}
		}
		return save.getId();*/
		return null;
	}

	@Override
	public Boolean addParagraphData(ParagraphAddDTO dto) {
		Object[] obj = dto.getObj();
		for (Object object:obj){
			HashMap map=(HashMap)object;
			Paragraph paragraph = new Paragraph();
			paragraph.setHeadlinesId(Long.valueOf(map.get("id").toString()));
			paragraph.setText(map.get("text").toString());
			paragraph.setSort(1);
			paragraphRepository.save(paragraph);
		}
		return true;
	}

	@Override
	public List<HeadlinesVO> findHtmlHeadlines(Long id) {
		/*List<HeadlinesVO> result = new ArrayList<>();
		List<Headlines> list = headlinesRepository.findByReportIdAndParentIdOrderBySort(id,0L);
		for (Headlines headlines:list){
			HeadlinesVO vo = new HeadlinesVO();
			vo.setId(headlines.getId());
			vo.setName(headlines.getName());
			result.add(vo);
		}
		for (HeadlinesVO vo:result){
			List<Headlines> children = headlinesRepository.findByReportIdAndParentIdOrderBySort(id, vo.getId());
			vo.setChildren(children);
		}
		return result;*/
		return null;
	}

}

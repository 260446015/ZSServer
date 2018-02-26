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
import com.huishu.ManageServer.repository.first.h5.ScheduleRepository;
import com.huishu.ManageServer.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
	@Autowired
	private ScheduleRepository scheduleRepository;

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
		}else if(type.equals("chain")||type.equals("keyWord")){
			Headlines headlines = headlinesRepository.findByReportIdAndName(0L, type);
			List<Paragraph> list = paragraphRepository.findByHeadlinesIdAndReportId(headlines.getId(),id);
			JSONArray array = new JSONArray();
			list.forEach(paragraph -> {
				JSONObject object = new JSONObject();
				object.put("text",paragraph.getText());
				String keyWord = paragraph.getKeyWord();
				String[] split = keyWord.split("、");
				object.put("keyWord",split);
				array.add(object);
			});
			return array;
		}else if(type.equals("recommend")){
			Headlines headlines = headlinesRepository.findByReportIdAndName(0L, type);
			List<Paragraph> list = paragraphRepository.findByHeadlinesIdAndReportId(headlines.getId(),id);
			JSONObject object = new JSONObject();
			JSONArray array = new JSONArray();
			list.forEach(paragraph -> {
				if(paragraph.getKeyWord().equals("1")){
					JSONObject obj = new JSONObject();
					obj.put("name",paragraph.getCompany());
					obj.put("reason",paragraph.getText());
					if(StringUtil.isEmpty(paragraph.getImg())){
						obj.put("logo","/images/company.png");
					}else{
						obj.put("logo",paragraph.getImg());
					}
					array.add(obj);
				}else{
					JSONObject obj = new JSONObject();
					obj.put("name", paragraph.getPeople());
					obj.put("identity", paragraph.getCompany());
					obj.put("reason", paragraph.getText());
					obj.put("logo",paragraph.getImg());
					object.put("people", obj);
				}
			});
			object.put("company", array);
			return object;
		}else if(type.equals("industry")){
			Headlines headlines = headlinesRepository.findByReportIdAndName(0L, type);
			List<Paragraph> faucet = paragraphRepository.findByHeadlinesIdAndReportIdAndKeyWord(headlines.getId(),id,"faucet");
			List<Paragraph> growth = paragraphRepository.findByHeadlinesIdAndReportIdAndKeyWord(headlines.getId(),id,"growth");
			List<Paragraph> potential = paragraphRepository.findByHeadlinesIdAndReportIdAndKeyWord(headlines.getId(),id,"potential");
			JSONObject obj = new JSONObject();
			obj.put("faucet",faucet);
			obj.put("growth",growth);
			obj.put("potential",potential);
			return obj;
		}else{
			Headlines one = headlinesRepository.findOne(Long.valueOf(type));
			if("各地新闻".equals(one.getName())){
				List<Object[]> area = paragraphRepository.findByHeadlinesIdGroupByKeyWord(Long.valueOf(type));
				JSONArray array = new JSONArray();
				area.forEach(city ->{
					JSONObject object=new JSONObject();
					object.put("area",city[0]);
					Object[] myList = {city[1],city[2]};
					object.put("coordinate",myList);
					object.put("data",paragraphRepository.findByHeadlinesIdAndKeyWord(Long.valueOf(type),city[0].toString()));
					array.add(object);
				});
				return array;
			}else if("会议日程".equals(one.getName())){
				Paragraph paragraph = paragraphRepository.findByHeadlinesId(Long.valueOf(type)).get(0);
				JSONObject obj = new JSONObject();
				obj.put("place",paragraph.getTime());
				obj.put("total",paragraph.getMoney());
				obj.put("industry",paragraph.getText());
				obj.put("advise",paragraph.getKeyWord());
				obj.put("schedule",scheduleRepository.findByParagraphId(paragraph.getId()));
				return obj;
			}else if("投融速递".equals(one.getName())){
				List<Paragraph> paragraph = paragraphRepository.findByHeadlinesId(Long.valueOf(type));
				JSONArray array = new JSONArray();
				paragraph.forEach(para -> {
					JSONObject obj = new JSONObject();
					obj.put("industry",para.getKeyWord());
					obj.put("money",para.getMoney());
					String[] split = para.getText().split("、");
					obj.put("array",split);
					array.add(obj);
				});
				return array;
			}else {
				return paragraphRepository.findByHeadlinesId(Long.valueOf(type));
			}
		}
	}

	@Override
	public JSONObject getHtmlData(Long id) {
		JSONObject object = new JSONObject();
		object.put("info",getHtmlData(id,null));
		object.put("focus",getHtmlData(id,"focus"));
		object.put("dynamic",getHtmlData(id,"dynamic"));
		object.put("chain",getHtmlData(id,"chain"));
		object.put("keyWord",getHtmlData(id,"keyWord"));
		object.put("recommend",getHtmlData(id,"recommend"));
		object.put("industry",getHtmlData(id,"industry"));
		return object;
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

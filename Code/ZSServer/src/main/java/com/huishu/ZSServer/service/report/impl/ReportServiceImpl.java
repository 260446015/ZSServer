package com.huishu.ZSServer.service.report.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.transaction.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.h5.Headlines;
import com.huishu.ZSServer.entity.h5.Paragraph;
import com.huishu.ZSServer.entity.h5.Schedule;
import com.huishu.ZSServer.entity.report.FilePdfImg;
import com.huishu.ZSServer.entity.vo.PdfVO;
import com.huishu.ZSServer.repository.h5.HeadlinesRepository;
import com.huishu.ZSServer.repository.h5.MonthlyReportRepository;
import com.huishu.ZSServer.repository.h5.ParagraphRepository;
import com.huishu.ZSServer.repository.h5.ScheduleRepository;
import com.huishu.ZSServer.repository.report.FilePdfImgRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.PageConstant;
import com.huishu.ZSServer.entity.report.FilePdf;
import com.huishu.ZSServer.entity.report.FilePdfDownload;
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
	private FilePdfImgRepository filePdfImgRepository;
	@Autowired
	private FileDownloadRepository fileDownloadRepository;
	@Autowired
	private HeadlinesRepository headlinesRepository;
	@Autowired
	private ParagraphRepository paragraphRepository;
	@Autowired
	private MonthlyReportRepository monthlyReportRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	
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
	public PdfVO getReportContent(Long id) {
		FilePdf one = filePdfRepository.findOne(id);
		List<FilePdfImg> imgs = filePdfImgRepository.findByPdfId(id);
		PdfVO vo = new PdfVO();
		vo.setCreateTime(one.getCreateTime());
		vo.setData(one.getData());
		vo.setDownloads(one.getDownloads());
		vo.setImageUrl(imgs);
		vo.setId(one.getId());
		vo.setLabel(one.getLabel());
		vo.setName(one.getName());
		vo.setUrl(one.getUrl());
		return vo;
	}

	@Transactional
	@Override
	public void addReportRecord(Long userId,Long id) {
		FilePdf one = filePdfRepository.findOne(id);
		one.setDownloads(one.getDownloads()+1);
		filePdfRepository.save(one);
		FilePdfDownload download = fileDownloadRepository.findByUserIdAndFileId(userId, id);
		if(download==null){
			download = new FilePdfDownload();
			download.setDownloads(1);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			download.setDownloadTime(format.format(new Date()));
			download.setFileData(one.getData());
			download.setFileId(id);
			download.setFileType(one.getFileType());
			download.setUserId(userId);
		}else{
			download.setDownloads(download.getDownloads()+1);
		}
		fileDownloadRepository.save(download);
	}

	@Override
	public Object getHtmlData(Long id, String type) {
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
				object.put("text",paragraph.getText().replaceAll("<br/>","\n"));
				object.put("id",paragraph.getId());
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
					obj.put("id", paragraph.getId());
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
					obj.put("id", paragraph.getId());
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
				List<String> area = paragraphRepository.findByHeadlinesIdGroupByKeyWord(Long.valueOf(type));
				JSONArray array = new JSONArray();
				area.forEach(city ->{
					JSONObject object=new JSONObject();
					object.put("area",city);
					object.put("data",paragraphRepository.findByHeadlinesIdAndKeyWord(Long.valueOf(type),city));
					array.add(object);
				});
				return array;
			}else if("会议日程".equals(one.getName())){
				Paragraph paragraph = paragraphRepository.findByHeadlinesId(Long.valueOf(type)).get(0);
				List<Schedule> list = scheduleRepository.findByParagraphId(paragraph.getId());
				HashMap<Integer, List<Schedule>> map = new HashMap<>();
				for (Schedule schedule:list) {
					if(map.get(schedule.getDate())==null){
						List<Schedule> value = new ArrayList<Schedule>();
						value.add(schedule);
						map.put(schedule.getDate(),value);
					}else{
						List<Schedule> value=map.get(schedule.getDate());
						value.add(schedule);
					}
				}
				JSONObject obj = new JSONObject();
				obj.put("id", paragraph.getId());
				obj.put("place",paragraph.getTime());
				obj.put("total",paragraph.getMoney());
				obj.put("industry",paragraph.getText());
				obj.put("advise",paragraph.getKeyWord());
				obj.put("schedule",map);
				return obj;
			}else if("投融速递".equals(one.getName())){
				List<Paragraph> paragraph = paragraphRepository.findByHeadlinesId(Long.valueOf(type));
				JSONArray array = new JSONArray();
				paragraph.forEach(para -> {
					JSONObject obj = new JSONObject();
					obj.put("id", para.getId());
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

}

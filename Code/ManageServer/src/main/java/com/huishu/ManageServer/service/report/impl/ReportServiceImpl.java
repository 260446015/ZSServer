package com.huishu.ManageServer.service.report.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.conf.HtmlConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import com.huishu.ManageServer.entity.dbFirst.h5.Headlines;
import com.huishu.ManageServer.entity.dbFirst.h5.MonthlyReport;
import com.huishu.ManageServer.entity.dbFirst.h5.Paragraph;
import com.huishu.ManageServer.entity.dbFirst.h5.Schedule;
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
import org.springframework.transaction.annotation.Transactional;

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
	public JSONArray getInfoData(Long id, String type) {
		JSONArray array = new JSONArray();
		List<Headlines> list = headlinesRepository.findByReportIdAndParentNameOrderBySort(id, type);
		list.forEach(headlines -> {
			JSONObject object = new JSONObject();
			object.put("name",headlines.getName());
			object.put("value",getHtmlData(id,headlines.getId().toString()));
			array.add(object);
		});
		return array;
	}

	@Override
	public Page<MonthlyReport> getHtmlReport(AbstractDTO dto) {
		Pageable pageable = new PageRequest(dto.getPageNum(), dto.getPageSize(), Sort.Direction.DESC, "createTime");
		return monthlyReportRepository.findAll(null,pageable);
	}

	@Override
	@Transactional
	public Boolean dropHtmlData(Long id) {
		paragraphRepository.deleteByReportId(id);
		headlinesRepository.deleteByReportId(id);
		monthlyReportRepository.delete(id);
		return true;
	}

	@Override
	@Transactional
	public Long addHtmlData(HtmlAddDTO dto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MonthlyReport report = new MonthlyReport();
		report.setId(dto.getId());
		report.setName(dto.getName());
		report.setTime(dto.getTime());
		report.setCreateTime(sdf.format(new Date()));
		MonthlyReport save = monthlyReportRepository.save(report);
		if(save==null){
			return null;
		}

		//月关键词
		JSONObject[] keyWord = dto.getKeyWord();
		Headlines keyHeadlines = headlinesRepository.findByReportIdAndName(0L, "keyWord");
		saveTextParagraph(keyWord,save.getId(),keyHeadlines.getId());

		//产业链高亮
		JSONObject[] chain = dto.getChain();
		Headlines chainHeadlines = headlinesRepository.findByReportIdAndName(0L, "chain");
		saveTextParagraph(chain,save.getId(),chainHeadlines.getId());

		//明星推荐
		JSONObject recommend = dto.getRecommend();
		Headlines recommendHeadlines = headlinesRepository.findByReportIdAndName(0L, "recommend");
		JSONArray array = recommend.getJSONArray("company");
		array.forEach(value ->{
			JSONObject company =(JSONObject)value;
			Paragraph paragraph = new Paragraph();
			paragraph.setId(company.getLong("id"));
			paragraph.setCompany(company.getString("name"));
			paragraph.setText(company.getString("reason"));
			paragraph.setImg(company.getString("logo"));
			paragraph.setKeyWord("1");
			paragraph.setReportId(save.getId());
			paragraph.setHeadlinesId(recommendHeadlines.getId());
			paragraphRepository.save(paragraph);
		});
		JSONObject people = recommend.getJSONObject("people");
		Paragraph paragraph = new Paragraph();
		paragraph.setId(people.getLong("id"));
		paragraph.setPeople(people.getString("name"));
		paragraph.setText(people.getString("reason"));
		paragraph.setImg(people.getString("logo"));
		paragraph.setCompany(people.getString("identity"));
		paragraph.setKeyWord("2");
		paragraph.setReportId(save.getId());
		paragraph.setHeadlinesId(recommendHeadlines.getId());
		paragraphRepository.save(paragraph);

		//推荐企业
		JSONObject industry = dto.getIndustry();
		Headlines industryHeadlines = headlinesRepository.findByReportIdAndName(0L, "industry");
		JSONArray faucet = industry.getJSONArray("faucet");
		faucet.forEach(value ->
			saveCompanyParagraph((JSONObject)value,"faucet",save.getId(),industryHeadlines.getId())
		);
		JSONArray growth = industry.getJSONArray("growth");
		growth.forEach(value ->
			saveCompanyParagraph((JSONObject)value,"growth",save.getId(),industryHeadlines.getId())
		);
		JSONArray potential = industry.getJSONArray("potential");
		potential.forEach(value ->
			saveCompanyParagraph((JSONObject)value,"potential",save.getId(),industryHeadlines.getId())
		);

		//本月焦点
		String[] focus = dto.getFocus();
		if(focus!=null){
			for (int i = 0; i < focus.length; i++) {
				Headlines headlines = new Headlines();
				headlines.setSort(i);
				headlines.setReportId(save.getId());
				if(HtmlConstant.ZHENG_CE.equals(focus[i])){
					headlines.setName(HtmlConstant.ZHENG_CE);
					headlines.setLogoClass(HtmlConstant.ZHENG_CE_PNG);
				}else if(HtmlConstant.ZI_BEN.equals(focus[i])){
					headlines.setName(HtmlConstant.ZI_BEN);
					headlines.setLogoClass(HtmlConstant.ZI_BEN_PNG);
				}else if(HtmlConstant.SHI_CHANG.equals(focus[i])){
					headlines.setName(HtmlConstant.SHI_CHANG);
					headlines.setLogoClass(HtmlConstant.SHI_CHANG_PNG);
				}else if(HtmlConstant.YU_LUN.equals(focus[i])){
					headlines.setName(HtmlConstant.YU_LUN);
					headlines.setLogoClass(HtmlConstant.YU_LUN_PNG);
				}else if(HtmlConstant.JI_SHU.equals(focus[i])){
					headlines.setName(HtmlConstant.JI_SHU);
					headlines.setLogoClass(HtmlConstant.JI_SHU_PNG);
				}else if(HtmlConstant.WEI_LAI.equals(focus[i])){
					headlines.setName(HtmlConstant.WEI_LAI);
					headlines.setLogoClass(HtmlConstant.WEI_LAI_PNG);
				}else{
					continue;
				}
				headlines.setParentName(HtmlConstant.FOCUS);
				headlinesRepository.save(headlines);
			}
		}

		//行业动态
		String[] dynamic = dto.getDynamic();
		if(dynamic!=null){
			for (int i = 0; i < dynamic.length; i++) {
				Headlines headlines = new Headlines();
				headlines.setSort(i);
				headlines.setReportId(save.getId());
				if(HtmlConstant.GE_DI.equals(dynamic[i])){
					headlines.setName(HtmlConstant.GE_DI);
					headlines.setLogoClass(HtmlConstant.GE_DI_PNG);
				}else if(HtmlConstant.HE_ZUO.equals(dynamic[i])){
					headlines.setName(HtmlConstant.HE_ZUO);
					headlines.setLogoClass(HtmlConstant.HE_ZUO_PNG);
				}else if(HtmlConstant.QI_YE.equals(dynamic[i])){
					headlines.setName(HtmlConstant.QI_YE);
					headlines.setLogoClass(HtmlConstant.QI_YE_PNG);
				}else if(HtmlConstant.HUI_YI.equals(dynamic[i])){
					headlines.setName(HtmlConstant.HUI_YI);
					headlines.setLogoClass(HtmlConstant.HUI_YI_PNG);
				}else if(HtmlConstant.PAI_HANG.equals(dynamic[i])){
					headlines.setName(HtmlConstant.PAI_HANG);
					headlines.setLogoClass(HtmlConstant.PAI_HANG_PNG);
				}else if(HtmlConstant.TOU_RONG.equals(dynamic[i])){
					headlines.setName(HtmlConstant.TOU_RONG);
					headlines.setLogoClass(HtmlConstant.TOU_RONG_PNG);
				}else{
					continue;
				}
				headlines.setParentName(HtmlConstant.DYNAMIC);
				headlinesRepository.save(headlines);
			}
		}
		return save.getId();
	}

	@Override
	@Transactional
	public Boolean addParagraphData(ParagraphAddDTO dto) {
		JSONObject[] obj = dto.getObj();
		for (JSONObject object:obj){
			paragraphRepository.deleteByReportIdAndHeadlinesId(dto.getId(), object.getLong("headlinesId"));
		}
		for (JSONObject object:obj){
			Paragraph paragraph = new Paragraph();
			paragraph.setId(object.getLong("id"));
			paragraph.setHeadlinesId(object.getLong("headlinesId"));
			paragraph.setText(object.getString("text"));
			paragraph.setKeyWord(object.getString("keyWord"));
			paragraph.setImg(object.getString("img"));
			paragraph.setMoney(object.getString("money"));
			paragraph.setTime(object.getString("time"));
			paragraph.setPeople(object.getString("people"));
			paragraph.setReportId(dto.getId());
			Paragraph save = paragraphRepository.save(paragraph);
			if(object.getJSONArray("schedule")!=null){
				JSONArray array = object.getJSONArray("schedule");
				array.forEach(sch->{
					JSONObject s=(JSONObject)sch;
					Schedule schedule = new Schedule();
					schedule.setParagraphId(save.getId());
					schedule.setDate(s.getInteger("date"));
					schedule.setPlace(s.getString("place"));
					schedule.setSponsor(s.getString("sponsor"));
					scheduleRepository.save(schedule);
				});
			}
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

	private void saveTextParagraph(JSONObject[] objs,Long reportId,Long headlinesId){
		for (int i = 0; i < objs.length; i++) {
			Paragraph paragraph = new Paragraph();
			paragraph.setId(objs[i].getLong("id"));
			paragraph.setKeyWord(objs[i].getString("key"));
			paragraph.setText(objs[i].getString("text"));
			paragraph.setReportId(reportId);
			paragraph.setHeadlinesId(headlinesId);
			paragraphRepository.save(paragraph);
		}
	}

	private void saveCompanyParagraph(JSONObject obj,String keyWord,Long reportId,Long headlinesId){
		Paragraph paragraph = new Paragraph();
		paragraph.setId(obj.getLong("id"));
		paragraph.setCompany(obj.getString("company"));
		paragraph.setTime(obj.getString("time"));
		paragraph.setMoney(obj.getString("money"));
		paragraph.setText(obj.getString("text"));
		paragraph.setKeyWord(keyWord);
		paragraph.setReportId(reportId);
		paragraph.setHeadlinesId(headlinesId);
		paragraphRepository.save(paragraph);
	}
}

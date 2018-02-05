package com.huishu.ManageServer.service.industry.info.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huishu.ManageServer.common.util.ExportExcel;
import org.apache.poi.hssf.usermodel.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.es.entity.AITInfo;
import com.huishu.ManageServer.es.entity.SummitInfo;
import com.huishu.ManageServer.service.industry.info.IndustryInfoService;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;
import com.huishu.ManageServer.es.repository.BaseElasticsearch;
import com.huishu.ManageServer.repository.first.KeyArticleRepository;
import com.huishu.ManageServer.repository.first.KeyWordRepository;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.KeyWordEntity;
import com.huishu.ManageServer.entity.dbFirst.KeywordArticle;
import scala.annotation.meta.field;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hhy
 * @date 2018年1月18日
 * @Parem
 * @return 
 * 产业动态相关service实现类
 */
@Service
public class IndustryInfoServiceImpl extends AbstractService  implements IndustryInfoService {
	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	protected BaseElasticsearch rep;
	
	@Autowired
	private KeyArticleRepository keyRep;
	
	@Autowired
	private KeyWordRepository krep;
	/**
	 * 获取科研成果的数据
	 */
	@Override
	public List<AITInfo> findResearchList(JSONObject json) {
		List<AITInfo>  list= new ArrayList<AITInfo>();
		String ss = json.getString("dimension");
		
		JSONArray arr = json.getJSONArray("industryLabel");
		
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				List<AITInfo> li = getInfo(str,ss);
				if(li.size()>1){
					list.add(li.get(0));
					list.add(li.get(1));
				}
			}
		}
		return list;
	}

	/**
	 * @param str
	 * @param ss
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private List<AITInfo> getInfo(String str, String ss) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(ss)) {
			bq.must(QueryBuilders.termQuery("dimension", ss));
		}
		bq.must(QueryBuilders.termQuery("industryLabel",str ));
		Pageable pageable = new PageRequest(0, 3,new Sort(Direction.DESC, "publishTime"));
		SearchQuery query = getBoolQueryBuilder().withQuery(bq).withPageable(pageable).withSort(SortBuilders.fieldSort("hitCount")).build();
		List<AITInfo> li = template.query(query, res->{
			SearchHits hits = res.getHits();
			List<AITInfo>  list = new ArrayList<AITInfo>();
			if(hits != null){
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit hit : hits) {
					AITInfo info = new AITInfo();
					String industrylabel = hit.getSource().get("industryLabel").toString();
					if(industrylabel.equals("生物医药")){
						info.setIndustryLabel("生物技术");
					}else{
						info.setIndustryLabel(industrylabel);
					}
					
					String title = hit.getSource().get("title").toString();
					info.setArticleLink(hit.getSource().get("articleLink").toString());
					
					info.setTitle(title);
					info.setId(hit.getId());
					try {
						String publishTime = hit.getSource().get("publishTime").toString();
						
						info.setPublishTime(publishTime);
					} catch (Exception e) {

						info.setPublishTime("");
					}
					String content = hit.getSource().get("content").toString();
					List<String> business = null;
					try {
						 business = getBusiness(title,content);
						 info.setBus(business);
					} catch (Exception e) {
						business = getBusiness(title,content);
						info.setBus(business);
					}
					if(content.length()>300){
						String replaceHtml = StringUtil.replaceHtml(content.substring(0, 300));
						info.setContent(replaceHtml);
					}else{
						String replaceHtml = StringUtil.replaceHtml(content.substring(0, content.length()));
						info.setContent(replaceHtml);
					}
					list.add(info);
				}
			}
			return list;
		});
		return li;
	}

	
	/**
	 * 获取产业咨询相关内容
	 */
	@Override
	public Page<AITInfo> getIndustryInfoByPage(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		JSONArray arr = json.getJSONArray("industry");
		BoolQueryBuilder or = new BoolQueryBuilder();
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				or.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		bq.must(or);
		String dimension = json.getString("dimension");
		if(StringUtil.isNotEmpty(dimension)){
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		
		String area = json.getString("area");
		if(StringUtil.isNotEmpty(area)){
			bq.must(QueryBuilders.wildcardQuery("area", "*"+area+"*"));
		}
		String type = json.getString("type");
		Pageable pageable = null;
		Page<AITInfo> page = null;
		if(type.equals("按时间")){
			//按时间排序
			pageable = new PageRequest(json.getIntValue("pageNumber"), json.getIntValue("pageSize"), new Sort(Direction.DESC, "publishTime"));
			page = rep.search(bq, pageable);
		}else{
			pageable = new PageRequest(json.getIntValue("pageNumber"), json.getIntValue("pageSize"),new Sort(Direction.ASC, "hitCount"));
			 page = rep.search(bq, pageable);
		}
		return page;
	}

	
	@Override
	public JSONArray getKeyWordList(JSONObject json) {
		JSONArray data = new JSONArray();
		String time = json.getString("time");
		if(StringUtil.isEmpty(time)){
			return null;
		}
		List<KeyWordEntity> list = krep.getByTime(time);
		list.forEach(action ->{
			JSONObject obj = new JSONObject();
			obj.put("name", action.getKey());
			obj.put("value", action.getValue());
			data.add(obj);
		});
		return data;
	}

	@Override
	public JSONArray findArticleInfo(String time, String keyWord) {
		JSONArray arr = new JSONArray();
		KeyWordEntity entry = 	krep.findByTimeAndKey(time,keyWord);
		 if( entry != null){
			List<KeywordArticle> list = keyRep.findBykid( entry.getId());
			for(int i =0; i<10;i++){
				JSONObject jsonObject = new JSONObject();
				KeywordArticle ent = list.get(i);
				jsonObject.put("id", ent.getId());
				jsonObject.put("aid", ent.getAid());
				jsonObject.put("industryLabel", ent.getIndustryLabel());
				jsonObject.put("title", ent.getTitle());
				jsonObject.put("articleLink", ent.getArticleLink());
				arr.add(jsonObject);
			}
			return arr;
		 }else{
			 return null;
		 }
	}

	
	@Override
	public List<KeywordModel> fiindKeyWordList(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(json.getString("dimension"))) {
			String dimension = json.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		if (StringUtil.isNotEmpty(json.getString("startTime")) && StringUtil.isNotEmpty(json.getString("endTime"))) {
			String startTime = json.getString("startTime");
			String endTime = json.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
		}
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = json.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				or.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		bq.must(or);
		PageRequest pageRequest = new PageRequest(0,1000);
		SearchQuery query = getBoolQueryBuilder().withQuery(bq).withPageable(pageRequest).build();
		List<String> contentList = new ArrayList<String>();
		template.query(query, res -> {
			SearchHits hits = res.getHits();
			if (hits != null) {
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit h : hitsList) {
					if (h.getSource() != null) {
						contentList.add(h.getSource().get("content") + ""+h.getSource().get("title")+"" );
					}
				}
			}
			return "";
		});
	 	JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, 10);
	 	List<KeywordModel> list = null;
	 	if (keywordCloud.getBooleanValue("status")) {
		  list = (List<KeywordModel>) keywordCloud.get("result");
	 	}
		return list;
	}
	@Override
	public JSONArray getArticleListByKeyWord(JSONObject obj) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(obj.getString("dimension"))) {
			String dimension = obj.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		JSONArray arr = obj.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				bq.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		if (StringUtil.isNotEmpty(obj.getString("keyWord"))) {
			String keyWord = obj.getString("keyWord");
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyWord + "*"));
		}
		if (StringUtil.isNotEmpty(obj.getString("startTime")) && StringUtil.isNotEmpty(obj.getString("endTime"))) {
			String startTime = obj.getString("startTime");
			String endTime = obj.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
		}
		TermsBuilder articleLinkBuilder = AggregationBuilders.terms("articleLink").field("articleLink")
				.order(Order.count(false)).size(500);

		SearchQuery authorQuery = getBoolQueryBuilder().withQuery(bq).addAggregation(articleLinkBuilder).build();

		JSONArray array = template.query(authorQuery, res -> {
			JSONArray jsonArray = new JSONArray();
			SearchHits hits = res.getHits();
			for (SearchHit hit : hits) {
				JSONObject jsonObject = new JSONObject();
				Map<String, Object> map = hit.getSource();
				jsonObject.put("id", hit.getId());
				jsonObject.put("industryLabel", map.get("industryLabel").toString());
				jsonObject.put("title", map.get("title").toString());
				jsonObject.put("articleLink", map.get("articleLink").toString());
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		});
		return array;
	}
	/**
	 * 删除产业资讯的文章信息根据id
	 */
	@Override
	public boolean deleteArticleInfoById(String id) {
		try {
			rep.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 产业资讯获取文章接口
	 */
	@Override
	public AITInfo findIndustryInfoById(String id) {
		AITInfo one = rep.findOne(id);
		return one;
	}

	/**
	 * 删除研究成果的数据
	 */
	@Override
	public boolean deleteInfoById(String id) {
		AITInfo info = rep.findOne(id);
		if( info != null){
			try {
				rep.delete(info);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else{
			return false;
		}
	}

	
	@Override
	public boolean saveIndudustryInfo(AITInfo enter) {
		try {
				AITInfo save = rep.save(enter);
			if(save != null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void exportExcel(HttpServletResponse response) {
		//获取一周内的数据
		String[] date = analysisDate("近一周");
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.rangeQuery("publishTime").from(date[0]).to(date[1]));
		bq.must(QueryBuilders.termQuery("dimension","产业头条"));
		SearchQuery query = getBoolQueryBuilder().withQuery(bq).build();
		List<AITInfo> list = template.query(query, res -> {
			List<AITInfo> listInfo = new ArrayList<AITInfo>();
			SearchHits hits = res.getHits();
			for (SearchHit hit : hits) {
				AITInfo info = new AITInfo();
				Map<String, Object> map = hit.getSource();
				info.setId(hit.getId());
				info.setIndustryLabel(map.get("industryLabel").toString());
				info.setIndustry(map.get("industry").toString());
				info.setContent(map.get("content").toString());
				info.setPublishTime(map.get("publishTime").toString());
				info.setArticleLink(map.get("articleLink").toString());
				info.setSourceLink(map.get("sourceLink").toString());
				info.setSummary(map.get("summary").toString());
				info.setTitle(map.get("title").toString());
				info.setArea(map.get("area").toString());
				info.setAuthor(map.get("author").toString());
				info.setVector(map.get("vector").toString());
				info.setVector(map.get("source").toString());
				listInfo.add(info);
			}
			return listInfo;
		});
		String fileName = date[0]+"~"+date[1]+"企业动态数据.xls";
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);// 指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			font.setFontHeight((short) 200);
			cellStyle.setFont(font);

			// 工作表名
			List<String> cellList = new ArrayList<>();
			cellList.add("id");
			cellList.add("summary");
			cellList.add("publishTime");
			cellList.add("articleLink");
			cellList.add("title");
			cellList.add("content");
			cellList.add("author");
			cellList.add("sourceLink");
			cellList.add("source");
			cellList.add("area");
			cellList.add("industry");
			cellList.add("industryLabel");
			cellList.add("vector");

			HSSFSheet sheet = wb.createSheet();
			// 定义第一行
			HSSFRow row1 = sheet.createRow(1);
			HSSFCell cell1 = row1.createCell(0);
			for (int i = 0; i < cellList.size(); i++) {
				cell1 = row1.createCell(i);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(new HSSFRichTextString(cellList.get(i)));
			}
			//定义第二行
			HSSFRow row = sheet.createRow(2);
			HSSFCell cell = row.createCell(1);
			for (int i = 0; i < list.size(); i++) {
				AITInfo info = list.get(i);
				row = sheet.createRow(i + 2);
				for (int j = 0; j < cellList.size(); j++) {
					try {
						Field field = info.getClass().getDeclaredField(cellList.get(j));
						//用以访问私有属性
						field.setAccessible(true);
						cell.setCellValue(new HSSFRichTextString(field.get(info).toString()));
					} catch (Exception e) {
						cell.setCellValue(new HSSFRichTextString(""));
					}
					cell = row.createCell(j);
					cell.setCellStyle(cellStyle);
				}
			}
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			list.clear();
		}
	}

}

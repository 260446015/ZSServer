package com.huishu.ZSServer.service.data.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.huishu.ZSServer.entity.Enterprise;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.repository.company.EnterPriseRepository;
import com.huishu.ZSServer.repository.company.IndusCompanyTestRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.data.UpdateDataService;

/**
 * @author hhy
 * @date 2017年12月25日
 * @Parem
 * @return 数据更新迭代专用service
 */
@Service
public class UpdataServiceImpl extends AbstractService implements UpdateDataService {

	@Autowired
	private EnterPriseRepository ep;
	@Autowired
	private IndusCompanyTestRepository icr;
	
	@Autowired
	protected ElasticsearchTemplate template;
	/**
	 * 更新公司别名
	 */
	@Override
	public boolean updateCompany() {
		List<String> list = ep.findAllGroupByCompany();
		if (list.size() > 0) {
			list.forEach(str -> {
				// 根据公司全称查看公司信息
				List<IndusCompany> li = icr.findByCompany(str);
				if (li.size()==0) {
					IndusCompany indus = new IndusCompany();
					try {
						Enterprise ent = ep.findByCompany(str);
						data(str, indus);
						List<IndusCompany> ll = icr.findByCompanyName(indus.getCompanyName());
						if (ll.size() > 0) {
							data(str, indus);
						}
						indus.setCompany(str);
						indus.setIndustry(ent.getIndustry());
						indus.setIndustryLabel(ent.getIndustryType());
						IndusCompany save = icr.save(indus);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("公司已存在，无需再次入库");
					/*
					 * if(li.size() > 1){ li.forEach(action->{
					 * 
					 * }); }
					 */
				}
			});
			return true;
		}else{
			return false;
		}
	}

	private void data(String str, IndusCompany indus) throws Exception {
		JSONObject json = Analysis.getInitCompanyAbbr(str);
		if (json.getBoolean("status")) {
			Set<String> set = (Set<String>) json.get("result");
			set.forEach(act -> {
				switch (act.length()) {
				case 6:
					indus.setCompanyName(act);
					break;
				case 5:
					indus.setCompanyName(act);
					break;
				case 4:
					indus.setCompanyName(act);
					break;
				case 3:
					indus.setCompanyName(act);
					break;
				case 2:
					indus.setCompanyName(act);
					break;

				}
			});
		}
	}

	/**
	 * 科学研究智能推荐
	 */
	@Override
	public List<AITInfo> findInfo(String str) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.termQuery("industryLabel", str));
		bq.must(QueryBuilders.termQuery("dimension", "科学研究"));
		SearchQuery query = getSearchQueryBuilder().withQuery(bq).withSort(SortBuilders.fieldSort("publishTime")).withSort(SortBuilders.fieldSort("hitCount")).build();
		List<AITInfo> query2 = template.query(query, res->{
			SearchHits hits = res.getHits();
			List<AITInfo>  list = new ArrayList<AITInfo>();
			if(hits != null){
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit hit : hits) {
					AITInfo info = new AITInfo();
					info.setArticleLink(hit.getSource().get("articleLink").toString());
					info.setIndustryLabel(hit.getSource().get("industryLabel").toString());
					info.setTitle(hit.getSource().get("title").toString());
					info.setContent(hit.getSource().get("content").toString());
					list.add(info);
				}
			}
			return list;
		});
		return query2;
	}

	@Override
	public boolean setCompanyInfo() {
		List<IndusCompany> list = icr.getCompanyInfo();
		System.out.println("没有简称的数据有：》》》》》》"+list.size()+"条》》》》》》》》》");
		list.forEach(action->{
//			action.getCompany();
			IndusCompany indus = new IndusCompany();
			 int count =0;
			try {
				data(action.getCompany(), action);
			} catch (Exception e) {
				e.printStackTrace();
				return ;
			}
			try {
//				action.setCompanyName(indus.getCompanyName());
				IndusCompany save = icr.save(action);
				if(save != null){
					count ++;
					System.out.println("更新了"+count+"条数据");
				}
			} catch (Exception e2) {

			}
			
		});
		return true;
	}

}

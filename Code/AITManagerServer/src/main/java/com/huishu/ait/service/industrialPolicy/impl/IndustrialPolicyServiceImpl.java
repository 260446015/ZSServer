package com.huishu.ait.service.industrialPolicy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.DateCheck;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import com.huishu.ait.repository.expertOpinionDetail.UserCollectionRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;

/**
 * 慧数招商--产业--产业政策
 * 
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
@Service
public class IndustrialPolicyServiceImpl extends AbstractService implements IndustrialPolicyService {

	private static Logger log = LoggerFactory.getLogger(IndustrialPolicyServiceImpl.class);

	@Autowired
	private BaseElasticsearch elasticsearch;

	@Resource
	private UserCollectionRepository userCollectionRepository;

	@SuppressWarnings("unused")

	/**
	 * 获取产业政策列表 使用springboot
	 */
	@Override
	public JSONArray getIndustrialPolicyList(IndustrialPolicyDTO dto) {
		JSONArray array = new JSONArray();
		if (dto.getIndustryLabel().equals("不限")) {
			dto.setIndustryLabel(null);
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			PageRequest request = new PageRequest(0, 10, new Sort(Direction.DESC, "publishTime"));
			dto.setDimension("高峰论坛");
			Page<AITInfo> page2 = elasticsearch.search(dto.builderQuery(), request);
			page2.getContent().forEach((data) -> {
				String summary = data.getSummary();
				if (StringUtil.isEmpty(summary)) {
					summary = data.getContent().substring(0, 50);
				}
				summary = StringUtil.replaceHtml(summary);
				data.setSummary(summary);
				String area = data.getArea();
				if(StringUtil.isEmpty(area)){
					List<String> areas = findArea(data.getTitle(), data.getContent());
					area = areas.get(0);
				}
				data.setArea(area);
			});
			// Page<AITInfo> page2 = elasticsearch.search(dto.builderQuery(),
			// dto.builderPageRequest());
			Page<AITInfo> pageBusiness2 = setPageBusiness(page2);
			dto.setDimension("科学研究");
			Page<AITInfo> page3 = elasticsearch.search(dto.builderQuery(), request);
			page3.getContent().forEach((data) -> {
				String summary = data.getSummary();
				if (StringUtil.isEmpty(summary)) {
					summary = data.getContent().substring(0, 50);
				}
				summary = StringUtil.replaceHtml(summary);
				data.setSummary(summary);
				String area = data.getArea();
				if(StringUtil.isEmpty(area)){
					List<String> areas = findArea(data.getTitle(), data.getContent());
					area = areas.get(0);
				}
				data.setArea(area);
			});
			// Page<AITInfo> page3 = elasticsearch.search(dto.builderQuery(),
			// dto.builderPageRequest());
			Page<AITInfo> pageBusiness3 = setPageBusiness(page3);
			map.put("forum", pageBusiness2); // 高峰论坛
			map.put("research", pageBusiness3); // 科学研究

			array.add(map);

			return array;
		} catch (Exception e) {
			log.error("查询产业政策列表失败", e);
			return array;
		}
	}

	/**
	 * 通过ES，根据id获取政策详情 (1),使用ES中ElasticSearchRepository的findOne方法
	 */
	@Override
	public JSONObject getIndustrialPolicyDetail(String id, Long userId) {
		/**
		 * 直接调用ElasticsearchRepository 中的 findOne方法
		 */
		JSONObject obj = new JSONObject();

		AITInfo info = elasticsearch.findOne(id);
		List<String> business = getBusiness(info.getTitle(), info.getContent());
		info.setBus(business);
		obj = (JSONObject) JSONObject.toJSON(info);
		if (null == userCollectionRepository.findByArticleIdAndUserId(id, userId)) {
			obj.put("isCollect", "false");
		} else {
			obj.put("isCollect", "true");
		}
		return obj;
	}

	/**
	 * 获取政策解读数据
	 */
	@Override
	public JSONArray getIndustrialList(IndustrialPolicyDTO dto) {
		JSONArray array = new JSONArray();
		if (dto.getIndustryLabel().equals("不限")) {
			dto.setIndustryLabel(null);
		}
		if (dto.getArea().equals("全部") || dto.getArea().equals("不限")) {
			dto.setArea(null);
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 按文章类型按照维度获取数据 1,政策解读
			dto.setDimension("政策解读");
			Page<AITInfo> page1 = elasticsearch.search(dto.builderQuery(), dto.builderPageRequest());
			Page<AITInfo> pageBusiness1 = setPageBusiness(page1);
			map.put("policy", pageBusiness1); // 政策解读
			array.add(map);

			return array;
		} catch (Exception e) {
			log.error("查询产业政策列表失败", e);
			return array;
		}
	}

	
	/**
	 * 获取产业政策下的文章（政策解读，高峰论坛，科学研究）
	 */
	@Override
	public Page<ArticleListDTO> findArticleList(JSONObject param) {
		String type = param.getString("type");
		if(type.equals("产业政策")){
			param.put("dimension", "政策解读");
		}else{
			param.put("dimension", type);
		}
		param.remove("type");
		String time = param.getString("time");
		param = DateCheck.dateCheck(time, param);
		param.remove("time");
		BoolQueryBuilder bq = getIndustryBuilder(param);
		Page<ArticleListDTO> list = getArtivleList(bq);
		
		return list;
	}

}

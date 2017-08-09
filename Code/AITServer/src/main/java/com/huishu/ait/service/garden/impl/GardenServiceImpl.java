package com.huishu.ait.service.garden.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Garden;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.GardenPolicy;
import com.huishu.ait.es.repository.garden.GardenInformationRepository;
import com.huishu.ait.es.repository.garden.GardenPolicyRepository;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {
	
	@Autowired
	private Client client;
	@Autowired
    private GardenPolicyRepository gardenPolicyRepository;
	@Autowired
    private GardenInformationRepository gardenInformationRepository;
	@Resource
	private GardenRepository gardenRepository;
	@Resource
	private GardenUserRepository gardenUserRepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GardenServiceImpl.class);
	
	@Override
	public JSONArray getGardenPolicyList(AreaSearchDTO searchModel) {
		//组装查询条件
		Map<String,String> map = new HashMap<String,String>();
		map.put("park", searchModel.getPark());
		map.put("articleType", "政策解读");
		//组装排序字段,按时间和点击量降序排列
		 String[] order = {"publishDateTime","hitCount"};
		 List<String> orderList = Arrays.asList(order);
		//组装返回数据字段
		 String[] data = {"title","content"};
		 List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, orderList, dataList);
		return array;
	}
	@Override
	public GardenPolicy getGardenPolicyById(String id) {
		return gardenPolicyRepository.findOne(id);
	}
	@Override
	public JSONArray getGardenInformationList(AreaSearchDTO searchModel) {
		//组装查询条件
		Map<String,String> map = new HashMap<String,String>();
		map.put("park", searchModel.getPark());
		map.put("articleType", "园区情报");
		//组装排序字段,按时间和点击量降序排列
		String[] order = {"publishDateTime","hitCount"};
		List<String> orderList = Arrays.asList(order);
		//组装返回数据字段
		String[] data = {"title","vector"};
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, orderList, dataList);
		return array;
	}
	@Override
	public GardenInformation getGardenInformationById(String id) {
		return gardenInformationRepository.findOne(id);
	}
	@Override
	public JSONArray getGardenBusinessList(AreaSearchDTO searchModel) {
		//组装查询条件
		Map<String,String> map = new HashMap<String,String>();
		map.put("park", searchModel.getPark());
		map.put("dimension", "企业排行");
		//组装排序字段,按点击量降序排列
		String[] order = {"hitCount"};
		List<String> orderList = Arrays.asList(order);
		//组装返回数据字段
		String[] data = {"business"};
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, orderList, dataList);
		return array;
	}
	
	@Override
	public JSONArray findGardensList(GardenDTO dto) {
		String[] msg = dto.getMsg();
		String area = msg[0];
		String industryType = msg[1];
//		String searchName = dto.getSerarchName();
		int pageNum = dto.getPageNum();
		int pageSize = dto.getPageSize();
		int from = pageSize*pageNum - pageSize;
		JSONArray data = new JSONArray();
		Page<Garden> findGardensPage = null;
		try{
			if("不限".equals(area)){
				area = "%%";
			}
			if("不限".equals(industryType)){
				industryType = "%%";
			}
			PageRequest pageRequest = new PageRequest(pageNum, pageSize);
//			if(!StringUtil.isEmpty(searchName)){
//				findGardensPage = gardenRepository.findByNameLike(searchName,pageRequest);
//			}else{//
				findGardensPage = gardenRepository.findByAreaLikeAndIndustryTypeLike(area, industryType, pageRequest);
//			}
			data.add(findGardensPage);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return data;
	}
	
	@Override
	public JSONArray findGardensCondition(GardenDTO dto) {
		JSONArray data = new JSONArray();
		int from = dto.getPageNum()*dto.getPageSize()-dto.getPageSize();
		try{
			List<String> gardenName = gardenUserRepository.findGardensCondition(dto.getUserId());
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termsQuery("park", gardenName));
			SearchResponse response = requestBuilder.setQuery(bq).addSort(SortBuilders.fieldSort("publishDateTime").order(SortOrder.DESC)).setFrom(from+dto.getPageSize()).execute().actionGet();
			System.out.println(requestBuilder);
			SearchHits hits = response.getHits();
			for (SearchHit searchHit : hits) {
				JSONObject obj = new JSONObject();
				JSONObject condition = JSONObject.parseObject(searchHit.getSourceAsString());
				obj.put("id", searchHit.getId());
				obj.put("title", condition.getString("title"));
				obj.put("park", condition.getString("park"));
				obj.put("content", condition.getString("content"));
				data.add(obj);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("园区动态查询结果:"+data.toJSONString());
		return data;
	}
	
    @Override
    public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
        Integer userId = dto.getUserId();
        String area = dto.getArea();
        String industryType = dto.getIndustryType();
        try{
            PageRequest pageRequest = new PageRequest(dto.getPageNum(), dto.getPageSize());
            Page<GardenUser> page = null;
                //对area 和 industryType 没有约束条件，全部查询
            if (dto.getArea().equals("不限") && dto.getIndustryType().equals("不限")) {
                page = gardenUserRepository.findByUserId(userId, pageRequest);
                //对 area 没有约束，根据 id 和 industryType 查询 
            }else if ((!dto.getArea().equals("不限")) && dto.getIndustryType().equals("不限")) {
                page = gardenUserRepository.findByUserIdAndArea(userId, area, pageRequest);
                //对 industryType 没有约束，根据 id 和 area 进行查询
            }else if (dto.getArea().equals("不限") && (!dto.getIndustryType().equals("不限"))) {
                page = gardenUserRepository.findByUserIdAndIndustryType(userId, industryType, pageRequest);
            }else{
                page = gardenUserRepository.findByUserIdAndAreaAndIndustryType(userId, area, industryType, pageRequest);
            }
            return page;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }
	/* 园区情报中获取所有园区内容
	 * @see com.huishu.ait.service.garden.GardenService#findGardensAll()
	 */
	@Override
	public JSONArray findGardensAll() {
		JSONArray data = new JSONArray();
		try{
			Iterable<Garden> findAll = gardenRepository.findAll();
			for (Garden garden : findAll) {
				JSONObject obj = new JSONObject();
				obj.put("id", garden.getId());
				obj.put("area", garden.getArea());
				obj.put("gardenType", garden.getGardenType());
				data.add(obj);
			}
		}catch(Exception e){
			LOGGER.error("获取园区情报中获取所有园区内容失败",e);
		}
		return data;
	}
	/* 此处实现关注园区的功能
	 */
	@Override
	public GardenUser attentionGarden(String gardenId,String userId,boolean flag) {
		Garden garden = gardenRepository.findOne(Integer.parseInt(gardenId));
		
		if(flag){
			GardenUser gu = new GardenUser();
			gu.setGardenName(garden.getName());
			gu.setAddress(garden.getAddress());
			gu.setArea(garden.getArea());
			gu.setAttentionDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis()).toString());
			gu.setDescription(garden.getDescription());
			gu.setUserId(Integer.parseInt(userId));
			gu.setIndustryType(garden.getIndustryType());
			gardenUserRepository.save(gu);
			return gu;
		}else{
			gardenUserRepository.deleteByGardenName(garden.getName());
			return null;
		}
	}
	@Override
	public JSONObject findGardensConditionById(String cid) {
		JSONObject obj = new JSONObject();
		GardenInformation information = gardenInformationRepository.findOne(cid);
		return obj.parseObject(information.toString());
	}
	
}

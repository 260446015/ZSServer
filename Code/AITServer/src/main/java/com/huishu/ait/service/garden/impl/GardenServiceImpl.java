package com.huishu.ait.service.garden.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.ImgConstant;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.IndustryClass;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.repository.GardenEsRepository;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;
import com.huishu.ait.repository.industry_class.IndustryClassRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {

	@Resource
	private GardenRepository gardenRepository;
	@Resource
	private GardenUserRepository gardenUserRepository;
	@Autowired
	private GardenEsRepository gardenEsRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private IndustryClassRepository industryClassRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(GardenServiceImpl.class);

	@Override
	public JSONArray getGardenPolicyList(AreaSearchDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("park", searchModel.getPark());
		map.put("dimension", "园区政策");
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishTime", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "title", "content" };
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, null, orderList, dataList, true);
		return array;
	}

	@Override
	public JSONArray getGardenInformationList(AreaSearchDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("park", searchModel.getPark());
		map.put("dimension", "园区动态");
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishTime", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "title", "vector" };
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, null, orderList, dataList, true);
		return array;
	}

	@Override
	public JSONArray getGardenBusinessList(AreaSearchDTO searchModel) {
		List<Company> list = companyRepository.findByPark(searchModel.getPark());
		JSONArray array = new JSONArray();
		if (list == null || list.size() == 0) {
			return null;
		}
		searchModel.setTotalSize(list.size());
		Integer end = searchModel.getPageFrom() + searchModel.getPageSize();
		for (int i = searchModel.getPageFrom(); i < (list.size() > end ? end : list.size()); i++) {
			JSONObject obj = new JSONObject();
			obj.put("business", list.get(i).getCompanyName());
			array.add(obj);
		}
		return array;
	}

	@Override
	public JSONArray findGardensList(GardenDTO dto) {
		String[] msg = dto.getMsg();
		String area = msg[0];
		String industryType = msg[1];
		// String searchName = dto.getSerarchName();
		int pageNum = dto.getPageNumber();
		int pageSize = dto.getPageSize();
		JSONArray data = new JSONArray();
		try {
			if ("不限".equals(area)) {
				area = "%%";
			} else {
				area = "%" + area + "%";
			}
			if ("不限".equals(industryType)) {
				industryType = "%%";
			} else {
				industryType = "%" + industryType + "%";
			}
			PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize);
			List<GardenData> list = gardenRepository.findByAreaLikeAndIndustryLike(area, industryType);
			list.forEach(GardenData ->{
				String gardenIntroduce = GardenData.getGardenIntroduce();
				String gardenSuperiority = GardenData.getGardenSuperiority();
				String address = GardenData.getAddress();
				String picture = GardenData.getGardenPicture();
				if (gardenIntroduce == null || StringUtil.isEmpty(gardenIntroduce) || gardenIntroduce.equals("NULL")) {
					if (gardenSuperiority == null || StringUtil.isEmpty(gardenSuperiority)
							|| gardenSuperiority.equals("NULL")) {
						GardenData.setGardenIntroduce("暂无");
					} else {
						GardenData.setGardenIntroduce(gardenSuperiority);
					}
				}
				if (address == null || StringUtil.isEmpty(address) || address.equals("NULL")) {
					GardenData.setAddress("暂无");
				}
				if (picture == null || StringUtil.isEmpty(picture) || picture.equals("NULL")) {
					GardenData.setGardenPicture(ImgConstant.IP_PORT + "park_img/default.jpg");
				}
			});
			list.stream().sorted((a,b) ->b.getEnterCount()-a.getEnterCount());
			
			// 第二步：对结果进行排序，按照热度排序，分页取十条数据

			int total = list.size();
			pageNum = pageNum - 1;
			pageSize = pageRequest.getPageSize();
			List<GardenData> newList = new ArrayList<>();
			list.stream().skip(pageNum * pageSize).limit(pageSize).forEach(newList :: add); 
			Page<GardenData> page = new PageImpl<>(newList, pageRequest, total);
			data.add(page);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return data;
	}

	@Override
	public JSONArray findGardensCondition(GardenDTO dto) {
		JSONArray data = new JSONArray();
		try {
			List<String> gardenName = gardenUserRepository.findGardensCondition(dto.getUserId());
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termsQuery("park", gardenName));
			bq.must(QueryBuilders.termQuery("dimension", Constans.YUANQUDONGTAI));
			Sort sortx = new Sort(new Sort.Order(Sort.Direction.DESC, "publishDate"));
			PageRequest pageRequest = new PageRequest(dto.getPageNumber() - 1, dto.getPageSize(), sortx);
			Page<AITInfo> search = gardenEsRepository.search(bq, pageRequest);
			search.forEach((ait)->{
				ait.setSummary(StringUtil.replaceHtml(ait.getSummary()));
				List<String> business = getBusiness(ait.getTitle(),ait.getContent());
				ait.setBus(business);
			});
			data.add(search);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("园区动态查询结果:" + data.toJSONString());
		return data;
	}

	@Override
	public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
		Integer userId = dto.getUserId();
		String area = dto.getArea();
		String industryType = dto.getIndustryType();
		try {
			PageRequest pageRequest = new PageRequest(dto.getPageNumber() - 1, dto.getPageSize());

			Page<GardenUser> findAll = gardenUserRepository.findAll(getSpec(area, industryType, userId), pageRequest);
			findAll.forEach(GardenUser -> {
				String picture = GardenUser.getGardenPicture();
				String description = GardenUser.getDescription();
				if (description == null || StringUtil.isEmpty(description) || description.equals("NULL")) {
					GardenUser.setDescription("暂无");
				}
				if (picture == null || StringUtil.isEmpty(picture) || picture.equals("NULL")) {
					GardenUser.setGardenPicture(ImgConstant.IP_PORT + "park_img/default.jpg");
				}
			});
			return findAll;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	/*
	 * 此处实现关注园区的功能
	 */
	@Override
	public GardenUser attentionGarden(String gardenId, String userId, boolean flag) {
		try {
			GardenData garden = gardenRepository.findOne(Integer.parseInt(gardenId));

			if (flag) {
				GardenUser check = gardenUserRepository.findByGardenNameAndUserId(garden.getGardenName(),
						Integer.parseInt(userId));
				if (check != null) {
					return null;
				}
				if (null != garden) {
					GardenUser gu = new GardenUser();
					gu.setGardenName(garden.getGardenName());
					gu.setAddress(garden.getAddress());
					gu.setArea(garden.getArea());
					gu.setAttentionDate(
							new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis()).toString());
					String gardenIntroduce = garden.getGardenIntroduce();
					if (gardenIntroduce == null || StringUtil.isEmpty(gardenIntroduce)
							|| gardenIntroduce.equals("NULL")) {
						gu.setDescription("暂无");
					} else {
						gu.setDescription(garden.getGardenIntroduce());
					}
					gu.setUserId(Integer.parseInt(userId));
					gu.setGardenPicture(garden.getGardenPicture());
					gu.setGardenId(gardenId);
					gu.setIndustryType(garden.getIndustry());
					gardenUserRepository.save(gu);
					return gu;
				}
			} else {
				GardenUser gu = gardenUserRepository.findByGardenIdAndUserId(gardenId, Integer.valueOf(userId));
				if (gu != null) {
					gardenUserRepository.delete(gu);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public GardenUser getGardenByName(String gardenName) {
		return gardenUserRepository.findByGardenName(gardenName);
	}

	@Override
	public JSONObject getGardenTableData(String gardenName, Long userId) {
		JSONArray array = getGardenPolicyList(getAreaSearchDTODemo(gardenName));
		JSONArray array2 = getGardenInformationList(getAreaSearchDTODemo(gardenName));
		String cs = gardenRepository.findByGardenName(gardenName).getEnterCompany();
		JSONArray array3 = new JSONArray();
		if (!StringUtil.isEmpty(cs)) {
			String[] enterCompany = {};
			if(cs.contains("、")){
				enterCompany = cs.split("、");
			}else{
				enterCompany = cs.split(",");
			}
			for (int i = 0; i < (enterCompany.length < 6 ? enterCompany.length : 5); i++) {
				JSONObject obj = new JSONObject();
				obj.put("business", enterCompany[i]);
				array3.add(obj);
			}
		}
		GardenUser gu = gardenUserRepository.findByGardenNameAndUserId(gardenName, userId.intValue());
		JSONObject object = new JSONObject();
		object.put("leadCompany", array3);
		object.put("parkPolicy", array);
		object.put("parkDynamics", array2);
		if (null != gu) {
			object.put("isAttention", "yes");
		} else {
			object.put("isAttention", "no");
		}
		return object;
	}

	@Override
	public JSONArray findGardensByAreaAndIndustry(String area, String industry) {
		JSONArray arr = new JSONArray();
		try {
			area = "%" + area + "%";
			industry = "%" + industry + "%";
			List<GardenData> list = gardenRepository.findByAddressLikeAndIndustryLike(area, industry);
			for (GardenData garden : list) {
				JSONObject obj = new JSONObject();
				obj.put("address", garden.getAddress());
				obj.put("name", garden.getGardenName());
				obj.put("id", garden.getId());
				obj.put("industryType", garden.getLeadingIndustry());
				arr.add(obj);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return arr;
	}

	@Override
	public JSONArray findGardensAll() {
		JSONArray data = new JSONArray();
		try {
			Iterable<IndustryClass> findAll = industryClassRepository.findAll();
			JSONArray hulianwang = new JSONArray();// 互联网+
			JSONArray gaokeji = new JSONArray();// 高科技
			JSONArray wenhuachuangyi = new JSONArray();// 文化创意
			JSONArray jingyingpeitao = new JSONArray();// 精英配套
			JSONArray binhailvyou = new JSONArray();// 滨海旅游
			JSONArray gangkouwuliu = new JSONArray();// 港口物流
			for (IndustryClass industry : findAll) {
				JSONObject obj = new JSONObject();
				obj.put("name", industry.getArea());
				obj.put("count", industry.getCount());
				obj.put("industry", industry.getIndustry());
				int value = 0;
				switch (industry.getIndustry()) {
				case "互联网+":
					value = 0;
					break;
				case "高科技":
					value = 2;
					break;
				case "文化创意":
					value = 3;
					break;
				case "精英配套":
					value = 4;
					break;
				case "滨海旅游":
					value = 5;
					break;
				case "港口物流":
					value = 6;
					break;
				default:
					break;
				}
				obj.put("value", value);
				if (industry.getIndustry().equals("互联网+")) {
					hulianwang.add(obj);
				} else if (industry.getIndustry().equals("高科技")) {
					gaokeji.add(obj);
				} else if (industry.getIndustry().equals("文化创意")) {
					wenhuachuangyi.add(obj);
				} else if (industry.getIndustry().equals("精英配套")) {
					jingyingpeitao.add(obj);
				} else if (industry.getIndustry().equals("滨海旅游")) {
					binhailvyou.add(obj);
				} else if (industry.getIndustry().equals("港口物流")) {
					gangkouwuliu.add(obj);
				}
			}
			data.add(hulianwang);
			data.add(gaokeji);
			data.add(wenhuachuangyi);
			data.add(jingyingpeitao);
			data.add(binhailvyou);
			data.add(gangkouwuliu);
		} catch (Exception e) {
			LOGGER.error("获取园区情报中获取所有园区内容失败", e);
		}
		return data;
	}

	private Specification<GardenUser> getSpec(String area, String industryType, Integer userId) {
		return new Specification<GardenUser>() {
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<GardenUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (!"不限".equals(area)) {
					predicates.add(cb.equal(root.<String> get("area"), area));
				}
				if (!"不限".equals(industryType)) {
					predicates.add(cb.like(root.<String> get("industryType"), industryType));
				}
				predicates.add(cb.equal(root.get("userId"), userId));
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();

			}
		};
	}
}

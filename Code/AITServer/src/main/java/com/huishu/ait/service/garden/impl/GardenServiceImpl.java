package com.huishu.ait.service.garden.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.IndustryClass;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.GardenPolicy;
import com.huishu.ait.es.repository.GardenEsRepository;
import com.huishu.ait.es.repository.garden.GardenInformationRepository;
import com.huishu.ait.es.repository.garden.GardenPolicyRepository;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;
import com.huishu.ait.repository.industry_class.IndustryClassRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {

	@Autowired
	private GardenPolicyRepository gardenPolicyRepository;
	@Autowired
	private GardenInformationRepository gardenInformationRepository;
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
		map.put("dimension", "政策解读");
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishDate", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "title", "content" };
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, null, orderList, dataList, true);
		return array;
	}

	@Override
	public GardenPolicy getGardenPolicyById(String id) {
		return gardenPolicyRepository.findOne(id);
	}

	@Override
	public JSONArray getGardenInformationList(AreaSearchDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("park", searchModel.getPark());
		map.put("dimension", "园区动态");
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishDate", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "title", "vector" };
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, null, orderList, dataList, true);
		return array;
	}

	@Override
	public GardenInformation getGardenInformationById(String id) {
		return gardenInformationRepository.findOne(id);
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
		Page<GardenData> findGardensPage = null;
		try {
			if ("不限".equals(area)) {
				area = "%%";
			}
			if ("不限".equals(industryType)) {
				industryType = "%%";
			} else {
				industryType = "%" + industryType + "%";
			}
			PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize);
			// if(!StringUtil.isEmpty(searchName)){
			// findGardensPage =
			// gardenRepository.findByNameLike(searchName,pageRequest);
			// }else{//
			findGardensPage = gardenRepository.findByAreaLikeAndIndustryLikeOrderByIdDesc(area, industryType,
					pageRequest);
			// }
			data.add(findGardensPage);
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
			PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize());
			Page<GardenUser> page = null;
			// 对area 和 industryType 没有约束条件，全部查询
			if (dto.getArea().equals("不限") && dto.getIndustryType().equals("不限")) {
				page = gardenUserRepository.findByUserId(userId, pageRequest);
				// 对 area 没有约束，根据 id 和 industryType 查询
			} else if ((!dto.getArea().equals("不限")) && dto.getIndustryType().equals("不限")) {
				page = gardenUserRepository.findByUserIdAndArea(userId, area, pageRequest);
				// 对 industryType 没有约束，根据 id 和 area 进行查询
			} else if (dto.getArea().equals("不限") && (!dto.getIndustryType().equals("不限"))) {
				page = gardenUserRepository.findByUserIdAndIndustryType(userId, industryType, pageRequest);
			} else {
				page = gardenUserRepository.findByUserIdAndAreaAndIndustryType(userId, area, industryType, pageRequest);
			}
			return page;
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
					gu.setDescription(garden.getGardenIntroduce());
					gu.setUserId(Integer.parseInt(userId));
					gu.setGardenPicture(garden.getGardenPicture());
					gu.setIndustryType(garden.getLeadingIndustry());
					gardenUserRepository.save(gu);
					return gu;
				}
			} else {
				gardenUserRepository.delete(Integer.parseInt(gardenId));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public JSONObject findGardensConditionById(String cid) {
		GardenInformation information = new GardenInformation();
		try {
			information = gardenInformationRepository.findOne(cid);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return JSONObject.parseObject(information.toString());
	}

	@Override
	public GardenUser getGardenByName(String gardenName) {
		return gardenUserRepository.findByGardenName(gardenName);
	}

	@Override
	public JSONObject getGardenTableData(String gardenName, Long userId) {
		JSONArray array = getGardenPolicyList(getAreaSearchDTODemo(gardenName));
		JSONArray array2 = getGardenInformationList(getAreaSearchDTODemo(gardenName));
		JSONArray array3 = getGardenBusinessList(getAreaSearchDTODemo(gardenName));
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
	public JSONArray findGardensByAreaAndIndustry(String area, String leadIndustry) {
		JSONArray arr = new JSONArray();
		try {
			area = "%"+area+"%";
			List<GardenData> list = gardenRepository.findGardensByAddressLike(area);
			for (GardenData garden : list) {
				String maxStr, minStr;
				if (leadIndustry.length() > garden.getLeadingIndustry().length()) {
					maxStr = leadIndustry;
					minStr = garden.getLeadingIndustry();
				} else {
					maxStr = garden.getLeadingIndustry();
					minStr = leadIndustry;
				}
				int max = maxStr.length();
				int min = minStr.length();
				int result = 0;
				OK: for (int l = min; l > 0; l--) {
					for (int i = 0; i <= max - l; i++) {
						for (int j = 0; j <= min - l; j++) {
							if (maxStr.regionMatches(true, i, minStr, j, l)) {
								result = l;// System.out.println(l+" "+i+" "+j);
								break OK;
							}
						}
					}
				}
				if(result >= 3){
					JSONObject obj = new JSONObject();
					obj.put("address", garden.getAddress());
					obj.put("name", garden.getGardenName());
					obj.put("industryType", garden.getLeadingIndustry());
					arr.add(obj);
				}
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

}

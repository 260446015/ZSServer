package com.huishu.ZSServer.service.garden.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.GardenMapDTO;
import com.huishu.ZSServer.entity.garden.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenMap;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.repository.garden.GardenMapRepositroy;
import com.huishu.ZSServer.repository.garden.GardenRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService<GardenData> implements GardenService {

	private static Logger LOGGER = LoggerFactory.getLogger(GardenServiceImpl.class);
	@Autowired
	private GardenRepository gardenRepository;
	@Autowired
	private GardenMapRepositroy gardenMapRepositroy;
	@Autowired
	private BaseElasticsearch baseElasticsearch;

	@Override
	public Page<AITInfo> getInformationPush(AreaSearchDTO dto) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "publishTime"));
		orders.add(new Order(Direction.DESC, "hitCount"));
		PageRequest pageRequest = new PageRequest(0, 10, new Sort(orders));
		Map<String, Object> params = new HashMap<>();
		params.put("park", dto.getPark());
		params.put("dimension", dto.getDimension());
		return getAitinfo(params, pageRequest);
	}

	@Override
	public Page<AITInfo> findGardensCondition(GardenDTO dto) {
		Page<AITInfo> aitInfos = null;
		try {
			Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "publishDate"));
			PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(), sort);
			Map<String, Object> params = new HashMap<>();
			params.put("park", dto.getName());
			params.put("dimension", KeyConstan.YUANQUDONGTAI);
			aitInfos = getAitinfo(params, pageRequest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("园区动态查询结果:" + aitInfos.toString());
		return aitInfos;
	}

	@Override
	public List<GardenMap> findGardenGdp(String industry, Integer[] years, String province) {
		List<GardenMap> list = null;
		if (province == null)
			list = gardenMapRepositroy.findGdp(industry, years);
		else
			list = gardenMapRepositroy.findGdp(industry, province, years);
		return list;
	}

	@Override
	public Page<GardenData> findGardensList(GardenDTO dto) {
		String[] msg = dto.getMsg();
		int pageNum = dto.getPageNumber();
		int pageSize = dto.getPageSize();
		Page<GardenData> page = null;
		Map<String, Object> params = new HashMap<>();
		params.put("industryType", msg[0]);
		params.put("province", msg[1]);
		String sort = msg[2];
		String direct = msg[3];
		try {
			Specification<GardenData> spec = getSpec(params);
			List<GardenData> list = gardenRepository.findAll(spec);
			list.forEach(GardenData -> {
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
					GardenData.setGardenPicture(KeyConstan.IP_PORT + "park_img/default.jpg");
				}
			});
			if (sort.equals("园区占地")) {
				list.sort((a, b) -> {
					return direct.equalsIgnoreCase("DESC") ? b.getGardenSquare().compareTo(a.getGardenSquare())
							: a.getGardenSquare().compareTo(b.getGardenSquare());
				});
			} else if (sort.equals("企业数量")) {
				list.sort((a, b) -> {
					return direct.equalsIgnoreCase("DESC") ? b.getEnterCount().compareTo(a.getEnterCount())
							: a.getEnterCount().compareTo(b.getEnterCount());
				});
			} else if (sort.equals("产值")) {
				list.sort((a, b) -> {
					return direct.equalsIgnoreCase("DESC") ? b.getGdp().compareTo(a.getGdp())
							: a.getGdp().compareTo(b.getGdp());
				});
			}

			// 第二步：对结果进行排序，按照热度排序，分页取十条数据
			PageRequest pageRequest = new PageRequest(pageNum, pageSize);
			int total = list.size();
			List<GardenData> newList = new ArrayList<>();
			list.stream().skip(pageNum * pageSize).limit(pageSize).forEach(newList::add);
			page = new PageImpl<>(newList, pageRequest, total);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return page;
	}

	@Override
	public GardenData findGarden(Long gardenId) {
		return gardenRepository.findOne(gardenId);
	}

	@Override
	public Page<AITInfo> findGardenPolicy(GardenDTO dto) {
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		boolQuery.must(QueryBuilders.termQuery("dimension", "政策动向"));
		PageRequest pageable = new PageRequest(dto.getPageNumber(), dto.getPageSize(), new Sort(Direction.DESC, "publishTime"));
		Page<AITInfo> page = baseElasticsearch.search(boolQuery, pageable);
		return page;
		
	}

}

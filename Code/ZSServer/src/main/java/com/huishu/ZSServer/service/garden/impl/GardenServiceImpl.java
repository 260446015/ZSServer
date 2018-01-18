package com.huishu.ZSServer.service.garden.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.entity.dto.IndustryCount;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenIndustry;
import com.huishu.ZSServer.entity.garden.GardenMap;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.garden.GardenIndustryRepository;
import com.huishu.ZSServer.repository.garden.GardenMapRepositroy;
import com.huishu.ZSServer.repository.garden.GardenRepository;
import com.huishu.ZSServer.repository.garden_user.GardenUserRepository;
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
	private GardenUserRepository gardenUserRepository;
	@Autowired
	private GardenIndustryRepository gardenIndustryRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Page<AITInfo> getInformationPush(AreaSearchDTO dto) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "publishTime"));
		orders.add(new Order(Direction.DESC, "hitCount"));
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(), new Sort(orders));
		Map<String, Object> params = new HashMap<>();
		params.put("park", dto.getPark());
		params.put("dimension", dto.getDimension());
		return getAitinfo(params, pageRequest);
	}

	@Override
	public Page<AITInfo> findGardensCondition(GardenDTO dto) {
		Page<AITInfo> aitInfos = null;
		PageImpl<AITInfo> page = null;
		try {
			List<String> names = gardenUserRepository.findGardenNames(dto.getUserId());
			if (names.size() == 0) {
				return aitInfos;
			}
			Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "publishDate"));
			PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(), sort);
			Map<String, Object> params = new HashMap<>();
			params.put("park", names);
			params.put("dimension", KeyConstan.YUANQUDONGTAI);
			if (!StringUtil.isEmpty(dto.getProvince()))
				params.put("area", dto.getProvince());
			aitInfos = getAitinfo(params, pageRequest);
			
			aitInfos.getContent().forEach(obj ->{
				obj.setSummary(StringUtil.replaceHtml(obj.getSummary()));
			});
			page = new PageImpl<>(aitInfos.getContent(), pageRequest, aitInfos.getTotalElements());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("园区动态查询结果:" + aitInfos.toString());
		return page;
	}

	@Override
	public List<GardenMap> findGardenGdp(GardenDTO dto) {
		List<GardenMap> list = null;
		if (StringUtil.isEmpty(dto.getProvince()))
			list = gardenMapRepositroy.findGdp(dto.getIndustry(), dto.getYear());
		else {
			list = gardenMapRepositroy.findGdp(dto.getIndustry(), dto.getProvince(), dto.getYear());
			list.sort((a, b) -> {
				return a.getYear() - b.getYear();
			});
		}
		return list;
	}

	@Override
	public Page<GardenData> findGardensList(GardenDTO dto) {
		String[] msg = dto.getMsg();
		int pageNum = dto.getPageNumber();
		int pageSize = dto.getPageSize();
		PageImpl<GardenData> page = null;
		String sort = msg[2];
//		String direct = msg[3];
		PageRequest pageRequest = new PageRequest(pageNum, pageSize);
		List<GardenData> returnList = new ArrayList<>();
		if(msg[0].equals("全部") && msg[1].equals("全部")){
			returnList = (List<GardenData>) gardenRepository.findAll();
		}else{
			if(msg[0].equals("全部")){
				Map<String, Object> params = new HashMap<>();
				params.put("province", msg[1]);
				returnList = gardenRepository.findAll(getSpec(params));
			}else{
				returnList = gardenRepository.findByIndustryTypeLike("%" + msg[0] + "%");
			}
		}
		List<GardenData> list = new ArrayList<>();
		if(sort.equals("按企业数")){
			list = returnList.stream().sorted((a,b) -> {
				return b.getEnterCount() - a.getEnterCount();
			}).skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
		}else{
			list = returnList.stream().sorted((a,b) -> {
				return (int)(b.getGardenSquare() - a.getGardenSquare());
			}).skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
		}
		list.forEach(GardenData -> {
			GardenUser gu = gardenUserRepository.findByGardenNameAndUserId(GardenData.getGardenName(),
					dto.getUserId());
			if (gu != null)
				GardenData.setFlag(true);
			else
				GardenData.setFlag(false);
		});
		try {
			page = new PageImpl<>(list, pageRequest, returnList.size());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return page;
	}

	@Override
	public GardenData findGarden(String gardenName,Long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("gardenName", gardenName);
		GardenData findOne = gardenRepository.findOne(getSpec(params));
		findOne.setEnterCount(companyRepository.findByPark(gardenName).size());
		if(findOne != null){
			GardenUser gu = gardenUserRepository.findByGardenNameAndUserId(gardenName,
					userId);
			if (gu != null)
				findOne.setFlag(true);
			else
				findOne.setFlag(false);
		}
		return findOne;
	}

	@Override
	public Page<AITInfo> findGardenPolicy(GardenDTO dto) {
		List<String> findByProvince = gardenRepository.findByProvince(dto.getProvince());
		String province = dto.getProvince();
		Map<String, Object> params = new HashMap<>();
		params.put("area", province);
		params.put("dimension", KeyConstan.YUANQUZHENGCE);
		params.put("park", findByProvince);
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(),
				new Sort(Direction.DESC, "publishTime"));
		Page<AITInfo> page = getAitinfo(params, pageRequest);
		page.getContent().forEach(obj ->{
			obj.setSummary(StringUtil.replaceHtml(obj.getSummary()));
		});
		page = new PageImpl<>(page.getContent(), pageRequest, page.getTotalElements());
		return page;
	}

	@Override
	public List<Object[]> getGardenIndustryEcharts(String province, Integer year) {
		return gardenMapRepositroy.getGardenIndustryEcharts(province, year);
	}

	@Override
	public List<Object[]> getGardenIndustryCount(GardenDTO dto) {
		String industry = "%" + dto.getIndustryType().replaceAll("\n", "") + "%";
		List<Object[]> list = gardenRepository.getGardenIndustryCount(industry);
		list.sort((a, b) -> {
			return Integer.parseInt(b[1].toString()) - Integer.parseInt(a[1].toString());
		});
		return list;
	}

	@Override
	public List<IndustryCount> getIndustryByProvince(GardenDTO dto) {
		String province = "%" + dto.getProvince().replaceAll("\n", "") + "%";
		List<GardenData> list = gardenRepository.findByProvinceLike(province);
		List<IndustryCount> countList = new ArrayList<>();
		int jnhbCount = 0;// 节能环保
		int swcyCount = 0;// 生物产业
		int gdzbCount = 0;// 高端装备
		int xclCount = 0;// 新材料
		int szcyCount = 0;// 数字创意
		int xxxxCount = 0;// 新兴信息
		for (GardenData data : list) {
			String industryType = data.getIndustryType();
			if (industryType.contains(KeyConstan.IndustyType.GDZB)) {
				gdzbCount++;
			}
			if (industryType.contains(KeyConstan.IndustyType.JNHB)) {
				jnhbCount++;
			}
			if (industryType.contains(KeyConstan.IndustyType.SWCY)) {
				swcyCount++;
			}
			if (industryType.contains(KeyConstan.IndustyType.SZCY)) {
				szcyCount++;
			}
			if (industryType.contains(KeyConstan.IndustyType.XCL)) {
				xclCount++;
			}
			if (industryType.contains(KeyConstan.IndustyType.XXXX)) {
				xxxxCount++;
			}
		}
		IndustryCount c1 = new IndustryCount("高端装备\n制造产业", gdzbCount);
		IndustryCount c2 = new IndustryCount("节能环保和\n新能源产业", jnhbCount);
		IndustryCount c3 = new IndustryCount("生物产业", swcyCount);
		IndustryCount c4 = new IndustryCount("数字创意\n产业", szcyCount);
		IndustryCount c5 = new IndustryCount("新材料\n产业", xclCount);
		IndustryCount c6 = new IndustryCount("新兴信息\n产业", xxxxCount);
		countList.add(c1);
		countList.add(c2);
		countList.add(c3);
		countList.add(c4);
		countList.add(c5);
		countList.add(c6);
		countList.sort((a, b) -> b.getIndustryCount() - a.getIndustryCount());
		return countList;

	}

	@Override
	public List<GardenIndustry> getGardenIndustry() {
		Iterable<GardenIndustry> findAll = gardenIndustryRepository.findAll();
		List<GardenIndustry> list = new ArrayList<>();
		findAll.forEach(list::add);
		return list;
	}

	@Override
	public List<String> getGardenArea() {
		return gardenRepository.findArea();
	}
}

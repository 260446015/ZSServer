package com.huishu.ZSServer.service.garden.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@Override
	public Page<AITInfo> getInformationPush(AreaSearchDTO dto) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "publishTime"));
		orders.add(new Order(Direction.DESC, "hitCount"));
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), 4, new Sort(orders));
		Map<String, Object> params = new HashMap<>();
		params.put("park", dto.getPark());
		params.put("dimension", dto.getDimension());
		return getAitinfo(params, pageRequest);
	}

	@Override
	public Page<AITInfo> findGardensCondition(GardenDTO dto) {
		Page<AITInfo> aitInfos = null;
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
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("园区动态查询结果:" + aitInfos.toString());
		return aitInfos;
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
		Page<GardenData> page = null;
		String sort = msg[2];
		String direct = msg[3];
		Sort sortType = null;
		if (sort.equals("园区占地")) {
			if (direct.equalsIgnoreCase("desc"))
				sortType = new Sort(Direction.DESC, "gardenSquare");
			else
				sortType = new Sort(Direction.ASC, "gardenSquare");
		} else {
			if (direct.equalsIgnoreCase("desc"))
				sortType = new Sort(Direction.DESC, "gdp");
			else
				sortType = new Sort(Direction.ASC, "gdp");
		}
		PageRequest pageRequest = new PageRequest(pageNum, pageSize, sortType);
		if (msg[0].equals("不限") || msg[0].equals("全部")) {
			msg[0] = "%%";
		} else
			msg[0] = "%" + msg[0] + "%";
		if (msg[1].equals("不限") || msg[1].equals("全部")) {
			msg[1] = "%%";
		}else{
			msg[1] = "%" + msg[1] + "%";
		}
		try {
			page = gardenRepository.findByProvinceLikeAndIndustryTypeLike(msg[1], msg[0], pageRequest);
			page.getContent().forEach(GardenData -> {
				String gardenIntroduce = GardenData.getGardenIntroduce();
				String gardenSuperiority = GardenData.getGardenSuperiority();
				String address = GardenData.getAddress();
				String picture = GardenData.getGardenPicture();
				GardenUser gu = gardenUserRepository.findByGardenNameAndUserId(GardenData.getGardenName(),
						dto.getUserId());
				if (gu != null)
					GardenData.setFlag(true);
				else
					GardenData.setFlag(false);
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
					GardenData.setGardenPicture(KeyConstan.IP_PORT + "fileserver/img/list_img.jpg");
				}
			});

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
		String province = dto.getProvince();
		Map<String, Object> params = new HashMap<>();
		params.put("area", province);
		params.put("dimension", KeyConstan.ZHENGCEJIDU);
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(),
				new Sort(Direction.DESC, "publishTime"));
		Page<AITInfo> page = getAitinfo(params, pageRequest);
		return page;
	}

	@Override
	public List<Object[]> getGardenIndustryEcharts(String province, Integer year) {
		return gardenMapRepositroy.getGardenIndustryEcharts(province, year);
	}

	@Override
	public List<Object[]> getGardenIndustryCount(GardenDTO dto) {
		String industry = "%" + dto.getIndustryType() + "%";
		List<Object[]> list = gardenRepository.getGardenIndustryCount(industry);
		list.sort((a, b) -> {
			return Integer.parseInt(b[1].toString()) - Integer.parseInt(a[1].toString());
		});
		return list;
	}

	@Override
	public List<IndustryCount> getIndustryByProvince(GardenDTO dto) {
		String province = "%" + dto.getProvince() + "%";
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
		IndustryCount c1 = new IndustryCount(KeyConstan.IndustyType.GDZB, gdzbCount);
		IndustryCount c2 = new IndustryCount(KeyConstan.IndustyType.JNHB, jnhbCount);
		IndustryCount c3 = new IndustryCount(KeyConstan.IndustyType.SWCY, swcyCount);
		IndustryCount c4 = new IndustryCount(KeyConstan.IndustyType.SZCY, szcyCount);
		IndustryCount c5 = new IndustryCount(KeyConstan.IndustyType.XCL, xclCount);
		IndustryCount c6 = new IndustryCount(KeyConstan.IndustyType.XXXX, xxxxCount);
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

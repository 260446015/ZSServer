package com.huishu.ZSServer.service.garden_user.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.GardenCompare;
import com.huishu.ZSServer.entity.garden.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.repository.garden.GardenCompareRepositoy;
import com.huishu.ZSServer.repository.garden.GardenRepository;
import com.huishu.ZSServer.repository.garden_user.GardenUserRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.garden_user.GardenUserService;

@Service
public class GardenUserServiceImpl extends AbstractService<GardenUser> implements GardenUserService {

	private static Logger LOGGER = LoggerFactory.getLogger(GardenUserServiceImpl.class);
	@Autowired
	private GardenUserRepository gardenUserRepository;
	@Autowired
	private GardenRepository gardenRepository;
	@Autowired
	private GardenCompareRepositoy compareRepository;

	@Override
	public GardenUser attentionGarden(Long gardenId, Long userId, boolean flag) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("gardenId", gardenId);
			Specification<GardenUser> spec = getSpec(params);
			if (flag) {
				List<GardenUser> gardenUser = gardenUserRepository.findAll(spec);
				if (gardenUser.size() > 0) {
					return null;
				}
				if (null != gardenUser) {
					GardenData garden = gardenRepository.findOne(gardenId);
					GardenUser gu = new GardenUser();
					gu.setGardenName(garden.getGardenName());
					gu.setAddress(garden.getAddress());
					gu.setProvince(garden.getProvince());
					gu.setAttentionDate(
							new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis()).toString());
					String gardenIntroduce = garden.getGardenIntroduce();
					if (gardenIntroduce == null || StringUtil.isEmpty(gardenIntroduce)
							|| gardenIntroduce.equals("NULL")) {
						gu.setDescription("暂无");
					} else {
						gu.setDescription(garden.getGardenIntroduce());
					}
					gu.setUserId(userId);
					gu.setGardenPicture(garden.getGardenPicture());
					gu.setEnterCompany(garden.getEnterCompany());
					gu.setGardenId(gardenId);
					gu.setGdp(garden.getGdp());
					gu.setGardenSquare(garden.getGardenSquare());
					gu.setIndustryType(garden.getIndustryType());
					gu.setGardenWebsite(garden.getGardenWebsite());
					gu.setGardenLevel(garden.getGardenLevel());
					gardenUserRepository.save(gu);
					return gu;
				}
			} else {
				List<GardenUser> list = gardenUserRepository.findAll(spec);
				if (list.size() > 0) {
					gardenUserRepository.delete(list);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
		String[] msg = dto.getMsg();
		int pageNum = dto.getPageNumber();
		int pageSize = dto.getPageSize();
		Page<GardenUser> page = null;
		Map<String, Object> params = new HashMap<>();
		params.put("industryType", msg[0]);
		params.put("province", msg[1]);
		String sort = msg[2];
		String direct = msg[3];
		try {
			List<GardenUser> list = gardenUserRepository.findAll(getSpec(params));
			list.forEach(GardenUser -> {
				String picture = GardenUser.getGardenPicture();
				String description = GardenUser.getDescription();
				String address = GardenUser.getAddress();
				if (description == null || StringUtil.isEmpty(description) || description.equals("NULL")) {
					GardenUser.setDescription("暂无");
				}
				if (picture == null || StringUtil.isEmpty(picture) || picture.equals("NULL")) {
					GardenUser.setGardenPicture(KeyConstan.IP_PORT + "park_img/default.jpg");
				}
				if (address == null || StringUtil.isEmpty(address) || address.equals("NULL")) {
					GardenUser.setAddress("暂无");
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
			List<GardenUser> newList = new ArrayList<>();
			list.stream().skip(pageNum * pageSize).limit(pageSize).forEach(newList::add);
			page = new PageImpl<>(newList, pageRequest, total);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		return page;
	}

	@Override
	public List<GardenCompare> getGardenCompare(Long userId, Long gardenId) {
		List<GardenCompare> list = null;
		if (null == gardenId) {
			list = compareRepository.findByUserId(userId);
			list.forEach(gc -> {
				List<Object[]> listEcharts = compareRepository.getCompareEcharts(gc.getGardenName());
				Map<Object, Object> industryCount = new HashMap<>();
				listEcharts.forEach(arr -> {
					industryCount.put(arr[1], arr[0]);
				});
				gc.setIndustryCount(industryCount);
			});
		} else {
			list = compareRepository.findByUserIdAndGardenId(userId, gardenId);
		}
		return list;
	}

	@Override
	public JSONObject addGardenCompare(Long gardenId, Long userId) {
		JSONObject obj = new JSONObject();
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("gardenId", gardenId);
			params.put("userId", userId);
			List<GardenUser> gus = gardenUserRepository.findAll(getSpec(params));
			if (gus.size() == 0)
				return null;
			GardenUser gu = gus.get(0);
			List<GardenCompare> list = compareRepository.findByUserIdAndGardenId(userId, gardenId);
			if (list.size() > 0)
				return null;
			GardenCompare gc = new GardenCompare();
			gc.setEnterCompany(gu.getEnterCompany());
			gc.setLogo(gu.getGardenPicture());
			gc.setGdp(gu.getGdp());
			gc.setGardenId(gu.getGardenId());
			gc.setGardenSquare(gu.getGardenSquare());
			gc.setGardenName(gu.getGardenName());
			gc.setUserId(gu.getUserId());
			compareRepository.save(gc);
			obj.put("success", true);
		} catch (Exception e) {
			LOGGER.error("存储对比园区失败", e.getMessage());
			obj.put("success", false);
		}
		return obj;

	}

	@Override
	public boolean deleteCompare(List<GardenCompare> list) {
		boolean flag = false;
		try {
			compareRepository.delete(list);
			flag = true;
		} catch (Exception e) {
			LOGGER.error("删除园区对比失败", e.getMessage());
		}
		return flag;
	}

	@Override
	public List<String> getGardenAttainArea() {
		return gardenUserRepository.findArea();
	}

	
}

package com.huishu.ZSServer.service.garden_user.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
					gu.setGardenId(gardenId);
					// gu.setIndustryType(garden.getIndustry());
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
		Long userId = dto.getUserId();
		String province = dto.getProvince();
		String industryType = dto.getIndustryType();
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("province", province);
		params.put("industryType", industryType);
		try {
			PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize());
			Page<GardenUser> findAll = gardenUserRepository.findAll(getSpec(params), pageRequest);
			findAll.forEach(GardenUser -> {
				String picture = GardenUser.getGardenPicture();
				String description = GardenUser.getDescription();
				if (description == null || StringUtil.isEmpty(description) || description.equals("NULL")) {
					GardenUser.setDescription("暂无");
				}
				if (picture == null || StringUtil.isEmpty(picture) || picture.equals("NULL")) {
					GardenUser.setGardenPicture(KeyConstan.IP_PORT + "park_img/default.jpg");
				}
			});
			return findAll;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
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
			GardenUser gu = gardenUserRepository.findOne(gardenId);
			if (gu == null)
				return null;
			List<GardenCompare> list = compareRepository.findByUserIdAndGardenId(userId, gardenId);
			if (list.size() > 0)
				return null;
			GardenCompare gc = new GardenCompare();
			gc.setEnterCompany(gu.getEnterCompany());
			gc.setLogo(gu.getGardenPicture());
			gc.setGdp(gu.getGdp());
			gc.setGardenSquare(gu.getGardenSquare());
			gc.setGardenName(gu.getGardenName());
			gc.setUserId(gu.getUserId());
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

}

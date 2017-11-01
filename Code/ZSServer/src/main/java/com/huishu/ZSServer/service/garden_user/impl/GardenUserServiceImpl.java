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

import com.huishu.ZSServer.common.util.ConstansKey;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.GardenData;
import com.huishu.ZSServer.entity.GardenUser;
import com.huishu.ZSServer.entity.dto.GardenDTO;
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
//					gu.setIndustryType(garden.getIndustry());
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
					GardenUser.setGardenPicture(ConstansKey.IP_PORT + "park_img/default.jpg");
				}
			});
			return findAll;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

}

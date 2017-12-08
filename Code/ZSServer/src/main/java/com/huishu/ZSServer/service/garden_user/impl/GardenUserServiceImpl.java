package com.huishu.ZSServer.service.garden_user.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.GardenCompareDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.garden.GardenRepository;
import com.huishu.ZSServer.repository.garden_user.GardenUserRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.garden_user.GardenUserService;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

@Service
public class GardenUserServiceImpl extends AbstractService<GardenUser> implements GardenUserService {

	private static Logger LOGGER = LoggerFactory.getLogger(GardenUserServiceImpl.class);
	@Autowired
	private GardenUserRepository gardenUserRepository;
	@Autowired
	private GardenRepository gardenRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private OpeneyesService openeyesService;

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
					List<Company> cs = companyRepository.findByPark(garden.getGardenName());
					List<Company> list2 = cs.stream().sorted((a,b)-> {
						return new Double((b.getRegisterCapital().substring(0, b.getRegisterCapital().indexOf("万")))).compareTo(
								new Double(a.getRegisterCapital().substring(0, a.getRegisterCapital().indexOf("万"))));
					}).limit(50).collect(Collectors.toList());
					LOGGER.info("关注园区后开始搜索园区下按注册金额排名前50企业的名称");
					OpeneyesDTO dto = new OpeneyesDTO();
					list2.forEach((company) ->{
						String name = company.getCompanyName();
						LOGGER.info("搜索接口触发，当前搜索企业名称为:"+name);
						dto.setCname(name);
						try {
							openeyesService.getBaseInfo(dto);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
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
		}

		try {
			page = gardenUserRepository.findByProvinceLikeAndIndustryTypeLike(msg[1], msg[0], pageRequest);
			page.getContent().forEach(GardenUser -> {
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
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		return page;
	}

	@Override
	public List<String> getGardenAttainArea() {
		return gardenUserRepository.findArea();
	}

	@Override
	public List<GardenCompareDTO> getGardenCompare(Long[] arrId) {
		List<GardenUser> list = gardenUserRepository.findByGardenIdIn(arrId);
		List<GardenCompareDTO> gcList = new ArrayList<>();
		list.forEach((gu) ->{
			GardenCompareDTO dto = new GardenCompareDTO();
			dto.setEnterCount(gu.getEnterCount());
			dto.setGardenName(gu.getGardenName());
			dto.setGdp(gu.getGdp());
			dto.setSquare(gu.getGardenSquare());
			JSONArray industryType = new JSONArray();
			String[] enterCompany = gu.getEnterCompany().split("、");
			for(int i=0;i<enterCompany.length;i++){
				companyRepository.findByCompanyName(enterCompany[i]);
			}
			dto.setIndustryType(industryType);
			gu.getEnterCompany();
			
			gcList.add(dto);
		});
		return gcList;

	}

}

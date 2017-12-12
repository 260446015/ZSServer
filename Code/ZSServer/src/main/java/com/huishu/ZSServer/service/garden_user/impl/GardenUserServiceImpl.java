package com.huishu.ZSServer.service.garden_user.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.GardenCompareDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.entity.garden.ScanGarden;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.garden.GardenRepository;
import com.huishu.ZSServer.repository.garden_user.GardenUserRepository;
import com.huishu.ZSServer.repository.garden_user.ScanGardenRepository;
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
	@Autowired
	private ScanGardenRepository scanGardenRepository;

	@Override
	public GardenUser attentionGarden(Long gardenId, Long userId, boolean flag) {
		GardenUser gardenUser = gardenUserRepository.findByUserIdAndGardenIdAndDr(userId, gardenId, 0);
		try {
			if (flag) {

				if (gardenUser != null) {
					return gardenUser;
				} else {
					List<ScanGarden> gardenIds = scanGardenRepository.findByGardenIdAndDr(gardenId, 0);
					GardenData garden = gardenRepository.findOne(gardenId);
					if (gardenIds.size() == 0) {
						List<Company> cs = companyRepository.findByPark(garden.getGardenName());
						List<Company> list2 = cs.stream().sorted((a, b) -> {
							return new Double(
									(b.getRegisterCapital().substring(0, b.getRegisterCapital().indexOf("万"))))
											.compareTo(new Double(a.getRegisterCapital().substring(0,
													a.getRegisterCapital().indexOf("万"))));
						}).limit(50).collect(Collectors.toList());
						LOGGER.info("关注园区后开始搜索园区下按注册金额排名前50企业的名称");
						OpeneyesDTO dto = new OpeneyesDTO();
						list2.forEach((company) -> {
							String name = company.getCompanyName();
							LOGGER.info("搜索接口触发，当前搜索企业名称为:" + name);
							dto.setCname(name);
							try {
								openeyesService.getBaseInfo(dto);
								ScanGarden sg = new ScanGarden();
								sg.setDr(0);
								sg.setGardenId(gardenId);
								sg.setGardenName(garden.getGardenName());
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					}
					gardenUser = new GardenUser();
					gardenUser.setGardenName(garden.getGardenName());
					gardenUser.setAddress(garden.getAddress());
					gardenUser.setProvince(garden.getProvince());
					gardenUser.setAttentionDate(
							new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis()).toString());
					String gardenIntroduce = garden.getGardenIntroduce();
					if (gardenIntroduce == null || StringUtil.isEmpty(gardenIntroduce)
							|| gardenIntroduce.equals("NULL")) {
						gardenUser.setDescription("暂无");
					} else {
						gardenUser.setDescription(garden.getGardenIntroduce());
					}
					gardenUser.setUserId(userId);
					gardenUser.setGardenPicture(garden.getGardenPicture());
					gardenUser.setEnterCompany(garden.getEnterCompany());
					gardenUser.setGardenId(gardenId);
					gardenUser.setGdp(garden.getGdp());
					gardenUser.setGardenSquare(garden.getGardenSquare());
					gardenUser.setIndustryType(garden.getIndustryType());
					gardenUser.setGardenWebsite(garden.getGardenWebsite());
					gardenUser.setGardenLevel(garden.getGardenLevel());
					gardenUserRepository.save(gardenUser);
					return gardenUser;
				}
			} else {
				if (gardenUser != null) {
					gardenUser.setDr(1);
					gardenUserRepository.save(gardenUser);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return gardenUser;
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
					GardenUser.setGardenPicture(KeyConstan.IP_PORT + "fileserver/img/list_img.jpg");
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
		list.forEach((gu) -> {
			GardenCompareDTO dto = new GardenCompareDTO();
			dto.setGardenName(gu.getGardenName());
			dto.setSquare(gu.getGardenSquare());
			dto.setGdp(gu.getGdp());
			dto.setEnterCount(gu.getEnterCount());
			List<Object[]> echarts = companyRepository.findEcharts(gu.getGardenName());
			JSONArray industryList = new JSONArray();
			echarts.forEach(obj -> {
				String industry = (String) obj[0];
				if (!StringUtil.isEmpty(industry)) {
					int count = Integer.parseInt(obj[1].toString());
					JSONObject jobj = new JSONObject();
					jobj.put("industry", industry);
					jobj.put("count", count);
					industryList.add(jobj);
				}
			});
			dto.setIndustryType(industryList);
			gcList.add(dto);
		});
		return gcList;

	}

}

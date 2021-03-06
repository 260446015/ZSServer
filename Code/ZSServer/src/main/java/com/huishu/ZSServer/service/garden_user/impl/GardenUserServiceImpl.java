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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
		GardenUser gardenUser = gardenUserRepository.findByUserIdAndGardenId(userId, gardenId);
		try {
			if (flag) {

				if (gardenUser != null) {
					return gardenUser;
				} else {
					GardenData garden = gardenRepository.findOne(gardenId);
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
					gardenUser.setGardenId(gardenId);
					gardenUser.setGdp(garden.getGdp());
					gardenUser.setGardenSquare(garden.getGardenSquare());
					gardenUser.setIndustryType(garden.getIndustryType());
					gardenUser.setGardenWebsite(garden.getGardenWebsite());
					gardenUser.setGardenLevel(garden.getGardenLevel());
					gardenUser.setEnterCount(garden.getEnterCount());
					gardenUserRepository.save(gardenUser);
					return gardenUser;
				}
			} else {
				if (gardenUser != null) {
					gardenUserRepository.delete(gardenUser);
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
//		String direct = msg[3];
		PageRequest pageRequest = new PageRequest(pageNum, pageSize);
		List<GardenUser> returnList = new ArrayList<>();
		List<GardenUser> all = (List<GardenUser>) gardenUserRepository.findByUserId(dto.getUserId());
		if(msg[0].equals("全部") && msg[1].equals("全部")){
			returnList = all;
		}else{
			if(msg[0].equals("全部")){
				returnList = all.stream().filter(obj -> obj.getProvince().equals(msg[1])).collect(Collectors.toList());
			}else{
				returnList = all.stream().filter(obj -> obj.getIndustryType().contains(msg[0])).collect(Collectors.toList());
			}
		}
		List<GardenUser> list = new ArrayList<>();
		if(sort.equals("按企业数")){
			list = returnList.stream().sorted((a,b) -> {
				return b.getEnterCount() - a.getEnterCount();
			}).skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
		}else{
			list = returnList.stream().sorted((a,b) -> {
				return (int)(b.getGardenSquare() - a.getGardenSquare());
			}).skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
		}
		try {
			page = new PageImpl<>(list, pageRequest, returnList.size());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return page;
	}

	@Override
	public List<String> getGardenAttainArea(Long userId) {
		return gardenUserRepository.findArea(userId);
	}

	@Override
	public List<GardenCompareDTO> getGardenCompare(Long userId,Long[] arrId) {
		List<GardenUser> list = gardenUserRepository.findByUserIdAndGardenIdIn(userId,arrId);
		List<GardenCompareDTO> gcList = new ArrayList<>();
		list.forEach((gu) -> {
			GardenCompareDTO dto = new GardenCompareDTO();
			dto.setGardenName(gu.getGardenName());
			dto.setSquare(gu.getGardenSquare());
			dto.setGdp(gu.getGdp());
			int enterCount = companyRepository.findCountByPark(gu.getGardenName());
			dto.setEnterCount(enterCount);
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

	@Override
	public boolean scanGarden(Long gardenId,Long userId) {
		ScanGarden sg = scanGardenRepository.findByGardenId(gardenId);
		GardenData garden = gardenRepository.findOne(gardenId);
		if (sg == null) {
			sg = new ScanGarden();
			sg.setDr(0);
			sg.setGardenId(gardenId);
			sg.setGardenName(garden.getGardenName());
			sg.setScanCount(50);
			try{
				List<Company> cs = companyRepository.findByPark(garden.getGardenName());
				List<Company> list2 = cs.stream().sorted((a, b) -> {
					return b.getRc().compareTo(a.getRc());
				}).limit(50).collect(Collectors.toList());
				LOGGER.info("关注园区后开始搜索园区下按注册金额排名前50企业的名称");
				int count = list2.size();
				OpeneyesDTO dto = new OpeneyesDTO();
				for(int i=0;i<list2.size();i++){
					String name = list2.get(i).getCompanyName();
					LOGGER.info("搜索接口触发，当前搜索企业名称为:" + name);
					dto.setCname(name);
					dto.setUserId(userId);
					try {
						openeyesService.getBaseInfo(dto);
					} catch (Exception e) {
						LOGGER.info("搜索接口获取天眼查基本信息报错:"+e.getMessage());
						count --;
					}
				}
				LOGGER.info("成功扫描"+count+"家企业,失败"+(list2.size()-count)+"家企业");
			}catch(Exception e){
				sg.setDr(2);
			}
		}else{
			if(sg.getDr() == 0){
				return true;
			}else{
				try{
					List<Company> cs = companyRepository.findByPark(garden.getGardenName());
					List<Company> list2 = cs.stream().sorted((a, b) -> {
						return b.getRc().compareTo(a.getRc());
					}).limit(sg.getScanCount()).collect(Collectors.toList());
					LOGGER.info("关注园区后开始搜索园区下按注册金额排名前50企业的名称");
					int count = list2.size();
					OpeneyesDTO dto = new OpeneyesDTO();
					for(int i=0;i<list2.size();i++){
						String name = list2.get(i).getCompanyName();
						LOGGER.info("搜索接口触发，当前搜索企业名称为:" + name);
						dto.setCname(name);
						try {
							openeyesService.getBaseInfo(dto);
						} catch (Exception e) {
							LOGGER.info("搜索接口获取天眼查基本信息报错:"+e.getMessage());
							count --;
						}
					}
					LOGGER.info("成功扫描"+count+"家企业,失败"+(list2.size()-count)+"家企业");
					sg.setDr(0);
				}catch(Exception e){
					sg.setDr(2);
				}
			}
		}
		scanGardenRepository.save(sg);
		return true;
		
	}

	
}

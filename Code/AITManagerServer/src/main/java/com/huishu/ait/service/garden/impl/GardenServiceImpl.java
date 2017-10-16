package com.huishu.ait.service.garden.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.es.repository.garden.GardenInformationRepository;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.user.UserParkRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {

	@Resource
	private GardenRepository gardenRepository;
	@Resource
	private CompanyRepository companyRepository;
	@Autowired
	private UserParkRepository userParkRepository;
	@Resource
	private GardenInformationRepository gardenInformationRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(GardenServiceImpl.class);

	@Override
	public List<UserPark> findGardensList(GardenDTO dto) {
		List<UserPark> findGardensPage=null;
		try {
			findGardensPage=userParkRepository.findByAreaLikeAndNameLikeAndIndustryLikeOrderByIdDesc(dto.getArea(), dto.getSearch() ,dto.getType());
			findGardensPage.forEach(GardenData -> {
				String address = GardenData.getAddress();
				if (address == null || StringUtil.isEmpty(address) || address.equals("NULL")) {
					GardenData.setAddress("暂无");
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return findGardensPage;
	}

	@Override
	public JSONArray findDynamicList(BusinessSuperviseDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("park", searchModel.getPark());
		map.put("dimension",searchModel.getDimension());
		if(!searchModel.getEmotion().equals("全部")){
			map.put("emotion",searchModel.getEmotion());
		}
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishDate", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = {"author","title","summary", "content","publishTime","source","emotion"};
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, null, orderList, dataList);
		for (Object object : array) {
			JSONObject obj = (JSONObject)object;
			String summary;
			if (null==obj.get("summary")) {
				summary = String.valueOf(obj.get("content")).substring(0, 300);
				summary = StringUtil.replaceHtml(summary);
			} else {
				summary = StringUtil.replaceHtml(String.valueOf(obj.get("summary")));
				if(StringUtil.isEmpty(summary)){
					int i = String.valueOf(obj.get("content")).length();
					summary = String.valueOf(obj.get("content")).substring(0, 300<i?300:i);
					summary = StringUtil.replaceHtml(summary);
				}
			}
			obj.put("summary", summary);
		}
		return array;
	}

	@Override
	public void dropEssay(String id) {
		gardenInformationRepository.delete(id);
	}

	@Override
	public UserPark findGarden(Long id) {
		return userParkRepository.findOne(id);
	}

	@Override
	public JSONArray findInformationList(BusinessSuperviseDTO searchModel) {
		JSONArray array = new JSONArray();
		if(searchModel.getDimension().equals("企业库")){
			GardenData gardenName = gardenRepository.findByGardenName(searchModel.getPark());
			if(gardenName!=null){
				String company = gardenName.getEnterCompany();
				if(!StringUtil.isEmpty(company)){
					String[] split = company.split("、");
					List<String> list = new ArrayList<String>();
					for (String string : split) {
						if(!StringUtil.isEmpty(string)){
							list.add(string);
						}
					}
					searchModel.setTotalSize(list.size());
					Integer after=searchModel.getPageFrom()+searchModel.getPageSize();
					for (int i = searchModel.getPageFrom(); i < (list.size()>after?after:list.size()); i++) {
						array.add(list.get(i));
					}
				}
			}
			return array;
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("park", searchModel.getPark());
			map.put("dimension",searchModel.getDimension());
			String[] order = { "publishDate", "hitCount" };
			List<String> orderList = Arrays.asList(order);
			String[] data = {"author","title","summary", "content","publishTime","source","emotion"};
			List<String> dataList = Arrays.asList(data);
			array = getEsData(searchModel, map, null, orderList, dataList);
			for (Object object : array) {
				JSONObject obj = (JSONObject)object;
				String summary;
				if (null==obj.get("summary")) {
					summary = String.valueOf(obj.get("content")).substring(0, 300);
					summary = StringUtil.replaceHtml(summary);
				} else {
					summary = StringUtil.replaceHtml(String.valueOf(obj.get("summary")));
					if(StringUtil.isEmpty(summary)){
						int i = String.valueOf(obj.get("content")).length();
						summary = String.valueOf(obj.get("content")).substring(0, 300<i?300:i);
						summary = StringUtil.replaceHtml(summary);
					}
				}
				obj.put("summary", summary);
			}
		}
		return array;
	}

	@Override
	public void dropCompany(String park,String companyName) {
		GardenData data = gardenRepository.findByGardenName(park);
		String company = data.getEnterCompany();
		if(!StringUtil.isEmpty(company)){
			String replace = company.replace(companyName, "");
			data.setEnterCompany(replace);
			gardenRepository.save(data);
		}
		companyRepository.deleteByCompanyName(companyName);
	}

	@Override
	public void changeGarden(UserPark garden) {
		userParkRepository.save(garden);
	}

	@Override
	public void addCompany(Company company) {
		GardenData data = gardenRepository.findByGardenName(company.getPark());
		String enterCompany = data.getEnterCompany();
		data.setEnterCompany(enterCompany+"、"+company.getCompanyName());
		gardenRepository.save(data);
		companyRepository.save(company);
	}
} 

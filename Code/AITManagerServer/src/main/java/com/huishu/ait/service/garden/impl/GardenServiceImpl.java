package com.huishu.ait.service.garden.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.ImgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {

	@Resource
	private GardenRepository gardenRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(GardenServiceImpl.class);

	@Override
	public List<GardenData> findGardensList(GardenDTO dto) {
		List<GardenData> findGardensPage=null;
		try {
			findGardensPage = gardenRepository.findByAreaLikeAndIndustryLikeOrderByIdDesc(dto.getArea(), dto.getType());
			findGardensPage.forEach(GardenData -> {
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
					GardenData.setGardenPicture(ImgConstant.IP_PORT + "park_img/default.jpg");
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return findGardensPage;
	}

	@Override
	public JSONArray getBusinessBehaviours(BusinessSuperviseDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("park", searchModel.getPark());
		map.put("dimension",searchModel.getDimension());
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishTime", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "title","summary", "content","publishDate","source"};
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, null, orderList, dataList, true);
		return array;
	}
}

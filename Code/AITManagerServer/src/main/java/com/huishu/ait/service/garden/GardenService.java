package com.huishu.ait.service.garden;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;

/**
 * 模块管理-园区监管
 * 
 * @author yindq
 * @create 2017年9月28日
 */
public interface GardenService {
	/**
	 * 获取园区列表
	 * 
	 * @param dto
	 * @return
	 */
	List<GardenData> findGardensList(GardenDTO dto);
	/**
	 * 获取园区动态列表
	 * @param searchModel
	 * @return
	 */
	JSONArray getBusinessBehaviours(BusinessSuperviseDTO searchModel);
}

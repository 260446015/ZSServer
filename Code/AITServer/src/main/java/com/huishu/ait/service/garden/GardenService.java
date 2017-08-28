package com.huishu.ait.service.garden;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.GardenPolicy;

/**
 * 全景辖区概览service
 * 
 * @author yindq
 * @date 2017-7-28
 */
public interface GardenService {
	/**
	 * 获取园区的政策列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	JSONArray getGardenPolicyList(AreaSearchDTO searchModel);

	/**
	 * 根据政策ID获取政策详情
	 * 
	 * @param id
	 *            政策ID
	 * @return
	 */
	GardenPolicy getGardenPolicyById(String id);

	/**
	 * 获取园区的动态列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	JSONArray getGardenInformationList(AreaSearchDTO searchModel);

	/**
	 * 根据动态ID获取动态详情
	 * 
	 * @param id
	 *            动态ID
	 * @return
	 */
	GardenInformation getGardenInformationById(String id);

	/**
	 * 获取园区的龙头企业列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	JSONArray getGardenBusinessList(AreaSearchDTO searchModel);

	/**
	 * 获取园区的龙头企业，园区政策和园区情报信息
	 * 
	 * @param gardenName
	 *            园区名字
	 * @return
	 */
	JSONObject getGardenTableData(String gardenName, Long userId);

	/**
	 * 根据园区名字获取园区信息
	 * 
	 * @param id
	 *            动态ID
	 * @return
	 */
	GardenUser getGardenByName(String gardenName);

	/**
	 * 获取园区列表
	 * 
	 * @param dto
	 * @return
	 */
	JSONArray findGardensList(GardenDTO dto);

	/**
	 * 获取园区动态
	 * 
	 * @param dto
	 * @return
	 */
	JSONArray findGardensCondition(GardenDTO dto);

	/**
	 * 获取关注园区列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<GardenUser> getAttentionGardenList(GardenDTO dto);

	/**
	 * @param gardenId
	 *            传入想要关注的园区id,传入用户id,传入关注/取消操作,true是关注,false是取消
	 */
	GardenUser attentionGarden(String gardenId, String userId, boolean flag);

	/**
	 * 根据园区动态列表查询到动态详情
	 * 
	 * @param cid
	 *            园区动态id
	 * @return
	 */
	JSONObject findGardensConditionById(String cid);

	/**
	 * 根据地区查询到园区列表
	 * 
	 * @param area
	 *            地区
	 * @return
	 */
	JSONArray findGardensByAreaAndIndustry(String area,String leadIndustry);

	/**
	 * 园区情报中获取所有园区内容
	 */
	JSONArray findGardensAll();
}

package com.huishu.ZSServer.service.garden;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ZSServer.entity.GardenUser;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.es.entity.AITInfo;


/**
 * 全景辖区概览service
 * 
 * @author yindq
 * @date 2017-7-28
 */
public interface GardenService {
	/**
	 * 获取园区的情报推送
	 * 
	 * @param dto
	 * @return
	 */
	Page<AITInfo> getInformationPush(AreaSearchDTO dto);

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
	 * 根据地区查询到园区列表
	 * 
	 * @param area
	 *            地区
	 * @return
	 */
	JSONArray findGardensByAreaAndIndustry(String area, String leadIndustry);

	/**
	 * 园区情报中获取所有园区内容
	 */
	JSONArray findGardensAll();

	/**
	 * 全查园区
	 */
	List<String> findAll();
}

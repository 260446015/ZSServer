package com.huishu.ZSServer.service.garden_user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.GardenCompare;
import com.huishu.ZSServer.entity.garden.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenUser;

public interface GardenUserService {

	/**
	 * @param gardenId
	 *            传入想要关注的园区id,传入用户id,传入关注/取消操作,true是关注,false是取消
	 */
	GardenUser attentionGarden(Long gardenId, Long userId, boolean flag);

	/**
	 * 获取关注园区列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<GardenUser> getAttentionGardenList(GardenDTO dto);

	/**
	 * 加入园区对比的方法
	 * 
	 * @param gardenId
	 *            园区id
	 * @return
	 */
	JSONObject addGardenCompare(Long gardenId, Long userId);

	/**
	 * 查询园区对比的方法
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	List<GardenCompare> getGardenCompare(Long userId, Long gardenID);

	/**
	 * 删除园区对比的方法
	 * @param list
	 * @return
	 */
	boolean deleteCompare(List<GardenCompare> list);
}

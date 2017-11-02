package com.huishu.ZSServer.service.garden_user;

import org.springframework.data.domain.Page;

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
}

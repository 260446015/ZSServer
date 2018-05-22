package com.huishu.ManageServer.service.garden;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbFirst.GardenUser;
import com.huishu.ManageServer.entity.dto.GardenDTO;


public interface GardenUserService {


	/**
	 * 获取关注园区列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<GardenUser> getAttentionGardenList(GardenDTO dto);

	/**
	 * 删除关注园区
	 * @param id
	 * @return
	 */
	boolean deleteAttentionGarden(String id);


}

package com.huishu.ZSServer.service.garden_user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.dto.GardenCompareDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;

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
	 * 获取关注园区省份名称
	 * 
	 * @return
	 */
	List<String> getGardenAttainArea();

	/**
	 * 园区对比
	 * @return
	 */
	List<GardenCompareDTO> getGardenCompare(Long userId,Long[] arrId);

	/**
	 * 扫描园区
	 * @param gardenId
	 */
	boolean scanGarden(Long gardenId);

}

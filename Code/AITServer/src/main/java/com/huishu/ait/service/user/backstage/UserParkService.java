package com.huishu.ait.service.user.backstage;

import java.util.List;

import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDataDTO;
import com.huishu.ait.entity.dto.GardenSearchDTO;

/**
 * 后台管理-园区管理
 * 
 * @author yindq
 * @create 2017年9月8日
 */
public interface UserParkService {
	/**
	 * 查看园区分页列表
	 * @param searchModel    查询条件
	 * @return
	 */
	List<GardenDataDTO> getGardenList(GardenSearchDTO searchModel);
	/**
	 * 添加园区
	 * @param userPark
	 * @return
	 */
	AjaxResult addGarden(UserPark userPark);
	
}

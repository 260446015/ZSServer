package com.huishu.ait.service.user.backstage;

import java.util.List;

import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.AccountDataDTO;
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
	/**
	 * 查看园区信息
	 * @param id
	 * @return
	 */
	UserPark findParkInformation(Long id);
	/**
	 * 查看园区账号列表
	 * @param searchModel
	 * @return
	 */
	List<AccountDataDTO> findParkAccount(SearchModel searchModel);
	
}

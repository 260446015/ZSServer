package com.huishu.ait.service.garden;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.entity.UserPark;
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
	List<UserPark> findGardensList(GardenDTO dto);
	/**
	 * 获取全部园区列表
	 * 
	 * @param dto
	 * @return
	 */
	List<GardenData> findAllGardensList(GardenDTO dto);
	/**
	 * 查看园区详情
	 * @param id
	 * @return
	 */
	GardenData findGarden(Integer id);
	/**
	 * 修改园区信息
	 * @param garden
	 * @return
	 */
	void changeGarden(GardenData garden);
	/**
	 * 获取园区内企业动态/疑似外流
	 * @param searchModel
	 * @return
	 */
	JSONArray findDynamicList(BusinessSuperviseDTO searchModel);
	/**
	 * 获取园区内入驻企业/园区政策/园区情报
	 * @param searchModel
	 * @return
	 */
	JSONArray findInformationList(BusinessSuperviseDTO searchModel);
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	void dropEssay(String id);
	/**
	 * 删除企业
	 * @param park
	 * @param companyName
	 */
	void dropCompany(String park,String companyName);
	/**
	 * 添加企业
	 * @param company
	 */
	void addCompany(Company company);
}

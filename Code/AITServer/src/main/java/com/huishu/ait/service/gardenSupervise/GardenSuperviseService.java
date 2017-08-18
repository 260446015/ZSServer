package com.huishu.ait.service.gardenSupervise;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.CompanyGroup;
import com.huishu.ait.entity.dto.CompanyDTO;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：园区监管
 */
public interface GardenSuperviseService {

	// 获取园区的信息
	public JSONObject getGardenInfo(String park);

	// 获取园区内所有企业的信息
	public JSONArray getCompanyFromGarden(String park);

	// 获取园区内所有企业的信息（分页）
	public JSONArray getCompanyFromGardenForPage(CompanyDTO companyDTO);

	// 保存分组
	public String addCompanyGroup(String groupName, Long userId);

	// 查询分组信息
	public List<CompanyGroup> selectCompanyGroup(Long userId);

	// 删除企业分组
	public boolean dropCompanyGroup(String[] groupNames, Long userId);

	/**
	 * 通过企业分组id查询相关联的企业列表
	 * @param companyGroupId  企业分组id
	 * @return
	 */
	public JSONArray findCompanyByCompanyGroupId(String companyGroupId);
}

package com.huishu.ManageServer.service.industry.map;

import java.util.List;

import com.huishu.ManageServer.entity.dbFirst.Institutional;

/**
 * @author hhy
 * @date 2018年2月7日
 * @Parem
 * @return 
 * 
 */
public interface InstitutionalService {

	/**
	 * @param industry
	 * @return
	 * 获取重点实验室信息
	 */
	List<Institutional> getInstitutionListByIndustry(String industry);

	/**
	 * @param id
	 * @return
	 * 根据id删除实验室信息
	 */
	boolean deleteInstitutionalInfoById(String id);

	/**
	 * @param ent
	 * @return
	 * 保存实验室信息根据实体
	 */
	boolean saveOrUpdateInstitutionInfo(Institutional ent);

}

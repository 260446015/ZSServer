package com.huishu.ManageServer.service.enterprise;

import java.util.List;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.entity.dbFirst.IndusCompany;

/**
 * @author hhy
 * @date 2018年2月5日
 * @Parem
 * @return 
 * 
 */
public interface IndustryCompanyService {

	/**
	 * @return
	 * 删除精准筛选中的数据
	 */
	boolean deleteAll(Long userId);

	/**
	 * @param list
	 * @return
	 * 保存新的数据
	 */
	boolean saveListCompany(List<Enterprise> list,Long userId);

	/**
	 * @return
	 */
	List<IndusCompany> ListAllInfo();

	/**
	 * @param id
	 * @return
	 */
	boolean deleteInfoById(String id);

	/**
	 * @param ent
	 * @return
	 */
	boolean saveOrUpdateInfo(IndusCompany ent);

}

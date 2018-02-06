package com.huishu.ManageServer.service.enterprise;

import java.util.List;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;

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
	boolean deleteAll();

	/**
	 * @param list
	 * @return
	 * 保存新的数据
	 */
	boolean saveListCompany(List<Enterprise> list);

}

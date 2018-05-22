package com.huishu.ZSServer.service.data;

import java.util.List;

import com.huishu.ZSServer.es.entity.AITInfo;

/**
 * @author hhy
 * @date 2017年12月25日
 * @Parem
 * @return 
 * 
 */
public interface UpdateDataService {

	/**
	 * @return
	 */
	boolean updateCompany();

	/**
	 * @return
	 */
	List<AITInfo> findInfo(String str);

	/**
	 * @return
	 */
	boolean setCompanyInfo();

}

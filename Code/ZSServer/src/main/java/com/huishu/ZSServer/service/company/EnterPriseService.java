package com.huishu.ZSServer.service.company;

import java.util.List;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 
 */
public interface EnterPriseService {

	/**
	 * @param area
	 * @param industry
	 * @return
	 */
	List<String> findCompanyName(String area, String industry);

}

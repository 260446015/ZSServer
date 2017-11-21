package com.huishu.ZSServer.service.data;

import com.huishu.ZSServer.entity.Institutional;

/**
 * @author hhy
 * @date 2017年11月15日
 * @Parem
 * @return 
 * 
 */
public interface DataService {

	/**
	 * @param value
	 * @return
	 * 转换机构库数据
	 */
	Institutional transformData1(String value);

	/**
	 * @param info
	 * @return
	 */
	boolean addData1(Institutional info);

}

package com.huishu.ManageServer.service.second.keyword;

import java.util.List;

import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 * 
 */
public interface KeyInfoService {

	/**
	 * 保存关键词
	 * @param list
	 * @return
	 */
	boolean saveInfo(List<KeyInfoEntity> list);

}

package com.huishu.ZSServer.service.keyword;

import java.util.List;

import com.huishu.ZSServer.entity.KeyWordEntity;


/**
 * @author hhy
 * @date 2017年12月19日
 * @Parem
 * @return 
 * 
 */
public interface KeyWordService {

	/**
	 * @param str
	 * @return
	 * 获取关键词集合
	 */
	List<KeyWordEntity> findKeyWordList(String str);

	/**
	 * @param li
	 * @return
	 * 保存关键词
	 */
	boolean saveKeyWord(List<KeyWordEntity> li);

	/**
	 * 删除多余的数据
	 * @param list
	 * @return
	 */
	boolean deleteData(List<KeyWordEntity> list);
	
	
}

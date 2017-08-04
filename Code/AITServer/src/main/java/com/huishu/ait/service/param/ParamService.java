package com.huishu.ait.service.param;

import java.util.List;

import com.huishu.ait.entity.Param;

/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 
 * 针对公用参数的service方法
 */
public interface ParamService {

	/**
	 * @param info
	 * @return
	 */
	boolean insert(Param info);

	/**
	 * @param uid
	 * @return
	 */
	int findOne(Long uid);

	/**
	 * @param uid
	 */
	boolean deleteParamAllByUid(Long uid);

	/**
	 * @param uid
	 */
	List<Param> findByUid(Long uid);

}

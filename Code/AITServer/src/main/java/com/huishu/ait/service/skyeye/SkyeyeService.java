package com.huishu.ait.service.skyeye;

import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.ChangeInfo;
import com.huishu.ait.entity.SearchTrack;

/**
 * 
 * @author yindawei
 * @date 2017年9月13日下午6:01:23
 * @description 天眼查业务类
 * @version
 */
public interface SkyeyeService {

	/**
	 * 
	 * @param s
	 * @return 保存用户查询记录
	 */
	boolean saveSearchTrack(Collection<SearchTrack> s);

	/**
	 * 
	 * @param username
	 * @return 获取用户查询记录
	 */
	JSONObject findSearchTrack(String username);

	/**
	 * 
	 * @param s
	 * @return 定时调度
	 */
	boolean saveChangeInfo(Collection<ChangeInfo> s);
	
	/**
	 * 
	 * @param companyName
	 * @return 获取企业信息变更的id
	 */
	List<Integer> findChangeId(String companyName);

	/**
	 * 
	 * @param code
	 * @return 获取天眼查token的方法
	 * @throws Exception
	 */
	String getToken(String code) throws Exception;

	/**
	 * 
	 * @param userName
	 * @return 天眼查免登陆
	 */
	String loginOpenEye(String userName);

	/**
	 * 定时调度获取企业信息变更预警
	 */
	void getChangeInfo();

	/**
	 * 
	 * @param userName
	 * @param name
	 * @return 跳转公司详情
	 */
	String getCompanyDetail(String userName,String name);

	/**
	 * 
	 * @param userName
	 * @return 获取关注用户分组
	 */
	JSONObject getAttentionGroup(String userName);

	/**
	 * 
	 * @param userName
	 * @param tags
	 * @param ps
	 * @param pn
	 * @return 获取每个分组下企业
	 */
	JSONObject getCompanyByGroup(String userName,String tags,String ps,String pn);

	/**
	 * 
	 * @param userName
	 * @return 获取用户查询踪迹
	 */
	JSONObject getSearchTrack(String userName);

	/**
	 * 
	 * @param userName
	 * @param name
	 * @return 获取用户查询记录
	 */
	String getSearchList(String userName,String name);
}

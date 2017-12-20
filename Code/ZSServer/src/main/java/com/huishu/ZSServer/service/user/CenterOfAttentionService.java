package com.huishu.ZSServer.service.user;

import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.Institutional;

/**
 * 关注中心service
 * 
 * @author yindq
 * @date 2017年12月19日
 */
public interface CenterOfAttentionService {
	
	/**
	 * 根据产业和用户ID获取用户关注的机构
	 * @param industry
	 * @param userId
	 * @return
	 */
	Page<Institutional> findOrganizationList(String industry,Long userId,Integer pageNum);
	
	/**
	 * 取消对机构的关注
	 * @param id
	 * @param userId
	 */
	void cancelOrganization(Long id,Long userId);
	
	/**
	 * 意向联络
	 * @param id
	 * @param userId
	 * @return
	 */
	String liaison(Long id,Long userId);

}

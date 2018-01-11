package com.huishu.ZSServer.service.user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.CompnayGroup;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.entity.dto.CompanyDTO;
import com.huishu.ZSServer.entity.dto.IndustrySummitDTO;
import com.huishu.ZSServer.entity.vo.CompanyVO;

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
	
	/**
	 * 查询关注园区产业分类
	 * @param userId
	 * @return
	 */
	List<String> getCompnayIndustry(Long userId);
	
	/**
	 * 查询关注园区地域
	 * @param userId
	 * @return
	 */
	List<String> getCompnayArea(Long userId);
	
	/**
	 * 查询关注园区分组
	 * @param userId
	 * @return
	 */
	List<CompnayGroup> getCompnayGroup(Long userId);
	
	/**
	 * 查询关注企业列表
	 * @param userId
	 * @param dto
	 * @return
	 */
	List<CompanyVO> findCompnayList(Long userId,CompanyDTO dto);
	
	/**
	 * 添加企业分组
	 * @param userId
	 * @param name
	 */
	Boolean addCompnayGroup(Long userId,String name);
	/**
	 * 移动企业分组
	 * @param id
	 * @param userId
	 * @param groupId
	 * @param name
	 * @return
	 */
	Boolean moveCompnayGroup(Long id, Long userId,Long groupId,String name);
	
	/**
	 * 取消对企业的关注
	 * @param id
	 * @param userId
	 */
	void cancelCompnay(Long id,Long userId);
	
	/**
	 * 查询关注峰会列表
	 * @param json
	 * @return
	 */
	Page<UserSummitInfo> listSummitMeetingList(Long userId,IndustrySummitDTO dto);
	
	/**
	 * 取消对峰会的关注
	 * @param id
	 * @param userId
	 */
	void cancelSummitMeeting(Long id,Long userId);
}

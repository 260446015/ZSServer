package com.huishu.ZSServer.service.indus;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.es.entity.Essay;
import com.huishu.ZSServer.es.entity.SummitInfo;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 产业峰会service 接口
 */
public interface IndusSummitService {

	/**
	 * 分页查看产业峰会
	 * @param obj
	 * @return
	 */
	Page<SummitInfo> getIndustryForPage(JSONObject obj);

	/**
	 * 根据id查看峰会详情
	 * @param id
	 * @return
	 */
	Essay getSummitInfoById(String id);

	/**
	 * 关注 峰会
	 * @param id
	 * @return
	 */
	boolean insertSummitInfoById(UserSummitInfo info);

	/**
	 * 取消 关注 峰会
	 * @param id
	 * @return
	 */
	boolean deleteSummitInfoById(Long id);

	/**
	 * @param obj
	 * @return
	 * 推送峰会
	 */
	List<SummitInfo> findIndustrySummitList(JSONObject obj);

	/**
	 * 获取所有summit数据
	 * @return
	 */
	Page<SummitInfo> findAll();

	/**
	 * @param dto
	 * @return
	 */
	Page<SummitInfo> getIndustryList(JSONObject obj);

	/**
	 * @param aid
	 * @return
	 */
	String saveSummitInfoById(String aid,Long uid);

}

package com.huishu.ManageServer.service.third;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.addKeyWordDTO;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 
 */
public interface ThesaurusService {

	
	JSONArray findAllKeyWord();

	/**
	 * 根据dto查询关键词列表
	 * @param dto
	 * @return
	 */
	Page<ThesaurusEntity> findByPage(TKeyWordDTO dto);

	/**
	 * @param id
	 * @return
	 * 根据id查看关联关系
	 */
	JSONObject findRelatedById(String id);

	/**
	 * @param id
	 * @return
	 * 根据id删除关联关系
	 */
	boolean deleteRelatedById(String id);

	/**
	 * @param dto
	 * @return
	 */
	boolean saveOrUpdateInfo(addKeyWordDTO dto);

}

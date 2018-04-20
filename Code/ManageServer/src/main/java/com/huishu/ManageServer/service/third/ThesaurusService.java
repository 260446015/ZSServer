package com.huishu.ManageServer.service.third;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbThird.KeywordTypeEntity;
import com.huishu.ManageServer.entity.dbThird.RelatedWordEntity;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.entity.dto.dbThird.AttributeDTO;
import com.huishu.ManageServer.entity.dto.dbThird.RelatedDTO;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.WordDataDTO;
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
	Page<WordDataDTO> findByPage(TKeyWordDTO dto);

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

	/**
	 * @param id
	 * @return
	 * 根据id删除关联关系
	 */
	boolean deleteRelatedInfoById(String id);

	/**
	 * @param originalFilename
	 * @param string
	 */
	boolean printLog(String originalFilename, String message);

	/**
	 * @param value
	 */
	boolean addDataInfo(String value);

	/**
	 * @return
	 */
	List<KeywordTypeEntity> getLableInfo();

	/**
	 * @param id
	 * @return
	 */
	JSONObject findAttributeInfoById(String id);

	/**
	 * @param typeWord
	 * @return
	 */
	boolean updateTypeWord(String typeWord);

	/**
	 * @param dto
	 * @return
	 */
	boolean saveOrUpAttributeData(AttributeDTO dto);

	/**
	 * @param typeWord
	 */
	List<ThesaurusEntity> findKeyWordByType(String typeWord);

	/**
	 * @param relatedWord
	 * @return
	 */
	boolean updateRelatedWord(String relatedWord);

	/**
	 * @return
	 */
	List<RelatedWordEntity> getAllRelatedInfo();

	/**
	 * @param dto
	 * @return
	 */
	JSONArray addOrUpdate(RelatedDTO dto);
	
	//保存词与词之间的关系
	boolean saveOrUpdateData(JSONArray jsonArray);
	boolean saveOrUpdateData(String keyword,String typeWord);

	/**
	 * 根据新增词获取新增的所有数据
	 * @param keyword
	 * @return
	 */
	JSONObject findAllInfoByKeyWord(String keyword);

	/**
	 * @param subList
	 * @param value
	 */
	boolean addDataInfo(List<String> subList, String value);
}

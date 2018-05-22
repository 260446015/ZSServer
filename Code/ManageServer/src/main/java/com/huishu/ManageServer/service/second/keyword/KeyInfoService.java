package com.huishu.ManageServer.service.second.keyword;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;

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
	
	/**
	 * 获取关键词
	 * @return
	 */
	List<String> getKeyWord();

	/**
	 * @param keyname
	 * @return
	 */
	List<KeyInfoEntity> findInfoByKeyName(String keyname);

	/**
	 * 分页展示关键词
	 * @param dto
	 * @return
	 */
	Page<KeyInfoEntity> ListKeyWordInfo(AbstractDTO dto);

	/**
	 * 删除关键词
	 * @param id
	 * @return
	 */
	boolean deleteKeyWordInfo(String id);

	/**
	 * 更新或者保存某一个关键词
	 * @param ent
	 * @return
	 */
	boolean saveOrUpdateKeyWord(KeyInfoEntity ent);
	
	/**
	 * 查询所有实体
	 */
	List<KeyInfoEntity> findAllEntity();

}

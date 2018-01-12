package com.huishu.ManageServer.service.second.keyword.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.repository.second.KeyInfoRepository;
import com.huishu.ManageServer.service.second.keyword.KeyInfoService;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 */
@Service
public class KeyInfoServiceImpl implements KeyInfoService {
	private static final Logger LOGGER = Logger.getLogger( KeyInfoServiceImpl.class);
	@Autowired
	private KeyInfoRepository  kirep;

	/**
	 * 保存关键词数据 
	 */
	@TargetDataSource(name="second")
	@Override
	public boolean saveInfo(List<KeyInfoEntity> list) {
		try {
			Iterable<KeyInfoEntity> save = kirep.save(list);
			if(save != null){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 获取关键词集合
	 */
	@TargetDataSource(name="second")
	@Override
	public List<String> getKeyWord() {
		return kirep.getKeyWordList();
	}

	/**
	 * 获取关键词
	 */
	@Override
	@TargetDataSource(name="second")
	public List<KeyInfoEntity> findInfoByKeyName(String keyname) {
		return kirep.getKeywordInfo(keyname);
	}

	
	@Override
	@TargetDataSource(name="second")
	public Page<KeyInfoEntity> ListKeyWordInfo(AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<KeyInfoEntity> list =kirep.findPage(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = kirep.count();
		Page<KeyInfoEntity> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	/**
	 * 删除关键词
	 */
	@Override
	@TargetDataSource(name="second")
	public boolean deleteKeyWordInfo(String id) {
		 try {
			 long _id = Long.parseLong(id);
			 kirep.delete(_id);
		} catch (NumberFormatException e) {
			LOGGER.error("id输入不正确",e);
			return false;
		} catch (Exception e){
			LOGGER.info("数据库出现异常",e);
			return false;
		}
		return true;
	}

	@Override
	@TargetDataSource(name="second")
	public boolean saveOrUpdateKeyWord(KeyInfoEntity ent) {
		try {
			
			KeyInfoEntity save = kirep.save(ent);
			
			if(save == null){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			LOGGER.info("保存舆情公司信息失败！",e);
			return false;
		}
	}
	
	
}

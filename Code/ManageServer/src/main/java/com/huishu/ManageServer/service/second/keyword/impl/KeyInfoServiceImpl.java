package com.huishu.ManageServer.service.second.keyword.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;
import com.huishu.ManageServer.repository.second.KeyInfoRepository;
import com.huishu.ManageServer.service.second.keyword.KeyInfoService;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 */
@Service
//@Transactional("secondTransactionManager")
//@TargetDataSource(name="sapro")
public class KeyInfoServiceImpl implements KeyInfoService {

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
	
	
}

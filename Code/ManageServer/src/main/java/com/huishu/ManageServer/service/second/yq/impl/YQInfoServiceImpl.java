package com.huishu.ManageServer.service.second.yq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.YQInfoEntity;
import com.huishu.ManageServer.repository.second.YQInfoRepository;
import com.huishu.ManageServer.service.second.yq.YQInfoService;

/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 关于企业舆情信息的相关操作
 */
@Service
//@Transactional("secondTransactionManager")
//@TargetDataSource(name="sapro")
public class YQInfoServiceImpl implements YQInfoService {
	
	@Autowired
	private  YQInfoRepository  yqrep;
	
	@TargetDataSource(name="second")
	@Override
	public List<YQInfoEntity> getInfoBykid(Long kid) {
		return yqrep.findByKid(kid);
	}

}

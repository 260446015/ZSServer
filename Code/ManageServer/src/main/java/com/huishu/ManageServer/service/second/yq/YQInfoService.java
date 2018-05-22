package com.huishu.ManageServer.service.second.yq;

import java.util.List;

import com.huishu.ManageServer.entity.dbSecond.YQInfoEntity;

/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 舆情信息
 */
public interface YQInfoService {
	List<YQInfoEntity> getInfoBykid(Long kid);
}

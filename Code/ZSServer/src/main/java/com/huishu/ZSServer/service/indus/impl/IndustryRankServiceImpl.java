package com.huishu.ZSServer.service.indus.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.IndustryRank;
import com.huishu.ZSServer.repository.company.IndusRankRepository;
import com.huishu.ZSServer.service.indus.IndustryRankService;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 产业热度产业排行
 */
@Service
public class IndustryRankServiceImpl  implements IndustryRankService {

	private Logger LOGGER = LoggerFactory.getLogger(IndustryRankServiceImpl.class);
	@Autowired
	private IndusRankRepository rep;
	@Override
	public List<IndustryRank> findIndustryRank(String industry) {
		if(StringUtil.isEmpty(industry)){
			LOGGER.info("根据产业查询产业热度城市排行失败："+industry);
			return null;
		}
		return rep.findByIndustry(industry);
	}

}

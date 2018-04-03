package com.huishu.ManageServer.service.third.impl;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.SearchEngineKeyword;
import com.huishu.ManageServer.repository.third.SearchEngineKeywordRepository;
import com.huishu.ManageServer.service.third.SearchEngineKeywordService;
import javassist.compiler.ast.Keyword;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yindq
 * @since 2018-04-03
 */
@Service
public class SearchEngineKeywordServiceImpl implements SearchEngineKeywordService {

	@Autowired
	public SearchEngineKeywordRepository keywordRepository;

	@Override
	@TargetDataSource(name="third")
	public List<String> findAllKeyWorld() {
		return keywordRepository.findAllKeyWork();
	}

	@Override
	@TargetDataSource(name="third")
	public List<String> findByKeyWord(String keyWorld) {
		return keywordRepository.findByKeyWordLike("%"+keyWorld+"%");
	}

	@Override
	@TargetDataSource(name="third")
	@Transactional
	public boolean addKeyWorld(String keyWorld) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=format.format(new Date());
		Integer maxId = keywordRepository.findMaxId();
		keywordRepository.savaKeyWord(maxId+1,createTime,keyWorld);
		return true;
	}
}

package com.huishu.ait.service.personcenter.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.export.News;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.entity.UserCollection;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.PersonCollectDto;
import com.huishu.ait.repository.expertOpinionDetail.UserCollectionRepository;
import com.huishu.ait.service.personcenter.PersonCenterService;

/**
 * 
 * @author yindawei
 * @date 2017年9月4日下午2:07:51
 * @description 个人中心的业务处理层
 * @version 1.0
 */
@Service
public class PersonCenterServiceImpl implements PersonCenterService {

	private final Logger LOGGER = LoggerFactory.getLogger(PersonCenterServiceImpl.class);

	@Autowired
	private UserCollectionRepository userCollectionRepository;

	public JSONArray getPersonCollection(PersonCollectDto dto) {
		JSONArray data = new JSONArray();
		try {
			Sort sort = new Sort(Direction.DESC, "collectTime");
			PageRequest pageRequest = new PageRequest(dto.getPageNumber() - 1, dto.getPageSize(), sort);
			Page<UserCollection> page = userCollectionRepository.findByUserIdAndLanmuLike(dto.getUserId(),
					dto.getQuery(), pageRequest);
			data.add(page);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return data;
	}

	@Override
	public News getData(String articleId) {
		News download = null;
		try {
			UserCollection findOne = userCollectionRepository.findOne(Long.parseLong(articleId));
			download = new News();
			download.setTitle(findOne.getTitle());
			download.setPublishTime(findOne.getPublishTime());
			download.setContent(findOne.getContent());
			download.setSource(findOne.getSource());
			download.setSourceLink(findOne.getSourceLink());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return download;

	}

}

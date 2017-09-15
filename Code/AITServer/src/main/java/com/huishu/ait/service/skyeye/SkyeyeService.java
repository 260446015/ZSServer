package com.huishu.ait.service.skyeye;

import java.util.Collection;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.SearchTrack;

/**
 * 
 * @author yindawei 
 * @date 2017年9月13日下午6:01:23
 * @description 天眼查业务类
 * @version
 */
public interface SkyeyeService {

	boolean saveSearchTrack(Collection<SearchTrack> s);

	JSONObject findSearchTrack();
}

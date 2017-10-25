package com.huishu.ait.service.skyeye.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.ChangeInfo;
import com.huishu.ait.entity.SearchTrack;
import com.huishu.ait.repository.skyeye.ChangeRepository;
import com.huishu.ait.repository.skyeye.SearchTrackRepository;
import com.huishu.ait.service.skyeye.SkyeyeService;

@Service
public class SkyeyeServiceImpl implements SkyeyeService {

	@Autowired
	private SearchTrackRepository searchTrackRepository;

	@Autowired
	private ChangeRepository changeRepository;

	@Override
	public boolean saveSearchTrack(Collection<SearchTrack> s) {
		Iterable<SearchTrack> it = searchTrackRepository.save(s);
		if (it != null) {
			return true;
		}
		return false;

	}

	@Override
	public JSONObject findSearchTrack(String username) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Page<SearchTrack> page = null;
		try{
			page = searchTrackRepository.findByUsername(username, new PageRequest(0, 10, new Sort(Direction.DESC,"updateTime")));
			obj.put("total", page.getTotalElements());
			page.getContent().forEach((st)->{
				arr.add(st);
			});
			obj.put("items", arr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean saveChangeInfo(Collection<ChangeInfo> s) {
		Iterable<ChangeInfo> it = changeRepository.save(s);
		if (it != null) {
			return true;
		}
		return false;

	}

	@Override
	public List<Integer> findChangeId(String companyName) {
		return changeRepository.findChangeId(companyName);
		
	}

}

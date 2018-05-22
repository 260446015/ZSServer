package com.huishu.ManageServer.service.garden.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.entity.dbFirst.GardenUser;
import com.huishu.ManageServer.entity.dto.GardenDTO;
import com.huishu.ManageServer.repository.first.GardenUserRepository;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.service.garden.GardenUserService;

@Service
public class GardenUserServiceImpl extends AbstractService<GardenUser> implements GardenUserService {

	private static Logger LOGGER = LoggerFactory.getLogger(GardenUserServiceImpl.class);
	@Autowired
	private GardenUserRepository gardenUserRepository;

	@Override
	public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
		String[] msg = dto.getMsg();
		int pageNum = dto.getPageNum();
		int pageSize = dto.getPageSize();
		Page<GardenUser> page = null;
		String sort = msg[2];
//		String direct = msg[3];
		PageRequest pageRequest = new PageRequest(pageNum, pageSize);
		List<GardenUser> returnList = new ArrayList<>();
		List<GardenUser> all = (List<GardenUser>) gardenUserRepository.findByUserId(dto.getUserId());
		if(msg[0].equals("全部") && msg[1].equals("全部")){
			returnList = all;
		}else{
			if(msg[0].equals("全部")){
				returnList = all.stream().filter(obj -> obj.getProvince().equals(msg[1])).collect(Collectors.toList());
			}else{
				returnList = all.stream().filter(obj -> obj.getIndustryType().contains(msg[0])).collect(Collectors.toList());
			}
		}
		List<GardenUser> list = new ArrayList<>();
		if(sort.equals("按企业数")){
			list = returnList.stream().sorted((a,b) -> {
				return b.getEnterCount() - a.getEnterCount();
			}).skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
		}else{
			list = returnList.stream().sorted((a,b) -> {
				return (int)(b.getGardenSquare() - a.getGardenSquare());
			}).skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
		}
		try {
			page = new PageImpl<>(list, pageRequest, returnList.size());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return page;
	}

	@Override
	public boolean deleteAttentionGarden(String id) {
		boolean flag = false;
		try {
			gardenUserRepository.delete(Long.parseLong(id));
			flag = true;
		} catch (Exception e) {
			LOGGER.info("删除关注园区失败",e.getMessage());
		}
		return flag;
	}
}

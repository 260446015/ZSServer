package com.huishu.ZSServer.service.openeyes.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.EDT;
import com.huishu.ZSServer.entity.Openeyes;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.openeyes.Staff;
import com.huishu.ZSServer.repository.openeyes.EDTRepository;
import com.huishu.ZSServer.repository.openeyes.StaffRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

@Service
public class OpeneyesServiceImpl extends AbstractService<Openeyes> implements OpeneyesService {

	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private EDTRepository eDTRepository;
	@Override
	public JSONObject getStaffInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		List<Staff> list = staffRepository.findByCname(dto.getCname());
		if(list.size() > 0){
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("result");
		List<Staff> newList = new ArrayList<>();
		jsonArray.forEach((obj) ->{
			Staff parseObject = JSONObject.parseObject(obj.toString(),Staff.class);
			parseObject.setCname(dto.getCname());
			newList.add(parseObject);
		});
		staffRepository.save(newList);
		return openEyesTarget;
	}
	@Override
	public JSONObject getEDTInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		List<EDT> list = eDTRepository.findByName(dto.getCname());
		if(list.size() > 0){
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("result");
		List<EDT> newList = new ArrayList<>();
		jsonArray.forEach((obj) ->{
			EDT parseObject = JSONObject.parseObject(obj.toString(),EDT.class);
			newList.add(parseObject);
		});
		eDTRepository.save(newList);
		return openEyesTarget;
	}

}

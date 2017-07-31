package com.huishu.ait.garden.service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.dto.GardenDTO;

public interface GardenService {

	JSONArray findGardensList(GardenDTO dto);

	JSONArray findGardensCondition(GardenDTO dto);
}

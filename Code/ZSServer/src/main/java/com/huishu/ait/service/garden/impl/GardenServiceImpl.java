package com.huishu.ait.service.garden.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {


	@Override
	public JSONArray findGardensCondition(GardenDTO dto) {
		return null;
	}

	@Override
	public JSONArray getGardenPolicyList(AreaSearchDTO searchModel) {
		return null;
		
	}

	@Override
	public JSONArray getGardenInformationList(AreaSearchDTO searchModel) {
		return null;
		
	}

	@Override
	public JSONArray getGardenBusinessList(AreaSearchDTO searchModel) {
		return null;
		
	}

	@Override
	public JSONObject getGardenTableData(String gardenName, Long userId) {
		return null;
		
	}

	@Override
	public GardenUser getGardenByName(String gardenName) {
		return null;
		
	}

	@Override
	public JSONArray findGardensList(GardenDTO dto) {
		return null;
		
	}

	@Override
	public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
		return null;
		
	}

	@Override
	public GardenUser attentionGarden(String gardenId, String userId, boolean flag) {
		return null;
		
	}

	@Override
	public JSONArray findGardensByAreaAndIndustry(String area, String leadIndustry) {
		return null;
		
	}

	@Override
	public JSONArray findGardensAll() {
		return null;
		
	}

	@Override
	public List<String> findAll() {
		return null;
		
	}

}

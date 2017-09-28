package com.huishu.ait.service.param.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Param;
import com.huishu.ait.repository.param.ParamRepository;
import com.huishu.ait.service.param.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 公用参数对接serviceImpl方法
 */
@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private ParamRepository pr;

	/** 添加参数 */
	@Override
	public boolean insert(Param info) {
		Param save = pr.save(info);
		if (save != null) {
			return true;
		}
		return false;
	}

	/***
	 * 根据id查询用户个数
	 */
	@Override
	public int findOne(Long uid) {
		List<Param> list = pr.findByUid(uid);
		if (list != null) {
			return 1;
		}
		return 0;
	}

	/**
	 * 删除
	 */
	@Override
	public boolean deleteParamAllByUid(Long uid) {
		try {
			List<Param> list = pr.findByUid(uid);
			pr.delete(list);
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/**
	 * 通过id查询所有信息
	 */
	@Override
	public JSONObject findByUid(Long uid) {
		JSONObject obj = new JSONObject();
		List<Param> params = pr.findByUid(uid);
		for (Param param : params) {
			if (StringUtil.isEmpty(param.getIndustryLagel())) {
				obj.put(param.getIndustryInfo(), new String[0]);
			} else {
				obj.put(param.getIndustryInfo(), param.getIndustryLagel().split(","));
			}
		}
		return obj;
	}

	@Override
	public boolean saveParams(List<Param> list) {
		try {
			pr.save(list);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean getInsertParam(List<Param> params, Long userId) {
		try {
			List<Param> findByUid = pr.findByUid(userId);
			if (!findByUid.isEmpty()) {
				pr.delete(findByUid);
			}
			List<Param> find = pr.findByUid(userId);
			if (find.isEmpty()) {
				pr.save(params);
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

}

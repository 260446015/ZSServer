package com.huishu.ait.service.skyeye.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.service.SkyEyeAbstractService;
import com.huishu.ait.service.skyeye.SkyEyeService;

/**
 * @author yindawei
 * @date 2017年8月8日下午2:31:59
 * @description
 * @version
 */
@Service
public class SkyEyeServiceImpl extends SkyEyeAbstractService implements SkyEyeService {

	
	public JSONArray findBaseCompany(Map<String, String> params,HttpServletRequest request,HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try {
			List<String> specList = new ArrayList<>();
			specList.add(Constans.BASEINFO);specList.add(Constans.HOLDER);specList.add(Constans.INVERST);
			specList.add(Constans.CHANGEINFO);specList.add(Constans.ANNUALREPORT);
			super.sendHttpsRequest(specList, params, request, response);
		} catch (Exception e) {
			//=======================
		}
		return arr;
	}

	public JSONArray findBaseCompanyBusiness(Map<String, String> params,HttpServletRequest request,HttpServletResponse response) {
		JSONArray arr = new JSONArray();
		try{
			List<String> specList = new ArrayList<>();
			specList.add(Constans.GETPRODUCTINFO);specList.add(Constans.FINDTZANLI);specList.add(Constans.FINDJINGPIN);
			specList.add(Constans.LAWSUIT);specList.add(Constans.COURTANNOUNCEMENT);specList.add(Constans.ZHIXINGINFO);
			specList.add(Constans.PUNISHMENTINFO);specList.add(Constans.EQUITYINFO);specList.add(Constans.EMPLOYMENTS);
			specList.add(Constans.TAXCREDIT);specList.add(Constans.CHECKINFO);
			arr = super.sendHttpsRequest(specList, params, request, response);
		}catch(Exception e){
			//=====================
		}
		return arr;
	}
}

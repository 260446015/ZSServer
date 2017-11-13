package com.huishu.ZSServer.service.company;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
public interface IndusCompanyService {

	/**
	 * 根据别名查找公司信息
	 * @param companyName
	 * @return
	 */
	BaseInfo getCompanyInfo(String companyName);

	/**
	 * 智能推荐优质企业
	 * @return
	 */
	List<Company> listCompany();
	
	/**
	 * 根据上传名片获取相关公司列表
	 * @param imageBase64
	 * @return
	 */
	JSONArray uploadImage(String imageBase64);
}

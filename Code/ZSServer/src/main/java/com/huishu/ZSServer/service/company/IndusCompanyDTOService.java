package com.huishu.ZSServer.service.company;

import java.util.List;

import com.huishu.ZSServer.entity.dto.BaseInfoCustom;
import com.huishu.ZSServer.entity.dto.IndusCompanyDTO;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
public interface IndusCompanyDTOService {

	/**
	 * 根据别名查找公司信息
	 * @param companyName
	 * @return
	 */
	BaseInfoCustom getCompanyInfo(String companyName,Long userId);

	/**
	 * 智能推荐优质企业
	 * @return
	 */
	List<IndusCompanyDTO> listCompany();
//	List<IndusCompany> listCompany();
	/**
	 * 根据上传名片获取相关公司关键字
	 * @param imageBase64
	 * @return
	 */
	String uploadImage(String imageBase64);
	/**
	 * 根据别名查看公司信息
	 * @param companyName
	 * @return
	 */
//	IndusCompany findCompanyInfoByCompanyName(String companyName);
	IndusCompanyDTO findCompanyInfoByCompanyName(String companyName);
}

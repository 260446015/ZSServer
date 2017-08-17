package com.huishu.ait.controller.supervise;

import java.net.URLEncoder;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.CompanyGroup;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.security.ShiroDbRealm.ShiroUser;
import com.huishu.ait.service.gardenSupervise.GardenSuperviseService;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：园区监管的controller
 */
@RestController
@RequestMapping(value = "apis/supervise")
public class GardenSuperviseController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(GardenSuperviseController.class);

	@Autowired
	private GardenSuperviseService gardenSuperviseService;

	String park = "中关村软件园";

	/**
	 * @return 获取当前用户
	 */
	public ShiroUser getCurrentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	// TODO:获取当前用户所在的园区
	// 获取当前用户所在的园区
	/**
	 * @return 获取当前用户所在的园区
	 */
	public String getCurrentPark() {
		return null;
	}

	/**
	 * @return 获取当前园区的信息
	 */
	@RequestMapping(value = "getGardenInfo.json", method = RequestMethod.GET)
	public AjaxResult getGardenInfo() {
		try {
			JSONObject json = gardenSuperviseService.getGardenInfo(park);
			return success(json);
		} catch (Exception e) {
			log.error("获取园区信息失败", e.getMessage());
			return null;
		}
	}

	/**
	 * @return 获取当前园区中的所有企业列表
	 */
	@RequestMapping(value = "getCompanyFromGarden.json", method = RequestMethod.GET)
	public AjaxResult getCompanyFromGarden(String park) {
		try {
			JSONArray jsonArray = gardenSuperviseService.getCompanyFromGarden(park);
			return success(jsonArray);
		} catch (Exception e) {
			log.error("查询园区企业失败", e.getMessage());
			return null;
		}
	}

	/**
	 * @return 添加企业分组
	 */
	@RequestMapping(value = "addCompanyGroup.json", method = RequestMethod.GET)
	public AjaxResult addCompanyGroup(String groupName) {
		JSONObject result = new JSONObject();
		Long userId = 1L;
		// Long userId = getUserId();
		try {
			if (null != groupName && !"".equals(groupName)) {
				String state = gardenSuperviseService.addCompanyGroup(groupName, userId);
				if ("success".equals(state)) {
					result.put("state", "success");
				}
				if ("分组已经存在".equals(state)) {
					result.put("state", "分组已经存在");
				}
				return success(result);
			}
			return null;
		} catch (Exception e) {
			result.put("state", "failure");
			log.error("分组添加失败", e.getMessage());
			return success(result);
		}
	}

	/**
	 * @return 查询当前用户创建的企业分组信息
	 */
	@RequestMapping(value = "selectCompanyGroup.json", method = RequestMethod.GET)
	public AjaxResult selectCompanyGroup() {
		try {
			Long userId = 1L;
//			Long userId = getUserId();
			List<CompanyGroup> list = gardenSuperviseService.selectCompanyGroup(userId);
			return success(list);
		} catch (Exception e) {
			log.error("查询失败", e.getMessage());
			return null;
		}
	}

	/**
	 * @return 查询当前园区中的所有企业信息(分页)
	 */
	@RequestMapping(value = "searchCompanyFromGardenForPage.json", method = RequestMethod.POST)
	public AjaxResult getCompanyFromGardenForPage(CompanyDTO dto) {
		try {
			dto.setPark(park);
			dto = initPage(dto);
			JSONArray jsonArray = gardenSuperviseService.getCompanyFromGardenForPage(dto);
			return success(jsonArray);
		} catch (Exception e) {
			log.error("查询园区企业失败", e.getMessage());
			return null;
		}
	}

	/**
	 * 删除企业分组的controller
	 * 
	 * @return
	 */
	@RequestMapping(value = "dropCompanyGroup.json", method = RequestMethod.GET)
	public AjaxResult dropCompanyGroup(String companyGroupName) {
		Long userId = 1L;
		// Long userId = getUserId();
		boolean flag = false;
		try {
			flag = gardenSuperviseService.dropCompanyGroup(companyGroupName, userId);
		} catch (Exception e) {
			log.error("删除企业分组出错", e);
			return error("删除企业分组出错");
		}
		return success(flag);
	}
	
	/**
	 * 通过企业分组id查询相关联的企业列表
	 * @param companyGroupId
	 * @return
	 */
	@RequestMapping(value="/findCompanyByCompanyGroupId",method=RequestMethod.GET)
	public AjaxResult findCompanyByCompanyGroupId(String companyGroupId){
		JSONArray data = null;
		try{
			data = gardenSuperviseService.findCompanyByCompanyGroupId(companyGroupId);
		}catch(Exception e){
			log.error("通过企业分组id查询相关联的企业列表", e);
			return error("通过企业分组id查询相关联的企业列表");
		}
		return success(data);
	}

	/**
	 * @param dto
	 *            初始化分页的方法
	 */
	private CompanyDTO initPage(CompanyDTO dto) {
		if (dto.getPageNumber() == null) {
			dto.setPageNumber(1);
		}
		if (dto.getPageSize() == null) {
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if (dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER) {
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
}

package com.huishu.ait.controller.parkmodule.supervise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.CompanyGroup;
import com.huishu.ait.entity.CompanyGroupMiddle;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;
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

	/**
	 * @return 获取当前园区的信息
	 */
	@RequestMapping(value = "getGardenInfo.json", method = RequestMethod.GET)
	public AjaxResult getGardenInfo() {
		try {
			String park = getUserPark();
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
	public AjaxResult getCompanyFromGarden() {
		if (StringUtil.isEmpty(getUserPark())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray jsonArray = gardenSuperviseService.getCompanyFromGarden(getUserPark());
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
		Long userId = getUserId();
		if(StringUtil.isEmpty(groupName)){
			return error("企业分组名称不能为空!");
		}
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
			Long userId = getUserId();
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
	public AjaxResult getCompanyFromGardenForPage(@RequestBody CompanyDTO dto) {
		try {
			if (dto.getRegCapital() == null || dto.getIndustry() == null || dto.getGroupname() == null) {
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			String userPark = getUserPark();
			Long userId = getUserId();
			dto.setUserId(userId);
			dto.setPark(userPark);
			dto = initPage(dto);
			JSONArray jsonArray = gardenSuperviseService.getCompanyFromGardenForPage2(dto);
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
	public AjaxResult dropCompanyGroup(@RequestParam(value = "groupNames[]") String[] groupNames) {
		Long userId = getUserId();
		boolean flag = false;
		try {
			flag = gardenSuperviseService.dropCompanyGroup(groupNames, userId);
		} catch (Exception e) {
			log.error("删除企业分组出错", e);
			return error("删除企业分组出错");
		}
		return success(flag);
	}

	/**
	 * 保存操作的企业到企业分组的Controller
	 * 
	 * @param middle
	 *            企业与企业分组的中间表
	 * @return
	 */
	@RequestMapping(value = "/saveCompanyByGroupId.json", method = RequestMethod.POST)
	public AjaxResult saveCompanyByGroupId(@RequestBody CompanyGroupMiddle middle) {
		Long userId = getUserId();
		if (middle.getCompanyId() == null || middle.getGroupname() == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean flag = gardenSuperviseService.saveCompanyByGroupId(middle, userId);
		if (flag) {
			return success(0);
		} else {
			return success(1);
		}
	}

	/**
	 * 删除企业分组中的企业的Controller
	 * 
	 * @param middle
	 *            企业与企业分组的中间表
	 * @return
	 */
	@RequestMapping(value = "/deleteCompanyInGroup.json", method = RequestMethod.POST)
	public AjaxResult deleteCompanyInGroup(@RequestBody CompanyGroupMiddle middle) {
		boolean flag = gardenSuperviseService.deleteCompanyInGroup(middle);
		if (flag) {
			return success(flag);
		} else {
			return error("删除企业分组中的企业失败");
		}
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

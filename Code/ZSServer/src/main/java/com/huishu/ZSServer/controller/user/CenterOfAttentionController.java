package com.huishu.ZSServer.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.entity.dto.CompanyDTO;
import com.huishu.ZSServer.entity.dto.IndustrySummitDTO;
import com.huishu.ZSServer.service.user.CenterOfAttentionService;

/**
 * 关注中心的代码
 * 
 * @author yindq
 * @date 2017年12月19日
 */
@RestController
@RequestMapping("/apis/follow")
public class CenterOfAttentionController extends BaseController{

	private Logger LOGGER = LoggerFactory.getLogger(CenterOfAttentionController.class);

	@Autowired
	private CenterOfAttentionService centerOfAttentionService;
	
	/**
	 * 查看我所关注的机构
	 * @param industry
	 * @return
	 */
	@RequestMapping(value = "findOrganizationList.json", method = RequestMethod.GET)
	public AjaxResult findOrganizationList(String industry,Integer pageNum) {
		if(StringUtil.isEmpty(industry)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if(pageNum==null||pageNum<0){
			pageNum=0;
		}
		try {
			Page<Institutional> page = centerOfAttentionService.findOrganizationList(industry,getUserId(),pageNum);
			return successPage(page,pageNum+1);
		} catch (Exception e) {
			LOGGER.error("findOrganizationList失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 取消对该园区的关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancelOrganization.json", method = RequestMethod.GET)
	public AjaxResult cancelOrganization(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			centerOfAttentionService.cancelOrganization(id,getUserId());
			return success(MsgConstant.OPERATION_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("cancelOrganization失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 意向联络
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "liaison.json", method = RequestMethod.GET)
	public AjaxResult liaison(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			String msg = centerOfAttentionService.liaison(id,getUserId());
			if(msg.equals(MsgConstant.OPERATION_ERROR)){
				return error(MsgConstant.OPERATION_ERROR);
			}else{
				return success(msg);
			}
		} catch (Exception e) {
			LOGGER.error("liaison失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 查询关注园区产业分类
	 * @return
	 */
	@RequestMapping(value = "/getCompnayIndustry.json", method = RequestMethod.GET)
	public AjaxResult getCompnayIndustry() {
		try {
			return success(centerOfAttentionService.getCompnayIndustry(getUserId()));
		} catch (Exception e) {
			LOGGER.error("getGardenIndustry失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查询关注园区地域
	 * @return
	 */
	@RequestMapping(value = "/getCompnayArea.json", method = RequestMethod.GET)
	public AjaxResult getCompnayArea() {
		try {
			return success(centerOfAttentionService.getCompnayArea(getUserId()));
		} catch (Exception e) {
			LOGGER.error("getGardenIndustry失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查询关注园区分组
	 * @return
	 */
	@RequestMapping(value = "/getCompnayGroup.json", method = RequestMethod.GET)
	public AjaxResult getCompnayGroup() {
		try {
			return success(centerOfAttentionService.getCompnayGroup(getUserId()));
		} catch (Exception e) {
			LOGGER.error("getGroupArea失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查询企业列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/findCompnayList.json", method = RequestMethod.POST)
	public AjaxResult findCompnayList(@RequestBody CompanyDTO dto) {
		try {
			return success(centerOfAttentionService.findCompnayList(getUserId(),dto));
		} catch (Exception e) {
			LOGGER.error("findCompnayList失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 添加企业分组
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "addCompnayGroup.json", method = RequestMethod.GET)
	public AjaxResult addCompnayGroup(String name) {
		if(StringUtil.isEmpty(name)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean b = centerOfAttentionService.addCompnayGroup(getUserId(),name);
			if(b){
				return success(MsgConstant.OPERATION_SUCCESS);
			}
			return success("该分组已存在");
		} catch (Exception e) {
			LOGGER.error("addCompnayGroup失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 移动企业分组
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "moveCompnayGroup.json", method = RequestMethod.GET)
	public AjaxResult moveCompnayGroup(Long id,Long groupId,String name) {
		if(StringUtil.isEmpty(name)||id==null||groupId==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean b = centerOfAttentionService.moveCompnayGroup(id,getUserId(),groupId,name);
			if(b){
				return success(MsgConstant.OPERATION_SUCCESS);
			}
			return error(MsgConstant.OPERATION_ERROR);
		} catch (Exception e) {
			LOGGER.error("moveCompnayGroup失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 取消对该企业的关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancelCompnay.json", method = RequestMethod.GET)
	public AjaxResult cancelCompnay(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			centerOfAttentionService.cancelCompnay(id,getUserId());
			return success(MsgConstant.OPERATION_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("cancelCompnay失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查看峰会列表
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listSummitMeetingList", method = RequestMethod.POST)
	public AjaxResult listSummitMeetingList(@RequestBody IndustrySummitDTO dto) {
		if(dto.getIndustry()== null||dto.getArea()== null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try{
			Page<UserSummitInfo> page=centerOfAttentionService.listSummitMeetingList(getUserId(),dto);
			return successPage(page, page.getNumber()+1);
		} catch (Exception e) {
			LOGGER.error("listSummitMeetingList失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 取消对该峰会的关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancelSummitMeeting.json", method = RequestMethod.GET)
	public AjaxResult cancelSummitMeeting(Long id) {
		if(id==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			centerOfAttentionService.cancelSummitMeeting(id,getUserId());
			return success(MsgConstant.OPERATION_SUCCESS);
		} catch (Exception e) {
			LOGGER.error("cancelSummitMeeting失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}

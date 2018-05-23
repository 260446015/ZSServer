package com.huishu.ManageServer.controller.user;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.RolePermission;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.AccountDTO;
import com.huishu.ManageServer.entity.dto.AccountSearchDTO;
import com.huishu.ManageServer.entity.dto.UserBaseDTO;
import com.huishu.ManageServer.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理模块
 *
 * @author yindq
 * @date 2018/1/4
 */
@Controller
@RequestMapping("/apis/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}.html" }, method = RequestMethod.GET)
	public String pageJump(@PathVariable String page,String id,String parkId,Model model) {
		System.out.println("page的值是：---" + page);
		if("findParkInformation".equals(page)||"addUserBase".equals(page)){
			model.addAttribute("id",id);
		}else if("editUserBase".equals(page)||"userBase".equals(page)){
			model.addAttribute("id",id);
			model.addAttribute("parkId",parkId);
		}
		return "/user/" + page;
	}

	/**
	 * 分页查看园区用户列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listParkUserBase.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listParkUserBase(@RequestBody AccountDTO dto) {
		try {
			Page<UserBase> page = userService.listParkUserBase(dto);
			for (UserBase userBase : page) {
				userBase.setPassword(null);
				userBase.setSalt(null);
			}
			return successPage(page,dto.getPageNum()+1);
		}catch (Exception e){
			LOGGER.error("分页查看园区用户列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 分页查看用户列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listUserBase.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listUserBase(@RequestBody AbstractDTO dto) {
		try {
			Page<UserBase> page = userService.listUserBase(dto);
			for (UserBase userBase : page) {
				userBase.setPassword(null);
				userBase.setSalt(null);
			}
			return successPage(page,dto.getPageNum()+1);
		}catch (Exception e){
			LOGGER.error("分页查看用户列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 添加/修改用户
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "saveUserBase.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveUserBase(@RequestBody UserBaseDTO dto) {
		if(dto==null||StringUtil.isEmpty(dto.getTelphone())||StringUtil.isEmpty(dto.getUserPark())||StringUtil.isEmpty(dto.getUserType())
				||StringUtil.isEmpty(dto.getUserEmail())||StringUtil.isEmpty(dto.getUserTime())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = userService.saveUserBase(dto);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("添加/修改用户失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查看用户详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getUserBase.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getUserBase(String id) {
		if(id==null|| StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long aLong;
		try{
			aLong = Long.valueOf(id);
		}catch (Exception e){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			UserBase base = userService.findById(aLong);
			base.setPassword(null);
			base.setSalt(null);
			return success(base);
		}catch (Exception e){
			LOGGER.error("分页查看用户列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dropUserBase.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult dropUserBase(String id) {
		if(id==null|| StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long aLong;
		try{
			aLong = Long.valueOf(id);
		}catch (Exception e){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = userService.dropUserBase(aLong);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("删除用户失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	
	/**
	 * 统计用户天眼查查询金额
	 */
	@RequestMapping(value="/getAccountByUser.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getAccountByUser(@RequestBody AccountDTO dto){
		List<AccountDTO> out = null;
		try{
			out = userService.getAccountByUser(dto);
		}catch(Exception e){
			LOGGER.info("查询天眼查用户金额失败");
			return error("查询天眼查用户金额失败");
		}
		return success(out);
	}

	/**
	 * 修改用户能否单点登录
	 * @param id
	 * @param isSingle
	 * @return
	 */
	@RequestMapping(value = "modifyIsSingle.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult modifyIsSingle(String id,String isSingle) {
		if(id==null||StringUtil.isEmpty(id)||isSingle==null||StringUtil.isEmpty(isSingle)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long aLong;
		Integer aInt;
		try{
			aInt = Integer.valueOf(isSingle);
			aLong = Long.valueOf(id);
		}catch (Exception e){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = userService.modifyIsSingle(aLong,aInt);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("修改用户能否单点登录失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 查看待审核会员账号分页列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "getAccountList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getAccountList(@RequestBody AccountSearchDTO dto) {
		if (null == dto || StringUtil.isEmpty(dto.getType()) || StringUtil.isEmpty(dto.getTime())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<UserBase> page = userService.getAccountList(dto);
			for (UserBase userBase : page) {
				userBase.setPassword(null);
				userBase.setSalt(null);
			}
			return successPage(page,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 账号审核
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "modifyIsCheck.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult modifyIsCheck(String id) {
		if(id==null||StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long aLong;
		try{
			aLong = Long.valueOf(id);
		}catch (Exception e){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = userService.modifyIsCheck(aLong);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("账号审核失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

    /**
     * 账号查找
     * @param id
     * @return
     */
	@RequestMapping(value = "/getParkUser.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult findParkAccount(String id){
		List<UserBase> out = null;
		try{
			out = userService.getAccountByName(id);
		}catch(Exception e){
			LOGGER.info("查询失败",e);
			return error(e.getMessage());
		}
		return success(out);
	}


	/**
	 * 权限修改/添加
	 * @param
	 * @return
	 */

	@RequestMapping(value="/saveUserRolePer.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult UserRolePermission(@RequestBody RolePermission rolePermission) {

		try {
			Boolean flag = userService.saveUserRolePermission(rolePermission);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("用户权限添加失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}



}

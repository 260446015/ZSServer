package com.huishu.ZSServer.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.entity.UserLabel;
import com.huishu.ZSServer.entity.dto.RegisterDTO;
import com.huishu.ZSServer.entity.dto.UserDTO;
import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.repository.user.UserBaseRepository;
import com.huishu.ZSServer.repository.user.UserLabelRepository;
import com.huishu.ZSServer.security.Digests;
import com.huishu.ZSServer.security.Encodes;
import com.huishu.ZSServer.service.user.UserBaseService;
import com.huishu.ZSServer.common.conf.ConfConstant;

@Service
public class UserBaseServiceImpl implements UserBaseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseServiceImpl.class);
	@Autowired
	private UserBaseRepository userBaseRepository;
	@Autowired
	private UserLabelRepository userLabelRepository;
	@Override
	public UserBase findByUserId(long id) {
		return userBaseRepository.findOne(id);
	}

	@Override
	public String modifyPassword(long id, String beforPassword, String newPassword) {
		UserBase one = userBaseRepository.findOne(id);
		String oldPassword = getPasswordDB(beforPassword, one.getSalt());
		String password = getPasswordDB(newPassword, one.getSalt());
		if (oldPassword.equals(one.getPassword())) {
			one.setPassword(password);
			UserBase save = userBaseRepository.save(one);
			if (save == null) {
				return MsgConstant.OPERATION_ERROR;
			}else{
				return MsgConstant.OPERATION_SUCCESS;
			}
		}
		return MsgConstant.OLDPASSWORD_ERROR;
	}

	@Override
	public Boolean modifyInformation(long id, UserDTO dto) {
		UserBase one = userBaseRepository.findOne(id);
		one.setRealName(dto.getRealName());
		one.setTelphone(dto.getTelphone());
		one.setUserEmail(dto.getUserEmail());
		one.setUserDepartment(dto.getUserDepartment());
		one.setUserJob(dto.getUserJob());
		one.setUserPark(dto.getUserPark());
		UserBase save = userBaseRepository.save(one);
		if (save == null) {
			return false;
		}
		return true;
	}
	
	private String getPasswordDB(String password,String salt){
		byte[] saltByte = Encodes.decodeHex(salt);
		byte[] passwordByte = Digests.sha1(password.getBytes(), saltByte, Encodes.HASH_INTERATIONS);
		String passwordTrue = Encodes.encodeHex(passwordByte);
		return passwordTrue;
	}

	/**
	 * 获取用户标签信息
	 */
	@Override
	public UserLabel findLabelByUserId(Long uid) {
		UserLabel userlabel = userLabelRepository.findByUid(uid);
		if(userlabel==null){
			return null;
		}else{
			return userlabel;
		}
	}

	/**
	 * 保存或者更新标签信息
	 */
	@Override
	public boolean updateLabel(UserLabel user) {
		UserLabel save = userLabelRepository.save(user);
		if(save!= null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public UserBase findUserByTelphoneAndRealName(String telphone,String realName) {
		return userBaseRepository.findByTelphoneAndRealName(telphone, realName);
	}

	/**
	 * 添加试用账户
	 */
	@SuppressWarnings("unused")
	@Override
	public AjaxResult addRegisterUser(RegisterDTO dto) {
		AjaxResult result = new AjaxResult();
		//根据用户的手机号查看是否已经被注册
		UserBase user = userBaseRepository.findByTelphoneAndUserType(dto.getTelphone(), "user");
		if(user!=null){
			result.setMessage(MsgConstant.PHONE_REPEAT).setSuccess(false);
			return result;
		}
		UserBase save = null;
		try {
			UserBase base = new UserBase();
			byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
			base.setSalt(Encodes.encodeHex(salt));
			byte[] password = Digests.sha1(ConfConstant.DEFAULT_PASSWORD.getBytes(), salt, Encodes.HASH_INTERATIONS);
			base.setPassword(Encodes.encodeHex(password));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			base.setCreateTime(sdf.format(new Date()));
			base.setImageUrl("/images/default.jpg" );
			base.setArea(dto.getArea());
			base.setIsCheck(0);
			base.setIsSingle(0);
			base.setUserLevel(0);
			base.setIsWarn(0);
			base.setRealName(dto.getRealName());
			base.setTelphone(dto.getTelphone());
			base.setUserComp(dto.getUserComp());
			base.setUserDepartment(dto.getUserDepartment());
			base.setUserAccount(dto.getTelphone());
			base.setUserEmail(dto.getUserEmail());
			base.setUserJob(dto.getUserJob());
			base.setUserPark(dto.getUserPark());
			base.setUserType(MsgConstant.USER_TYPE);
			save = userBaseRepository.save(base);
		} catch (Exception e) {
			LOGGER.error("保存用户信息出错", e);
		}
		if (save == null) {
			return result.setSuccess(false).setData(false).setMessage(MsgConstant.REGISTER_ERROR);
		}
		return result.setSuccess(true).setData(true).setMessage(MsgConstant.REGISTER_SUCCESS);
	}

}

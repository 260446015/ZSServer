package com.huishu.ZSServer.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.entity.UserLabel;
import com.huishu.ZSServer.entity.dto.UserDTO;
import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.repository.user.UserBaseRepository;
import com.huishu.ZSServer.repository.user.UserLabelRepository;
import com.huishu.ZSServer.security.Digests;
import com.huishu.ZSServer.security.Encodes;
import com.huishu.ZSServer.service.user.UserBaseService;

@Service
public class UserBaseServiceImpl implements UserBaseService {

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

}

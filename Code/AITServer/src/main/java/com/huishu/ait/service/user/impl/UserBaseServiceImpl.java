package com.huishu.ait.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.common.conf.ConfConstant;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.RegisterDTO;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.security.Digests;
import com.huishu.ait.security.Encodes;
import com.huishu.ait.service.user.UserBaseService;

@Service
public class UserBaseServiceImpl implements UserBaseService{
	
	@Autowired
	private UserBaseRepository userBaseRepository;

	@Override
	public UserBase findUserByUserAccount(String userAccount) {
		return userBaseRepository.findByUserAccount(userAccount);
	}

	@Override
	public UserBase findUserByTelphone(String telphone) {
		return userBaseRepository.findByTelphone(telphone);
	}

	@Override
	public AjaxResult addRegisterUser(RegisterDTO dto) {
		AjaxResult result = new AjaxResult();
		UserBase account = findUserByUserAccount(dto.getUserAccount());
		if(account!=null){
			return result.setSuccess(false).setMessage(MsgConstant.ACCOUNT_REPEAT);
		}
		UserBase email = userBaseRepository.findByUserEmail(dto.getUserEmail());
		if(email!=null){
			return result.setSuccess(false).setMessage(MsgConstant.EMAIL_REPEAT);
		}
		UserBase base = new UserBase();
		byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
		base.setSalt(Encodes.encodeHex(salt));
		byte[] password = Digests.sha1(ConfConstant.DEFAULT_PASSWORD.getBytes(), salt, Encodes.HASH_INTERATIONS);
		base.setPassword(Encodes.encodeHex(password));
		base.setTelphone(dto.getTelphone());
		base.setUserAccount(dto.getUserAccount());
		base.setUserComp(dto.getCompany());
		base.setUserDepartment(dto.getDepartment());
		base.setUserEmail(dto.getUserEmail());
		base.setUserPark(dto.getPark());
		base.setUserType(dto.getUserType());
		UserBase save = userBaseRepository.save(base);
		if(save==null){
			return result.setSuccess(false).setMessage(MsgConstant.REGISTER_ERROR);
		}
		return result.setSuccess(true).setMessage(MsgConstant.REGISTER_SUCCESS);
	}

	@Override
	public UserBase findUserByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

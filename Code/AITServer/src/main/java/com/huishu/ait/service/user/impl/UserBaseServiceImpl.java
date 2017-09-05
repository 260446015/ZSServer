package com.huishu.ait.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.common.conf.ConfConstant;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.FindPasswordDTO;
import com.huishu.ait.entity.dto.RegisterDTO;
import com.huishu.ait.entity.dto.UserPasswordDTO;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.security.Digests;
import com.huishu.ait.security.Encodes;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.user.UserBaseService;

@Service
public class UserBaseServiceImpl extends AbstractService implements UserBaseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseServiceImpl.class);

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
	@Transactional
	public AjaxResult addRegisterUser(RegisterDTO dto) {
		AjaxResult result = new AjaxResult();
		UserBase account = findUserByUserAccount(dto.getUserAccount());
		if (account != null) {
			return result.setSuccess(false).setMessage(MsgConstant.ACCOUNT_REPEAT);
		}
		UserBase email = userBaseRepository.findByUserEmail(dto.getUserEmail());
		if (email != null) {
			return result.setSuccess(false).setMessage(MsgConstant.EMAIL_REPEAT);
		}
		UserBase save=null;
		try {
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			/*Calendar c = Calendar.getInstance();  
			c.setTime(new Date());
			Calendar nextDate = (Calendar) c.clone();  
			nextDate.add(Calendar.MONTH, +1);  */
			base.setCreateTime(sdf.format(new Date()));
			base.setUserLevel(0);
			//base.setExpireTime(sdf.format(nextDate.getTime()));
			save = userBaseRepository.save(base);
		} catch (Exception e) {
			LOGGER.error("保存用户信息出错", e);
		}
		if (save == null) {
			return result.setSuccess(false).setMessage(MsgConstant.REGISTER_ERROR);
		}
		return result.setSuccess(true).setMessage(MsgConstant.REGISTER_SUCCESS);
	}

	@Override
	public UserBase findUserByUserId(long id) {
		return userBaseRepository.findOne(id);
	}

	@Override
	@Transactional
	public AjaxResult modifyPassword(UserPasswordDTO param) {
		AjaxResult result = new AjaxResult();
		Long userId = param.getUserId();
		UserBase one = userBaseRepository.findOne(userId);
		String oldPassword = getPasswordDB(param.getOldPassword(),one.getSalt());
		String newPassword = getPasswordDB(param.getNewPassword(),one.getSalt());
		if (one != null && oldPassword.equals(one.getPassword())) {
			Integer integer = userBaseRepository.modifyPassword(newPassword, userId);
			if (integer == 1) {
				return result.setSuccess(true).setMessage(MsgConstant.PASSWORD_SUCCESS);
			} else {
				return result.setSuccess(false).setMessage(MsgConstant.CHANGE_ERROR);
			}
		}
		return result.setSuccess(false).setMessage(MsgConstant.OLDPASSWORD_ERROR);
	}

	@Override
	@Transactional
	public AjaxResult findPassword(FindPasswordDTO param) {
		AjaxResult result = new AjaxResult();
		UserBase one = userBaseRepository.findByUserAccount(param.getUserAccount());
		if (one != null) {
			String newPassword = getPasswordDB(param.getNewPassword(),one.getSalt());
			Long id = one.getId();
			Integer integer = userBaseRepository.modifyPassword(newPassword,id );
			if (integer == 1) {
				return result.setSuccess(true).setMessage(MsgConstant.PASSWORD_SUCCESS);
			} else {
				return result.setSuccess(false).setMessage(MsgConstant.CHANGE_ERROR);
			}
		}
		return result.setSuccess(false).setMessage(MsgConstant.USER_ERROR);
	}

	@Override
	@Transactional
	public AjaxResult auditAccount(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AjaxResult modifyEmail(Long userId, String email) {
		AjaxResult result = new AjaxResult();
		Integer integer = userBaseRepository.updateEmail(email, userId);
		if (integer == 1) {
			return result.setSuccess(true).setMessage(MsgConstant.EMAIL_SUCCESS);
		} else {
			return result.setSuccess(false).setMessage(MsgConstant.EMAIL_CHANGE_ERROR);
		}
	}

}

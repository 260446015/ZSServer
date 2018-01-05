package com.huishu.ManageServer.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.UserBaseDTO;
import com.huishu.ManageServer.repository.datajpa.first.UserRepository;
import com.huishu.ManageServer.security.Digests;
import com.huishu.ManageServer.security.Encodes;
import com.huishu.ManageServer.service.user.UserService;

/**
 * 用户管理实现类
 *
 * @author yindq
 * @date 2018/1/4
 */
@Service
@Transactional("firstTransactionManager")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<UserBase> listUserBase(AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<UserBase> list = userRepository.findPage(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = userRepository.count();
		Page<UserBase> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	@Override
	public Boolean saveUserBase(UserBaseDTO dto) {
		UserBase userBase = new UserBase();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = format.format(calendar.getTime());
		userBase.setCreateTime(now);
		String end;
		if(dto.getUserTime().equals("1")){
			calendar.add(Calendar.MONTH ,+1);
			end = format.format(calendar.getTime());
		}else if(dto.getUserTime().equals("12")){
			calendar.add(Calendar.MONTH ,+12);
			end = format.format(calendar.getTime());
		}else if (dto.getUserTime().equals("24")){
			calendar.add(Calendar.MONTH ,+24);
			end = format.format(calendar.getTime());
		}else if (dto.getUserTime().equals("36")){
			calendar.add(Calendar.MONTH ,+36);
			end = format.format(calendar.getTime());
		}else {
			calendar.add(Calendar.MONTH ,+120);
			end = format.format(calendar.getTime());
		}
		userBase.setExpireTime(end);
		userBase.setId(dto.getId());
		userBase.setImageUrl(dto.getImageUrl());
		userBase.setIsCheck(1);
		userBase.setIsWarn(0);
		byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
		byte[] hashPassword = Digests.sha1(MsgConstant.USER_PASSWORD.getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
				Encodes.HASH_INTERATIONS);
		userBase.setPassword(Encodes.encodeHex(hashPassword));
		userBase.setRealName(dto.getRealName());
		userBase.setSalt(Encodes.encodeHex(salt));
		userBase.setStartTime(now);
		userBase.setTelphone(dto.getTelphone());
		userBase.setUserAccount(dto.getUserAccount());
		userBase.setUserDepartment(dto.getUserDepartment());
		userBase.setUserEmail(dto.getUserEmail());
		userBase.setUserJob(dto.getUserJob());
		userBase.setUserLevel(dto.getUserLevel());
		userBase.setUserPark(dto.getUserPark());
		userBase.setUserType(dto.getUserType());
		UserBase save = userRepository.save(userBase);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public Boolean dropUserBase(Long id) {
		userRepository.delete(id);
		return true;
	}

	@Override
	public UserBase findById(Long id) {
		return userRepository.findOne(id);
	}
}

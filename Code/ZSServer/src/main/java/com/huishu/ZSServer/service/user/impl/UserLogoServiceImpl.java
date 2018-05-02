package com.huishu.ZSServer.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.repository.user.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.entity.user.UserLogo;
import com.huishu.ZSServer.repository.user.UserLogoRepository;
import com.huishu.ZSServer.service.user.UserLogoService;

@Service
public class UserLogoServiceImpl implements UserLogoService{
	
	@Autowired
	private UserLogoRepository userLogoRepository;

	@Autowired
	private UserBaseRepository userBaseRepository;
	@Override
	public Boolean addLoginLogo(long userId) {
		UserLogo one = new UserLogo();
		one.setUserId(userId);
		one.setLogoType(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = format.format(new Date());
		one.setLoginTime(today);
		UserLogo save = userLogoRepository.save(one);
		if (save == null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public Boolean addOperationLogo(long userId,String search) {
		UserLogo logo = userLogoRepository.findByUserIdAndSearchCompany(userId, search);
		if(logo==null){
			UserLogo one = new UserLogo();
			one.setUserId(userId);
			one.setLogoType(1);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = format.format(new Date());
			one.setOperationTime(today);
			one.setSearchCompany(search);
			one.setSearchCount(1);
			UserLogo save = userLogoRepository.save(one);
			if (save == null) {
				return false;
			}else{
				return true;
			}
		}else{
			logo.setSearchCount(logo.getSearchCount()+1);
			UserLogo save = userLogoRepository.save(logo);
			if (save == null) {
				return false;
			}else{
				return true;
			}
		}
	}

}

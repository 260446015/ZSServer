package com.huishu.ait.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.service.user.UserBaseService;

@Service
public class UserBaseServiceImpl implements UserBaseService{
	
	@Autowired
	private UserBaseRepository userBaseRepository;

	@Override
	public UserBase getUserByUserAccount(String userAccount) {
		return userBaseRepository.findByUserAccount(userAccount);
	}

}

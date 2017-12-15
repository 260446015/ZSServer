package com.huishu.ZSServer.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.repository.user.UserBaseRepository;
import com.huishu.ZSServer.service.user.UserBaseService;

@Service
public class UserBaseServiceImpl implements UserBaseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseServiceImpl.class);

	@Autowired
	private UserBaseRepository userBaseRepository;

	@Override
	public UserBase findByUserId(long id) {
		return userBaseRepository.findOne(id);
	}


}

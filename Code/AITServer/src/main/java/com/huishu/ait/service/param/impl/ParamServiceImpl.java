package com.huishu.ait.service.param.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Param;
import com.huishu.ait.repository.param.ParamRepository;
import com.huishu.ait.service.param.ParamService;

/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 
 * 公用参数对接serviceImpl方法
 */
@Service
public class ParamServiceImpl  implements ParamService{
	
	@Autowired
	private ParamRepository pr;
	/**添加参数*/
	@Override
	public boolean insert(Param info) {
		 Param save = pr.save(info);
	    if(save!= null){
			 return true;
		 }
		return false;
	}
	/***
	 * 根据id查询用户个数
	 */
	@Override
	public int findOne(Long uid) {
		List<Param> list = pr.findByUid(uid);
		if(list!= null){
			return 1;
		}
		return 0;
	}
	
	/**
	 * 删除
	 */
	@Override
	public boolean deleteParamAllByUid(Long uid) {
		try {
		  pr.delete(uid);
			
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}
	/**
	 * 通过id查询所有信息
	 */
	@Override
	public List<Param> findByUid(Long uid) {
		return pr.findByUid(uid);
	}

}

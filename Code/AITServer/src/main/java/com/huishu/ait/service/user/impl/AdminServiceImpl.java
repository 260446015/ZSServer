package com.huishu.ait.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.common.util.DateUtils;
import com.huishu.ait.controller.user.AdminController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AccountSearchDTO;
import com.huishu.ait.entity.dto.GardenSearchDTO;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.user.AdminService;

import net.minidev.json.JSONObject;

@Service
public class AdminServiceImpl extends AbstractService implements AdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@Override
	public Boolean auditAccount(UserBase base) {
		try {
			Calendar nextDate = DateUtils.getNow();
			nextDate.add(Calendar.MONTH, +1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			base.setStartTime(sdf.format(new Date()));
			base.setExpireTime(sdf.format(nextDate.getTime()));
			userBaseRepository.save(base);
			return true;
		} catch (Exception e) {
			LOGGER.error("预警失败！",e);
			return false;
		}
	}

	@Override
	public AjaxResult globalManagement() {
		AjaxResult result = new AjaxResult();
		Integer memberNum = userBaseRepository.findMemberNum("user");
		Integer expireMemberNum = userBaseRepository.findExpireMemberNum("user");
		List<Object[]> areaRatio = userBaseRepository.findAreaRatio("user");
		List<Object[]> industryRatio = userBaseRepository.findIndustryRatio();
		JSONObject object = new JSONObject();
		object.put("memberNum", memberNum);
		object.put("expireMemberNum", expireMemberNum);
		object.put("areaRatio", convertData(areaRatio));
		object.put("industryRatio",convertData(industryRatio ));
		return result.setSuccess(true).setData(object);
	}

	@Override
	public List<UserBase> getAccountList(AccountSearchDTO searchModel) {
		String[] times = analysisDate(searchModel.getDay());
		Integer count = userBaseRepository.findUserListCount(Integer.valueOf(searchModel.getType()),times[0], times[1],searchModel.getSearch());
		searchModel.setTotalSize(count);
		List<UserBase> list = userBaseRepository.findUserList(Integer.valueOf(searchModel.getType()), searchModel.getPageFrom(), searchModel.getPageSize(),times[0], times[1],searchModel.getSearch());
		return list;
	}
	

	@Override
	public List<UserBase> getWarningAccountList(AccountSearchDTO searchModel) {
		String[] times = analysisDate(searchModel.getDay());
		Integer count = userBaseRepository.findWarningUserListCount(Integer.valueOf(searchModel.getType()),times[0], times[1],searchModel.getSearch());
		searchModel.setTotalSize(count);
		List<UserBase> list = userBaseRepository.findWarningUserList(Integer.valueOf(searchModel.getType()), searchModel.getPageFrom(), searchModel.getPageSize(),times[0], times[1],searchModel.getSearch());
		return list;
	}
	
	@Override
	public AjaxResult warnAccount(Long id,Integer status) {
		AjaxResult result = new AjaxResult();
		UserBase base = userBaseRepository.findOne(id);
		base.setIsWarn(status);
		userBaseRepository.save(base);
		return result.setSuccess(true).setMessage("操作成功");
	}
	
	@Override
	public List<UserBase> getGardenList(GardenSearchDTO searchModel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String[] analysisDate(String day){
		String time1;
		String time2;
		Calendar nextDate = DateUtils.getNow();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day.equals("今日")){
			nextDate.add(Calendar.DATE, +1);
			time1=sdf.format(new Date());
			time2=sdf.format(nextDate.getTime());
		}else if(day.equals("昨日")){
			nextDate.add(Calendar.DATE, -1);
			time2=sdf.format(new Date());
			time1=sdf.format(nextDate.getTime());
		}else if(day.equals("近一周")){
			nextDate.add(Calendar.DATE, -6);
			time1=sdf.format(nextDate.getTime());
			nextDate.add(Calendar.DATE, +7);
			time2=sdf.format(nextDate.getTime());
		}else {
			time1="2017-01-01";
			time2=sdf.format(nextDate.getTime());
		}
		String[] times={time1,time2};
		return times;
	}

}

package com.huishu.ait.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.common.util.DateUtils;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AccountSearchDTO;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.user.AdminService;

import net.minidev.json.JSONObject;

@Service
public class AdminServiceImpl extends AbstractService implements AdminService {

	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@Override
	public AjaxResult auditAccount(long id) {
		UserBase base = userBaseRepository.findOne(id);
		Calendar nextDate = DateUtils.getNow();
		nextDate.add(Calendar.MONTH, +1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		base.setStartTime(sdf.format(new Date()));
		base.setExpireTime(sdf.format(nextDate.getTime()));
		UserBase save = userBaseRepository.save(base);
		AjaxResult result = new AjaxResult();
		if (save != null) {
			return result.setSuccess(true).setMessage("审核通过");
		} else {
			return result.setSuccess(false).setMessage("审核失败，请稍后再试");
		}
	}

	@Override
	public AjaxResult globalManagement(String park) {
		AjaxResult result = new AjaxResult();
		Integer memberNum = userBaseRepository.findMemberNum("user");
		Integer expireMemberNum = userBaseRepository.findExpireMemberNum("user");
		List<Object[]> areaRatio = userBaseRepository.findAreaRatio("user");
		List<Object[]> industryRatio = userBaseRepository.findIndustryRatio(park);
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
		if(searchModel.getType().equals("0")){
			Integer count = userBaseRepository.findUserListCount(Integer.valueOf(searchModel.getType()),times[0], times[1]);
			searchModel.setTotalSize(count);
			List<UserBase> list = userBaseRepository.findUserList(Integer.valueOf(searchModel.getType()), searchModel.getPageFrom(), searchModel.getPageSize(),times[0], times[1]);
			return list;
		}else{
			return null;
		}
	}
	

	@Override
	public List<UserBase> getWarningAccountList(AccountSearchDTO searchModel) {
		String[] times = analysisDate(searchModel.getDay());
		Integer count = userBaseRepository.findWarningUserListCount(Integer.valueOf(searchModel.getType()),times[0], times[1]);
		searchModel.setTotalSize(count);
		List<UserBase> list = userBaseRepository.findWarningUserList(Integer.valueOf(searchModel.getType()), searchModel.getPageFrom(), searchModel.getPageSize(),times[0], times[1]);
		return list;
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

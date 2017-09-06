package com.huishu.ait.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.common.Ratio;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.user.AdminService;

import net.minidev.json.JSONObject;

@Service
public class AdminServiceImpl extends AbstractService implements AdminService {

	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@Override
	@Transactional
	public AjaxResult auditAccount(long id) {
		UserBase base = userBaseRepository.findOne(id);
		Calendar c = Calendar.getInstance();  
		c.setTime(new Date());
		Calendar nextDate = (Calendar) c.clone();  
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
		//List<Ratio> areaRatio = userBaseRepository.findAreaRatio("user");
		//List<Ratio> industryRatio = userBaseRepository.findIndustryRatio(park);
		List<Ratio> industryRatio = null;
		List<Ratio> areaRatio = null;
		JSONObject object = new JSONObject();
		object.put("memberNum", memberNum);
		object.put("expireMemberNum", expireMemberNum);
		object.put("areaRatio", convertData(areaRatio));
		object.put("industryRatio",convertData(industryRatio ));
		return result.setSuccess(true).setData(object);
	}

}

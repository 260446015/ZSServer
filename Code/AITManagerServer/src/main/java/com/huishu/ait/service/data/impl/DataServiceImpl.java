package com.huishu.ait.service.data.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Log;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExternalFlowRepository;
import com.huishu.ait.repository.dataLog.LogRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.data.DataService;

/**
 *  管理文章数据相关实现类
 *
 * @author yindq
 * @date 2017/10/25
 */
@Service
public class DataServiceImpl extends AbstractService implements DataService {
	
	@Autowired
	private ExternalFlowRepository repository;
	@Autowired
	private LogRepository logRepository;

	@Override
	public AjaxResult addData(AITInfo info) {
		AjaxResult result = new AjaxResult();
		try {
			if(info.getPublishDate()!=null){
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf1.parse(info.getPublishDate());
				info.setPublishTime(sdf2.format(date));
			}
			info.setHitCount(getRandomNumber());
			info.setReplyCount(getRandomNumber());
			info.setSupportCount(getRandomNumber());
			info.setHasWarn(false);
			info.setIstop(false);
			info.setArticleLink(info.getSourceLink());
			AITInfo save = repository.save(info);
			if (save == null) {
				return result.setSuccess(false).setMessage("添加失败，请稍后再试");
			}
			return result.setSuccess(true).setMessage("添加成功");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public AITInfo transformData(String value) {
		String[] split = value.split("---");
		AITInfo info = new AITInfo();
		info.setArea(split[13]);
		info.setAuthor(split[4]);
		info.setBusiness(split[11]);
		info.setContent(split[8]);
		info.setDimension(split[0]);
		info.setEmotion(split[2]);
		info.setIndustry(split[9]);
		info.setIndustryLabel(split[10]);
		info.setPark(split[11]);
		info.setPublishDate(split[5]);
		info.setSource(split[6]);
		info.setSourceLink(split[7]);
		info.setTitle(split[1]);
		info.setVector(split[3]);
		return info;
	}

	@Override
	public Boolean checkString(String header) {
		String[] split = header.split("---");
		if(split.length!=14){
			return false;
		}
		if(split[0].equals("模块")&&split[1].equals("标题")&&split[2].equals("情感")
				&&split[3].equals("载体")&&split[4].equals("作者")&&split[5].equals("时间")
				&&split[6].equals("来源")&&split[7].equals("原文网址")&&split[8].equals("内容")
				&&split[9].equals("产业")&&split[10].equals("产业标签")&&split[11].equals("涉及公司")
				&&split[12].equals("涉及园区")&&split[13].equals("地域")){
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean printLog(String name, String message) {
		Log log = new Log();
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdate = sdf.format(date);
		log.setCreateTime(createdate);
		log.setMessage(message);
		log.setName(name);
		Log save = logRepository.save(log);
		if (save == null) {
			return false;
		}
		return true;
	}
}

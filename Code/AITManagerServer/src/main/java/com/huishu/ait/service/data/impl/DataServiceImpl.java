package com.huishu.ait.service.data.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExternalFlowRepository;
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
			info.setArticleLink(info.getSourceLink());
			AITInfo save = repository.save(info);
			if (save == null) {
				return result.setSuccess(false).setMessage("添加失败，请稍后再试");
			}
			return result.setSuccess(true).setMessage("添加成功");
		} catch (ParseException e) {
			return result.setSuccess(false).setMessage("添加失败，请稍后再试");
		}
		
	}

}

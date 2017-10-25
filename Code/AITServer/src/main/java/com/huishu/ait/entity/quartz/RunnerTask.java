package com.huishu.ait.entity.quartz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.common.util.StringUtil;

@Component // 此注解必加  
@EnableScheduling // 此注解必加  
public class RunnerTask {
	
	@Autowired
	public RunnerTask(EhCacheManager ehCacheManager) {
		this.cache = ehCacheManager.getCache("oauth2-cache");
	}
	private static Logger logger = LoggerFactory.getLogger(RunnerTask.class);
	
	private Cache cache;

	public void getChangeInfo() {
		try {
			String accessToken = (String) cache.get("accessToken");
			if(StringUtil.isEmpty(accessToken)){//检查token是否已经被清除，是否需要重新获取
				Map<String, String> params = new HashMap<>();
				params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
				params.put("response_type", "code");
				params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
				HttpUtils.sendGet(ConstantKey.OAUTH_CLIENT_ACCESS_CODE, params);
				accessToken = (String) cache.get("accessToken");
			}
			HttpUtils.sendGet("http://localhost:8092/apis/oauth/getChangeInfo.json");
		} catch (CacheException e) {
			logger.error("定时器调度缓存出现异常:", e.getMessage());
		} catch (IOException e) {
			logger.error("定时器调度发生io异常:", e.getMessage());
		}
	}

}

package com.huishu.ait.app.conf;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.security.ShiroDbRealm.ShiroUser;
import com.huishu.ait.service.user.UserBaseService;
import com.huishu.ait.service.user.backstage.AdminService;

@Aspect
@Configuration
public class AopConfiguration {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AopConfiguration.class);

	@Autowired
	private UserBaseService userBaseService;
	@Autowired
	private AdminService adminService;
	
    // 定义切点Pointcut
    @Pointcut("execution(* com.huishu.ait.controller.*.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        LOGGER.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        LOGGER.info("请求结束，controller的返回值是 " + JSONObject.toJSONString(result));
        
        if (result instanceof AjaxResult) {
        	AjaxResult ajaxResult =(AjaxResult) result;
        	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        	Long id = user.getId();
        	UserBase base = userBaseService.findUserByUserId(id);
        	if(base.getIsWarn()==1){
                adminService.warnAccount(id,0);
                ajaxResult.setTime(base.getExpireTime());
            }
        }
        return result;
    }
}

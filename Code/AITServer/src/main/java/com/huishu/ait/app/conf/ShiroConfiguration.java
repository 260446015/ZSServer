package com.huishu.ait.app.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huishu.ait.controller.garden.GardenController;
import com.huishu.ait.security.CustomCredentialsMatcher;
import com.huishu.ait.security.MyFormAuthenticationFilter;
import com.huishu.ait.security.ShiroDbRealm;

/**
 * shiro的配置
 * 
 * @author yindq
 * @date 2017年8月2日
 */
@Configuration
public class ShiroConfiguration {
	private static Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
	//拦截器，必须保证有序
	private final static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	private final static Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

	/**
	 * ShiroFilter
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		/*
		 * 配置访问权限
		 * anon：表示全部放权的资源路径，authc：表示需要认证才可以访问
		 */
		filterChainDefinitionMap.put("/apis/logout.do", "logout");
		
		filterChainDefinitionMap.put("/apis/security/generateKey.do", "anon");
		filterChainDefinitionMap.put("/apis/security/captcha.do", "anon");
		filterChainDefinitionMap.put("/apis/register.json", "anon");
		filterChainDefinitionMap.put("/apis/getPhoneCaptcha.json", "anon");
		filterChainDefinitionMap.put("/apis/user/findPassword.json", "anon");
		filterChainDefinitionMap.put("/apis/login.do", "anon");

//		filterChainDefinitionMap.put("/apis/**", "authc");
		filterChainDefinitionMap.put("/apis/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/apis/login.do");
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 配置核心安全事务管理器
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSessionManager(new DefaultWebSessionManager());
		securityManager.setRealm(getShiroDbRealm());
		LOGGER.info("===============shiro已经加载================");
		// 用户授权/认证信息Cache
		securityManager.setCacheManager(getEhCacheManager());
//		securityManager.setRememberMeManager(rememberMeManager);
		return securityManager;
	}
	
	/**
	 * 配置自定义的权限登录器
	 * (这个需要自己写，账号密码校验；权限等)
	 * @return
	 */
	@Bean(name = "shiroDbRealm")
	public ShiroDbRealm getShiroDbRealm() {
		ShiroDbRealm shiroRealm = new ShiroDbRealm();
		//配置自定义的密码比较器
		shiroRealm.setCredentialsMatcher(getCustomCredentialsMatcher());
		return shiroRealm;
	}

	/**
	 * 配置EhCache 缓存
	 * @return
	 */
	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
	        return new LifecycleBeanPostProcessor();
    }
	
	/**
	 * 开启shiro aop注解支持.
	 * @return
	 */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor auth = new AuthorizationAttributeSourceAdvisor();
        auth.setSecurityManager(getDefaultWebSecurityManager());
        return auth;
    }
    
    /**
     * FormAuthenticationFilter
     * @return
     */
    @Bean(name = "loginFormAuthenticationFilter")
    public MyFormAuthenticationFilter getMyFormAuthenticationFilter() {
        return new MyFormAuthenticationFilter();
    }

    /**
     * 自定义校验密码
     * @return
     */
    @Bean(name = "customCredentialsMatcher")
    public CustomCredentialsMatcher getCustomCredentialsMatcher() {
        return new CustomCredentialsMatcher();
    
    }
}
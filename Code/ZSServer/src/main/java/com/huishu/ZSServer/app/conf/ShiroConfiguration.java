package com.huishu.ZSServer.app.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huishu.ZSServer.controller.garden.GardenController;
import com.huishu.ZSServer.security.ShiroDbRealm;

/**
 * shiro的配置
 * 
 * @author yindq
 * @date 2017年8月2日
 */
@Configuration
public class ShiroConfiguration {
	private static Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
	// 拦截器，必须保证有序
	private final static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	private final static Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

	/**
	 * ShiroFilter
	 * 
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		//配置访问权限 anon：表示全部放权的资源路径，authc：表示需要认证才可以访问
		filterChainDefinitionMap.put("/logout", "logout");

		filterChainDefinitionMap.put("/login.html", "anon");

		filterChainDefinitionMap.put("/**", "anon");
//		filterChainDefinitionMap.put("/**/*.html", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.html");
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}

	/**
	 * 配置核心安全事务管理器
	 * 
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(getShiroDbRealm());
		LOGGER.info("===============shiro已经加载================");
		// securityManager.setRememberMeManager(rememberMeManager);
		return securityManager;
	}

	/**
	 * 配置自定义的权限登录器 (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean(name = "shiroDbRealm")
	public ShiroDbRealm getShiroDbRealm() {
		ShiroDbRealm shiroRealm = new ShiroDbRealm();
		// 启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
		shiroRealm.setAuthenticationCachingEnabled(true);
		return shiroRealm;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 开启shiro aop注解支持.
	 * 
	 * @return
	 */
	@Bean(name = "authorizationAttributeSourceAdvisor")
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor auth = new AuthorizationAttributeSourceAdvisor();
		auth.setSecurityManager(getDefaultWebSecurityManager());
		return auth;
	}

}
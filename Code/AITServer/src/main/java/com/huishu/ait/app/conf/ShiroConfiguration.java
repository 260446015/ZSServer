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
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huishu.ait.controller.garden.GardenController;
import com.huishu.ait.security.CustomCredentialsMatcher;
import com.huishu.ait.security.MyFormAuthenticationFilter;
import com.huishu.ait.security.MyUserFilter;
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
	//必须保证有序
	private final static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	private final static Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

	/**
	 * ShiroFilter
	 * 注册DelegatingFilterProxy（Shiro） 集成Shiro有2种方法：
	 *  1.按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
	 *  2.直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern， 默认拦截 /* ）
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		/*
		 * 配置访问权限
		 * anon：表示全部放权的资源路径，authc：表示需要认证才可以访问
		 */
		filterChainDefinitionMap.put("/logout.do", "logout");

//		filterChainDefinitionMap.put("/security/generateKey.do", "anon");
//		filterChainDefinitionMap.put("/security/captcha.do", "anon");
//		filterChainDefinitionMap.put("/addTrial.json", "anon");
//		filterChainDefinitionMap.put("/getPhoneCaptcha.json", "anon");
//		filterChainDefinitionMap.put("/findPassword.json", "anon");

		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
//		filterChainDefinitionMap.put("/**", "user");
		filterChainDefinitionMap.put("/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		filters.put("authc", new MyFormAuthenticationFilter());
		filters.put("user", new MyUserFilter());
//		shiroFilterFactoryBean.setFilters(filters);

		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");
		shiroFilterFactoryBean.setLoginUrl("/login.do");
		shiroFilterFactoryBean.setSuccessUrl("/");
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 配置核心安全事务管理器
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		//进行session会话管理之后，控制台会报session失效 的错误，在springboot的环境下暂时没有找到解决方法
		//securityManager.setSessionManager(new DefaultWebSessionManager());
		securityManager.setRealm(getShiroDbRealm());
		LOGGER.info("===============shiro已经加载================");
		// 用户授权/认证信息Cache
		securityManager.setCacheManager(getEhCacheManager());
		return securityManager;
	}
	
	/**
	 * 配置自定义的权限登录器
	 * @return
	 */
	@Bean(name = "shiroDbRealm")
	public ShiroDbRealm getShiroDbRealm() {
		ShiroDbRealm shiroRealm = new ShiroDbRealm();
		//配置自定义的密码比较器
		shiroRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
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

	//下面这三。。。。。。
	@Bean(name = "lifecycleBeanPostProcessor")
	    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
	        return new LifecycleBeanPostProcessor();
    }
	 
  /*  @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }*/
    
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor auth = new AuthorizationAttributeSourceAdvisor();
        auth.setSecurityManager(getDefaultWebSecurityManager());
        return auth;
    }
    @Bean(name = "loginFormAuthenticationFilter")
    public MyFormAuthenticationFilter getMyFormAuthenticationFilter() {
        return new MyFormAuthenticationFilter();
    }

    @Bean(name = "customCredentialsMatcher")
    public CustomCredentialsMatcher getCustomCredentialsMatcher() {
        return new CustomCredentialsMatcher();
    
    }
}
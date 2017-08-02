package com.huishu.ait.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义校验密码类
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	 @Override
	    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		 	//如果和教育一样，用户和管理员是分开的话就要这几写token类型，继承这个token就可以
	        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
	        
	        //获得用户输入的密码
	        String inPassword = getInPassword(utoken);
	        //获得数据库中的密码
	        String dbPassword=(String) info.getCredentials();
	        //进行密码的比对
	        return this.equals(inPassword, dbPassword);
	    }
	 
	 /**
	  * 对token中获取的密码进行特殊处理
	  * @param utoken
	  * @return
	  */
	 private String getInPassword(UsernamePasswordToken utoken){
		 //这里需要对密码进行特殊处理
		// String string = new String(utoken.getPassword());
		return "";
		 
	 }
}

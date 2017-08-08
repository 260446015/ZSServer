package com.huishu.ait.common.conf;

/**
 * 信息常量类
 * <br/>
 * 该类放一些返回信息相关的常量
 */
public class MsgConstant {
	 /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "登录成功";
    
    /**
     * 登录失败
     */
    public static final String LOGIN_ERRROR = "登录失败";
    
    /**
     * 用户名或密码错误
     **/
    public static final String CREDENTIAL_ERROR = "用户名或密码错误";
    
    /**
     * 验证码错误
     **/
    public static final String INCORRECT_CAPTCHA = "验证码错误";

    /**
     * 账号过期
     */
    public static final String ACCOUNTEXPIRED = "账号过期";

    /**
     * 系统异常
     **/
    public static final String SYSTEM_ERROR = "系统异常";

    /**
     * 参数不合法
     **/
    public static final String ILLEGAL_PARAM = "参数不合法";

    /**
     * 时间不能为空
     **/
    public static final String TIME_IS_NULL = "时间不能为空";

    /*** 开始时间不能大于结束时间 */
    public static final String START_GT_END = "开始时间不能大于结束时间";

}

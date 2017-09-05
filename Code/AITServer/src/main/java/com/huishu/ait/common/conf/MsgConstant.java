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
     * 两次密码不一致
     */
    public static final String PASSWORD_ERROR = "两次密码不一致";
    /**
     * 原密码输入有误
     */
    public static final String OLDPASSWORD_ERROR = "原密码输入有误";
    /**
     * 密码修改成功
     **/
    public static final String PASSWORD_SUCCESS = "密码修改成功";
    /**
     * 密码修改失败
     **/
    public static final String CHANGE_ERROR = "密码修改失败，请稍后再试";
    /**
     * 邮箱修改成功
     **/
    public static final String 	EMAIL_SUCCESS = "邮箱修改成功";
    /**
     * 邮箱修改失败
     **/
    public static final String EMAIL_CHANGE_ERROR = "邮箱修改失败，请稍后再试";
    /**
     * 验证码错误
     **/
    public static final String INCORRECT_CAPTCHA = "验证码错误";

    /**
     * 账号过期
     */
    public static final String ACCOUNTEXPIRED = "账号过期";
    /**
     * 锁定
     */
    public static final String LOCKING = "密码连续输入错误超过5次，锁定半小时！";

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
    /**
     * 注册成功
     **/
    public static final String REGISTER_SUCCESS = "注册成功";
    /**
     * 注册失败
     **/
    public static final String REGISTER_ERROR = "注册失败，请稍后再试";
    /**
     * 账号已注册
     */
    public static final String ACCOUNT_REPEAT = "该账号已被注册，请直接登录或者使用其他账号进行注册";
    /**
     * 邮箱已注册
     */
    public static final String EMAIL_REPEAT = "该邮箱已被注册，请直接登录或者使用其他邮箱进行注册";
    /**
     * 手机已注册
     */
    public static final String PHONE_REPEAT = "该手机号已被注册，请直接登录或者使用其他手机号进行注册";
    /**
     * 手机账号不匹配
     */
    public static final String PHONE_ERROR = "此手机不是该账户的绑定手机";
    /**
     * 用户不存在
     */
    public static final String USER_ERROR = "该用户不存在";

}

package com.huishu.ait.common.util;

/**
 * 
 * @author yindawei 
 * @date 2017年9月13日上午9:21:29
 * @description 存储天眼查oauth2的一些信息
 * @version
 */
public class ConstantKey {

	//client_id,CLIENT_SECRET,AUTH_ID
    public static final String OAUTH_CLIENT_ID = "f46a00dd-b600-4481-846e-407126290bb2";
    public static final String OAUTH_CLIENT_SECRET = "739b67ae-4808-4f86-a149-194781284bef";
    public static final String OAUTH_AUTH_ID = "5";
    
    
   
    public static final String OAUTH_CLIENT_SCOPE = "user,order";
    public static final String OAUTH_CLIENT_REDIRECT_URI = "http://yufa.std.tianyancha.com";
//    public static final String OAUTH_CLIENT_REDIRECT_URI_ID = "/#/company/24416401/icinfo";
    public static final String OAUTH_CLIENT_REDIRECT_URI_ID = "/";
    public static final String OAUTH_CLIENT_GRANT_TYPE = "authorization_code";


    public static final String MEMBER_SESSION_KEY = "MEMBER_SESSION_KEY";
    public static final String INVALID_CLIENT_GRANT = "VERIFY_CLIENTID_FAIL";
    public static final String INVALID_CLIENT_SECRET = "VERIFY_CLIENT_SECRET_FAIL";
    public static final String TARGET_URI = "targetUri";
    public static final String INVALID_SPECIAL = "special";
    public static final String OPENEYE_WARN_TOKEN_461 = "WARN_TOKEN_461";
    public static final String OPENEYE_WARN_TOKEN_460 = "WARN_TOKEN_460";


    //OAUTH2_URL
    public static final String OAUTH_CLIENT_TIANYANCHA = "http://yufa.std.tianyancha.com";
//    public static final String OAUTH_CLIENT_CALLBACK = "http://58.16.181.24:9208/apis/oauth/oauth_callback";
    public static final String OAUTH_CLIENT_CALLBACK = "http://localhost:8092/apis/oauth/oauth_callback";
    public static final String OAUTH_CLIENT_ACCESS_CODE = "http://yufa.std.tianyancha.com/auth/code.json";
    public static final String OAUTH_CLIENT_ACCESS_TOKEN = "http://yufa.std.tianyancha.com/auth/token.json";
//    public static final String OAUTH_CLIENT_GET_RESOURCE = "http://58.16.181.24:9208/apis/oauth/get_resource";
    public static final String OAUTH_CLIENT_GET_RESOURCE = "http://localhost:8092/apis/oauth/get_resource";
    
    
    //存储常量
    public static final String DEFAULT_USERNAME_PARAM = "username";
	public static final String DEFAULT_REDIRECT_PARAM = "redirect_uri";
	public static final String DEFAULT_REDIRECT_ID_PARAM = "redirect_uri_id";
	public static final String DEFAULT_SIGN_PARAM = "sign";
    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
    public static final String DEFAULT_AUTH_ID_PARAM = "authId";
    public static final String DEFAULT_CLIENT_ID_PARAM = "client_id";
    public static final String DEFAULT_CODE_PARAM = "code";
    public static final String DEFAULT_NAME_PARAM = "name";
    public static final String DEFAULT_TAGS_PARAM = "tags";
    public static final String DEFAULT_PS_PARAM = "pageSize";
    public static final String DEFAULT_PN_PARAM = "pageNumber";
    
    //存储天眼查各个访问url
    public static final String LOGIN_URI = "http://yufa.std.tianyancha.com/auth/login.json";
	/**
	 * 获取用户关注分组
	 */
	public static final String ATTENTION_GROUP = "http://yufa.std.tianyancha.com/auth/user/tags.json";
	/**
	 * 获取企业信息变更
	 */
	public static final String CHANGE_INFO = "http://yufa.std.tianyancha.com/auth/company/change.json";
	/**
	 * 获取企业gid
	 */
	public static final String GID = "http://yufa.std.tianyancha.com/auth/company/gid.json";
	/**
	 * 获取关注分组下的所有企业
	 */
	public static final String GID_COMPANY = "http://yufa.std.tianyancha.com/auth/user/tags/company.json";
	/**
	 * 获取用户查询记录
	 */
	public static final String SEARCH_TRACK = "http://yufa.std.tianyancha.com/auth/user/record/search.json";

}

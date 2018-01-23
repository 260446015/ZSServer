package com.huishu.aitanalysis.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共常量
 * @author
 */
public class Constants {
	
	/**
	 * Controller Service构建消息返回
	 * 1. 索引类型消息、状态码
	 * 2. JSON转Object消息、状态码
	 * 3. 操作消息、状态码
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:44:41
	 */
	public static class Msg{
		public static final String DEFAULT_INDEX_TYPE_EXISTS = "默认索引类型存在";
		public static final String DEFAULT_INDEX_TYPE_NOT_EXISTS = "默认索引类型不存在";
		public static final String DEFAULT_INDEX_TYPE_EXISTS_CODE = "300";
		public static final String DEFAULT_INDEX_TYPE_NOT_EXISTS_CODE = "301";
		
		public static final String INDEX_TYPE_EXISTS = "指定索引类型存在";
		public static final String INDEX_TYPE_NOT_EXISTS = "指定索引类型不存在";
		public static final String INDEX_TYPE_EXISTS_CODE = "302";
		public static final String INDEX_TYPE_NOT_EXISTS_CODE = "303";
		
		public static final String JSON_CONVERSION_OBJECT_ERROR = "JSON转换成Object对象集合错误,请检查JSON格式是否正确"; 
		public static final String JSON_CONVERSION_LIST_ERROR = "JSON转换成List集合错误,请检查JSON格式是否正确";
		public static final String JSON_CONVERSION_OBJECT_ERROR_CODE = "304"; 
		public static final String JSON_CONVERSION_LIST_ERROR_CODE = "305";
		
		//Msg
		public static final String SUCCESS = "操作成功";
		public static final String UNKNOWN_ERROR = "未知错误";
		public static final String SERVICE_TEMPORARILY_UNAVAILABLE = "后端服务暂时不可用";
		public static final String UNSUPPORTED_OPENAPI_METHOD = "Open api接口不被支持";	
		public static final String OPEN_API_REQUEST_LIMIT_REACHED = "应用对open api接口的调用请求数达到上限";
		public static final String UNAUTHORIZED_CLIENT_IP_ADDRESS = "open api调用端的IP未被授权";
		public static final String INVALID_PARAMETER = "参数无效或缺失";
		public static final String INVALID_TOKEN = "token无效";	
		public static final String INVALID_CALL_ID_PARAMETER = "Call_id参数无效或已超时";	
		public static final String INCORRECT_SIGNATURE = "签名无效";	
		public static final String TOO_MANY_PARAMETERS = "参数过多";	
		public static final String UNSUPPORTED_SIGNATURE_METHOD = "参数签名算法未被平台所支持";	
		public static final String NO_PERMISSION_TO_ACCESS_DATA = "没有权限访问数据";	//没有权限访问数据
		//MsgCode
		public static final String SUCCESS_CODE = "0";
		public static final String UNKNOWN_ERROR_CODE = "1";
		public static final String SERVICE_TEMPORARILY_UNAVAILABLE_CODE = "2";
		public static final String UNSUPPORTED_OPENAPI_METHOD_CODE = "3";	
		public static final String OPEN_API_REQUEST_LIMIT_REACHED_CODE = "4";
		public static final String UNAUTHORIZED_CLIENT_IP_ADDRESS_CODE = "5";
		public static final String INVALID_PARAMETER_CODE = "100";
		public static final String INVALID_TOKEN_CODE = "101";	
		public static final String INVALID_CALL_ID_PARAMETER_CODE = "103";	
		public static final String INCORRECT_SIGNATURE_CODE = "104";	
		public static final String TOO_MANY_PARAMETERS_CODE = "105";	
		public static final String UNSUPPORTED_SIGNATURE_METHOD_CODE = "106";	
		public static final String NO_PERMISSION_TO_ACCESS_DATA_CODE = "200";	//没有权限访问数据
		
	}
	
	/**
	 * 生成Token需要的key
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:44:25
	 */
	public static class Md5{
		public static final String GEN_TOKEN_KEY = "zkdj_yuQing"; //生成TOKEN需要的key
		public static final long TOKEN_LIVE_TIME = 100;  //TOKEN 有效期
	}
	/**
	 * 定义产业标签信息
	 * @author hhy
	 * @date 2017年8月31日
	 * @Parem
	 * @return 
	 *
	 */
	public static class Info{
		/**互联网+*/
		public static final String   HL = "网络游戏,大数据,电子商务,网络视听,移动阅读,智能硬件,物联网";
		/**高科技*/
		public static final String   GK = "新一代信息技术,智能机器人,生物医药,节能环保技术装备,新能源,新材料,航空装备,人工智能,生物制药,生物技术";
		/**文化创意*/
		public static final String   WH = "动漫制作,影视传媒,图书出版,广告营销";
		/**精英配套*/
		public static final String   JP = "金融服务,住宅地产,商业综合体,康体美容,母婴产业,健康产业,教育培训";
		/**滨海旅游*/
		public static final String   BW = "特色旅游综合体,体育产业";
		/**港口物流*/
		public static final String   GW = "生鲜贸易,食品加工,冷链物流";
	}
	/**
	 * 任务调用任务ID
	 * @author LvDapeng
	 * @date 2015年9月10日 上午2:13:57
	 */
	public static class Task{
		//定时分析
		public static final String ONTIME 				= "10002";
		//触发分析
		public static final String TRIGGER 				= "10003";
		//词库同步
		public static final String WORD_SYNC  			= "10004";
		//分词同步
		public static final String SEGMENTATION_SYNC	= "10006";
		//词库增量同步
		public static final String WORD_INCREMENT_SYNC  = "10009";
	}
	
	/**
	 * 词性存储Redis中需要的后缀
	 * @author LvDapeng
	 * @date 2015年9月30日 下午11:52:25
	 */
	public static class NatureSuffix{
		
		public static final String EVENT = "event"; 
		public static final Integer EVENT_LEN = 5;
		
		public static final String RELATED    = "related"; 
		public static final Integer RELATED_LEN    = 7; 
		public static final String RELATED_RULE    = "relatedrule"; 
		public static final Integer RELATED_RULE_LEN    = 11; 
		
		public static final String YUQING     = "yuqing"; 
		public static final Integer YUQING_LEN     = 6; 
		public static final String YUQING_RULE     = "yuqingrule"; 
		public static final Integer YUQING_RULE_LEN     = 10; 
		
		public static final String NEGATIVE   = "negative";
		public static final Integer NEGATIVE_LEN   = 8;
		public static final String NEGATIVE_RULE   = "negativerule";
		public static final Integer NEGATIVE_RULE_LEN   = 12;
		
		public static final String WARNING    = "warning";
		public static final Integer WARNING_LEN    = 7;
		public static final String WARNING_RULE    = "warningrule";
		public static final Integer WARNING_RULE_LEN    = 11;
		
		public static final String POSITIVE   = "positive";
		public static final Integer POSITIVE_LEN   = 8;
		public static final String POSITIVE_RULE   = "positiverule";
		public static final Integer POSITIVE_RULE_LEN   = 12;
		
		public static final String REGION     = "region";
		public static final Integer REGION_LEN     = 6;
		public static final String REGION_RULE     = "regionrule";
		public static final Integer REGION_RULE_LEN     = 10;
		
		public static final String COMPANY     = "company";
		public static final Integer COMPANY_LEN     = 7;
		public static final String COMPANY_RULE     = "companyrule";
		public static final Integer COMPANY_RULE_LEN     = 11;
		
		public static final String OFFICE     = "office";
		public static final Integer OFFICE_LEN     = 6;
		public static final String OFFICE_RULE     = "officerule";
		public static final Integer OFFICE_RULE_LEN     = 10;
		
		public static final String SCHOOL     = "school";
		public static final Integer SCHOOL_LEN     = 6;
		public static final String SCHOOL_RULE     = "schoolrule";
		public static final Integer SCHOOL_RULE_LEN     = 10;
		
		public static final String JOBTITLE     = "jobtitle";
		public static final Integer JOBTITLE_LEN     = 8;
		public static final String JOBTITLE_RULE     = "jobtitlerule";
		public static final Integer JOBTITLE_RULE_LEN     = 12;
		
		public static final String PERSON     = "person";
		public static final Integer PERSON_LEN     = 6;
		public static final String PERSON_RULE     = "personrule";
		public static final Integer PERSON_RULE_LEN     = 10;
		
		public static final String CARRIE     = "carrie";
		public static final Integer CARRIE_LEN     = 6;
	}
	
	/**
	 * 分词器
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:43:35
	 */
	public static class AnalysisType{
		public static final String IK = "ik";
	}
	
	/**
	 * 默认分页
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:44:00
	 */
	public static class MyPage{
		public static final Integer page = 0;
		public static final Integer size = 10;
	}
	
	/**
	 * 默认排序字段和排序类型
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:43:49
	 */
	public static class MySort{
		public static final String sortField = "publishTime";
		public static final String sortType = "DESC";
	}
	
	/**
	 * 发送预警信息
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:43:49
	 */
	public static class Warn{
		public static final String URL = "pomp.restserver.warn";
		public static final String AUTO = "pomp.restserver.warn.auto.start";
		public static final String APPID = "pomp.restserver.warn.appid";
	}
	
	/**
	 * 调用腾讯文智
	 * @author LvDapeng
	 * @date 2015年8月22日 上午12:43:49
	 */
	public static class Sentiment{
		public static final String ID = "tencent.sentiment.secretid";
		public static final String KEY = "tencent.sentiment.secretkey";
		public static final String AUTO = "tencent.sentiment.auto.start";
		public static final String POSITIVE = "tencent.sentiment.positive";
		public static final String NEGATIVE = "tencent.sentiment.negative";
	}
	
	/**
	 * 地域文件路径
	 */
	public static class Area{
		public static final String areaPath = "area.path";
	}
}

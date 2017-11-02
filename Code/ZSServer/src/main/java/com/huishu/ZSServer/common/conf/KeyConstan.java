package com.huishu.ZSServer.common.conf;

/**
 * 
 * @author yindawei
 * @date 2017年10月30日下午4:02:50
 * @description 存储一些常量的类
 * @version
 */
public class KeyConstan{

	/** 天眼查token */
	public static final String OPENEYES_TOKEN = "jeNB8qBn5Cw5";
	public static final String YUANQUDONGTAI = "园区动态";
	public static final String RONGZIDONGTAI = "融资动态";
	/**
	 * local
	 */
//	 public static final String IP_PORT = "http://localhost:8093/" ;
	/**
	 * On-line
	 */
	public static final String IP_PORT = "http://58.16.181.24:9209/";
	
	/**
	 * 天眼查url
	 * 
	 * @author yindq
	 * @date 2017年11月2日
	 */
	public static class URL {
		/**
		 * 企业融资url
		 */
		public static final String RONGZI = "https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json";
		/**
		 * 企业年报url-年产值、年税收
		 */
		public static final String NIANBAO = "https://open.api.tianyancha.com/services/v3/newopen/annualreport.json";
	}
}

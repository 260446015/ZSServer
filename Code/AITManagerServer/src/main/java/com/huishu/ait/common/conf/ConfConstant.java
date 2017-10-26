package com.huishu.ait.common.conf;

/**
 * 配置常量类 <br/>
 * 该类放系统配置常量
 */
public class ConfConstant {

	/** 最小页码 */
	public static final int MIN_PAGE_NUMBER = 0;

	/** 最大页码 */
	public static final int MAX_PAGE_NUMBER = 1000;

	/** 默认页容量 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/** 最小页容量 */
	public static final int MIN_PAGE_SIZE = 1;

	/** 最大页容量 */
	public static final int MAX_PAGE_SIZE = 20;

	public static final String[] USER_LEVEL = { "A级会员", "B级会员", "C级会员", "试用辖区" };

	/** 会员默认初始密码 */
	public final static String DEFAULT_PASSWORD = "huishuzhaoshang2017";

	/** 园区默认存放地址 */
	public final static String DEFAULT_LOGOURL = "logo";
	public final static String DEFAULT_URL = "img";

	/** 产业 */
	public static class INDUSTRY {
		public static final String FIRST_industry = "互联网";
		public static final String SECOND_industry = "高科技";
		public static final String THIRD_industry = "文化创意";
		public static final String FOURTH_industry = "精英配套";
	}
	/**
	 * 特殊字符转义
	 */
	public static class EscapeSpecialChar {
		/**
		 * 转义 " & " -> " huishu_@@@ "
		 */
		public static final String SPACE_AND_SPACE = " huishu_@@@ ";
	}
}

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
	
	/** 图片文本提取URL */
	public static final String DISTINGUISH = "http://114.55.4.218:6001/Api/OCRServices";
	
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
		/**
		 * 基本信息
		 */
		public static final String BASEINFO = "https://open.api.tianyancha.com/services/v3/newopen/baseinfoV2.json";
		/**
		 * 分支机构
		 */
		public static final String BRANCH = "https://open.api.tianyancha.com/services/v3/newopen/branch.json";
		/**
		 * 融资历史
		 */
		public static final String HISTORYRONGZI = "https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json";
		/**
		 * 核心团队
		 */
		public static final String TEAMMEMBER = "https://open.api.tianyancha.com/services/v3/newopen/findTeamMember.json";
		/**
		 * 企业业务
		 */
		public static final String PRODUCTINFO = "https://open.api.tianyancha.com/services/v3/newopen/getProductInfo.json";
		/**
		 * 投资案例
		 */
		public static final String TZANLI = "https://open.api.tianyancha.com/services/v3/newopen/findTzanli.json";
		/**
		 * 竞品信息
		 */
		public static final String JINGPIN = "https://open.api.tianyancha.com/services/v3/newopen/findJingpin.json";
		/**
		 * 商标信息
		 */
		public static final String SHANGBIAO = "https://open.api.tianyancha.com/services/v3/newopen/tm.json";
		/**
		 * 专利
		 */
		public static final String PATENTS = "https://open.api.tianyancha.com/services/v3/newopen/patents.json";
		/**
		 * 著作权
		 */
		public static final String COPYREG = "https://open.api.tianyancha.com/services/v3/newopen/copyReg.json";
		/**
		 * 网站备案
		 */
		public static final String ICP = "https://open.api.tianyancha.com/services/v3/newopen/icp.json";
		/**
		 * 经营异常
		 */
		public static final String ABNORMAL = "https://open.api.tianyancha.com/services/v3/newopen/abnormal.json";
		/**
		 * 行政处罚
		 */
		public static final String XINGZHENGCHUFA = "https://open.api.tianyancha.com/services/v3/newopen/punishmentInfo.json";
		/**
		 * 严重违法
		 */
		public static final String YANZHONGWEIFA = "https://open.api.tianyancha.com/services/v3/newopen/illegalinfo.json";
		/**
		 * 欠税公告
		 */
		public static final String QIANSHUIGONGGAO = "https://open.api.tianyancha.com/services/v3/newopen/ownTax.json";
		/**
		 * 新闻
		 */
		public static final String NEWS = "https://open.api.tianyancha.com/services/v3/open/news.json";
		/**
		 * 失信人
		 */
		public static final String SHIXINREN = "https://open.api.tianyancha.com/services/v3/newopen/dishonest.json";
		/**
		 * 企业风险
		 */
		public static final String QIYEFENGXIAN = "https://open.api.tianyancha.com/services/v3/newopen/riskInfo.json";
		/**
		 * 人风险
		 */
		public static final String RENFENGXIAN = "https://open.api.tianyancha.com/services/v3/newopen/humanRiskInfo.json";
		/**
		 * 风险信息
		 */
		public static final String FENGXIANXINXI = "https://open.api.tianyancha.com/services/v3/newopen/riskDetail.json";
		/**
		 * 搜索企业列表
		 */
		public static final String SOUSUO = "https://open.api.tianyancha.com/services/v3/newopen/searchV2.json";
		/**
		 * 关键字查询
		 */
		public static final String GUANJIANZI = "https://open.api.tianyancha.com/services/v4/open/searchV2.json";
		/**
		 * 税务评级查询
		 */
		public static final String SHUIWU = "https://open.api.tianyancha.com/services/v3/newopen/taxCredit.json";
		/**
		 * 主要人员
		 */
		public static final String STAFF = "https://open.api.tianyancha.com/services/v3/newopen/staff.json";
	}
	/**
	 * 区分接口访问来源
	 * @author yindawei 
	 * @date 2017年11月16日下午4:41:06
	 * @description 
	 * @version
	 */
	public static class From{
		public static final String CUSTOM = "1";
		public static final String GUANGXI = "2";
	}
}

package com.huishu.ZSServer.common.conf;

/**
 * 
 * @author yindawei
 * @date 2017年10月30日下午4:02:50
 * @description 存储一些常量的类
 * @version
 */
public class KeyConstan {

	/** 天眼查token */
	public static final String OPENEYES_TOKEN = "jeNB8qBn5Cw5";
	public static final String YUANQUDONGTAI = "园区动态";
	public static final String RONGZIDONGTAI = "融资动态";
	public static final String RONGZIKUAIXUN = "融资快讯";
	public static final String ZHENGCEJIDU = "政策解读";
	public static final String ZHAO_SHANG_BAO_GAO = "招商报告";
	public static final String YUANQUZHENGCE = "园区政策";
	/**天眼查调用地址*/
	public static final String OPEN_URL = "http://58.16.181.24:11002/openeyes/search.json";
	/**
	 * local
	 */
	// public static final String IP_PORT = "http://localhost:8093/" ;
	/**
	 * On-line
	 */
	public static final String IP_PORT = "http://58.16.181.24:9322/";

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
		/**
		 * 股东信息
		 */
		public static final String HOLDER = "https://open.api.tianyancha.com/services/v3/newopen/holder.json";
		/**
		 * 信息变更
		 */
		public static final String CHANGEINFO = "https://open.api.tianyancha.com/services/v3/newopen/changeinfo.json";
		/**
		 * 对外投资
		 */
		public static final String INVERST = "https://open.api.tianyancha.com/services/v3/newopen/inverst.json";
		/**
		 * 招投标
		 */
		public static final String BIDS = "https://open.api.tianyancha.com/services/v3/newopen/bids.json";
		/**
		 * 债券信息
		 */
		public static final String BOND = "https://open.api.tianyancha.com/services/v3/newopen/bond.json";
		/**
		 * 购地信息
		 */
		public static final String PURCHASELAND = "https://open.api.tianyancha.com/services/v3/newopen/purchaseLand.json";
		/**
		 * 购地信息
		 */
		public static final String EMPLOYMENTS = "https://open.api.tianyancha.com/services/v3/newopen/employments.json";
		/**
		 * 抽查检查
		 */
		public static final String CHECKINFO = "https://open.api.tianyancha.com/services/v3/newopen/checkInfo.json";
		/**
		 * 产品信息
		 */
		public static final String APPBKINFO = "https://open.api.tianyancha.com/services/v3/newopen/appbkInfo.json";
		/**
		 * 法律诉讼
		 */
		public static final String LAWSUIT = "https://open.api.tianyancha.com/services/v3/newopen/lawSuit.json";
		/**
		 * 法院公告
		 */
		public static final String COURTANNOUNCEMENT = "https://open.api.tianyancha.com/services/v3/newopen/courtAnnouncement.json";
		/**
		 * 被执行人
		 */
		public static final String ZHIXINGINFO = "https://open.api.tianyancha.com/services/v3/newopen/zhixinginfo.json";
		/**
		 * 股票行情
		 */
		public static final String VOLATILITY = "https://open.api.tianyancha.com/services/v3/newopen/volatility.json";
		/**
		 * 企业简介（股票）
		 */
		public static final String COMPANYINFO = "https://open.api.tianyancha.com/services/v3/newopen/companyInfo.json";
		/**
		 * 高管信息（股票）
		 */
		public static final String SENIOREXECUTIVE = "https://open.api.tianyancha.com/services/v3/newopen/seniorExecutive.json";
		/**
		 * 参股控股（股票）
		 */
		public static final String HOLDINGCOMPANY = "https://open.api.tianyancha.com/services/v3/newopen/holdingCompany.json";
		/**
		 * 上市公告（股票）
		 */
		public static final String ANNOUNCEMENT = "https://open.api.tianyancha.com/services/v3/newopen/announcement.json";
		/**
		 * ⼗⼤股东（⼗⼤流通股东）（股票）
		 */
		public static final String SHAREHOLDER = "https://open.api.tianyancha.com/services/v3/newopen/shareholder.json";
		/**
		 * 发⾏相关（股票）
		 */
		public static final String ISSUERELATED = "https://open.api.tianyancha.com/services/v3/newopen/issueRelated.json";
		/**
		 * 股本结构（股票）
		 */
		public static final String SHARESTRUCTURE = "https://open.api.tianyancha.com/services/v3/newopen/shareStructure.json";
		/**
		 * 股本变动（股票）
		 */
		public static final String EQUITYCHANGE = "https://open.api.tianyancha.com/services/v3/newopen/equityChange.json";
		/**
		 * 【web版】分红情况（股票）
		 */
		public static final String BONUSINFO = "https://open.api.tianyancha.com/services/v3/newopen/bonusInfo.json";
		/**
		 * 【web版】分红情况（股票）
		 */
		public static final String ALLOTMEN = "https://open.api.tianyancha.com/services/v3/newopen/allotmen.json";
		/**
		 * 【web版】资质证书
		 */
		public static final String CERTIFICATE = "https://open.api.tianyancha.com/services/v3/newopen/certificate.json";
	}

	/**
	 * 区分接口访问来源
	 * 
	 * @author yindawei
	 * @date 2017年11月16日下午4:41:06
	 * @description
	 * @version
	 */
	public static class From {
		public static final String CUSTOM = "1";
		public static final String GUANGXI = "2";
	}

	/**
	 * 配置六大产业
	 */
	public static class IndustyType {
		/** 节能环保和新能源产业 */
		public static final String JNHB = "节能环保和新能源产业";
		/** 生物产业 */
		public static final String SWCY = "生物产业";
		/** 高端装备制造产业 */
		public static final String GDZB = "高端装备制造产业";
		/** 新材料产业 */
		public static final String XCL = "新材料产业";
		/** 数字创意产业 */
		public static final String SZCY = "数字创意产业";
		/** 新兴信息产业 */
		public static final String XXXX = "新兴信息产业";
	}
}

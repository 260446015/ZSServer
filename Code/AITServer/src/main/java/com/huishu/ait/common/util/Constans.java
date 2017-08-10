package com.huishu.ait.common.util;

/**
 * @author yindawei 
 * @date 2017年8月8日上午11:08:59
 * @description 存贮一写https请求的信息
 * @version 1.0
 */
public class Constans {

	/**
	 * 存储天眼查的授权码，具体存在哪待定
	 */
	public static final String SKYEYE_ACCESS_TOKEN = "XXX";
	/**
	 * 存储天眼查的测试账号
	 */
	public static final String SKYEYE_ACCOUNT = "zkdj";
	/**
	 *  存储【web版】搜索接口url
	 *  https://open.api.tianyancha.com/services/v3/newopen/searchV2.json?word=北京百度网讯科技有限公司
	 *  天眼查接口ID 159
	 */
	public static final String SEARCHV2 = "https://open.api.tianyancha.com/services/v3/newopen/searchV2.json";
	/**
	 *  存储【web版】获取企业基本信息url
	 *  https://open.api.tianyancha.com/services/v3/newopen/baseinfo.json?id=22822
	 *  天眼查接口ID 98
	 */
	public static final String BASEINFO = "https://open.api.tianyancha.com/services/v3/newopen/baseinfo.json";
	/**
	 *  存储【web版】获取企业基本信息（含联系方式）url
	 *  https://open.api.tianyancha.com/services/v3/newopen/baseinfoV2.json?id=22822
	 *  天眼查接口ID 99
	 */
	public static final String BASEINFOV2 = "https://open.api.tianyancha.com/services/v3/newopen/baseinfoV2.json";
	/**
	 * 存储【web版】获取企业基本信息（含主要人员）url
	 * https://open.api.tianyancha.com/services/v3/newopen/baseinfoV3.json?id=22822
	 * 天眼查接口ID 100
	 */
	public static final String BASEINFOV3 = "https://open.api.tianyancha.com/services/v3/newopen/baseinfoV3.json";
	/**
	 * 存储【web版】获取企业基本信息（含主要人员）url
	 * https://open.api.tianyancha.com/services/v3/newopen/staff.json?id=22822
	 * 天眼查接口ID 101
	 */
	public static final String STAFF = "https://open.api.tianyancha.com/services/v3/newopen/staff.json";
	/**
	 * 存储【web版】股东信息url
	 * https://open.api.tianyancha.com/services/v3/newopen/holder.json?id=22822
	 * 天眼查接口ID 102
	 */
	public static final String HOLDER = "https://open.api.tianyancha.com/services/v3/newopen/holder.json";
	/**
	 * 存储【web版】对外投资url
	 * https://open.api.tianyancha.com/services/v3/newopen/inverst.json?id=22822
	 * 天眼查接口ID 103
	 */
	public static final String INVERST = "https://open.api.tianyancha.com/services/v3/newopen/inverst.json";
	/**
	 * 存储【web版】变更记录url
	 * https://open.api.tianyancha.com/services/v3/newopen/changeinfo.json?id=22822
	 * 天眼查接口ID 104
	 */
	public static final String CHANGEINFO = "https://open.api.tianyancha.com/services/v3/newopen/changeinfo.json";
	/**
	 * 存储【web版】企业年报url(正常和带股权出质的传的id不同)
	 * 正常：https://open.api.tianyancha.com/services/v3/newopen/annualreport.json?id=22822
	 * 带股权出质公司：https://open.api.tianyancha.com/services/v3/newopen/annualreport?id=4693597
	 * 天眼查接口ID 105
	 */
	public static final String ANNUALREPORT = "https://open.api.tianyancha.com/services/v3/newopen/annualreport.json";
	/**
	 * 存储【web版】分支机构url
	 * https://open.api.tianyancha.com/services/v3/newopen/branch.json?id=199557844
	 * 天眼查接口ID 106
	 */
	public static final String BRANCH = "https://open.api.tianyancha.com/services/v3/newopen/branch.json";
	/**
	 * 存储【web版】融资历史url
	 * https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 107
	 */
	public static final String FINDHISTORYRONGZI = "https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json";
	/**
	 * 存储【web版】核心团队url
	 * https://open.api.tianyancha.com/services/v3/newopen/findTeamMember.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 108
	 */
	public static final String FINDTEAMMEMBER = "https://open.api.tianyancha.com/services/v3/newopen/findTeamMember.json";
	/**
	 * 存储【web版】企业业务url
	 * https://open.api.tianyancha.com/services/v3/newopen/getProductInfo.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 109
	 */
	public static final String GETPRODUCTINFO = "https://open.api.tianyancha.com/services/v3/newopen/getProductInfo.json";
	/**
	 * 存储【web版】投资事件url
	 * https://open.api.tianyancha.com/services/v3/newopen/findTzanli.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 110
	 */
	public static final String FINDTZANLI = "https://open.api.tianyancha.com/services/v3/newopen/findTzanli.json";
	/**
	 * 存储【web版】竞品信息url
	 * https://open.api.tianyancha.com/services/v3/newopen/findJingpin.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 111
	 */
	public static final String FINDJINGPIN = "https://open.api.tianyancha.com/services/v3/newopen/findJingpin.json";
	/**
	 * 存储【web版】法律诉讼url
	 * https://open.api.tianyancha.com/services/v3/newopen/lawSuit.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 112
	 */
	public static final String LAWSUIT = "https://open.api.tianyancha.com/services/v3/newopen/lawSuit.json";
	/**
	 * 存储【web版】法院公告url
	 * https://open.api.tianyancha.com/services/v3/newopen/courtAnnouncement.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 113
	 */
	public static final String COURTANNOUNCEMENT = "https://open.api.tianyancha.com/services/v3/newopen/courtAnnouncement.json";
	/**
	 * 存储【web版】失信人url
	 * https://open.api.tianyancha.com/services/v3/newopen/dishonest.json?name=天津信裕房地产发展有限公司
	 * 天眼查接口ID 114
	 */
	public static final String DISHONEST = "https://open.api.tianyancha.com/services/v3/newopen/dishonest.json";
	/**
	 * 存储【web版】被执行人url
	 * https://open.api.tianyancha.com/services/v3/newopen/zhixinginfo.json?id=22822
	 * 天眼查接口ID 115
	 */
	public static final String ZHIXINGINFO = "https://open.api.tianyancha.com/services/v3/newopen/zhixinginfo.json";
	/**
	 * 存储【web版】经营异常url
	 * https://open.api.tianyancha.com/services/v3/newopen/abnormal.json?id=1073117874
	 * 天眼查接口ID 116
	 */
	public static final String ABNORMAL = "https://open.api.tianyancha.com/services/v3/newopen/abnormal.json";
	/**
	 * 存储【web版】行政处罚url
	 * https://open.api.tianyancha.com/services/v3/newopen/punishmentInfo.json?name=德化县孙壁电器商店
	 * 天眼查接口ID 117
	 */
	public static final String PUNISHMENTINFO = "https://open.api.tianyancha.com/services/v3/newopen/punishmentInfo.json";
	/**
	 * 存储【web版】严重违法url
	 * https://open.api.tianyancha.com/services/v3/newopen/illegalinfo.json?name=陕西华庭酒店有限公司
	 * 天眼查接口ID 118
	 */
	public static final String ILLEGALINFO = "https://open.api.tianyancha.com/services/v3/newopen/illegalinfo.json";
	/**
	 * 存储【web版】股权出质url
	 * https://open.api.tianyancha.com/services/v3/newopen/equityInfo.json?name=北京逸璟物业管理有限公司
	 * 天眼查接口ID 119
	 */
	public static final String EQUITYINFO = "https://open.api.tianyancha.com/services/v3/newopen/equityInfo.json";
	/**
	 * 存储【web版】动产抵押url
	 * https://open.api.tianyancha.com/services/v3/newopen/mortgageInfo.json?name=讷河市丰盛现代农业农机专业合作社
	 * 天眼查接口ID 120
	 */
	public static final String MORTGAGEINFO = "https://open.api.tianyancha.com/services/v3/newopen/mortgageInfo.json";
	/**
	 * 存储【web版】欠税公告url
	 * https://open.api.tianyancha.com/services/v3/newopen/ownTax.json?id=546136510
	 * 天眼查接口ID 121
	 */
	public static final String OWNTAX = "https://open.api.tianyancha.com/services/v3/newopen/ownTax.json";
	/**
	 * 存储【web版】招投标url
	 * https://open.api.tianyancha.com/services/v3/newopen/bids.json?id=22822
	 * 天眼查接口ID 122
	 */
	public static final String BIDS = "https://open.api.tianyancha.com/services/v3/newopen/bids.json";
	/**
	 * 存储【web版】债券信息url
	 * https://open.api.tianyancha.com/services/v3/newopen/bond.json?name=嘉兴银行股份有限公司
	 * 天眼查接口ID 123
	 */
	public static final String BOND = "https://open.api.tianyancha.com/services/v3/newopen/bond.json";
	/**
	 * 存储【web版】债券信息url
	 * https://open.api.tianyancha.com/services/v3/newopen/purchaseLand.json?name=金地（集团）股份有限公司
	 * 天眼查接口ID 124
	 */
	public static final String PURCHASELAND = "https://open.api.tianyancha.com/services/v3/newopen/purchaseLand.json";
	/**
	 * 存储【web版】招聘url
	 * https://open.api.tianyancha.com/services/v3/newopen/employments.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 125
	 */
	public static final String EMPLOYMENTS = "https://open.api.tianyancha.com/services/v3/newopen/employments.json";
	/**
	 * 存储【web版】税务评级url
	 * https://open.api.tianyancha.com/services/v3/newopen/taxCredit.json?id=22822
	 * 天眼查接口ID 126
	 */
	public static final String TAXCREDIT = "https://open.api.tianyancha.com/services/v3/newopen/taxCredit.json";
	/**
	 * 存储【web版】抽查检查url
	 * https://open.api.tianyancha.com/services/v3/newopen/checkInfo.json?name=上海浦东新区三林供销社三民商店
	 * 天眼查接口ID 127
	 */
	public static final String CHECKINFO = "https://open.api.tianyancha.com/services/v3/newopen/checkInfo.json";
	/**
	 * 存储【web版】产品信息url
	 * https://open.api.tianyancha.com/services/v3/newopen/appbkInfo.json?id=22822
	 * 天眼查接口ID 128
	 */
	public static final String APPBKINFO = "https://open.api.tianyancha.com/services/v3/newopen/appbkInfo.json";
	/**
	 * 存储【web版】资质证书url
	 * https://open.api.tianyancha.com/services/v3/newopen/qualification.json?id=24416401
	 * 天眼查接口ID 129
	 */
	public static final String QUALIFICATION = "https://open.api.tianyancha.com/services/v3/newopen/qualification.json";
	/**
	 * 存储【web版】商标信息url
	 * https://open.api.tianyancha.com/services/v3/newopen/tm.json?id=22822
	 * 天眼查接口ID 130
	 */
	public static final String TM = "https://open.api.tianyancha.com/services/v3/newopen/tm.json";
	/**
	 * 存储【web版】专利url
	 * https://open.api.tianyancha.com/services/v3/newopen/patents.json?id=22822
	 * 天眼查接口ID 131
	 */
	public static final String PATENTS = "https://open.api.tianyancha.com/services/v3/newopen/patents.json";
	/**
	 * 存储【web版】著作权url
	 * https://open.api.tianyancha.com/services/v3/newopen/copyReg.json?id=22822
	 * 天眼查接口ID 132
	 */
	public static final String COPYREG = "https://open.api.tianyancha.com/services/v3/newopen/copyReg.json";
	/**
	 * 存储【web版】网站备案url
	 * https://open.api.tianyancha.com/services/v3/newopen/icp.json?id=22822
	 * 天眼查接口ID 133
	 */
	public static final String ICP = "https://open.api.tianyancha.com/services/v3/newopen/icp.json";
	/**
	 * 存储【web版】股票行情url
	 * https://open.api.tianyancha.com/services/v3/newopen/volatility.json?id=199557844
	 * 天眼查接口ID 134
	 */
	public static final String VOLATILITY = "https://open.api.tianyancha.com/services/v3/newopen/volatility.json";
	/**
	 * 存储【web版】企业简介（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/companyInfo.json?id=485568977
	 * 天眼查接口ID 135
	 */
	public static final String COMPANYINFO = "https://open.api.tianyancha.com/services/v3/newopen/companyInfo.json";
	/**
	 * 存储【web版】高管信息（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/seniorExecutive.json?id=485568977&pageNum=1&pageSize=10
	 * 天眼查接口ID 136
	 */
	public static final String SENIOREXECUTIVE = "https://open.api.tianyancha.com/services/v3/newopen/seniorExecutive.json";
	/**
	 * 存储【web版】参股控股（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/holdingCompany.json?id=485568977&pageNum=1&pageSize=10
	 * 天眼查接口ID 137
	 */
	public static final String HOLDINGCOMPANY = "https://open.api.tianyancha.com/services/v3/newopen/holdingCompany.json";
	/**
	 * 存储【web版】上市公告（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/announcement.json?id=485568977&pageNum=1&pageSize=10
	 * 天眼查接口ID 138
	 */
	public static final String ANNOUNCEMENT = "https://open.api.tianyancha.com/services/v3/newopen/announcement.json";
	/**
	 * 存储【web版】十大股东（十大流通股东）（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/shareholder.json?id=485568977&type=1
	 * 天眼查接口ID 139
	 */
	public static final String SHAREHOLDER = "https://open.api.tianyancha.com/services/v3/newopen/shareholder.json";
	/**
	 * 存储【web版】发行相关（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/issueRelated.json?id=485568977
	 * 天眼查接口ID 141
	 */
	public static final String ISSUERELATED = "https://open.api.tianyancha.com/services/v3/newopen/issueRelated.json";
	/**
	 * 存储【web版】股本结构（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/shareStructure.json?id=485568977
	 * 天眼查接口ID 142
	 */
	public static final String SHARESTRUCTURE = "https://open.api.tianyancha.com/services/v3/newopen/shareStructure.json";
	/**
	 * 存储【web版】股本变动（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/equityChange.json?id=485568977
	 * 天眼查接口ID 143
	 */
	public static final String EQUITYCHANGE = "https://open.api.tianyancha.com/services/v3/newopen/equityChange.json";
	/**
	 * 存储【web版】分红情况（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/bonusInfo.json?id=485568977
	 * 天眼查接口ID 144
	 */
	public static final String BONUSINFO = "https://open.api.tianyancha.com/services/v3/newopen/bonusInfo.json";
	/**
	 * 存储【web版】配股情况（股票）url
	 * https://open.api.tianyancha.com/services/v3/newopen/allotmen.json?id=485568977
	 * 天眼查接口ID 145
	 */
	public static final String ALLOTMEN = "https://open.api.tianyancha.com/services/v3/newopen/allotmen.json";
	/**
	 * 存储【web版】股权结构图url
	 * https://open.api.tianyancha.com/services/v3/newopen/equityRatio.json?id=22822
	 * 天眼查接口ID 146
	 */
	public static final String EQUITYRATIO = "https://open.api.tianyancha.com/services/v3/newopen/equityRatio.json";
	/**
	 * 存储【web版】获取纳税人识别号url
	 * https://open.api.tianyancha.com/services/v3/newopen/taxesCode.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 147
	 */
	public static final String TAXESCODE = "https://open.api.tianyancha.com/services/v3/newopen/taxesCode.json";
	/**
	 * 存储获取公司详情 只有基本信息+分支机构+对外投资+股东+高管url
	 * https://open.api.tianyancha.com/services/v3/open/detail.json?name=中科软科技股份有限公司
	 * 天眼查接口ID 18
	 */
	public static final String DETAIL = "https://open.api.tianyancha.com/services/v3/open/detail.json";
	/**
	 * 存储搜索接口V1url
	 * https://open.api.tianyancha.com/services/v3/open/search.json?keyword=百度
	 * 天眼查接口ID 21
	 */
	public static final String SEARCH = "https://open.api.tianyancha.com/services/v3/open/search.json";
	/**
	 * 存储搜索接口V3url
	 * https://open.api.tianyancha.com/services/v3/open/searchV3.json?keyword=百度
	 * 天眼查接口ID 66
	 */
	public static final String SEARCHV3 = "https://open.api.tianyancha.com/services/v3/open/searchV3.json";
	/**
	 * 存储搜索接口V4url
	 * https://open.api.tianyancha.com/services/v3/open/searchV4.json?word=百度
	 * 天眼查接口ID 22
	 */
	public static final String SEARCHV4 = "https://open.api.tianyancha.com/services/v3/open/searchV4.json";
	/**
	 * 存储公司详情,（根据id）url
	 * 正常:https://open.api.tianyancha.com/services/v3/open/w/companydetail.json?id=22822
     * 带经营异常：https://open.api.tianyancha.com/services/v3/open/w/companydetail.json?id=1073117874
     * 带分支机构：https://open.api.tianyancha.com/services/v3/open/w/companydetail.json?id=376321715
	 * 天眼查接口ID 40
	 */
	public static final String COMPANYDETAIL = "https://open.api.tianyancha.com/services/v3/open/w/companydetail.json";
	/**
	 * 存储公司详情,根据公司名称获得url
	 * 正常:https://open.api.tianyancha.com/services/v3/open/w/detail.json?name=北京百度网讯科技有限公司
     * 带经营异常：https://open.api.tianyancha.com/services/v3/open/w/detail.json?name=湖南瑞源生物医药科技有限公司
     * 带分支机构：https://open.api.tianyancha.com/services/v3/open/w/detail.json?name=重庆金夫人实业有限公司
	 * 天眼查接口ID 41
	 */
	public static final String WDETAIL = "https://open.api.tianyancha.com/services/v3/open/w/detail.json";
	/**
	 * 存储获取公司商标信息url
	 * https://open.api.tianyancha.com/services/v3/open/tm?id=22822&pageNum=1
	 * 天眼查接口ID 35
	 */
	public static final String OPENTM = "https://open.api.tianyancha.com/services/v3/open/tm";
	/**
	 * 存储获取公司所有商标信息url
	 * https://open.api.tianyancha.com/services/v3/open/allTm?id=22822
	 * 天眼查接口ID 36
	 */
	public static final String ALLTM = "https://open.api.tianyancha.com/services/v3/open/allTm";
	/**
	 * 存储获取公司专利信息url
	 * https://open.api.tianyancha.com/services/v3/open/patents?name=北京百度网讯科技有限公司&pageNum=1
	 * 天眼查接口ID 43
	 */
	public static final String OPENPATENTS = "https://open.api.tianyancha.com/services/v3/open/patents";
	/**
	 * 存储获取公司所有专利信息url
	 * https://open.api.tianyancha.com/services/v3/open/allPatents?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 44
	 */
	public static final String ALLPATENTS = "https://open.api.tianyancha.com/services/v3/open/allPatents";
	/**
	 * 存储获取公司招投标信息url
	 * https://open.api.tianyancha.com/services/v3/open/bids?name=北京百度网讯科技有限公司&pageNum=1
	 * 天眼查接口ID 45
	 */
	public static final String OPENBIDS = "https://open.api.tianyancha.com/services/v3/open/bids";
	/**
	 * 存储获取公司著作权信息url
	 * https://open.api.tianyancha.com/services/v3/open/copyrights?name=北京百度网讯科技有限公司&pageNum=1
	 * 天眼查接口ID 46
	 */
	public static final String COPYRIGHTS = "https://open.api.tianyancha.com/services/v3/open/copyrights";
	/**
	 * 存储获取公司所有著作权信息url
	 * https://open.api.tianyancha.com/services/v3/open/allCopyrights?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 47
	 */
	public static final String ALLCOPYRIGHTS = "https://open.api.tianyancha.com/services/v3/open/allCopyrights";
	/**
	 * 存储获取公司招聘信息url
	 * https://open.api.tianyancha.com/services/v3/open/employments?name=北京百度网讯科技有限公司&pageNum=1
	 * 天眼查接口ID 42
	 */
	public static final String OPENEMPLOYMENTS = "https://open.api.tianyancha.com/services/v3/open/employments";
	/**
	 * 存储获取公司失信信息url
	 * https://open.api.tianyancha.com/services/v3/open/dishonest?name=天津信裕房地产发展有限公司&pageNum=1
	 * 天眼查接口ID 49
	 */
	public static final String OPENDISHONEST = "https://open.api.tianyancha.com/services/v3/open/dishonest";
	/**
	 * 存储获取公司债券信息url
	 * https://open.api.tianyancha.com/services/v3/open/bonds?name=中国人民银行&pageNum=1
	 * 天眼查接口ID 50
	 */
	public static final String BONDS = "https://open.api.tianyancha.com/services/v3/open/bonds";
	/**
	 * 存储获取公司备案信息url
	 * https://open.api.tianyancha.com/services/v3/open/icp?id=22822
	 * 天眼查接口ID 32
	 */
	public static final String OPENICP = "https://open.api.tianyancha.com/services/v3/open/icp";
	/**
	 * 存储获取公司诉讼信息url
	 * https://open.api.tianyancha.com/services/v3/open/lawsuit?name=北京百度网讯科技有限公司&pageNum=1
	 * 天眼查接口ID 28
	 */
	public static final String OPENLAWSUIT = "https://open.api.tianyancha.com/services/v3/open/lawsuit";
	/**
	 * 存储获取诉讼详情通过uuidurl
	 * https://open.api.tianyancha.com/services/v3/open/lawsuit/detail?id=d4e220698a6c4e178bad5a0913f69dc6
	 * 天眼查接口ID 29
	 */
	public static final String OPENLAWSUITDETAIL = "https://open.api.tianyancha.com/services/v3/open/lawsuit/detail";
	/**
	 * 存储获取公司法院公告信息url
	 * https://open.api.tianyancha.com/services/v3/open/court?id=22822&pageNum=1
	 * 天眼查接口ID 33
	 */
	public static final String COURT = "https://open.api.tianyancha.com/services/v3/open/court";
	/**
	 * 存储获取公司变更信息 by idurl
	 * https://open.api.tianyancha.com/services/v3/open/changeinfo?id=22822
	 * https://open.api.tianyancha.com/services/v3/open/changeInfo?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 31
	 */
	public static final String OPENCHANGEINFO = "https://open.api.tianyancha.com/services/v3/open/changeinfo";
	/**
	 * 存储获取公司新闻信息url
	 * https://open.api.tianyancha.com/services/v3/open/news.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 34
	 */
	public static final String NEWS = "https://open.api.tianyancha.com/services/v3/open/news.json";
	/**
	 * 存储获取公司严重违法信息url
	 * https://open.api.tianyancha.com/services/v3/open/Illegal.json?name=陕西华庭酒店有限公司&pageNum=1
	 * 天眼查接口ID 48
	 */
	public static final String ILLEGAL = "https://open.api.tianyancha.com/services/v3/open/Illegal.json";
	/**
	 * 存储获取公司股权出质信息url
	 * https://open.api.tianyancha.com/services/v3/open/companyEquity.json?name=北京逸璟物业管理有限公司
	 * 天眼查接口ID 56
	 */
	public static final String COMPANYEQUITY = "https://open.api.tianyancha.com/services/v3/open/companyEquity.json";
	/**
	 * 存储获取公司行政处罚信息url
	 * https://open.api.tianyancha.com/services/v3/open/punishment.json?name=北京逸璟物业管理有限公司
	 * 天眼查接口ID 58
	 */
	public static final String PUNISHMENT = "https://open.api.tianyancha.com/services/v3/open/punishment.json";
	/**
	 * 存储获取公司检查抽查信息url
	 * https://open.api.tianyancha.com/services/v3/open/checkInfoList.json?name=博乐市婷芳农资服务部
	 * 天眼查接口ID 59
	 */
	public static final String CHECKINFOLIST = "https://open.api.tianyancha.com/services/v3/open/checkInfoList.json";
	/**
	 * 存储获取公司动产抵押信息url
	 * https://open.api.tianyancha.com/services/v3/open/companyMortgage.json?name=讷河市丰盛现代农业农机专业合作社
	 * 天眼查接口ID 57
	 */
	public static final String COMPANYMORTGAGE = "https://open.api.tianyancha.com/services/v3/open/companyMortgage.json";
	/**
	 * 存储获取公司关联发现信息url
	 * https://open.api.tianyancha.com/services/v3/open/oneKey/c.json?id=948954637
	 * 天眼查接口ID 54
	 */
	public static final String ONEKEY_C = "https://open.api.tianyancha.com/services/v3/open/oneKey/c.json";
	/**
	 * 存储获取公司经营异常信息url
	 * https://open.api.tianyancha.com/services/v3/open/abnormalInfo.json?name=湖南瑞源生物医药科技有限公司
	 * 天眼查接口ID 63
	 */
	public static final String ABNORMALINFO = "https://open.api.tianyancha.com/services/v3/open/abnormalInfo.json";
	/**
	 * 存储获取基本信息url
	 * https://open.api.tianyancha.com/services/v3/open/base.json?id=22822
	 * 天眼查接口ID 37
	 */
	public static final String BASE = "https://open.api.tianyancha.com/services/v3/open/base.json";
	/**
	 * 存储获取基本信息url
	 * https://open.api.tianyancha.com/services/v3/open/edt.json?companyName=北京百度网讯科技有限公司
	 * 天眼查接口ID 38
	 */
	public static final String EDT = "https://open.api.tianyancha.com/services/v3/open/edt.json";
	/**
	 * 存储获取企业基本信息url
	 * https://open.api.tianyancha.com/services/v3/open/baseinfo.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 89
	 */
	public static final String OPEN_BASEINFO = "https://open.api.tianyancha.com/services/v3/open/baseinfo.json";
	/**
	 * 存储获取简单的公司信息url
	 * https://open.api.tianyancha.com/services/v3/open/suggestDetailByName.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 53
	 */
	public static final String SUGGESTDETAILBYNAME = "https://open.api.tianyancha.com/services/v3/open/suggestDetailByName.json";
	/**
	 * 存储获取公司详情 只有基本信息+分支机构+对外投资+股东(增加认缴额度等)+高管url
	 * https://open.api.tianyancha.com/services/v3/open/detail/dt.json?name=中科软科技股份有限公司
	 * 天眼查接口ID 65
	 */
	public static final String DETAIL_DT = "https://open.api.tianyancha.com/services/v3/open/detail/dt.json";
	/**
	 * 存储搜索下拉框url
	 * https://open.api.tianyancha.com/services/v3/open/suggest.json?word=中科软科技股份有限公司
	 * 天眼查接口ID 51
	 */
	public static final String SUGGEST = "https://open.api.tianyancha.com/services/v3/open/suggest.json";
	/**
	 * 存储查询著作权基本信息url
	 * https://open.api.tianyancha.com/services/v3/open/simpleCopy.json?keyword=北京百度网讯科技有限公司
	 * 天眼查接口ID 61
	 */
	public static final String SIMPLECOPY = "https://open.api.tianyancha.com/services/v3/open/simpleCopy.json";
	/**
	 * 存储股东url
	 * https://open.api.tianyancha.com/services/v3/open/holder?id=22822
	 * 天眼查接口ID 72
	 */
	public static final String OPEN_HOLDER = "https://open.api.tianyancha.com/services/v3/open/holder.json";
	/**
	 * 存储获取上市公司基本信息接口url
	 * https://open.api.tianyancha.com/services/v3/open/companyInfo.json?name=深圳市振业（集团）股份有限公司
	 * 天眼查接口ID 74
	 */
	public static final String OPEN_COMPANYINFO = "https://open.api.tianyancha.com/services/v3/open/companyInfo.json";
	/**
	 * 存储获取上市公司高管信息接口url
	 * https://open.api.tianyancha.com/services/v3/open/seniorExecutive.json?name=深圳市振业（集团）股份有限公司&pageNum=1&pageSize=10
	 * 天眼查接口ID 75
	 */
	public static final String OPEN_SENIOREXECUTIVE = "https://open.api.tianyancha.com/services/v3/open/seniorExecutive.json";
	/**
	 * 存储获取上市公司参股或控股的公司url
	 * https://open.api.tianyancha.com/services/v3/open/holdingCompany.json?name=深圳市振业（集团）股份有限公司&pageNum=1&pageSize=10
	 * 天眼查接口ID 76
	 */
	public static final String OPEN_HOLDINGCOMPANY = "https://open.api.tianyancha.com/services/v3/open/holdingCompany.json";
	/**
	 * 存储获取上市公司参股或控股的公司url
	 * https://open.api.tianyancha.com/services/v3/open/shareholder.json?name=深圳市振业（集团）股份有限公司&type=1
	 * 天眼查接口ID 77
	 */
	public static final String OPEN_SHAREHOLDER = "https://open.api.tianyancha.com/services/v3/open/shareholder.json";
	/**
	 * 存储融资历史url
	 * https://open.api.tianyancha.com/services/v3/open/findHistoryRongzi.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 84
	 */
	public static final String OPEN_FINDHISTORYRONGZI = "https://open.api.tianyancha.com/services/v3/open/findHistoryRongzi.json";
	/**
	 * 存储核心团队url
	 * https://open.api.tianyancha.com/services/v3/open/findTeamMember.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 85
	 */
	public static final String OPEN_FINDTEAMMEMBER = "https://open.api.tianyancha.com/services/v3/open/findTeamMember.json";
	/**
	 * 存储企业业务url
	 * https://open.api.tianyancha.com/services/v3/open/getProductInfo.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 86
	 */
	public static final String OPEN_GETPRODUCTINFO = "https://open.api.tianyancha.com/services/v3/open/getProductInfo.json";
	/**
	 * 存储投资事件url
	 * https://open.api.tianyancha.com/services/v3/open/findTzanli.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 87
	 */
	public static final String OPEN_FINDTZANLI = "https://open.api.tianyancha.com/services/v3/open/findTzanli.json";
	/**
	 * 存储竞品信息url
	 * https://open.api.tianyancha.com/services/v3/open/findJingpin.json?name=北京百度网讯科技有限公司
	 * 天眼查接口ID 88
	 */
	public static final String OPEN_FINDJINGPIN = "https://open.api.tianyancha.com/services/v3/open/findJingpin.json";
	/**
	 * 存储投资族谱对外接口url
	 * https://open.api.tianyancha.com/services/v3/open/investtree.json?id=948954637&flag=2&dir=down
	 * 天眼查接口ID 73
	 */
	public static final String INVESTTREE = "https://open.api.tianyancha.com/services/v3/open/investtree.json";
	/**
	 * 存储全维度数据 返回xml数据url
	 * https://open.api.tianyancha.com/services/v3/open/allDetail.json?companyName=中科软科技股份有限公司&type=100001
	 * 天眼查接口ID 69
	 */
	public static final String ALLDETAIL = "https://open.api.tianyancha.com/services/v3/open/allDetail.json";
	/**
	 * 存储全维度数据api 返回json数据url
	 * https://open.api.tianyancha.com/services/v3/open/allDetail2.json?companyName=北京百度网讯科技有限公司&type=100001
	 * 天眼查接口ID 70
	 */
	public static final String ALLDETAIL2 = "https://open.api.tianyancha.com/services/v3/open/allDetail2.json";
	
}

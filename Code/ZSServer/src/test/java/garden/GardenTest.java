package garden;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.app.Application;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.Enterprise;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.garden.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenMap;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.entity.openeyes.Patents;
import com.huishu.ZSServer.entity.openeyes.TouZi;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.repository.company.EnterPriseRepository;
import com.huishu.ZSServer.repository.openeyes.PatentsRepository;
import com.huishu.ZSServer.repository.openeyes.ShangBiaoRepository;
import com.huishu.ZSServer.repository.openeyes.TouZiRepository;
import com.huishu.ZSServer.service.company.CompanyService;
import com.huishu.ZSServer.service.garden.GardenService;
import com.huishu.ZSServer.service.garden_user.GardenUserService;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GardenTest {

	@Autowired
	private GardenService gardenService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private GardenUserService gardenUserService;
	@Autowired
	private OpeneyesService openeyesService;
	@Autowired
	private TouZiRepository repository;
	@Autowired
	private ShangBiaoRepository shangBiaoRepository;
	@Autowired
	private PatentsRepository patentsRepository;
	@Autowired
	private BaseElasticsearch baseElasticsearch;
	@Autowired
	private EnterPriseRepository enterPriseRepository;
	private static Logger log = LoggerFactory.getLogger(GardenTest.class);

	@Test
	public void testFindGardenList() {
		GardenDTO dto = new GardenDTO();
		dto.setIndustryType("生物产业");
		dto.setProvince("北京");
		dto.setMsg(new String[] { "生物产业", "北京", "园区占地", "DESC" });
		Page<GardenData> page = gardenService.findGardensList(dto);
		System.out.println(page.getContent());
	}

	@Test
	public void testFindCompanyList() {
		CompanySearchDTO dto = new CompanySearchDTO();
		String[] msg = new String[] { "全部", "全部", "全部", "全部", "全部" };
		dto.setMsg(msg);
		Page<Company> page = companyService.findCompanyList(dto);
		System.out.println(page.getContent());
	}

	@Test
	public void testFindGardenInfo() {
		Long gardenId = 1L;
		GardenData garden = gardenService.findGarden(gardenId);
		System.out.println(garden);
	}

	@Test
	public void testFindGardensCondition() {
		GardenDTO dto = new GardenDTO();
		dto.setUserId(1L);
		Page<AITInfo> page = gardenService.findGardensCondition(dto);
		System.out.println(page.getContent());
	}

	@Test
	public void testAddAttention() {
		Long gardenId = 1L;
		boolean flag = true;
		GardenUser gu = gardenUserService.attentionGarden(gardenId, 1L, flag);
		System.out.println(gu);
	}

	@Test
	public void testBaseInfo() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("苏宁云商集团股份有限公司");
		JSONObject targetInfo = openeyesService.getBaseInfo(dto);
		System.out.println(targetInfo);
	}

	@Test
	public void testFindGdp() {
		// 2017,2016,2015,2014
		Integer[] ia = new Integer[] { 2017, 2016, 2015, 2014 };
//		List<GardenMap> findGardenGdp = gardenService.findGardenGdp("新材料", ia, "江苏");
//		findGardenGdp.forEach(System.out::println);
	}

	@Test
	public void testAddGardenCompare() {
		JSONObject json = gardenUserService.addGardenCompare(1L, 1L);
		System.out.println(json);
	}

	@Test
	public void testFindBaseInfo() {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "北京百度网讯科技有限公司");

	}

	@Test
	public void testBranch() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("平安银行股份有限公司");
		JSONObject targetInfo = openeyesService.getBranch(dto);
		System.out.println(targetInfo);
	}

	@Test
	public void testHistoryRongZi() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getHistoryRongZi(dto);
		System.out.println(targetInfo);
	}

	@Test
	public void testTeamMember() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getTeamMember(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testProductInfo() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getProductInfo(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testTouZi() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getTouZi(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testJsonArr() {
		String str = "[{\"product\":\"蜻蜓FM\",\"lunci\":\"E轮\",\"company_id\":53051561,\"rongzi_map\":\"{微影资本:24752625,百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/e51ccae1104718c3cb45770442e68bc5.jpg\",\"organization_name\":\"微影资本、百度投资部领投，中国民生投资集团、国中创投、智度股份（000676）等跟投\",\"tzdate\":1506528000000,\"graph_id\":2321537875,\"yewu\":\"电台聚合平台\",\"money\":\"近10亿人民币\",\"location\":\"上海\",\"company\":\"上海麦克风文化传媒有限公司\",\"id\":523418,\"iconOssPath\":\"logo/product/7136c9b5848435105eac3ff3dc106a82.jpg\",\"hangye1\":\"文化娱乐\"},{\"product\":\"kitt.ai\",\"lunci\":\"并购\",\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/b2d51e11d02378927c1faa9280f06867.jpg\",\"organization_name\":\"百度投资部(NASDAQ:BIDU)\",\"tzdate\":1499184000000,\"yewu\":\"热词探测技术开发商\",\"money\":\"未披露\",\"location\":\"西雅图/美国\",\"company\":\"KITT.AI\",\"id\":536234,\"iconOssPath\":\"logo/product/0fb2dc1daba12a3c99a794eda6050db5.jpg\",\"hangye1\":\"人工智能\"},{\"product\":\"红手指\",\"lunci\":\"天使轮\",\"company_id\":25068974,\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img.798youxi.com/product/icon1/968d97b3dc9530692e8d283a2baae342.jpg\",\"organization_name\":\"百度投资部\",\"tzdate\":1499011200000,\"graph_id\":1263915123,\"yewu\":\"智能手机应用虚拟化平台构建与研发\",\"money\":\"未披露\",\"location\":\"湖南\",\"company\":\"湖南红手指信息技术有限公司\",\"id\":491971,\"iconOssPath\":\"logo/product/cba15a572ce36404d362c37621bd660e.jpg\",\"hangye1\":\"工具软件\"},{\"product\":\"蔚来汽车\",\"lunci\":\"D轮\",\"company_id\":76067345,\"rongzi_map\":\"{腾讯产业共赢基金:9519792,华平投资:,百度投资部:22822,高瓴资本:7870866}\",\"icon\":\"http://img1.qimingpian.com/product/raw/b966731e939ae2540738fc58f52866a3.jpg\",\"organization_name\":\"光际资本产业基金领投，腾讯产业共赢基金、百度投资部（纳斯达克:BIDU ）、华平投资、高瓴资本等跟投\",\"tzdate\":1496246400000,\"graph_id\":2353626699,\"yewu\":\"电动汽车研发商\",\"money\":\"未披露\",\"location\":\"上海\",\"company\":\"上海蔚来科技有限公司\",\"id\":524362,\"iconOssPath\":\"logo/product/e5a5d5e2ad7ae2ac85368f91fd072877.jpg\",\"hangye1\":\"汽车交通\"},{\"product\":\"易车网\",\"lunci\":\"战略融资\",\"company_id\":291080,\"rongzi_map\":\"{京东金融:12562796,百度投资部:22822,腾讯产业共赢基金:9519792}\",\"icon\":\"http://img1.qimingpian.com/product/raw/ff1657221265db1852d928b4f602c005.jpg\",\"organization_name\":\"腾讯产业共赢基金、百度投资部、京东金融等\",\"tzdate\":1492617600000,\"graph_id\":13645818,\"yewu\":\"综合汽车互联网企业\",\"money\":\"10亿美元\",\"location\":\"北京\",\"company\":\"北京易车信息科技有限公司\",\"id\":515393,\"iconOssPath\":\"logo/product/bd11b1a007c58defd016c474e9ce10ae.jpg\",\"hangye1\":\"汽车交通\"},{\"product\":\"xPerception\",\"lunci\":\"并购\",\"company_id\":105444277,\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/8efc1d7926f30ec1443c2c9dd561a4df.jpg\",\"organization_name\":\"百度投资部\",\"tzdate\":1492012800000,\"graph_id\":2979182704,\"yewu\":\"机器视觉软硬件解决方案提供商\",\"money\":\"未披露\",\"location\":\"广东\",\"company\":\"万物感知（深圳）科技有限公司\",\"id\":529063,\"iconOssPath\":\"logo/product/5c69c752ec575254182b235a80991922.jpg\",\"hangye1\":\"人工智能\"},{\"product\":\"爱奇艺\",\"lunci\":\"债权融资\",\"company_id\":486797,\"rongzi_map\":\"{高瓴资本:7870866,红杉资本中国:90700255,百度投资部:22822,IDG资本:}\",\"icon\":\"http://img1.qimingpian.com/product/raw/aec9deafdf93f60b7d7df5d603e625a8.jpg\",\"organization_name\":\"百度投资部（3亿美元），高瓴资本，博裕资本，润良泰基金，IDG资本，光际资本产业基金，红杉资本中国，襄禾资本等\",\"tzdate\":1487692800000,\"graph_id\":23537076,\"yewu\":\"网络视频综合服务商\",\"money\":\"15.3亿美元\",\"location\":\"北京\",\"company\":\"北京爱奇艺科技有限公司\",\"id\":514083,\"iconOssPath\":\"logo/product/9220f4a464b7af43c46d2baa3bd8b505.jpg\",\"hangye1\":\"文化娱乐\"},{\"product\":\"渡鸦科技\",\"lunci\":\"并购\",\"company_id\":553089,\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/0d8d066daf7045369224e2d32417d4cc.jpg\",\"organization_name\":\"百度投资部\",\"tzdate\":1487174400000,\"graph_id\":26889474,\"yewu\":\"智能家居中控硬件研发商\",\"money\":\"未披露\",\"location\":\"北京\",\"company\":\"渡鸦科技有限责任公司\",\"id\":492375,\"iconOssPath\":\"logo/product/0bbf25d4ae61b77e2646c5c1b49f8f39.jpg\",\"hangye1\":\"人工智能\"},{\"product\":\"房司令\",\"lunci\":\"D轮\",\"company_id\":15389634,\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/0d60e3882148b9fbcee86ca2a86c7967.jpg\",\"organization_name\":\"百度投资部领投，赢知天泽基金联合投资\",\"tzdate\":1486656000000,\"graph_id\":775198605,\"yewu\":\"租房分期支付平台\",\"money\":\"数千万美元\",\"location\":\"江苏\",\"company\":\"南京邦航投资管理有限公司\",\"id\":510633,\"iconOssPath\":\"logo/product/9a36f0bcddeade9b6775bf7a31942ab9.jpg\",\"hangye1\":\"金融\"},{\"product\":\"李叫兽\",\"lunci\":\"并购\",\"company_id\":78625501,\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img.798youxi.com/product/upload/5864f81a12162.png\",\"organization_name\":\"百度投资部\",\"tzdate\":1482940800000,\"graph_id\":2358180982,\"yewu\":\"企业营销咨询公司\",\"money\":\"近亿人民币\",\"location\":\"北京\",\"company\":\"北京受教信息科技有限公司\",\"id\":518402,\"iconOssPath\":\"logo/product/46f1a75c03fd03ae51f5fe9b55ac99ba.png\",\"hangye1\":\"企业服务\"},{\"product\":\"RiceQuant\",\"lunci\":\"A轮\",\"company_id\":25477265,\"rongzi_map\":\"{华睿投资:2349759748,百度投资部:22822}\",\"icon\":\"http://img.798youxi.com/product/icon/201603220157139014.png\",\"organization_name\":\"华睿投资、百度投资部\",\"tzdate\":1480521600000,\"graph_id\":1284552427,\"yewu\":\"量化交易平台\",\"money\":\"2500万人民币\",\"location\":\"广东\",\"company\":\"深圳米筐科技有限公司\",\"id\":492722,\"iconOssPath\":\"logo/product/02fda21b063f227f2c40b64d5c374371.png\",\"hangye1\":\"金融\"},{\"product\":\"智能车联\",\"lunci\":\"天使轮\",\"company_id\":99113705,\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img.798youxi.com/product/upload/589bde18f0589.png\",\"organization_name\":\"百度投资部，亦庄国投\",\"tzdate\":1480521600000,\"graph_id\":2959951982,\"yewu\":\"智慧交通与车联网设备研发\",\"money\":\"未披露\",\"location\":\"北京\",\"company\":\"北京智能车联产业创新中心有限公司\",\"id\":515117,\"iconOssPath\":\"logo/product/424e1b3359207502b14d6cc442f700b0.png\",\"hangye1\":\"汽车交通\"},{\"product\":\"智课网\",\"lunci\":\"B轮\",\"company_id\":508617,\"rongzi_map\":\"{海通创意资本:408631025,百度投资部:22822,娱乐工场:,海通开元:23130794}\",\"icon\":\"http://img.798youxi.com/product/upload/58170a7807dc0.png\",\"organization_name\":\"金砖资本、海通创意资本、广东南方媒体融合基金、南方资本、百度投资部、娱乐工场、海通开元\",\"tzdate\":1478534400000,\"graph_id\":24643544,\"yewu\":\"留学考试及英语在线学习平台\",\"money\":\"2亿人民币\",\"location\":\"北京\",\"company\":\"北京创新伙伴教育科技有限公司\",\"id\":518989,\"iconOssPath\":\"logo/product/25a8557e374efba39c5b5266887e7f2f.png\",\"hangye1\":\"教育培训\"},{\"product\":\"斗米兼职\",\"lunci\":\"B轮\",\"company_id\":37264484,\"rongzi_map\":\"{高瓴资本:7870866,百度投资部:22822,高榕资本:46501402,蓝湖资本:1711434583,腾讯产业共赢基金:9519792}\",\"icon\":\"http://img.798youxi.com/product/upload/5799c102e79bf.png\",\"organization_name\":\"高瓴资本、腾讯产业共赢基金、百度投资部、新希望集团、高榕资本、蓝湖资本\",\"tzdate\":1476806400000,\"graph_id\":2310295327,\"yewu\":\"兼职招聘服务网站\",\"money\":\"4000万美元\",\"location\":\"北京\",\"company\":\"北京世诚优聘科技发展有限公司\",\"id\":520490,\"iconOssPath\":\"logo/product/5cef661ba850de150f0c964d10d65d1c.png\",\"hangye1\":\"企业服务\"},{\"product\":\"oTMS\",\"lunci\":\"B轮\",\"company_id\":841948,\"rongzi_map\":\"{经纬中国:81171605,百度投资部:22822,成为资本:2312528457}\",\"icon\":\"http://img.798youxi.com/product/icon/201603212107154897.jpg\",\"organization_name\":\"陈伟星领投，成为资本、经纬中国,百度投资部跟投\",\"tzdate\":1473609600000,\"graph_id\":41476007,\"yewu\":\"SaaS社区型运输系统提供商\",\"money\":\"2500万美元\",\"location\":\"北京\",\"company\":\"北京百川快线网络科技有限公司\",\"id\":513735,\"iconOssPath\":\"logo/product/7e4b76406a422af7c3b13aaee0d61b86.jpg\",\"hangye1\":\"物流运输\"},{\"product\":\"MoboPlayer\",\"lunci\":\"A轮\",\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/bb2b946ca5eb102a8f2ce2d1f834cc05.jpg\",\"organization_name\":\"百度投资部\",\"tzdate\":1472745600000,\"yewu\":\"视频播放工具\",\"money\":\"未披露\",\"location\":\"山东\",\"company\":\"济南四叶草信息技术有限公司\",\"id\":497278,\"iconOssPath\":\"logo/product/6dd6b8f733ad082ec41018da7dd70d70.jpg\",\"hangye1\":\"工具软件\"},{\"product\":\"趣医网\",\"lunci\":\"B+轮\",\"company_id\":27199706,\"rongzi_map\":\"{百度投资部:22822,软银中国资本:2345036842,弘晖资本:573084610}\",\"icon\":\"http://img.798youxi.com/product/upload/5943375dcb9d3.png\",\"organization_name\":\"软银中国资本、弘晖资本、百度投资部、德屹资本\",\"tzdate\":1472054400000,\"graph_id\":1371561799,\"yewu\":\"就医咨询与陪诊服务平台\",\"money\":\"2.16亿人民币\",\"location\":\"上海\",\"company\":\"上海趣医网络科技有限公司\",\"id\":524098,\"iconOssPath\":\"logo/product/6dc2b33d52382917168d5d211da4d7e2.png\",\"hangye1\":\"医疗健康\"},{\"product\":\"Velodyne\",\"lunci\":\"战略融资\",\"rongzi_map\":\"{百度投资部:22822}\",\"icon\":\"http://img.798youxi.com/product/upload/57b40a4a19780.png\",\"organization_name\":\"百度投资部,福特汽车\",\"tzdate\":1471363200000,\"yewu\":\"激光雷达技术研发商\",\"money\":\"1.5亿美元\",\"location\":\"加州摩根山/美国\",\"company\":\"Velodyne LiDAR, Inc.\",\"id\":530102,\"iconOssPath\":\"logo/product/782eccae453f081287bffdd9c47529e3.png\",\"hangye1\":\"硬件\"},{\"product\":\"客来乐\",\"lunci\":\"B轮\",\"company_id\":525425,\"rongzi_map\":\"{紫辉创投:18887532,经纬中国:81171605,百度投资部:22822}\",\"icon\":\"http://img1.qimingpian.com/product/raw/2496c3b18ee09282d2e62016102e493a.jpg\",\"organization_name\":\"嘉信资本，经纬中国，紫辉创投，百度投资部\",\"tzdate\":1470931200000,\"graph_id\":25495389,\"yewu\":\"实体店支付解决方案服务商\",\"money\":\"1.1亿人民币\",\"location\":\"北京\",\"company\":\"北京卡富通盈科技有限公司\",\"id\":518482,\"iconOssPath\":\"logo/product/24d72df90809f49cfdf04b76ba284bcc.jpg\",\"hangye1\":\"金融\"},{\"product\":\"易鑫车贷\",\"lunci\":\"B轮\",\"company_id\":688129,\"rongzi_map\":\"{百度投资部:22822,腾讯产业共赢基金:9519792,京东金融:12562796}\",\"icon\":\"http://img1.qimingpian.com/product/raw/c0bb2f8c755ff015512f0135f37ab152.jpg\",\"organization_name\":\"腾讯产业共赢基金、百度投资部、京东金融等\",\"tzdate\":1470153600000,\"graph_id\":33710392,\"yewu\":\"线上汽车金融服务平台\",\"money\":\"5.5亿美元\",\"location\":\"北京\",\"company\":\"北京易鑫信息科技有限公司\",\"id\":515375,\"iconOssPath\":\"logo/product/5995d3f35f4f47e654b49a84282fe9c9.jpg\",\"hangye1\":\"汽车交通\"}]";
		JSONArray arr = JSONArray.parseArray(str);
		List<TouZi> list = new ArrayList<>();
		arr.forEach(data -> {
			TouZi parseObject = JSONObject.parseObject(data.toString(), TouZi.class);
			parseObject.setCompanyName("北京百度网讯科技有限公司");
			list.add(parseObject);
		});
		try {
			repository.save(list);
		} catch (Exception e) {
			log.debug("保存投资失败", e.getMessage());
		}
	}

	@Test
	public void testJingpin() {// 没有主键，测试未通过
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getJingPin(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testShangbiao() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getShangBiao(dto);
		System.out.println(targetInfo.toJSONString());
		// String str =
		// "[{\"regNo\":\"24821594\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/9a0328188201408af12cc376850b9789.jpg\",\"appDate\":\"1497974400000\",\"id\":\"23491404\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24833962\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/92e1b61ee2ee285e07926cf603cc81a1.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23477603\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24849930\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/f49aff07fb645047fa326f5e4b2af0c4.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23486196\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24818934\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/44f43568a8dbd54f3c78b3c2fb3c9173.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23471448\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24818955\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/08b4e57f8a57537d380c000d1b630200.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23471513\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24833196\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/03cdcb878ab7d4f2a2aeee47ac8b4805.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23476881\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24823541\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/1f3b24ad9bcd58c4ac4750f40ef54412.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23492973\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24863807\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/13df3351f36b75ff0338a1e5955bdd34.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23500569\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24878598\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/82cc8017d84a48e552d5afade76da97d.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23506223\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24818219\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/1958e268eeb9ca6fe110d8bce31e2b7c.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23470940\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24820559\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/3808c9d8ff8e7df0be93f0548e48c64d.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23472485\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24831088\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/51bf99970118ef0d50714bb2239827fa.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23474803\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24833187\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/e12d10baca7859bd7be9e6eb2f27a097.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23476867\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24878600\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/878719502ba61a6d006da40c9a2d3dea.jpg\",\"appDate\":\"1497801600000\",\"id\":\"23506250\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24114937\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/1b46bde3fd8e1c57601860681f2fd648.jpg\",\"appDate\":\"1494518400000\",\"id\":\"23310006\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24115401\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/14ee793e0f6f785029f90de818b86a91.jpg\",\"appDate\":\"1494518400000\",\"id\":\"23310631\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"24023586\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/9070494f70396e0dd8eed57cd9aeed42.jpg\",\"appDate\":\"1494259200000\",\"id\":\"23205928\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"23967305\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/2dd4a8ba7a1ca57571e61663c0769601.jpg\",\"appDate\":\"1493827200000\",\"id\":\"23167638\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"23896054\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/8d4f888f5b638ece6a5ccc681870f3c5.jpg\",\"appDate\":\"1493827200000\",\"id\":\"23126296\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"},{\"regNo\":\"23896075\",\"companyId\":\"22822\",\"tmPic\":\"http://tm-image.tianyancha.com/tm/2f0b888d8b42cc3be6e31464cb2f7bab.jpg\",\"appDate\":\"1493827200000\",\"id\":\"23126433\",\"intCls\":\"38-电讯、通信服务\",\"category\":\"其他\",\"applicantCn\":\"北京百度网讯科技有限公司\",\"status\":\"不定\"}]";
		// JSONArray arr = JSONArray.parseArray(str);
		// List<ShangBiao> list = new ArrayList<>();
		// arr.forEach(data ->{
		// ShangBiao parseObject = JSONObject.parseObject(data.toString(),
		// ShangBiao.class);
		// list.add(parseObject);
		// });
		// try{
		// shangBiaoRepository.save(list);
		// }catch(Exception e){
		// log.debug("保存投资失败", e.getMessage());
		// }
	}

	@Test
	public void testZhuanli() {
		// OpeneyesDTO dto = new OpeneyesDTO();
		// Map<String, String> params = new HashMap<>();
		// params.put("name", "北京百度网讯科技有限公司");
		// dto.setParams(params);
		// dto.setSpec(KeyConstan.URL.PATENTS);
		// JSONObject targetInfo = openeyesService.getPatents(dto);
		// System.out.println(targetInfo.toJSONString());
		String str = "{\"special\":\"\",\"isLogin\":0,\"data\":{\"pageSize\":\"20\",\"viewtotal\":\"1618\",\"items\":[{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了信息推送方法和装置。该方法的一具体实施方式包括：获取第一用户的第一定位坐标和第一用户需求信息；从至少一个第二用户中选取第二定位坐标与第一定位坐标的距离小于预设距离阈值的第二用户形成候选第二用户集合；获取候选第二用户集合中每个候选第二用户的候选第二用户职业信息；在用于表征用户需求信息与用户职业信息的对应关系的对应关系表中，查询与第一用户需求信息对应的用户职业信息，并在候选第二用户集合中选取候选第二用户职业信息与所查询到的用户职业信息匹配的候选第二用户作为目标第二用户；向第一用户所使用的第一终端推送目标第二用户的联系方式信息。该实施方式实现了富于针对性的信息推送。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710083552.7\",\"applicationPublishTime\":\"2017.06.20\",\"uuid\":\"09cdf7c8e5cc4d64afc64d8665787257\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710083552.7/201710083552.gif\",\"mainCatNum\":\"G06Q50/00(2012.01)I\",\"applicationTime\":\"2017.02.16\",\"inventor\":\"杨延超\",\"patentName\":\"信息推送方法和装置\",\"applicationPublishNum\":\"CN106875279A\",\"allCatNum\":\"G06Q50/00(2012.01)I;G06F17/30(2006.01)I;H04L29/08(2006.01)I;H04M1/2745(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明的目的是提供一种用于流式计算系统中的故障处理的方法、装置、计算节点和计算机程序产品。其中，在一个计算节点，记录来自上游计算节点的各原始数据的到达顺序；将所述各原始数据按照预定的周期进行持久化操作；当发生故障而重启后，按照所记录的到达顺序从经持久化操作的原始数据和/或所述上游计算节点恢复内存中的待计算数据，并将所恢复的待计算数据按照其之前的到达顺序进行重放和计算；将每条计算完毕的结果数据按照故障前上一持久化操作周期的结果数据的偏移量继续编码并发送至下一节点。与现有技术相比，本发明提供了一种在框架层实现的流式计算输出结果不重不丢的容错机制，可应对各种软硬件故障，保证输出结果的高准确性。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710035881.4\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"83a78bb251c54bdd983c25103ea4e9aa\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710035881.4/201710035881.gif\",\"mainCatNum\":\"G06F11/07(2006.01)I\",\"applicationTime\":\"2017.01.17\",\"inventor\":\"石然;程怡;张建伟;高伟康\",\"patentName\":\"流式计算系统中计算节点的故障处理\",\"applicationPublishNum\":\"CN106874133A\",\"allCatNum\":\"G06F11/07(2006.01)I\"},{\"agent\":\"宋合成\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京清亦华知识产权代理事务所(普通合伙) 11201\",\"abstracts\":\"本发明提出一种基于位置信息的推送方法，其特征在于，包括以下步骤：推送服务器记录用户经过的多个区域；推送服务器根据用户在多个区域中使用电子地图的频次获得用户的常驻区域；当判断用户从常驻区域进入新区域时，推送服务器根据用户在常驻区域中的兴趣点信息向用户推送与新区域中的兴趣点信息。本发明本方法充分挖掘用户兴趣，进行个性化定制，可以更好的满足并激发用户需求，适用面广泛，易于扩展。本发明还公开了一种基于位置信息的推送系统和推送服务器。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201310113186.7\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"c9dac895850445d6ad085912af29ae02\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/SQ/20170620/201310113186.7/201310113186.gif\",\"mainCatNum\":\"G06F17/30(2006.01)I\",\"applicationTime\":\"2013.04.02\",\"inventor\":\"林华;罗珺;杜洪先\",\"patentName\":\"基于位置信息的推送方法、系统和装置\",\"applicationPublishNum\":\"CN104102638B\",\"allCatNum\":\"G06F17/30(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明提供了一种网关防攻击的方法和装置。该方法包括：获取网关各接口上的单位时间接收访问请求次数；基于获取的接口上的单位时间接收访问请求次数与接口单位时间接收访问请求次数阈值的比较，确定不安全接口，其中所述接口单位时间接收访问请求次数阈值基于该接口单位时间最大负载访问请求次数、以及统计出的该接口平均负载饱和度占网关所有接口的接口平均负载饱和度之和的比事先确定；基于访问不安全接口的访问者地址单位时间访问该不安全接口的请求次数，在不安全接口上确定不安全访问者地址。本发明在更准确地识别出不安全访问者地址的同时，减少了将正常用户的频繁访问当作攻击的可能性。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710020303.3\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"d59c6a8463574facb691e7c7fb60b616\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710020303.3/201710020303.gif\",\"mainCatNum\":\"H04L29/06(2006.01)I\",\"applicationTime\":\"2017.01.11\",\"inventor\":\"杨延超\",\"patentName\":\"网关防攻击的方法和装置\",\"applicationPublishNum\":\"CN106878282A\",\"allCatNum\":\"H04L29/06(2006.01)I;H04L12/66(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明的目的是提供一种用于在云系统中进行资源调度的方法和装置。根据本发明的方法包括以下步骤：根据云系统中计算资源的稳定性，确定各个计算资源各自的资源优先级；‑在需要进行资源调度时，确定当前作业任务相对应的调度算法；‑基于所述调度算法和当前可用的各个计算资源的资源优先级，进行资源分配。与现有技术相比，本发明具有以下优点：通过对计算资源的优先级进行细分并支持多种调度算法，从而基于多样化的调度算法和资源优先级来进行资源调度，提高了资源调度的灵活性，提升了资源利用率和系统吞吐量。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710005566.7\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"b8357b24c85f44578c701b0df012dbf9\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710005566.7/201710005566.gif\",\"mainCatNum\":\"H04L29/08(2006.01)I\",\"applicationTime\":\"2017.01.04\",\"inventor\":\"张慕华;孟宪军;应茹\",\"patentName\":\"用于在云系统中进行资源调度的方法和装置\",\"applicationPublishNum\":\"CN106878389A\",\"allCatNum\":\"H04L29/08(2006.01)I;H04L12/911(2013.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了信息推送方法和装置。该方法的一具体实施方式包括：接收第一客户端发送的对目标网页的分享请求，其中，该分享请求包括第一用户标识和该目标网页的页面标识；基于该第一用户标识和该页面标识，生成���一标识，并生成用于指示该目标网页的、带有该第一标识的第一网址；响应于接收到第二客户端发送的包含该第一网址的第一网页查询请求，确定第二用户标识，并执行如下映射建立步骤：基于该第二用户标识和该页面标识，生成第二标识；建立该第一标识和该第二标识的映射；向与所建立的映射相关联的各个客户端推送预置信息。该实施方式实现了富于针对性的信息推送。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710083553.1\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"e4590aa4d226408488200b75339ab98d\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710083553.1/201710083553.gif\",\"mainCatNum\":\"G06F17/30(2006.01)I\",\"applicationTime\":\"2017.02.16\",\"inventor\":\"蔡泽华\",\"patentName\":\"信息推送方法和装置\",\"applicationPublishNum\":\"CN106874471A\",\"allCatNum\":\"G06F17/30(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明的目的是提供一种超大规模机器自动化维修的方法和装置。与现有技术相比，本发明收集超大规模机器中的软和/或硬件故障；对所述软和/或硬件故障进行故障分析，获得对应的故障数据；基于所述故障数据，采用维修状态机，对各个状态流转完成对所述超大规模机器的自动化维修，其中，对于需要迁移的数据所对应的机器进行迁移整机维修，对于存储型服务所对应的机器进行在线修盘。对于超大规模机器，本发明可以满足诸如故障检测、服务迁移、环境部署、机器维修状态流转、快速交付等，节约了运维人力、通过提高流转效率节约机器；检测、维修、服务迁移和部署实现全部自动化无需人工接入；机器交付效率高，可以实现小时级和分钟级交付。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710005057.4\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"fbc0df030b734a609b66c97effd54a99\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710005057.4/201710005057.gif\",\"mainCatNum\":\"G06Q10/00(2012.01)I\",\"applicationTime\":\"2017.01.04\",\"inventor\":\"胡志广;张祐;胡达\",\"patentName\":\"一种超大规模机器自动化维修的方法和装置\",\"applicationPublishNum\":\"CN106875018A\",\"allCatNum\":\"G06Q10/00(2012.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了用于检测机房故障的方法、装置及设备。待检测机房包括多个服务器集合，每个服务器集合处理一种类型的数据请求并且该服务器集合响应于所处理的数据请求满足预设条件而生成报警信息，该报警信息包括该服务器集合的服务器集合标识，该方法的一具体实施方式包括：获取预定时间段内待检测机房的报警记录，其中，该报警记录包括该预定时间段内该待检测机房内的服务器集合生成的报警信息；确定第一数量，其中，第一数量为该报警记录中出现的不同服务器集合标识的数量；基于所确定的第一数量，确定该待检测机房是否出现故障。该实施方式提高了确定机房是否出现故障的效率。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710089057.7\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"126b189aa801404f99de500673cd6623\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710089057.7/201710089057.gif\",\"mainCatNum\":\"G06F11/07(2006.01)I\",\"applicationTime\":\"2017.02.20\",\"inventor\":\"陈云;王博;郭宣佑\",\"patentName\":\"用于检测机房故障的方法、装置及设备\",\"applicationPublishNum\":\"CN106874135A\",\"allCatNum\":\"G06F11/07(2006.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了用于输出信息的方法和装置。该方法的一具体实施方式包括：响应于接收到用户输入的字符，输出候选字词集合；响应于接收到用户输入的与字符相关联的语音，对语音进行语音识别得到识别字词集合；将识别字词集合与候选字词集合进行匹配得到匹配字词集合；输出匹配字词集合。该实施方式实现了对通过键盘输入法或手写输入法得到的候选字词进一步使用语音进行筛选，减少了候选字词的数量，从而提高了文字输入速度。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710083540.4\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"372204469e4046e283de94f7cffa2e6a\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710083540.4/201710083540.gif\",\"mainCatNum\":\"G06F3/023(2006.01)I\",\"applicationTime\":\"2017.02.16\",\"inventor\":\"李瑾;胡官钦\",\"patentName\":\"用于输出信息的方法和装置\",\"applicationPublishNum\":\"CN106873798A\",\"allCatNum\":\"G06F3/023(2006.01)I\"},{\"agent\":\"宋合成\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京清亦华知识产权代理事务所(普通合伙) 11201\",\"abstracts\":\"本发明提出一种地图的标注方法和系统。其中该方法包括以下步骤：分布式计算系统获取原始地图数据；任务调度服务器对原始地图数据进行网格切分以获取多个网格地图数据，并将多个网格地图数据分发至多个任务执行服务器；任务执行服务器对网格地图数据进行避让计算，并对网格地图数据中的每个元素进行标注；任务调度服务器根据获取多个任务执行服务器发送的多个标注结果，并根据元素的ID将元素及对应的标注结果分发至多个任务执行服务器；以及任务执行服务器对元素的标注结果进行合并去重以获取原始地图中每个元素的标注结果。根据本发明的实施例，极大提高数据量的吞吐能力和处理速度。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201210592995.6\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"0470af060da44b45b986c4704f2516c1\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/SQ/20170620/201210592995.6/201210592995.gif\",\"mainCatNum\":\"G06F9/46(2006.01)I\",\"applicationTime\":\"2012.12.31\",\"inventor\":\"邱胜科\",\"patentName\":\"地图的标注方法和系统\",\"applicationPublishNum\":\"CN103914334B\",\"allCatNum\":\"G06F9/46(2006.01)I;G09B29/00(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明的目的是提供一种检测分布式存储系统中的慢节点的方法、设备、系统以及计算机程序产品。其中，当检测到慢节点事件，客户端向所述文件的各副本节点发送异步请求，所述副本节点为存储有所述文件的副本的存储节点；根据所述各副本节点的响应信息，从所述各副本节点中确定慢节点；向所述主节点通知所述慢节点，以由所述主节点将所述慢节点从所述副本节点列表中删除；刷新所述文件的副本节点列表，以重新对其中一个副本节点中的文件副本执行数据写入操作。与现有技术相比，本发明通过对分布式存储系统中慢节点的探测及规避策略，使得分布式存储系统在用户的读写请求的耗时长尾显著减少。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710005058.9\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"afe5b66fefd34e3ebb5225c652498d48\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710005058.9/201710005058.gif\",\"mainCatNum\":\"H04L29/08(2006.01)I\",\"applicationTime\":\"2017.01.04\",\"inventor\":\"李志文;郭波\",\"patentName\":\"对分布式存储系统中慢节点的检测\",\"applicationPublishNum\":\"CN106878388A\",\"allCatNum\":\"H04L29/08(2006.01)I;G06F3/06(2006.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了输入方法和装置。该方法的一具体实施方式包括：获取用户的输入信息；在预先建立的词库中查询与输入信息匹配的候选词条；根据预定数量的历史周期内用户输入候选词条的次数，调整候选词条的当前词频；根据调整后的词频，确定候选词条的顺序；按照所述顺序，呈现候选词条。该实施方式实现了根据用户习惯的改变调整词频，提供个性化的搜索结果，从而提高了用户输入词条的效率。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710083643.0\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"c60b30f12c054c9eaa9c0f3d195d3200\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710083643.0/201710083643.gif\",\"mainCatNum\":\"G06F3/023(2006.01)I\",\"applicationTime\":\"2017.02.16\",\"inventor\":\"张东栋;田野;孟可丰\",\"patentName\":\"输入方法和装置\",\"applicationPublishNum\":\"CN106873799A\",\"allCatNum\":\"G06F3/023(2006.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了语音输入方法和装置。该方法的一具体实施方式包括：响应于检测到启动语音输入方式的指令，将输入法应用的当前输入方式切换为语音输入方式；识别用户在语音输入方式下输入的语音的音量值；确定所识别出的音量值是否处于预设音量值范围内；若所识别出的音量值不是处于预设音量值范围内，则向用户呈现提示信息以提示用户控制当前输入的语音的音量值；响应于接收到结束语音输入的指令，将用户在语音输入方式下输入的语音以预设方式输出。该实施方式实现了在语音输入方式下对用户输入语音的音量值的有效控制。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710083638.X\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"e8ee37d2633842308586401bf65b97a7\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710083638.X/201710083638.gif\",\"mainCatNum\":\"G06F3/16(2006.01)I\",\"applicationTime\":\"2017.02.16\",\"inventor\":\"苑小军\",\"patentName\":\"语音输入方法和装置\",\"applicationPublishNum\":\"CN106873937A\",\"allCatNum\":\"G06F3/16(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明的目的是提供一种用于管理元信息的方法和装置。根据本发明的方法包括以下步骤：在目标系统的各个数据块中存储或更新其对应的元信息。与现有技术相比，本发明具有以下优点：通过将元信息存储于数据块中，可避免由于文件损坏而导致大量数据的丢失，提升了数据存储的安全性；并且，根据本发明的方案可创建多个线程以并行的方式加载元信息，从而提升了系统启动速度。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710005568.6\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"f0a0b5c913244a2aa47cdf3bd00d3922\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710005568.6/201710005568.gif\",\"mainCatNum\":\"G06F3/06(2006.01)I\",\"applicationTime\":\"2017.01.04\",\"inventor\":\"付万宇\",\"patentName\":\"用于管理元信息的方法和装置\",\"applicationPublishNum\":\"CN106873906A\",\"allCatNum\":\"G06F3/06(2006.01)I\"},{\"agent\":\"罗朋\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京汉昊知识产权代理事务所(普通合伙) 11370\",\"abstracts\":\"本发明提供了一种分布式工作流调度的方法和装置，获取用户提供的作业执行指令、工作流拓扑关系以及作业间的依赖关系，基于所述工作流拓扑关系和所述作业间的依赖关系查找与所述作业执行指令对应的节点，获取已准备就绪的所述节点，通过资源管理模块确定已准备就绪的所述节点所需要的资源配额，并通过集群资源调度系统调度与所述集群资源配额对应的资源至所述资源管理模块由已获取所述资源的所述资源管理模块通过资源调度代理发送启动指令，以启动远程作业执行模块执行所述作业执行指令。本发明可以基于作业间复杂的依赖关系遍历Flow的拓扑，实现了工作流的分布式化运行，分离了工作流中的调度逻辑与执行逻辑，并分离了工作流的调度与资源管理逻辑。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710005620.8\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"4079049f287247bfa9541605c75b59d5\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710005620.8/201710005620.gif\",\"mainCatNum\":\"G06F9/48(2006.01)I\",\"applicationTime\":\"2017.01.04\",\"inventor\":\"苗科展;孟宪军\",\"patentName\":\"一种分布式工作流调度的方法和装置\",\"applicationPublishNum\":\"CN106874084A\",\"allCatNum\":\"G06F9/48(2006.01)I;G06F9/50(2006.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了信息输出方法和装置。该方法的一具体实施方式包括：接收用户输入的语音信息；对上述语音信息进行分析，确定上述用户的情绪特征以及与上述语音信息对应的文本信息；基于上述情绪特征，对上述文本信息设置文字效果；将已应用上述文字效果的上述文本信息按照预定格式进行输出。该实施方式实现了多样化的信息输出。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710089052.4\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"1f98f808f1464de8baafca7225bb9649\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710089052.4/201710089052.gif\",\"mainCatNum\":\"G06F3/023(2006.01)I\",\"applicationTime\":\"2017.02.20\",\"inventor\":\"门文;杨林达;黎蕾\",\"patentName\":\"信息输出方法和装置\",\"applicationPublishNum\":\"CN106873800A\",\"allCatNum\":\"G06F3/023(2006.01)I;G10L25/63(2013.01)I;G10L15/26(2006.01)I\"},{\"agent\":\"袁媛\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京鸿德海业知识产权代理事务所(普通合伙) 11412\",\"abstracts\":\"本发明提供了一种自动驾驶切断装置以及一种自动驾驶切断方法和系统，其中自动驾驶切断装置包括：乘客操作接口、控制开关以及指令过滤器；所述控制开关一端作为输入端用于连接驾驶系统，另一端连接所述指令过滤器；所述指令过滤器中未与控制开关相连接的一端作为输出端用于连接车辆底盘控制单元；所述乘客操作接口，用于被触发后断开所述控制开关；所述指令过滤器，用于若接收到来自驾驶系统的驾驶指令，则通过输出端输出该驾驶指令；否则，通过输出端输出本地存储的安全驾驶指令。本发明所提供的自动驾驶切断装置，用于实现在驾驶系统出现异常时切断驾驶系统所发出的驾驶指令，并能够确保车辆不会失去所有控制，从而提高车辆行驶的安全性。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710081535.X\",\"applicationPublishTime\":\"2017-06-20\",\"uuid\":\"cfdfb8170da5452fb8be0fc1f4033552\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170620/201710081535.X/201710081535.gif\",\"mainCatNum\":\"G05B23/02(2006.01)I\",\"applicationTime\":\"2017.02.15\",\"inventor\":\"张博;云朋;佘晓丽\",\"patentName\":\"自动驾驶切断装置以及自动驾驶切断方法和系统\",\"applicationPublishNum\":\"CN106873572A\",\"allCatNum\":\"G05B23/02(2006.01)I\"},{\"agent\":\"宋合成\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京清亦华知识产权代理事务所(普通合伙) 11201\",\"abstracts\":\"本发明提出一种数据中心的云监控方法及云平台，其中，方法包括：获取数据中心的基础设施的监控数据，对监控数据进行风险评估，得到基础设施存在风险的风险概率，在风险概率高于或者等于预设的阈值时输出告警信息。本发明中，通过云平台来监控数据中心的基础设施，可以基于采集的基础设施的监控数据，预先对基础设施可能出现风险或者故障的概率进行评估，在概率较大时可以输出告警信息，从而可以实现在故障出现之前向运维人员预先告警，使得运维人员可以尽早发现故障，对其进行排查或者修复，摒弃了现有的故障发生后再报警维修的监控模式，能够尽可能地保证了数据中心的持续稳定地运行。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710069736.8\",\"applicationPublishTime\":\"2017-06-16\",\"uuid\":\"6eff6082e4c04ff795391c7493f5d542\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170616/201710069736.8/201710069736.gif\",\"mainCatNum\":\"H04L29/08(2006.01)I\",\"applicationTime\":\"2017.02.08\",\"inventor\":\"颜小云;王尊;李孝众;张炳华\",\"patentName\":\"数据中心的云监控方法及云平台\",\"applicationPublishNum\":\"CN106856508A\",\"allCatNum\":\"H04L29/08(2006.01)I;H04L29/06(2006.01)I;H04L12/24(2006.01)I\"},{\"agent\":\"王达佐;马晓亚\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京英赛嘉华知识产权代理有限责任公司 11204\",\"abstracts\":\"本申请公开了用于操作群组的方法和装置、服务器以及终端。该方法的一具体实施方式包括：接收终端发送的加入群组的请求，其中群组的群组标识预先与地理位置范围信息关联存储；获取终端当前所处地理位置的地理位置信息；判断地理位置信息是否与群组标识关联的地理位置范围信息匹配；若匹配，则将终端的用户标识添加至群组的成员列表中。该实施方式实现了对群组加入请求的自动验证。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710054606.7\",\"applicationPublishTime\":\"2017-06-13\",\"uuid\":\"552d031bca85446faa5c80826e8966ff\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170613/201710054606.7/201710054606.gif\",\"mainCatNum\":\"H04L29/08(2006.01)I\",\"applicationTime\":\"2017.01.24\",\"inventor\":\"杨延超;李培帅\",\"patentName\":\"用于操作群组的方法和装置、服务器以及终端\",\"applicationPublishNum\":\"CN106850794A\",\"allCatNum\":\"H04L29/08(2006.01)I;H04L12/18(2006.01)I\"},{\"agent\":\"宋合成\",\"address\":\"100085 北京市海淀区上地十街10号百度大厦2层\",\"agency\":\"北京清亦华知识产权代理事务所(普通合伙) 11201\",\"abstracts\":\"本发明提出一种基于人工智能的新闻摘要生成和显示方法、装置及系统，通过识别新闻稿的逻辑结构之后，生成各逻辑结构的新闻摘要，并在各逻辑结构的新闻摘要之间插入对应的引导语。由于新闻稿的各个逻辑结构具有一些特殊性，因此，基于新闻稿的逻辑结构，采用对应的识别网络进行各逻辑结构的新闻摘要提取，能够提高各逻辑结构的新闻摘要的准确性和连贯性，并且在各逻辑结构的新闻摘要之间插入引导语进一步增强了摘要的逻辑性和连贯性，解决了现有技术中新闻摘要不够准确，新闻摘要的前后句子不连贯，逻辑性不强的技术问题。\",\"applicantName\":\"北京百度网讯科技有限公司\",\"patentNum\":\"CN201710016480.4\",\"applicationPublishTime\":\"2017-06-13\",\"uuid\":\"d62ac63c52e4401ca07ddf530e02bb6a\",\"patentType\":\"发明专利\",\"imgUrl\":\"http://pic.cnipr.com:8080/XmlData/FM/20170613/201710016480.4/201710016480.gif\",\"mainCatNum\":\"G06F17/27(2006.01)I\",\"applicationTime\":\"2017.01.10\",\"inventor\":\"闭玮;刘志慧;曹宇慧;周古月;石磊;何径舟\",\"patentName\":\"基于人工智能的新闻摘要生成和显示方法、装置及系统\",\"applicationPublishNum\":\"CN106844340A\",\"allCatNum\":\"G06F17/27(2006.01)I;G06F17/30(2006.01)I\"}]},\"vipMessage\":\"\",\"state\":\"ok\",\"message\":\"\"}";
		JSONArray arr = JSONObject.parseObject(str).getJSONObject("data").getJSONArray("items");
		List<Patents> list = new ArrayList<>();
		if (arr != null) {
			arr.forEach(obj -> {
				Patents parseObject = JSONObject.parseObject(obj.toString(), Patents.class);
				list.add(parseObject);
			});
		}
		try {
			patentsRepository.save(list);
		} catch (Exception e) {
			log.debug("保存专利失败", e.getMessage());
		}
	}

	@Test
	public void testZhuzuoquan() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getCopyReg(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testStaff() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("苏宁云商集团股份有限公司");
		JSONObject targetInfo = openeyesService.getStaffInfo(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testICP() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		JSONObject targetInfo = openeyesService.getIcp(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testAbnormal() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setSpec(KeyConstan.URL.ABNORMAL);
		dto.setCname("湖南瑞源生物医药科技有限公司");
		dto.setPageNumber(1);
		dto.setFrom("2");
		JSONObject targetInfo = openeyesService.getAbnormal(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testPunishmentInfo() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("德化县孙壁电器商店");
		dto.setPageNumber(1);
		dto.setFrom("2");
		JSONObject targetInfo = openeyesService.getPunishmentInfo(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testIllegalinfo() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setSpec(KeyConstan.URL.YANZHONGWEIFA);
		dto.setCname("北京百度网讯科技有限公司");
		;
		JSONObject targetInfo = openeyesService.getIllegalinfo(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testOwnTax() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setSpec(KeyConstan.URL.QIANSHUIGONGGAO);
		dto.setCname("北京百度网讯科技有限公司");
		;
		JSONObject targetInfo = openeyesService.getOwnTax(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testNews() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname("北京百度网讯科技有限公司");
		dto.setPageNumber(1);
		JSONObject targetInfo = openeyesService.getNews(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testDishonest() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setSpec(KeyConstan.URL.SHIXINREN);
		dto.setCname("北京百度网讯科技有限公司");
		;
		JSONObject targetInfo = openeyesService.getDishonest(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testRiskInfo() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setSpec(KeyConstan.URL.QIYEFENGXIAN);
		dto.setCname("北京百度网讯科技有限公司");
		;
		JSONObject targetInfo = openeyesService.getRiskInfo(dto);
		System.out.println(targetInfo.toJSONString());
	}

	@Test
	public void testinfo() {
		AreaSearchDTO dto = new AreaSearchDTO();
		dto.setDimension("企业动态");
		dto.setPark("中关村软件园");
		Page<AITInfo> page = gardenService.getInformationPush(dto);
		System.out.println(page.getContent());

	}

	@Test
	public void testSousuo() {
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setSpec(KeyConstan.URL.SOUSUO);
		dto.setCname("人工智能广东");
		JSONObject targetInfo = openeyesService.getSousuoCompanyList(dto);
		System.out.println(targetInfo.toJSONString());
	}

	/*
	 * @Test public void testBaseInfoAndSave(){ String strs =
	 * "南京高科股份有限公司,江苏舜天股份有限公司,苏交科集团股份有限公司,江苏保千里视像科技集团股份有限公司,江苏省农垦农业发展股份有限公司,南京云海特种金属股份有限公司,南京公用发展股份有限公司,南京熊猫电子股份有限公司,金陵药业股份有限公司,南京红太阳股份有限公司,航天晨光股份有限公司,江苏弘业股份有限公司,南京栖霞建设股份有限公司,南京康尼机电股份有限公司,中设设计集团股份有限公司,江苏金智科技股份有限公司,南京普天通信股份有限公司,红宝丽集团股份有限公司,江苏凤凰置业投资股份有限公司,南京化纤股份有限公司,莱绅通灵珠宝股份有限公司,南京华东电子信息科技股份有限公司,幸福蓝海影视文化集团股份有限公司,江苏润和软件股份有限公司,国睿科技股份有限公司,江苏龙蟠科技股份有限公司,南京华脉科技股份有限公司,江苏中旗科技股份有限公司,南京纺织品进出口股份有限公司,金陵饭店股份有限公司,多伦科技股份有限公司,光一科技股份有限公司,维格娜丝时装股份有限公司,南京寒锐钴业股份有限公司,天泽信息产业股份有限公司,焦点科技股份有限公司,南京埃斯顿自动化股份有限公司,南京我乐家居股份有限公司,中电环保股份有限公司,江苏三六五网络股份有限公司,大千生态景观股份有限公司,南京新联电子股份有限公司,南京健友生化制药股份有限公司,南京科远自动化集团股份有限公司,诚迈科技(南京)股份有限公司,南京音飞储存设备(集团)股份有限公司,南京佳力图机房环境技术股份有限公司,南京全信传输科技股份有限公司,基蛋生物科技股份有限公司,南京奥联汽车电子电器股份有限公司,江苏大烨智能电气股份有限公司,江苏美思德化学股份有限公司,南京海辰药业股份有限公司,南京宝色股份公司,江苏久吾高科技股份有限公司,南京港股份有限公司,南京三超新材料股份有限公司,国旅联合股份有限公司";
	 * String[] arr = strs.split(","); int i = 0; for (String str : arr) {
	 * OpeneyesDTO dto = new OpeneyesDTO(); dto.setCname(str);
	 * dto.setPageNumber(1); JSONObject targetInfo =
	 * openeyesService.getBaseInfo(dto); JSONObject jsonObj =
	 * targetInfo.getJSONObject("result"); BaseInfo p =
	 * JSONObject.parseObject(jsonObj.toString(), BaseInfo.class);
	 * com.huishu.ZSServer.entity.garden.Company c = new
	 * com.huishu.ZSServer.entity.garden.Company();
	 * c.setAddress(p.getRegLocation()); c.setArea(p.getBase());
	 * c.setBoss(p.getLegalPersonName()); c.setCompanyName(p.getName());
	 * c.setEngageState(p.getRegStatus()); c.setIndustry(p.getIndustry());
	 * c.setRegisterCapital(Double.parseDouble((p.getRegCapital().substring(0,
	 * p.getRegCapital().indexOf("万"))))); SimpleDateFormat format = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date date = new
	 * Date(p.getEstiblishTime()); c.setRegisterDate(format.format(date));
	 * companyCopyRepository.save(c); i++; } System.out.println("保存成功"+i+"次"); }
	 */
	@Test
	public void testExport() {
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		boolQuery.must(QueryBuilders.termQuery("dimension", "政策解读"));
		boolQuery.must(QueryBuilders.termQuery("industry", "文化创意"));
		Iterable<AITInfo> aitinfo = baseElasticsearch.search(boolQuery);
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("学生表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("摘要");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("发布时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("发布年份");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("文章链接");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("标题");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("内容");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("原文链接");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("来源");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("地域");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("产业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("二级产业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 11);
		cell.setCellValue("载体");
		cell.setCellStyle(style);
		cell = row.createCell((short) 12);
		cell.setCellValue("维度");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		List<AITInfo> list = new ArrayList<>();
		aitinfo.forEach(ait -> {
			list.add(ait);
		});
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			AITInfo stu = (AITInfo) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(stu.getSummary());
			row.createCell((short) 1).setCellValue(stu.getPublishTime());
			row.createCell((short) 2).setCellValue(stu.getPublishYear());
			row.createCell((short) 3).setCellValue(stu.getArticleLink());
			row.createCell((short) 4).setCellValue(stu.getTitle());
			row.createCell((short) 5).setCellValue(stu.getContent());
			row.createCell((short) 6).setCellValue(stu.getSourceLink());
			row.createCell((short) 7).setCellValue(stu.getSource());
			row.createCell((short) 8).setCellValue(stu.getArea());
			row.createCell((short) 9).setCellValue(stu.getIndustry());
			row.createCell((short) 10).setCellValue(stu.getIndustryLabel());
			row.createCell((short) 11).setCellValue(stu.getVector());
			row.createCell((short) 12).setCellValue(stu.getDimension());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("D:/政策解读.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Resource(name = "transactionManager")
	JpaTransactionManager tm;

	@Test
	public void testSaveEnterprise() throws FileNotFoundException {
		WpsUtilsTest util = new WpsUtilsTest();
		FileInputStream is = new FileInputStream(
				"C:\\Users\\yindawei\\Documents\\Tencent Files\\260446015\\FileRecv\\四大产业企业信息采集汇总.xlsx");
		Map<Integer, String> content = util.readExcelContentXlsx(is, "~");
		List<Enterprise> list = new ArrayList<>();
		content.forEach((k, v) -> {
			String[] arr = v.split("~");
			Enterprise prise = new Enterprise();
			prise.setCompany(arr[0].trim());
			prise.setArea(arr[1].trim());
			prise.setScoring(arr[2].trim());
			prise.setEngageState(arr[3].trim());
			prise.setPublicCompany(arr[4].trim());
			prise.setBoss(arr[5].trim());
			prise.setRegisterCapital(arr[6].trim());
			prise.setRegisterTime(arr[7].trim());
			prise.setOperateScope(arr[8].trim());
			prise.setIndustry(arr[9].trim());
			list.add(prise);
		});
		// 事务开始
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		def.setTimeout(30);
		// 事务状态
//		TransactionStatus status = tm.getTransaction(def);
		int count = 0;
		List<Enterprise> errorList = new ArrayList<>();
		try {
			for (int i = 0; i < list.size(); i++) {
				count = i;
				try {
					enterPriseRepository.save(list.get(i));
				} catch (Exception e) {
					errorList.add(list.get(i));
					continue;
				}
			}
			log.info("失败集合:"+errorList);
//			tm.commit(status);
		} catch (Exception e) {
			System.out.println("第"+count+"条异常");
			e.printStackTrace();
//			if (!status.isCompleted()) {
//				tm.rollback(status);
//			}
			System.out.println("发生异常:"+e.getMessage());
		}
		System.out.println("mission all over-------------------------");
	}

}

package test;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.ait.app.Application;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.repository.user.UserPermissionRepository;
import com.huishu.ait.service.user.UserBaseService;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。  
@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration 
public class TestLogin {
	@Autowired
	private UserBaseService userBaseService;
	@Autowired
	private UserPermissionRepository userPermissionService;
	@Autowired
	private Client client;
	
	@Test
	public void login(){
		System.out.println("--------------------------------------");
		List<Long> permissionIds = userPermissionService.findPermissionIdByUserLevel(3);
		System.out.println("权限"+permissionIds);
	}
	@Test
	public void aaa(){
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		SearchRequestBuilder srb = client.prepareSearch("aitserver_2017-08-15").setTypes(DBConstant.EsConfig.TYPE);
		SearchResponse searchResponse = srb.setQuery(bq).setSize(50).execute().actionGet();
		
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			hits.forEach((searchHit)->{
				Map<String, Object> map = searchHit.getSource();
				 for (String key : map.keySet()) {
				   System.out.println("key= "+ key + " and value= " + map.get(key));
				  }
			});
		}
	}
	
	
	/**
	 * 测试用json串
	 */
	@SuppressWarnings("unused")
	private void jsonString(){
		/**
		 * 根据关键字查出来的公司信息，有值
		 */
		String text="{\"state\": \"ok\",\"message\": \"\",\"totalPage\": 3,\"humanCount\": 0,\"companyCount\": 20,\"total\": 54,\"data\": [{\"id\": 22822,\"name\": \"<em>北京百度网讯科技有限公司</em>\",\"type\": null,\"matchType\": null,\"legalPersonName\": \"梁志祥\",\"estiblishTime\":\"2001-06-05 00:00:00.0\",\"regCapital\": \"89000 万人民币\"}],\"modifiedQuery\": null,\"special\":\"\"}";
		/**
		 * 根据关键字查出来的公司信息，无值
		 */
		String text2="{\"state\": \"ok\",\"message\": \"\",\"totalPage\": 0,\"humanCount\": 0,\"companyCount\": 0,\"total\": 0,\"data\": [],\"modifiedQuery\": null,\"special\":\"\"}";
		
		/**
		 * 企业变更信息
		 */
		String test="{"
				+ "\"state\": \"ok,"
+"message\": \","
+"special\": \","
+"vipMessage\":\","
+"isLogin\": 0,"
+"data\": {"
			+"total\": 13,"
			+"result\": [{"
	  +"changeItem\": \"经营范围,"
	  +"createTime\": \"2017-03-14,"
	  +"contentBefore\": \"因特信息服务业务（除出版、教育、医疗保健以外的内容）；第一类增值电信业务中的在线数据处理与交易处理业务、国内因特网虚拟专用网业务、因特网数据中心业务；第二类增值电信业务中的因特网接入服务业务、呼叫中心业务、信息服务业务（不含固定网电话信息服务和互联网信息服务）；利用互联网经营音乐娱乐产品，游戏产品运营，网络游戏虚拟货币发行，美术品，演出剧（节）目，动漫（画）产品，从事互联网文化产品展览、比赛活动（网络文化经营许可证有效期至2016年11月21日）；设计、开发、销售计算机软件；技术服务、技术培训、技术推广；经济信息咨询；利用www.baidu.com、www.hao123.com(www.hao222.net、www.hao222.com)、网站发布广告；设计、制作、代理、发布广告；货物进出口、技术进出口、代理进出口；医疗软件技术开发；委托生产电子产品、玩具、照相器材；销售家用电器、机械设备、五金交电、电子产品、文化用品、照相器材、计算机、软件及辅助设备、化妆品、卫生用品、体育用品、纺织品、服装、鞋帽、日用品、家具、首饰、避孕器具、工艺品、钟表、眼镜、玩具、汽车及摩托车配件、仪器仪表、塑料制品、花、草及观赏植物、建筑材料、通讯设备；预防保健咨询。企业依法自主选择经营项目，开展经营活动；增值电信业务以及依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。,"
	  +"contentAfter\": \"因特信息服务业务（除出版、教育、医疗保健以外的内容）；第一类增值电信业务中的在线数据处理与交易处理业务、国内因特网虚拟专用网业务、因特网数据中心业务；第二类增值电信业务中的因特网接入服务业务、呼叫中心业务、信息服务业务（不含固定网电话信息服务和互联网信息服务）；利用互联网经营音乐娱乐产品，游戏产品运营，网络游戏虚拟货币发行，美术品，演出剧（节）目，动漫（画）产品，从事互联网文化产品展览、比赛活动（网络文化经营许可证有效期至2016年11月21日）；<em>图书、电子出版物、音像制品批发、零售、网上销售；</em>设计、开发、销售计算机软件；技术服务、技术培训、技术推广；经济信息咨询；利用www.baidu.com、www.hao123.com(www.hao222.net、www.hao222.com)、网站发布广告；设计、制作、代理、发布广告；货物进出口、技术进出口、代理进出口；医疗软件技术开发；委托生产电子产品、玩具、照相器材；销售家用电器、机械设备、五金交电、电子产品、文化用品、照相器材、计算机、软件及辅助设备、化妆品、卫生用品、体育用品、纺织品、服装、鞋帽、日用品、家具、首饰、避孕器具、工艺品、钟表、眼镜、玩具、汽车及摩托车配件、仪器仪表、塑料制品、花、草及观赏植物、建筑材料、通讯设备；预防保健咨询<em>；公园门票、文艺演出、体育赛事、展览会票务代理</em>。企业依法自主选择经营项目，开展经营活动；增值电信业务以及依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。,"
	  + "changeTime\": \"2016-05-11"
			  +"}]}}";
	}
	
	
}

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.service.headline.HeadlinesService;

/**
 * @author hhy
 * @date 2017年9月26日
 * @Parem
 * @return
 * 
 */
// 这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
//@RunWith(SpringJUnit4ClassRunner.class)
// 这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
//@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
public class Test {
	@Autowired
//	private ArticleService service;
	private HeadlinesService service;
	//private ExpertOpinionService service;
	//	private CompanyService service;
	//	private IndustrialPolicyService service;
	
	/*查看文章详情测试
	 * @org.junit.Test
	public void test1(){
		String id = "16ff92b2073280d292110d32ebf482d52ef0a18a";
		AITInfo info = service.findArticleInfoById(id);
		System.out.println("id============"+info.getId());
	}*/
	/*更新情感测试
	 * @org.junit.Test
	public void test2(){
		String id = "c974908a6a7fa9877dc105b14a4c80c3171e848f";
		AITInfo info = service.findArticleInfoById(id);
		System.out.println("emotion======="+info.getEmotion());
		System.out.println("id============"+info.getId());
		Boolean updateEmotion = service.updateEmotion(info.getId());
		
		System.out.println("更新结果"+updateEmotion);
		AITInfo info1 = service.findArticleInfoById(id);
		System.out.println("emotion======="+info1.getEmotion());
		System.out.println("id============"+info1.getId());
	}*/
	/*//删除文章测试
	@org.junit.Test
	public void test3(){
		String id = "c974908a6a7fa9877dc105b14a4c80c3171e848f";
		AITInfo info = service.findArticleInfoById(id);
		System.out.println("id============"+info.getId());
		Boolean delArticleById = service.delArticleById(info.getId());
		System.out.println("删除结果为："+delArticleById);
		AITInfo info1 = service.findArticleInfoById(id);
		System.out.println("id============"+info1.getId());
		
	}*/
	/*关键词测试
	 * @org.junit.Test
	public void test4(){
		JSONObject param = new JSONObject();
		param.put("type", "产业云图");
		param.put("time", "全部");
		param.put("industry", "互联网+");
		param.put("industryLabel", "全部");
		                       
		List<KeywordModel> list = service.findArticleKeyword(param);
		System.out.println(list.size());
	}*/
	@org.junit.Test
	public void test4(){
		JSONObject param = new JSONObject();
		param.put("type", "产业头条");
		param.put("time", "全部");
		param.put("industry", "互联网+");
		param.put("industryLabel", "全部");
		                       
		//List<KeywordModel> list = service.findArticleKeyword(param);
		//	Page<ArticleListDTO> list = service.findExpertOpinionArticleList(param);
		Page<ArticleListDTO> list = service.findArticleList(param);
//		Page<ArticleListDTO> list = service.findArticleList(param);
		list.forEach(action ->{
			System.out.println("查询文章结果的id为》》》》》"+action.getId());
		});
		System.out.println(list.getSize());
	}
	@org.junit.Test
	public void aa(){
		System.out.println("day.compareTo(\"29\")的值是：====" + "99".compareTo("29"));
	}
}

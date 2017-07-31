package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.app.Application;
import com.huishu.ait.es.entity.dto.ExpertOpinionDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;

/**
 * @author yxq
 *专家观点的测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class ExpertOpinionTest{
	
	@Autowired
	private ExpertOpinionService expertOpinionService;
	@Test
	public void testgetExertOpinionList(){
		ExpertOpinionDTO expertOpinionDTO=new ExpertOpinionDTO();
		expertOpinionDTO.setIndustry("互联网");
		expertOpinionDTO.setIndustryLabel("大数据");
		/*expertOpinionDTO.setHitCount(00L);*/
		expertOpinionDTO.setSortByTimeFlag("111");
		JSONArray exertOpinionList = expertOpinionService.getExertOpinionList(expertOpinionDTO);
		for (Object object : exertOpinionList) {
			System.out.println("------"+object);
		}
	}
	@Test
	public void testgetExpertOpinionByAuthor(){
		ExpertOpinionDTO expertOpinionDTO=new ExpertOpinionDTO();
		expertOpinionDTO.setAuthor("熊志强");
		JSONArray exertOpinionList = expertOpinionService.findExpertOpinionByAuthor(expertOpinionDTO);
		for (Object object : exertOpinionList) {
			System.out.println("------"+object);
		}
	}
	@Test
	public void testGetExpertOpinionDetailById(){
		JSONObject json = expertOpinionService.findExpertOpinionById("AV2I3tLjkW6DYjADS_Au");
		System.out.println(json);
	}
	@Test
	public void testexpertOpinionCollect(){
		ExpertOpinionDTO param=new ExpertOpinionDTO();
		param.setId("10");
		param.setTitle("大数据将何去何从？");
		param.setAuthor("黄渤");
		param.setContent("对于“大数据”（Big data）研究机构Gartner给出了这样的定义。“大数据”是需要新处理模式才能具有更强的决策力、洞察发现力和流程优化能力来适应海量、高增长率和多样化的信息资产.");
		param.setPublishDateTime("2017-7-31 16:45:23");
		Boolean flag = expertOpinionService.expertOpinionCollect(param);
		System.out.println(flag);
	}
}
package industry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.huishu.ZSServer.app.Application;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})*/
public class IndusCompanyTest {
    @Autowired
    private BaseElasticsearch elasticsearch ;
    
   /* @Test
    public void GetCompanyInfo(){
    	 System.out.println("---------------测试开始----------------");
    	 String companyName = "华胜天成";
    	 BaseInfo info = service.getCompanyInfo(companyName);
    	 System.out.println("测试结果为：>>>>>>>>>>>>>>>>>>>>>"+info.toString());
    	 System.out.println("---------------测试结束---------------");
    	}*/
    @Test
    public void GetCompanyInfo(){
    	 System.out.println("---------------测试开始----------------");
//    	 String companyName = "华胜天成";
//    	 BaseInfo info = service.getCompanyInfo(companyName);
//    	 List<Company> list = service.listCompany();
//    	 System.out.println("测试结果为：>>>>>>>>>>>>>>>>>>>>>"+list.size());
//    	 PageRequest request = new PageRequest(0, 100);
    	/* Page<AITInfo> page = elasticsearch.findByTitleLike("title", request);
    	 for (AITInfo aitInfo : page) {
			System.out.println(aitInfo);
			elasticsearch.delete(aitInfo.getId());
		}*/
    	 try {
			JSONObject object = Analysis.updateCompany("海通安恒");
			System.out.println(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 System.out.println("---------------测试结束---------------");
    	}
    
    @Test
    public void GetInfo(){
    	 System.out.println("---------------测试开始----------------");
    	 PageRequest request = new PageRequest(0, 33);
    	 /*Page<AITInfo> page = elasticsearch.findByBusinessLike("1900-12-25", request);
    	 for (AITInfo aitInfo : page) {
    		 aitInfo.setBusiness("360");
    		 elasticsearch.save(aitInfo);
		}*/
    	 System.out.println("---------------测试结束---------------");
    }
}

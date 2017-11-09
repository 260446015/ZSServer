package industry;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huishu.ZSServer.app.Application;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.service.company.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class IndusCompanyTest {
    @Autowired
    private IndusCompanyService service ;
    
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
    	 List<Company> list = service.listCompany();
    	 System.out.println("测试结果为：>>>>>>>>>>>>>>>>>>>>>"+list.size());
    	 System.out.println("---------------测试结束---------------");
    	}
}

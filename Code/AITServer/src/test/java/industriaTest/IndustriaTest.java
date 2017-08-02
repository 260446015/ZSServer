package industriaTest;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.app.Application;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.es.entity.AITInfo;
//import com.huishu.ait.repository.industry.IndustrialPolicyListRepository;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class IndustriaTest {

    @Autowired
    private IndustrialPolicyService industrialPolicyService;
    
/*    @org.junit.Test
    public void industriaPolicyDetailTest(){
        try{
            AITInfo info = new AITInfo();
            String id = "AV2HPk5DkW6DYjADS--W";
            info = industrialPolicyService.getIndustrialPolicyDetailById(id);
            System.out.println(info.toString());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("原因"+e.getMessage());
        }
    }
    */
    /**
     * 产业政策列表测试类
     */
    @org.junit.Test
    public void industryListTest(){
        try{
            IndustrialPolicyDTO dto = new IndustrialPolicyDTO();
            dto.setIndustry("互联网");
            dto.setArea("上海");
            dto.setIndustryLabel("网络游戏");
            dto.setStartDate("2016-7-31 17:50:38");
            dto.setEndDate("2017-7-31 17:50:46");
            dto.setPageNumber(1);
            dto.setPageSize(10);
            Page<AITInfo> page = industrialPolicyService.getIndustrialPolicyList(dto);
            System.out.println("==================");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

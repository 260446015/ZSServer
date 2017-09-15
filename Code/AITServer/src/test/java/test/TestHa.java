package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.ait.app.Application;
import com.huishu.ait.entity.GardenData;
import com.huishu.ait.repository.garden.GardenRepository;

import test.util.ReadExcelUtil;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。  
@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration 
public class TestHa {
	@Autowired
	private GardenRepository gardenRepository;
	@Test
	public void saveLrvel(){
		ReadExcelUtil util = new ReadExcelUtil();
		List<String> map = util.readExcel("F:/Tencent/myData/572213080/FileRecv/haha.xlsx", "haha");
		for (String value : map) {
//			System.out.println(value);
			String[] split = value.split("---");
			GardenData level = new GardenData();
			level.setAddress(split[5]);
			level.setArea(split[3]);
			level.setEstablishDate(split[7]);
			level.setGardenLevel(split[2]);
			level.setGardenName(split[1]);
			level.setGardenNewName(split[11]);
			level.setGardenIntroduce("暂无");
			level.setGardenPicture(split[9]);
			level.setGardenPolicy(split[10]);
			level.setGardenSquare(split[8]);
			level.setGardenSuperiority("暂无");
			level.setGardenWebsite(split[0]);
			String haha="";
			for (int i = 0; i < industry.length; i++) {
				int j = split[6].indexOf(industry[i]);
				if(j!=-1){
					if(haha.equals("")){
						haha=industry[i];
					}else{
						haha=haha+","+industry[i];
					}
				}
			}
			level.setIndustry(haha);
			level.setLeadingIndustry(split[6]);
			GardenData save = gardenRepository.save(level);
		}
	}
	
	
	private static String[] industry = { "互联网","高科技","文化创意","精英配套","滨海旅游","港口物流"};
}

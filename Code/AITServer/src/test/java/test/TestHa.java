package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.ait.app.Application;
import com.huishu.ait.entity.IndustryLevel;
import com.huishu.ait.repository.industry.IndustryLevelRepository;

import test.util.ReadExcelUtil;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。  
@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration 
public class TestHa {
	@Autowired
	private IndustryLevelRepository industryLevelRepository;
	@Test
	public void saveLrvel(){
		ReadExcelUtil util = new ReadExcelUtil();
		List<String> map = util.readExcel("C:/Users/尹冬晴/Desktop/文档/慧数招商2.0/产业模块/园区/a.xlsx", "a");
		for (String value : map) {
			System.out.println(value);
			String[] split = value.split("---");
			IndustryLevel level = new IndustryLevel();
			level.setIndustryName(split[0]);
			level.setLevel(2);
			level.setFatherId(3L);
			IndustryLevel save = industryLevelRepository.save(level);
			System.out.println("保存"+save);
		}
	}
	
}

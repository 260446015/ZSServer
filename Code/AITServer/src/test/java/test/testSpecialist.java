package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.ait.app.Application;
import com.huishu.ait.entity.Specialist;
import com.huishu.ait.service.Specialist.SpecialistService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class testSpecialist {
	
	@Autowired
	private SpecialistService specialistService;
	
	@Test
	public void testSpecialist() {
		
		Iterable<Specialist> findAll = specialistService.findAll();
		for (Specialist specialist : findAll) {
			System.out.println("===="+specialist);
		}
	}

}

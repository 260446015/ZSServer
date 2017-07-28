package test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huishu.ait.app.Application;
import com.huishu.ait.entity.Specialist;
import com.huishu.ait.repository.SpecialRepository;


/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class Test {
	@Autowired
	private SpecialRepository  test;
	
	@org.junit.Test
	public void test(){
		List<Specialist> all = (List<Specialist>) test.findAll();
	   if(all != null){
		   System.out.println("================");
	   }	
	}
	
}

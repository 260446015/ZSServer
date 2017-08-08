package test;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.ait.app.Application;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.security.RSAUtils;
import com.huishu.ait.service.user.UserBaseService;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。  
@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration 
public class TestLogin {
	@Autowired
	private UserBaseService userBaseService;
	
	@Test
	public void login(){
		System.out.println("==================================");
		UserBase user = userBaseService.getUserByUserAccount("18301649800");
		System.out.println(user);
	}
	
	
}

package test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.ait.app.Application;


/**
 * @author hhy
 * @date 2017年7月29日
 * @Parem
 * @return 
 * 单元测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BaseTest {

}

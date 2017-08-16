package test;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.echart.Option;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.service.Headlines.HeadlinesService;

/**
 * @author hhy
 * @date 2017年7月29日
 * @Parem
 * @return 
 * 
 */
public class HeadlinesTest extends BaseTest{

	@Autowired
	private HeadlinesService service ;
	
	 
	@org.junit.Test
	public void HeadlinesServiceTest(){
		HeadlinesDTO   headlinesDTO = new HeadlinesDTO();
		headlinesDTO.setIndustry("文化创意");
		headlinesDTO.setIndustryLabel("网络游戏");
		/*Option option = service.getCarClondChartList(headlinesDTO);
		System.out.println(option.getLegend());*/
	}
}

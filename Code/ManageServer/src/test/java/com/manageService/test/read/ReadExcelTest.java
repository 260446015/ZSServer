package com.manageService.test.read;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huishu.ManageServer.app.Application;
import com.huishu.ManageServer.common.util.ReadExcelUtil;
import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;
import com.huishu.ManageServer.service.second.keyword.KeyInfoService;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 * 读取Excel
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReadExcelTest {
	@Autowired
	private KeyInfoService service;
	@Test
	public void readTest(){
		ReadExcelUtil util = new ReadExcelUtil();
		List<String> excel = util.readExcel("E:/慧数平台/招商平台/精筛产业关键词.xlsx", "精筛产业关键词.xlsx");
		excel.subList(0, 1).clear();
		List<KeyInfoEntity> list = new ArrayList<KeyInfoEntity>();
		for(String  value : excel){
			String[] split = value.split("---");
			KeyInfoEntity entry = new KeyInfoEntity();
			entry.setId(Long.getLong(split[0]));
			entry.setKeyword(split[1]);
			entry.setProportion(Double.valueOf(split[2]));
			entry.setIndustry(split[3]);
			list.add(entry);
		}
		boolean info = service.saveInfo(list);
		if(info){
			System.out.println("读取关键词并存库成功："+info);
		}else{
			System.out.println("读取关键词并存库失败："+info);
		}
	}
}

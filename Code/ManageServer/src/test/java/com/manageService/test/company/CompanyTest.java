/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CompanyTest
 * Author:   12080
 * Date:     2018/5/17 17:49
 * Description: aaaa
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.manageService.test.company;

import com.huishu.ManageServer.app.Application;
import com.huishu.ManageServer.common.util.ReadExcelUtil;
import com.huishu.ManageServer.service.third.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyTest {
    @Resource
    private DataService DataService;
    //补充数据
    @Test
    public void companyDataTest(){
        String name = "所缺企业数据采集"+"."+"xlsx";
        ReadExcelUtil util = new ReadExcelUtil();
        List<String> map = util.readExcel("E:\\招商文件\\Excel表格\\"+name,name);
       //清除第一行数据
        map.subList(0, 1).clear();//不是属性表，需要把第一行清除
        int count =0;
        //遍历数据
        for(String value:map){
            //保存数据
            DataService.addDataInfo(value);
            count ++;
        }
        System.out.print("录入数据条数：>>>>>>>>>>>>"+count);
    }
}

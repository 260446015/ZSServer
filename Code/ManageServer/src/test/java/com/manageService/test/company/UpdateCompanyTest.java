package com.manageService.test.company;

import java.net.URLDecoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.huishu.ManageServer.app.Application;
import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.entity.dbFirst.Institutional;
import com.huishu.ManageServer.repository.first.EnterPriseRepository;
import com.huishu.ManageServer.repository.first.InstitutionalRepostitory;
import com.huishu.ManageServer.service.enterprise.EnterpriseService;

/**
 * @author hhy
 * @date 2018年1月25日
 * @Parem
 * @return 
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UpdateCompanyTest {
	@Autowired
	private InstitutionalRepostitory rep;
//	private EnterpriseService  service;
	/*private EnterPriseRepository ent;
	private final int count =0;
	@Test
	public void InsertCompany(){
		Iterable<Enterprise> all = ent.findAll();
		all.forEach( action->{
			String company = action.getCompany();
			try {
				JSONObject obj = Analysis.updateCompany(company);
				System.out.println("更新的结果为："+obj.getString("msg"));
			} catch (Exception e) {
			   System.out.println("更新企业信息失败："+e);
			}
		});
	}
	*/
	@Test
	public void updateDate(){
		Iterable<Institutional> all = rep.findAll();
		all.forEach(action->{
			String url = action.getUrl();
			try {
				System.out.println(url);
				String decode = URLDecoder.decode(url, "utf-8");
				System.out.println(decode);
				action.setUrl(decode);
				rep.save(action);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

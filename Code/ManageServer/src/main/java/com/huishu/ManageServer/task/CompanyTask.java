package com.huishu.ManageServer.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.service.enterprise.EnterpriseService;
import com.huishu.ManageServer.service.enterprise.IndustryCompanyService;

/**
 * @author hhy
 * @date 2018年2月3日
 * @Parem
 * @return 
 * 智能推荐公司信息获取算法
 */
@Component
public class CompanyTask {
	@Autowired
	private EnterpriseService eps;
	@Autowired
	private IndustryCompanyService ics;
	private static final Logger log = LoggerFactory.getLogger(CompanyTask.class);
	//每日更新十家企业信息到精准筛选的表单中
	//每天晚上十点半更新数据
	@Scheduled(cron="0 30 22  * * ?")
	//测试数据，十分钟一次
	//@Scheduled(fixedDelay =1000*60*5)
	public void getCompanyInfoTask(){
		Map<Integer,Enterprise> map= new HashMap<Integer,Enterprise>();
		List<Enterprise> list =new ArrayList<Enterprise>();
		
		//第一步：获取十家企业
		while(list.size()<10){
			findCompanyInfo(map, list);
		}
		//清空原本智能推荐的数据
		boolean flag = ics.deleteAll();
		if(flag){
			//保存新的数据
				boolean info = ics.saveListCompany(list);
				if(info){
					log.info("更新精准筛选数据时：保存数据时成功"+info);
				}else{
					log.info("更新精准筛选数据时：保存数据时报错"+info);
				}
		}else{
			//删除数据报错，无法进行
			log.debug("更新精准筛选数据时：删除数据报错，无法进行");
		}
	}
	private void findCompanyInfo(Map<Integer, Enterprise> map, List<Enterprise> list) {
		Enterprise ent = eps.findCompany();
		int i = ent.getCompany().hashCode();
		//第二步判断数据的可使用度
		if(map.containsKey(i)){
			//第三步不可用，重新获取数据
			findCompanyInfo(map,list);
		}else{
			//第四步，可用，数据进行
			map.put(i, ent);
			list.add(ent);
		}
	}
}

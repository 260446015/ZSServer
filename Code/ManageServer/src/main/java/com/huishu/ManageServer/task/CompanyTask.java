package com.huishu.ManageServer.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huishu.ManageServer.entity.dbFirst.UserLogo;
import com.huishu.ManageServer.repository.first.UserLogoRepository;
import com.huishu.ManageServer.repository.first.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huishu.ManageServer.common.util.StringUtil;
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
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserLogoRepository  logoRepository;
	private static final Logger log = LoggerFactory.getLogger(CompanyTask.class);
	//每日更新十家企业信息到精准筛选的表单中
	//每天晚上十点半更新数据
	@Scheduled(cron="0 30 22  * * ?")
	//测试数据，十分钟一次
//	@Scheduled(fixedDelay =1000*60*20)
	public void getCompanyInfoTask(){
		Map<Integer,Enterprise> map= new HashMap<Integer,Enterprise>();
		List<Enterprise> list =new ArrayList<Enterprise>();
		//第一步：获取所有的用户id;每一个用户放到一个线程中
		List<Long> list2 = repository.findAllUserIds();
		list2.forEach(action->{
		    new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            //第二步：获取用户每天的企业搜索记录（后期补充）
							List<UserLogo> alist = logoRepository.findListUserLog(action);
                            //第四步：如果搜索记录为空，则推送现有的优质企业
							if(alist.size()<=9){
                                //第五步：获取十家企业
                                while(list.size()<10){
                                    findCompanyInfo(map, list);
                                }
                                //清空原本智能推荐的数据
                                boolean flag = ics.deleteAll(action);
                                if(flag){
                                    //保存新的数据
                                    boolean info = ics.saveListCompany(list,action);
                                    if(info){
                                        log.info("更新精准筛选数据时：保存数据时成功"+info);
                                    }else{
                                        log.info("更新精准筛选数据时：保存数据时报错"+info);
                                    }
                                }else{
                                    //删除数据报错，无法进行
                                    log.debug("更新精准筛选数据时：删除数据报错，无法进行");
                                }
							}else{
                                //第三步：根据用户的企业搜索记录，选取当前类型下比较优质的企业信息；
                                alist.forEach(act->{
                                    String companyName = act.getSearchCompany();
                                    Long userId = act.getUserId();
                                    Enterprise ent = eps.findByCompanyName(companyName);
                                    if(ent == null){
                                        //如果为空，则需要查询基础信息baseInfo，并进行入库
                                        //根据天眼查接口获取企业信息

                                    }else {
//                                        map.put(ent.getCompany().hashCode(),ent);
                                        list.add(ent);
                                    }
                                });
                                boolean info = ics.saveListCompany(list,action);
                                if(info){
                                    log.info("更新精准筛选数据时：保存数据时成功"+info);
                                }else{
                                    log.info("更新精准筛选数据时：保存数据时报错"+info);
                                }
                            }
                        }
                    }
            ).start();
        });


	}
	private void findCompanyInfo(Map<Integer, Enterprise> map, List<Enterprise> list) {
		Enterprise ent = eps.findCompany();
		String industry = ent.getIndustry();
		if(!StringUtil.isEmpty(industry)){
			if(industry.equals("人工智能")||industry.equals("大数据")||industry.equals("物联网")||industry.equals("生物技术")){
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
		
	}
}

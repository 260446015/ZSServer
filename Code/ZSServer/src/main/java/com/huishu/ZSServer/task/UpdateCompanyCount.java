package com.huishu.ZSServer.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.garden.GardenRepository;
import com.huishu.ZSServer.repository.garden_user.GardenUserRepository;

/**
 * 定时更新
 * 
 * @author yindawei
 * @date 2018年2月1日上午9:51:54
 * @description
 * @version
 */
@Component
public class UpdateCompanyCount {
	
	@Autowired
	private GardenRepository gardenRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private GardenUserRepository gardenUserRepository;

	@Scheduled(cron = "0 10 12 ? * *")
	public void updateCount() {
		List<String> names = gardenRepository.findByGardenName();
		names.forEach(name ->{
			int findCountByPark = companyRepository.findCountByPark(name);
			GardenData findByGardenName = gardenRepository.findByGardenName(name);
			List<GardenUser> gus = gardenUserRepository.findByGardenName(name);
			gus.forEach(gu ->{
				gu.setEnterCount(findCountByPark);
			});
			findByGardenName.setEnterCount(findCountByPark);
			gardenRepository.save(findByGardenName);
			gardenUserRepository.save(gus);
		});
	}
}

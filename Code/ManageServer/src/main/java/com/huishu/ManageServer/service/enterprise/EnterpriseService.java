package com.huishu.ManageServer.service.enterprise;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 
 */
public interface EnterpriseService {
	Enterprise findOneByName(String name);
}

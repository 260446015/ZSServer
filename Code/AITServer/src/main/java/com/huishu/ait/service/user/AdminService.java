package com.huishu.ait.service.user;

import com.huishu.ait.entity.common.AjaxResult;

/**
 * 后台管理service
 * @author yindq
 * @date 2017年8月8日
 */
public interface AdminService {
	/**
	 * 账号审核
	 * @param id
	 * @return
	 */
	AjaxResult auditAccount(long id);
	/**
	 * 查看全局管理数据
	 * @return
	 */
	AjaxResult globalManagement(String park);
}

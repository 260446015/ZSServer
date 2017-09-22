package com.huishu.ait.service.user.backstage;

import java.util.List;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AccountSearchDTO;

/**
 * 后台管理service
 * 
 * @author yindq
 * @date 2017年8月8日
 */
public interface AdminService {
	/**
	 * 账号审核
	 * 
	 * @param base
	 * @return
	 */
	Boolean auditAccount(UserBase base);

	/**
	 * 账号预警
	 * 
	 * @param id
	 * @return
	 */
	AjaxResult warnAccount(Long id, Integer status);

	/**
	 * 查看全局管理数据
	 * 
	 * @return
	 */
	AjaxResult globalManagement();

	/**
	 * 获取账号列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	List<UserBase> getAccountList(AccountSearchDTO searchModel);

	/**
	 * 获取预到期账号列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	List<UserBase> getWarningAccountList(AccountSearchDTO searchModel);
}

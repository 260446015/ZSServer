package com.huishu.ManageServer.service.enterprise;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.entity.dto.AbstractDTO;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 企业库service
 *
 * @author yindq
 * @date 2017/12/29
 */
public interface EnterpriseService {
	/**
	 * 分页查看企业列表
	 * @param dto
	 * @return
	 */
	Page<Enterprise> listEnterprise(AbstractDTO dto);

	/**
	 * 添加/修改企业
	 * @param enter
	 * @return
	 */
	Boolean saveEnterprise(Enterprise enter);

	/**
	 * 删除企业
	 * @param id
	 * @return
	 */
	Boolean dropEnterprise(Long id);

	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	Enterprise findById(Long id);

	/**
	 * 获取十家公司信息
	 * @return
	 */
	List<Enterprise> findTop10Company();

	/**
	 * 获取公司信息
	 * @return
	 */
	Enterprise findCompany();

}

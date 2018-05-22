package com.huishu.ManageServer.service.second.company;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 公司信息
 */
public interface CompanyService {

	/**
	 * 查询
	 * @return
	 */
	List<CompanyEntity> findAllCompany();

	/**
	 * 分页列表展示
	 * @param dto
	 * @return
	 */
	Page<CompanyEntity> listCompany(AbstractDTO dto);

	/**
	 * 删除舆情公司信息
	 * @param id
	 * @return
	 */
	boolean deleteCompanyInfo(String id);

	/**
	 * 保存用户信息
	 * @param ent
	 * @return
	 */
	boolean saveOrUpdateCompany(CompanyEntity ent);

}

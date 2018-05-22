package com.huishu.ManageServer.service.second.company.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.repository.second.CompanyRepository;
import com.huishu.ManageServer.service.second.company.CompanyService;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 关于公司的相关操作
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	private static final Logger LOGGER = Logger.getLogger( CompanyServiceImpl.class);
	
	@Autowired
	private CompanyRepository rep;
	/**
	 * 获取所有公司的信息
	 */
	@Override
	@TargetDataSource(name="second")
	public List<CompanyEntity> findAllCompany() {
		return rep.getInfo();
	}
	
	/**
	 * 分页查询所有数据
	 */
	@Override
	@TargetDataSource(name="second")
	public Page<CompanyEntity> listCompany(AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<CompanyEntity> list =rep.findPage(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = rep.count();
		Page<CompanyEntity> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	/**
	 * 删除一条舆情信息
	 */
	@Override
	@TargetDataSource(name="second")
	public boolean deleteCompanyInfo(String id) {
		 try {
			 long _id = Long.parseLong(id);
			 rep.delete(_id);
		} catch (NumberFormatException e) {
			LOGGER.error("id输入不正确");
			return false;
		} catch (Exception e){
			LOGGER.info("数据库出现异常");
			return false;
		}
		return true;
	}
	
	@TargetDataSource(name="second")
	@Override
	public boolean saveOrUpdateCompany(CompanyEntity ent) {
		try {
			CompanyEntity save = rep.save(ent);
			if(save == null){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			LOGGER.info("保存舆情公司信息失败！");
			return false;
		}
	}
	
}

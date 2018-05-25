package com.huishu.ManageServer.service.enterprise.impl;

import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.repository.first.EnterPriseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.service.enterprise.EnterpriseService;
import com.huishu.ManageServer.util.AnalysisUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 企业库service
 *
 * @author yindq
 * @date 2017/12/29
 */
@Service
//@Transactional("firstTransactionManager")
public class EnterpriseServiceImpl implements EnterpriseService{

	@Autowired
	private EnterPriseRepository enterPriseRepository;

	@Override
	public Page<Enterprise> listEnterprise(AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<Enterprise> list = enterPriseRepository.findPage(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = enterPriseRepository.count();
		Page<Enterprise> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	@Override
	public Boolean saveEnterprise(Enterprise enter) {
		Enterprise save = enterPriseRepository.save(enter);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public Boolean dropEnterprise(Long id) {
		enterPriseRepository.delete(id);
		return true;
	}

	@Override
	public Enterprise findById(Long id) {
		return enterPriseRepository.findOne(id);
	}

	/**
	 * 获取十家公司信息
	 */
	@Override
	public List<Enterprise> findTop10Company() {
		
		return null;
	}

	/**
	 * 获取公司信息
	 */
	@Override
	public Enterprise findCompany() {
		//第一步：获取id最大值
		Long id = enterPriseRepository.getMaxId();
		Enterprise ent =  findEnterInfo(id);
		//第四步：判断获取的企业信息是否存在，数据是否可用
		if(StringUtil.isNotEmpty(ent.getCompany())&&StringUtil.isNotEmpty(ent.getCompanyName())&&StringUtil.isNotEmpty(ent.getCreateTime())&&StringUtil.isNotEmpty(ent.getUpdateTime())){
			//若可用，则返回该值
			return ent;
		}else{
			//若不可用，判断哪里出的问题
			if(StringUtil.isEmpty(ent.getCompanyName())){
				//根据分析方法获取公司简称
				JSONObject info = AnalysisUtil.getCompanyInfo(ent.getCompany());
				String companyName = info.getString("resultAbbr");
				ent.setCompanyName(companyName);
				Enterprise save = enterPriseRepository.save(ent);
				return save;
			}
			if(StringUtil.isEmpty(ent.getCreateTime())||StringUtil.isEmpty(ent.getUpdateTime())){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
				if(StringUtil.isEmpty(ent.getCreateTime())){
					ent.setCreateTime(date);
					if(StringUtil.isEmpty(ent.getUpdateTime())){
						ent.setUpdateTime(date);
					}
				}else{
					ent.setUpdateTime(date);
				}
				Enterprise save = enterPriseRepository.save(ent);
				return save;
			}
			return null;
		}
	}

	@Override
	public Enterprise findByCompanyName(String companyName) {
		return enterPriseRepository.findByCompany(companyName);
	}

	private Enterprise findEnterInfo(Long id ) {
		//第二步：随机抽取一个id值
		Long fid = (long) (Math.random()*id+1);
		if(fid>=id){
			fid=id;
		}
		//第三步：根据id查看企业信息
		Enterprise ent = enterPriseRepository.findOne(fid);
		while(ent==null){
			//第五步若数据不存在，重新获取
			ent = findEnterInfo(id);
		}
		return ent;
	}

}

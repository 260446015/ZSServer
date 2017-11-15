package com.huishu.ait.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.Label;
import com.huishu.ait.entity.PoolCompany;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanySearchDTO;
import com.huishu.ait.repository.user.PoolCompanyRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.user.DemandPoolService;

/**
 * 需求池相关实现类
 *
 * @author yindq
 * @date 2017/9/21
 */
@Service
public class DemandPoolServiceImpl extends AbstractService implements DemandPoolService {

    @Autowired
    private PoolCompanyRepository poolCompanyRepository;

    @Override
    public List<PoolCompany> getMyCompanyList(CompanySearchDTO searchModel) {
        String[] msg = searchModel.getMsg();
        searchModel.setLabel(msg[0]);
        searchModel.setStatus(msg[1]);
        String[] times = analysisDate(msg[2]);
        List<PoolCompany> list;
        if(searchModel.getStatus().equals("未入住")){
            Integer count = poolCompanyRepository.findMyNotCompanyCount(searchModel.getUserId(), searchModel.getLabel(), "已入住", times[0], times[1]);
            searchModel.setTotalSize(count);
            list = poolCompanyRepository.findMyNotCompanyList(searchModel.getUserId(), searchModel.getLabel(), "已入住", times[0], times[1], searchModel.getPageFrom(), searchModel.getPageSize());
        }else{
            Integer count = poolCompanyRepository.findMyCompanyCount(searchModel.getUserId(), searchModel.getLabel(), searchModel.getStatus(),times[0], times[1]);
            searchModel.setTotalSize(count);
            list = poolCompanyRepository.findMyCompanyList(searchModel.getUserId(), searchModel.getLabel(), searchModel.getStatus(),times[0], times[1], searchModel.getPageFrom(), searchModel.getPageSize());
        }
        return list;
    }

    @Override
    public List<PoolCompany> getCompanyList(CompanySearchDTO searchModel) {
    	String[] msg = searchModel.getMsg();
        searchModel.setLabel(msg[0]);
        searchModel.setStatus(msg[1]);
        String[] times = analysisDate(msg[2]);
        List<PoolCompany> list;
        if(searchModel.getStatus().equals("未入住")){
            Integer count = poolCompanyRepository.findNotCompanyCount(searchModel.getPark(), searchModel.getLabel(), "已入住", times[0], times[1],searchModel.getSearch());
            searchModel.setTotalSize(count);
            list = poolCompanyRepository.findNotCompanyList(searchModel.getPark(), searchModel.getLabel(), "已入住", times[0], times[1], searchModel.getPageFrom(), searchModel.getPageSize(),searchModel.getSearch());
        }else{
            Integer count = poolCompanyRepository.findCompanyCount(searchModel.getPark(), searchModel.getLabel(), searchModel.getStatus(),times[0], times[1],searchModel.getSearch());
            searchModel.setTotalSize(count);
            list = poolCompanyRepository.findCompanyList(searchModel.getPark(), searchModel.getLabel(), searchModel.getStatus(),times[0], times[1], searchModel.getPageFrom(), searchModel.getPageSize(),searchModel.getSearch());
        }
        return list;
    }

    @Override
    public AjaxResult addPoolCompany(PoolCompany company) {
    	AjaxResult result = new AjaxResult();
    	List<PoolCompany> list = poolCompanyRepository.findByName(company.getName());
		if(list.size()==0){
			result.setSuccess(false).setMessage("添加失败，请勿重复添加该企业");
		}
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        company.setCreateTime(sdf.format(new Date()));
        if(company.getFatherName()==null) company.setFatherName("");
        PoolCompany save = poolCompanyRepository.save(company);
        if (save == null) {
            result.setSuccess(false).setMessage("添加失败，请稍后再试");
        }
        return result.setSuccess(true).setMessage("添加成功");
    }

	@Override
	public AjaxResult editPoolCompany(PoolCompany company) {
		AjaxResult result = new AjaxResult();
		PoolCompany one = poolCompanyRepository.findOne(company.getId());
		one.setInvestmentRemark(company.getInvestmentRemark());
		one.setInvestmentStatus(company.getInvestmentStatus());
		PoolCompany save = poolCompanyRepository.save(one);
        if (save == null) {
            result.setSuccess(false).setMessage("修改失败，请稍后再试");
        }
        return result.setSuccess(true).setMessage("修改成功");
	}

	@Override
	public PoolCompany findPoolCompanyById(Long id) {
		return poolCompanyRepository.findOne(id);
	}
}

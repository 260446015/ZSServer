package com.huishu.ManageServer.service.garden.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.common.util.DateUtils;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.Company;
import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.repository.first.GardenCompanyRepository;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.service.garden.GardenCompanyService;


/**
 * 
 * @author yindawei
 * @date 2017年10月31日下午4:16:03
 * @description 处理企业相关的service
 * @version
 */
@Service
public class GardenCompanyServiceImpl extends AbstractService<Company> implements GardenCompanyService {

	private static Logger LOGGER = LoggerFactory.getLogger(GardenCompanyServiceImpl.class);
	@Autowired
	private GardenCompanyRepository gardenCompanyRepository;
//	@Autowired
//	private CompanyAttaRepository companyAttaRepository;
//	@Autowired
//	private TelContectRepository telContectRepository;

	@Override
	public Page<Company> findCompanyList(CompanySearchDTO dto) {
		PageImpl<Company> page = null;
		try {
			String[] msg = dto.getMsg();
			Integer pageNum = dto.getPageNum();
			Integer pageSize = dto.getPageSize();
			PageRequest pageRequest = new PageRequest(pageNum, pageSize);
			List<Company> findAll = null;
			if(StringUtil.isEmpty(dto.getPark())){
				findAll = (List<Company>) gardenCompanyRepository.findAll();
			}else{
				findAll = gardenCompanyRepository.findByPark(dto.getPark());
			}
			
			String time = msg[0];
			String startTime = null;
			String endTime = null;
			if ("成立1年内".equals(time)) {
				startTime = DateUtils.getMonthDate(-12);
				endTime = DateUtils.getTodayDate1();
			} else if ("成立1-5年".equals(time)) {
				startTime = DateUtils.getMonthDate(-60);
				endTime = DateUtils.getMonthDate(-12);
			} else if ("成立5-10年".equals(time)) {
				startTime = DateUtils.getMonthDate(-120);
				endTime = DateUtils.getMonthDate(-60);
			} else if ("成立10-15年".equals(time)) {
				startTime = DateUtils.getMonthDate(-180);
				endTime = DateUtils.getMonthDate(-120);
			} else if ("成立15年以上".equals(time)) {
				startTime = DateUtils.getMonthDate(-1800);
				endTime = DateUtils.getMonthDate(-180);
			} else if ("全部".equals(time)) {
				startTime = DateUtils.getMonthDate(-1800);
				endTime = DateUtils.getTodayDate1();
			}
			String stime = startTime;
			String etime = endTime;
			String regist = msg[1];
			if (regist.equals("全部"))
				regist = "0-999999";
			else if ("0-100万".equals(regist))
				regist = "0-100";
			else if ("100-200万".equals(regist))
				regist = "100-200";
			else if ("200-500万".equals(regist))
				regist = "200-500";
			else if ("500-1000万".equals(regist))
				regist = "500-1000";
			else if ("1000万以上".equals(regist))
				regist = "1000-9999999";
			Double sregist = Double.parseDouble(regist.substring(0, regist.indexOf("-")));
			Double eregist = Double.parseDouble(regist.substring(regist.indexOf("-") + 1));
			List<Company> list2;
			findAll = findAll.stream()
					// .filter(obj -> {
					// return obj.getScale() >= Integer.parseInt(sscale) &&
					// obj.getScale() < Integer.parseInt(escale);
					// })
					.filter(obj -> {
						return obj.getRegisterDate().compareTo(stime) >= 0
								&& obj.getRegisterDate().compareTo(etime) < 0;
					}).filter(obj -> {
						return obj.getRc() >= sregist && obj.getRc() < eregist;
					}).sorted((a, b) -> b.getRegisterCapital().compareTo(a.getRegisterCapital())).collect(Collectors.toList());
			list2 = findAll.stream().skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
			page = new PageImpl<>(list2, pageRequest, findAll.size());
		} catch (Exception e) {
			LOGGER.error("查询企业列表失败", e.getMessage());
		}
		return page;
	}

	@Override
	public boolean saveGardenCompany(Company company) {
		boolean flag = false;
		try{
			if(company.getId() == null){
				gardenCompanyRepository.save(company);
			}else{
				Company findOne = gardenCompanyRepository.findOne(company.getId());
				findOne.setAddress(company.getAddress());
				findOne.setCompanyName(company.getCompanyName());
				findOne.setBoss(company.getBoss());
				findOne.setPark(company.getPark());
				findOne.setRegisterCapital(company.getRegisterCapital());
				findOne.setRegisterDate(company.getRegisterDate());
				gardenCompanyRepository.save(findOne);
			}
			flag = true;
		}catch(Exception e){
			LOGGER.info("保存园区企业失败:",e.getMessage());
		}
		return flag;
	}


}

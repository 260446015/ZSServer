package com.huishu.ZSServer.service.company.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.util.DateUtils;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.company.CompanyService;

/**
 * 
 * @author yindawei
 * @date 2017年10月31日下午4:16:03
 * @description 处理企业相关的service
 * @version
 */
@Service
public class CompanyServiceImpl extends AbstractService<Company> implements CompanyService {

	private static Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Page<Company> findCompanyList(CompanySearchDTO dto) {
		Page<Company> page = null;
		try {
			String[] msg = dto.getMsg();
			String industry = msg[0];
			if(industry.equals("全部"))
				industry = "%%";
			String industryLable = msg[1];
			if(industryLable.equals("全部"))
				industryLable = "%%";
			String scale = msg[2];
			if(scale.equals("全部"))
				scale = "0-100000";
			String time = msg[3];
			String regist = msg[4];
			if(regist.equals("全部"))
				regist = "0-999999";
			String invest = msg[5];
			if(invest.equals("全部"))
				invest = "%%";
			String sscale = scale.substring(0, scale.indexOf("-"));
			String escale = scale.substring(scale.indexOf("-") + 1);
			String startTime = "";
			String endTime = "";
			switch (time) {
			case "1":
				startTime = DateUtils.getMonthDate(-12);
				endTime = DateUtils.getTodayDate1();
				break;
			case "1-5":
				startTime = DateUtils.getMonthDate(-60);
				endTime = DateUtils.getMonthDate(-12);
				break;
			case "5-10":
				startTime = DateUtils.getMonthDate(-120);
				endTime = DateUtils.getMonthDate(-60);
				break;
			case "10-15":
				startTime = DateUtils.getMonthDate(-180);
				endTime = DateUtils.getMonthDate(-120);
				break;
			case "more":
				startTime = DateUtils.getMonthDate(-1800);
				endTime = DateUtils.getMonthDate(-180);
				break;
			case "全部":
				startTime = DateUtils.getMonthDate(-1800);
				endTime = DateUtils.getTodayDate1();
				break;
			default:
				break;
			}
			String sregist = regist.substring(0, regist.indexOf("-"));
			String eregist = regist.substring(regist.indexOf("-") + 1);
			PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(), new Sort(Direction.DESC, "registerCapital"));
			page = companyRepository.findCompanyList(industry, industryLable, Integer.valueOf(sscale), Integer.valueOf(escale), startTime, endTime,
					Double.valueOf(sregist), Double.valueOf(eregist), invest, pageRequest);
		} catch (Exception e) {
			LOGGER.error("查询企业列表失败", e.getMessage());
		}
		return page;
	}

	/**
	 * 查找公司名称
	 */
	@Override
	public List<String> findCompanyName(String area, String industry) {
		List<String> list = companyRepository.findByAreaAndIndustry(area,industry);
		return list;
	}

	
	

}

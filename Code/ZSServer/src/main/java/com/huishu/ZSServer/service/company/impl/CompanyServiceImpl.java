package com.huishu.ZSServer.service.company.impl;

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

import com.huishu.ZSServer.common.util.DateUtils;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAttation;
import com.huishu.ZSServer.entity.TelContect;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.repository.company.CompanyAttaRepository;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.company.TelContectRepository;
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
	@Autowired
	private CompanyAttaRepository companyAttaRepository;
	@Autowired
	private TelContectRepository telContectRepository;

	@Override
	public Page<Company> findCompanyList(CompanySearchDTO dto) {
		PageImpl<Company> page = null;
		try {
			String[] msg = dto.getMsg();
			Integer pageNum = dto.getPageNumber();
			Integer pageSize = dto.getPageSize();
			PageRequest pageRequest = new PageRequest(pageNum, pageSize);
			if (msg.length == 1) {
				return companyRepository.findByParkAndAddressNotNull(msg[0], pageRequest);
			}
			List<Company> findAll = companyRepository.findByParkAndAddressNotNull(msg[2]);
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
	public boolean attationCompany(Long companyId, boolean flag, Long userId) {
		CompanyAttation ca = companyAttaRepository.findByCompanyIdAndUserId(companyId, userId);
		try {
			if (flag) {
				if (ca == null) {
					ca = new CompanyAttation();
					ca.setCompanyId(companyId);
					ca.setUserId(userId);
					ca.setCompanyGroup("");
					companyAttaRepository.save(ca);
				}
			} else {
				if (ca == null) {
					return true;
				} else
					companyAttaRepository.delete(ca);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean telContact(String name, String cname, Long userId) {
		try {
			TelContect tc = telContectRepository.telContact(userId, cname, name);
			if (tc == null) {
				tc = new TelContect();
				tc.setCname(cname);
				tc.setName(name);
				tc.setUserId(userId);
				tc.setUrgency("false");
				tc.setDate(System.currentTimeMillis());
				tc.setType("company");
				telContectRepository.save(tc);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;

	}

}

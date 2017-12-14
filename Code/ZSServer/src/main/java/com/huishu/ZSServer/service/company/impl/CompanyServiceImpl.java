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
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.repository.company.CompanyAttaRepository;
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
	@Autowired
	private CompanyAttaRepository companyAttaRepository;

	@Override
	public Page<Company> findCompanyList(CompanySearchDTO dto) {
		PageImpl<Company> page = null;
		try {
			String[] msg = dto.getMsg();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("industry", msg[0]);
			params.put("invest", msg[3]);
			params.put("park", msg[4]);
			Integer pageNum = dto.getPageNumber();
			Integer pageSize = dto.getPageSize();
			List<Company> findAll = companyRepository.findAll(getSpec(params));
			// String scale = msg[2];
			// if (scale.equals("全部"))
			// scale = "0-100000";
			// String sscale = scale.substring(0, scale.indexOf("-"));
			// String escale = scale.substring(scale.indexOf("-") + 1);
			String time = msg[1];
			String startTime = null;
			String endTime = null;
			if ("1".equals(time)) {
				startTime = DateUtils.getMonthDate(-12);
				endTime = DateUtils.getTodayDate1();
			} else if ("1-5".equals(time)) {
				startTime = DateUtils.getMonthDate(-60);
				endTime = DateUtils.getMonthDate(-12);
			} else if ("5-10".equals(time)) {
				startTime = DateUtils.getMonthDate(-120);
				endTime = DateUtils.getMonthDate(-60);
			} else if ("10-15".equals(time)) {
				startTime = DateUtils.getMonthDate(-180);
				endTime = DateUtils.getMonthDate(-120);
			} else if ("more".equals(time)) {
				startTime = DateUtils.getMonthDate(-1800);
				endTime = DateUtils.getMonthDate(-180);
			} else if ("全部".equals(time)) {
				startTime = DateUtils.getMonthDate(-1800);
				endTime = DateUtils.getTodayDate1();
			}
			String stime = startTime;
			String etime = endTime;
			String regist = msg[2];
			if (regist.equals("全部"))
				regist = "0-999999";
			Double sregist = Double.parseDouble(regist.substring(0, regist.indexOf("-")));
			Double eregist = Double.parseDouble(regist.substring(regist.indexOf("-") + 1));
			List<Company> list2 = findAll.stream()
					// .filter(obj -> {
					// return obj.getScale() >= Integer.parseInt(sscale) &&
					// obj.getScale() < Integer.parseInt(escale);
					// })
					.filter(obj -> {
						return obj.getRegisterDate().compareTo(stime) >= 0
								&& obj.getRegisterDate().compareTo(etime) < 0;
					}).filter(obj -> {
						return Double.parseDouble(
								obj.getRegisterCapital().substring(0, obj.getRegisterCapital().indexOf("万")))
								- sregist >= 0
								&& Double.parseDouble(
										obj.getRegisterCapital().substring(0, obj.getRegisterCapital().indexOf("万")))
										- eregist < 0;
					})
					.skip(pageNum * pageSize).limit(pageSize)
					.sorted((a, b) -> new Double(Double
							.parseDouble(b.getRegisterCapital().substring(0, b.getRegisterCapital().indexOf("万"))))
									.compareTo(new Double(Double.parseDouble(
											a.getRegisterCapital().substring(0, a.getRegisterCapital().indexOf("万"))))))
					.collect(Collectors.toList());
			PageRequest pageRequest = new PageRequest(pageNum, pageSize);
			page = new PageImpl<>(list2, pageRequest, findAll.size());
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
		List<String> list = companyRepository.findByAreaAndIndustry(area, industry);
		return list;
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

}

package com.huishu.ZSServer.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.conf.PageConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.CompanyAttation;
import com.huishu.ZSServer.entity.CompnayGroup;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.entity.TelContect;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.entity.dto.CompanyDTO;
import com.huishu.ZSServer.entity.dto.IndustrySummitDTO;
import com.huishu.ZSServer.entity.vo.CompanyVO;
import com.huishu.ZSServer.repository.company.CompanyAttaRepository;
import com.huishu.ZSServer.repository.company.CompnayGroupRepository;
import com.huishu.ZSServer.repository.company.TelContectRepository;
import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
import com.huishu.ZSServer.repository.summit.SummitRepository;
import com.huishu.ZSServer.repository.user.UserInstitutionalRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.user.CenterOfAttentionService;

@Service
public class CenterOfAttentionServiceImpl extends AbstractService<T> implements CenterOfAttentionService{
	
	private Logger LOGGER = LoggerFactory.getLogger(CenterOfAttentionServiceImpl.class);
	
	@Autowired
	private InstitutionalRepostitory institutionalRepostitory;
	@Autowired
	private UserInstitutionalRepository userInstitutionalRepository;
	@Autowired
	private TelContectRepository telContectRepository;
	@Autowired
	private CompanyAttaRepository companyAttaRepository;
	@Autowired
	private CompnayGroupRepository compnayGroupRepository;
	@Autowired
	private SummitRepository summitRepository;
	
	@Override
	public Page<Institutional> findOrganizationList(String industry, Long userId,Integer pageNum) {
		List<Institutional> list = institutionalRepostitory.findByIdAndIndustry(userId, industry, pageNum*PageConstant.FIVE, PageConstant.FIVE);
		PageRequest request = new PageRequest(pageNum,PageConstant.FIVE);
		if(list.size()==0){
			return new PageImpl<>(list, request, 0);
		}else{
			list.forEach(act ->{
				String url = act.getUrl();
				int i1 = url.indexOf("%");
				int i = url.indexOf("?");
				if(i1>0){
					act.setUrl(url.substring(0, i1));
					
				}
				if(i>0){
					act.setUrl(url.substring(0, i));
				}
			});
		}
		Integer total = institutionalRepostitory.findCountByIdAndIndustry(userId, industry);
		Page<Institutional> impl = new PageImpl<>(list, request, total);
		return impl;
	}

	@Transactional
	@Override
	public void cancelOrganization(Long id, Long userId) {
		userInstitutionalRepository.deleteByInsIdAndUserId(id, userId);
	}

	@Override
	public String liaison(Long id, Long userId) {
		TelContect tc = telContectRepository.findByUserIdAndOrganizationIdAndType(userId, id, "organization");
		if (tc == null) {
			tc = new TelContect();
			tc.setOrganizationId(id);
			tc.setUserId(userId);
			tc.setUrgency("false");
			tc.setDate(System.currentTimeMillis());
			tc.setType("organization");
			TelContect save = telContectRepository.save(tc);
			if (save == null) {
				return MsgConstant.OPERATION_ERROR;
			}
			return "正在全力为您联系，请耐心等待消息";
		}else{
			return "已反馈意向，请耐心等待消息";
		}
	}

	@Override
	public List<String> getCompnayIndustry(Long userId) {
		return companyAttaRepository.getCompnayIndustry(userId);
	}

	@Override
	public List<String> getCompnayArea(Long userId) {
		List<String> list = companyAttaRepository.getCompnayArea(userId);
		List<String> areas = new ArrayList<String>();
		for (String string : list) {
			String area = conversionArea(string);
			areas.add(area);
		}
		return areas;
	}

	@Override
	public List<CompnayGroup> getCompnayGroup(Long userId) {
		return compnayGroupRepository.findByUserId(userId);
	}
	
	@Override
	public List<CompanyVO> findCompnayList(Long userId, CompanyDTO dto) {
		Calendar calendar = Calendar.getInstance();
		Long time1=0L;
		Long time2=calendar.getTimeInMillis();
		if(!StringUtil.isEmpty(dto.getTime())){
			 switch(dto.getTime()){
	            case "1年内":
	                calendar.add(Calendar.YEAR, -1);
	                time1 = calendar.getTimeInMillis();
	                break;
	            case "1-5年":
	            	calendar.add(Calendar.YEAR, -5);
	            	time1 = calendar.getTimeInMillis();
	            	calendar.add(Calendar.YEAR, +4);
	                time2 = calendar.getTimeInMillis();
	                break;
	            case "5-10年":
	            	calendar.add(Calendar.YEAR, -10);
	            	time1 = calendar.getTimeInMillis();
	            	calendar.add(Calendar.YEAR, +5);
	                time2 = calendar.getTimeInMillis();
	                break;
	            case "10-15年":
	            	calendar.add(Calendar.YEAR, -15);
	            	time1 = calendar.getTimeInMillis();
	            	calendar.add(Calendar.YEAR, +10);
	                time2 = calendar.getTimeInMillis();
	                break;
	            case "15年以上":
	            	calendar.add(Calendar.YEAR, -15);
	                time2 = calendar.getTimeInMillis();
	                break;
			 }
		}
		String area="%%";
		if(!dto.getArea().equals("%%")){
			area = conversionAreaTwo(dto.getArea());
		}
		List<Object[]> list = companyAttaRepository.findCompnayList(userId, dto.getIndustry(), time1, time2, area, dto.getGroup());
		List<CompanyVO> arrayList = new ArrayList<CompanyVO>();
		if(list.size()==0){
			return arrayList;
		}
		for (Object[] array : list) {
			CompanyVO vo = new CompanyVO();
			vo.setId(Long.valueOf(array[0].toString()));
			vo.setName(array[1].toString());
			vo.setBase(conversionArea(array[2].toString()));
			vo.setLegalPersonName(array[3].toString());
			vo.setRegCapital(array[4].toString());
			Date d = new Date(Long.valueOf(array[5].toString()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			vo.setEstiblishTime(sdf.format(d));
			arrayList.add(vo);
		}
		if(StringUtil.isEmpty(dto.getMoney())){
			return arrayList;
		}
		String regist = dto.getMoney();
		if ("0-100万".equals(regist))
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
		arrayList = arrayList.stream().filter(obj -> {
			String dataString=obj.getRegCapital();
			if(!StringUtil.isEmpty(dataString)){
				Double myMoney=0.0;
				if (dataString.indexOf("RMB") != -1 || dataString.indexOf("¥") != -1 || dataString.indexOf("￥") != -1
						|| dataString.indexOf("人民币") != -1) {
					myMoney = conversionData(dataString);
				} else if (dataString.indexOf("$") != -1 || dataString.indexOf("美元") != -1 || dataString.indexOf("USD") != -1) {
					myMoney = conversionData(dataString) * 6.6;
				} else if (dataString.indexOf("港币") != -1) {
					myMoney = conversionData(dataString) * 0.8455;
				} else {
					LOGGER.error("无法识别的融资金额币种来源：" + dataString);
				}
				return myMoney >= sregist && myMoney < eregist;
			}else{
				return false;
			}
		}).collect(Collectors.toList());
		return arrayList;
	}

	@Transactional
	@Override
	public void cancelCompnay(Long id, Long userId) {
		companyAttaRepository.deleteByCompanyIdAndUserId(id, userId);
	}

	@Override
	public Boolean addCompnayGroup(Long userId, String name) {
		CompnayGroup groupName = compnayGroupRepository.findByUserIdAndGroupName(userId,name);
		if(groupName!=null){
			return false;
		}
		CompnayGroup group = new CompnayGroup();
		group.setGroupName(name);
		group.setUserId(userId);
		compnayGroupRepository.save(group);
		return true;
	}

	@Override
	public Boolean dropCompnayGroup(Long userId, Long groupId) {
		try {
			compnayGroupRepository.delete(groupId);
			companyAttaRepository.deleteByUserIdAndGroupId(userId, groupId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	@Override
	public Boolean moveCompnayGroup(Long id, Long userId,Long groupId, String name) {
		CompanyAttation attation = companyAttaRepository.findByCompanyIdAndUserId(id, userId);
		attation.setCompanyGroup(name);
		attation.setGroupId(groupId);
		CompanyAttation save = companyAttaRepository.save(attation);
		if(save==null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public Page<UserSummitInfo> listSummitMeetingList(Long userId, IndustrySummitDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNumber(), dto.getPageSize());
		String idustry="%"+dto.getIndustry()+"%";
		if(dto.getIndustry().equals("全部")){
			idustry="%%";
		}
		String address="%"+dto.getArea()+"%";
		if(dto.getArea().equals("全部")){
			address="%%";
		}
		Page<UserSummitInfo> page = summitRepository.findByUidAndAddressLikeAndIdustryLike(userId, address, idustry, request);
		return page;
	}

	@Transactional
	@Override
	public void cancelSummitMeeting(Long id, Long userId) {
		summitRepository.deleteByAidAndUid(id, userId);
	}

}

package com.huishu.ZSServer.service.openeyes.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.DateUtil;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAttation;
import com.huishu.ZSServer.entity.dto.BaseInfoCustom;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.openeyes.Abnormal;
import com.huishu.ZSServer.entity.openeyes.Allotmen;
import com.huishu.ZSServer.entity.openeyes.Announcement;
import com.huishu.ZSServer.entity.openeyes.AppbkInfo;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.entity.openeyes.Bids;
import com.huishu.ZSServer.entity.openeyes.Bond;
import com.huishu.ZSServer.entity.openeyes.BonusInfo;
import com.huishu.ZSServer.entity.openeyes.Branch;
import com.huishu.ZSServer.entity.openeyes.Certificate;
import com.huishu.ZSServer.entity.openeyes.ChangeInfo;
import com.huishu.ZSServer.entity.openeyes.CheckInfo;
import com.huishu.ZSServer.entity.openeyes.CompanyInfo;
import com.huishu.ZSServer.entity.openeyes.CopyReg;
import com.huishu.ZSServer.entity.openeyes.CourtAnnouncement;
import com.huishu.ZSServer.entity.openeyes.Dishonest;
import com.huishu.ZSServer.entity.openeyes.Employment;
import com.huishu.ZSServer.entity.openeyes.EquityChange;
import com.huishu.ZSServer.entity.openeyes.HistoryRongZi;
import com.huishu.ZSServer.entity.openeyes.Holder;
import com.huishu.ZSServer.entity.openeyes.HoldingCompany;
import com.huishu.ZSServer.entity.openeyes.HumanRiskInfo;
import com.huishu.ZSServer.entity.openeyes.Icp;
import com.huishu.ZSServer.entity.openeyes.Illegalinfo;
import com.huishu.ZSServer.entity.openeyes.Inverst;
import com.huishu.ZSServer.entity.openeyes.IssueRelated;
import com.huishu.ZSServer.entity.openeyes.JingPin;
import com.huishu.ZSServer.entity.openeyes.LawSuit;
import com.huishu.ZSServer.entity.openeyes.News;
import com.huishu.ZSServer.entity.openeyes.OwnTax;
import com.huishu.ZSServer.entity.openeyes.Patents;
import com.huishu.ZSServer.entity.openeyes.ProductInfo;
import com.huishu.ZSServer.entity.openeyes.PunishmentInfo;
import com.huishu.ZSServer.entity.openeyes.Purchaseland;
import com.huishu.ZSServer.entity.openeyes.RiskDetail;
import com.huishu.ZSServer.entity.openeyes.RiskInfo;
import com.huishu.ZSServer.entity.openeyes.SeniorExecutive;
import com.huishu.ZSServer.entity.openeyes.ShangBiao;
import com.huishu.ZSServer.entity.openeyes.ShareHolder;
import com.huishu.ZSServer.entity.openeyes.ShareStructure;
import com.huishu.ZSServer.entity.openeyes.Staff;
import com.huishu.ZSServer.entity.openeyes.TaxCredit;
import com.huishu.ZSServer.entity.openeyes.TeamMember;
import com.huishu.ZSServer.entity.openeyes.TouZi;
import com.huishu.ZSServer.entity.openeyes.Volatility;
import com.huishu.ZSServer.entity.openeyes.ZhixingInfo;
import com.huishu.ZSServer.exception.OpeneyesException;
import com.huishu.ZSServer.repository.company.CompanyAttaRepository;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.openeyes.AbnormalRepository;
import com.huishu.ZSServer.repository.openeyes.AllotmenRepository;
import com.huishu.ZSServer.repository.openeyes.AnnouncementRepository;
import com.huishu.ZSServer.repository.openeyes.AppbkInfoRepository;
import com.huishu.ZSServer.repository.openeyes.BaseInfoRepository;
import com.huishu.ZSServer.repository.openeyes.BidsRepository;
import com.huishu.ZSServer.repository.openeyes.BondRepository;
import com.huishu.ZSServer.repository.openeyes.BonusInfoRepository;
import com.huishu.ZSServer.repository.openeyes.BranchRepository;
import com.huishu.ZSServer.repository.openeyes.CertificateRepository;
import com.huishu.ZSServer.repository.openeyes.ChangeInfoRepository;
import com.huishu.ZSServer.repository.openeyes.CheckInfoRepository;
import com.huishu.ZSServer.repository.openeyes.CompanyInfoRepository;
import com.huishu.ZSServer.repository.openeyes.CopyRegRepository;
import com.huishu.ZSServer.repository.openeyes.CourtAnnouncementRepository;
import com.huishu.ZSServer.repository.openeyes.DishonestRepository;
import com.huishu.ZSServer.repository.openeyes.EmploymentRepository;
import com.huishu.ZSServer.repository.openeyes.EquityChangeRepository;
import com.huishu.ZSServer.repository.openeyes.HistoryRongZiRepository;
import com.huishu.ZSServer.repository.openeyes.HolderRepository;
import com.huishu.ZSServer.repository.openeyes.HoldingCompanyRepository;
import com.huishu.ZSServer.repository.openeyes.HumanRiskInfoRepository;
import com.huishu.ZSServer.repository.openeyes.IcpRepository;
import com.huishu.ZSServer.repository.openeyes.IllegalinfoRepository;
import com.huishu.ZSServer.repository.openeyes.InverstRepository;
import com.huishu.ZSServer.repository.openeyes.IssueRelatedRepository;
import com.huishu.ZSServer.repository.openeyes.JingPinRepository;
import com.huishu.ZSServer.repository.openeyes.LawSuitRepository;
import com.huishu.ZSServer.repository.openeyes.NewsRepository;
import com.huishu.ZSServer.repository.openeyes.OwnTaxRepository;
import com.huishu.ZSServer.repository.openeyes.PatentsRepository;
import com.huishu.ZSServer.repository.openeyes.ProductInfoRepository;
import com.huishu.ZSServer.repository.openeyes.PunishmentInfoRepository;
import com.huishu.ZSServer.repository.openeyes.PurchaselandRepository;
import com.huishu.ZSServer.repository.openeyes.RiskDetailRepository;
import com.huishu.ZSServer.repository.openeyes.RiskInfoRepository;
import com.huishu.ZSServer.repository.openeyes.SeniorExecutiveRepository;
import com.huishu.ZSServer.repository.openeyes.ShangBiaoRepository;
import com.huishu.ZSServer.repository.openeyes.ShareHolderRepository;
import com.huishu.ZSServer.repository.openeyes.ShareStructureRepository;
import com.huishu.ZSServer.repository.openeyes.StaffRepository;
import com.huishu.ZSServer.repository.openeyes.TaxCreditRepository;
import com.huishu.ZSServer.repository.openeyes.TeamMemberRepository;
import com.huishu.ZSServer.repository.openeyes.TouZiRepository;
import com.huishu.ZSServer.repository.openeyes.VolatilityRepository;
import com.huishu.ZSServer.repository.openeyes.ZhixingInfoRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

@Service
public class OpeneyesServiceImpl<T> extends AbstractService<T> implements OpeneyesService {

	private static Logger log = LoggerFactory.getLogger(OpeneyesServiceImpl.class);
	@Autowired
	private BaseInfoRepository baseInfoRepository;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private HistoryRongZiRepository historyRongZiRepository;
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	@Autowired
	private ProductInfoRepository productInfoRepository;
	@Autowired
	private TouZiRepository touZiRepository;
	@Autowired
	private JingPinRepository jingPinRepository;
	@Autowired
	private ShangBiaoRepository shangBiaoRepository;
	@Autowired
	private PatentsRepository patentsRepository;
	@Autowired
	private CopyRegRepository copyRegRepository;
	@Autowired 
	private IcpRepository icpRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private AbnormalRepository abnormalRepository;
	@Autowired
	private PunishmentInfoRepository punishmentInfoRepository;
	@Autowired
	private IllegalinfoRepository illegalinfoRepository;
	@Autowired
	private OwnTaxRepository ownTaxRepository;
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private DishonestRepository dishonestRepository;
	@Autowired
	private RiskInfoRepository riskInfoRepository;
	@Autowired
	private HumanRiskInfoRepository humanRiskInfoRepository;
	@Autowired
	private RiskDetailRepository riskDetailRepository;
	@Autowired
	private TaxCreditRepository taxCreditRepository;
//	@Autowired
//	private SearchRepository searchRepository;
	@Autowired
	private HolderRepository holderRepository;
	@Autowired
	private ChangeInfoRepository changeInfoRepository;
	@Autowired
	private InverstRepository inverstRepository;
	@Autowired
	private BidsRepository bidsRepository;
	@Autowired
	private BondRepository bondRepository;
	@Autowired
	private PurchaselandRepository purchaselandRepository;
	@Autowired
	private EmploymentRepository employmentRepository;
	@Autowired
	private CheckInfoRepository checkInfoRepository;
	@Autowired
	private AppbkInfoRepository appbkInfoRepository;
	@Autowired
	private LawSuitRepository lawSuitRepository;
	@Autowired
	private CourtAnnouncementRepository courtAnnouncementRepository;
	@Autowired
	private ZhixingInfoRepository zhixingInfoRepository;
	@Autowired
	private VolatilityRepository volatilityRepository;
	@Autowired
	private CompanyInfoRepository companyInfoRepository;
	@Autowired
	private SeniorExecutiveRepository seniorExecutiveRepository;
	@Autowired
	private HoldingCompanyRepository holdingCompanyRepository;
	@Autowired
	private AnnouncementRepository announcementRepository;
	@Autowired
	private ShareHolderRepository shareHolderRepository;
	@Autowired
	private IssueRelatedRepository issueRelatedRepository;
	@Autowired
	private ShareStructureRepository shareStructureRepository;
	@Autowired
	private EquityChangeRepository equityChangeRepository;
	@Autowired
	private BonusInfoRepository bonusInfoRepository;
	@Autowired
	private AllotmenRepository allotmenRepository;
	@Autowired
	private CertificateRepository certificateRepository;
	@Autowired
	private CompanyAttaRepository companyAttaRepository;

	@Override
	public JSONObject getStaffInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.STAFF);
		dto.setParams(params);
		List<Staff> list = staffRepository.findByCname(dto.getCname());
//		List<Staff> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			result.put("result", list);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					staffRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("result");
		} catch (NullPointerException e) {
			log.debug("天眼查主要人员查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		jsonArray.forEach((obj) -> {
			Staff parseObject = JSONObject.parseObject(obj.toString(), Staff.class);
			parseObject.setCname(dto.getCname());
			parseObject.setCreationTime(System.currentTimeMillis());
			list.add(parseObject);
		});
		staffRepository.save(list);
		return openEyesTarget;
	}

	@Override
	public JSONObject getBaseInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.BASEINFO);
		BaseInfo info = baseInfoRepository.findByName(dto.getCname());
		if (info != null) {
			BaseInfoCustom custom = new BaseInfoCustom();
			BeanUtils.copyProperties(info, custom);
			custom.setIsAttation(false);
			CompanyAttation ca = companyAttaRepository.findByCompanyIdAndUserId(info.getId(), dto.getUserId());
			if(ca != null){
				custom.setIsAttation(true);
			}	
			openEyesTarget.put("result", custom);
			try {
				long time = System.currentTimeMillis() - info.getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					baseInfoRepository.delete(info);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		if(null != openEyesTarget){
			JSONObject jsonObj = openEyesTarget.getJSONObject("result");
			if(jsonObj == null){
				throw new OpeneyesException();
			}
			info = JSONObject.parseObject(jsonObj.toString(), BaseInfo.class);
			info.setCreationTime(System.currentTimeMillis());
			if(dto.getFlag() != null){
				if(dto.getFlag())
					getJingPin(dto);//此处调用获取竞品信息的功能，存入数据库
			}
			Company company = companyRepository.findByCompanyName(dto.getCname());
			if(null != company){
				company.setAddress(info.getRegLocation());
				company.setBoss(info.getLegalPersonName());
				company.setEngageState(info.getRegStatus());
//			company.setFinancingAmount(parseObject.get);
//			company.setFinancingDate(financingDate);
				company.setOpenIndustry(info.getIndustry());
//			company.setInvest(parseObject.get);
//			company.setInvestor(parseObject.get);
				company.setLogo(info.getWebsiteList());
				company.setOpenRegisterCapital(info.getRegCapital());
//			company.setRegisterDate(parseObject.get);
//			company.setScale(scale);
				company.setOpenActualCapital(info.getActualCapital());
				company.setOpenBase(info.getBase());
				company.setOpenBusinessScope(info.getBusinessScope());
				company.setOpenCategoryScore(info.getCategoryScore());
				company.setOpenCompanyOrgType(info.getCompanyOrgType());
				company.setOpenCorrectCompanyId(info.getCorrectCompanyId());
				company.setOpenCreditCode(info.getCreditCode());
				company.setOpenEstiblishTime(info.getEstiblishTime());
				company.setOpenFromTime(info.getFromTime());
				company.setOpenLegalPersonId(info.getLegalPersonId());
				company.setOpenLegalPersonName(info.getLegalPersonName());;
				company.setOpenOrgApprovedInstitute(info.getOrgApprovedInstitute());
				company.setOpenOrgNumber(info.getOrgNumber());
				company.setOpenPercentileScore(info.getPercentileScore());
				company.setOpenPhoneNumber(info.getPhoneNumber());
				company.setOpenRegInstitute(info.getRegInstitute());
				company.setOpenRegNumber(info.getRegNumber());
				company.setOpenToTime(info.getToTime());
				company.setOpenType(info.getType());
				companyRepository.save(company);
			}
			baseInfoRepository.save(info);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getBranch(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.BRANCH);
		List<Branch> list = branchRepository.findByCompanyName(dto.getCname());
//		List<Branch> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("result", list);
			openEyesTarget.put("data", inList);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					branchRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		if(null != openEyesTarget){
			JSONArray jsonArray = null;
			try {
				jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("result");
			} catch (NullPointerException e) {
				log.debug("天眼查分支机构查询数据为空",e.getMessage());
				throw new OpeneyesException();
			}
			if(jsonArray != null){
				jsonArray.forEach(obj -> {
					Branch parseObject = JSONObject.parseObject(obj.toString(), Branch.class);
					parseObject.setCompanyName(dto.getCname());
					parseObject.setCreationTime(System.currentTimeMillis());
					list.add(parseObject);
				});
				branchRepository.save(list);
			}
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getHistoryRongZi(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.HISTORYRONGZI);
		List<HistoryRongZi> list = historyRongZiRepository.findByCompanyName(dto.getCname());
//		List<HistoryRongZi> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("rows", list);
			JSONObject inList2 = new JSONObject();
			inList2.put("page", inList);
			openEyesTarget.put("result", inList2);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					historyRongZiRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		if(null != openEyesTarget){
			JSONArray jsonArray = null;
			try {
				jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
			} catch (NullPointerException e) {
				log.debug("天眼查历史融资查询数据为空",e.getMessage());
				throw new OpeneyesException();
			}
			if(jsonArray != null){
				jsonArray.forEach(obj -> {
					HistoryRongZi parseObject = JSONObject.parseObject(obj.toString(), HistoryRongZi.class);
					String id = getGeneratedId(parseObject);
					parseObject.setCreationTime(System.currentTimeMillis());
					parseObject.setId(id);
					list.add(parseObject);
				});
				historyRongZiRepository.save(list);
			}
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTeamMember(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.TEAMMEMBER);
		List<TeamMember> list = teamMemberRepository.findByCompanyName(dto.getCname());
//		List<TeamMember> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("rows", list);
			JSONObject page = new JSONObject();
			page.put("page", inList);
			openEyesTarget.put("result", page);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					teamMemberRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		} catch (NullPointerException e) {
			log.debug("天眼查核心团队查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				TeamMember parseObject = JSONObject.parseObject(obj.toString(), TeamMember.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			teamMemberRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getProductInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.PRODUCTINFO);
		List<ProductInfo> list = productInfoRepository.findByCompanyName(dto.getCname());
//		List<ProductInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("rows", list);
			JSONObject page = new JSONObject();
			page.put("page", inList);
			openEyesTarget.put("result", page);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					productInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		} catch (NullPointerException e) {
			log.debug("天眼查企业业务查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				ProductInfo parseObject = JSONObject.parseObject(obj.toString(), ProductInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			productInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTouZi(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.TZANLI);
		List<TouZi> list = touZiRepository.findByCompanyName(dto.getCname());
//		List<TouZi> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					touZiRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		} catch (NullPointerException e) {
			log.debug("天眼查投资案例查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				TouZi parseObject = JSONObject.parseObject(obj.toString(), TouZi.class);
				parseObject.setCompanyName(dto.getCname());
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			touZiRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getJingPin(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.JINGPIN);
		List<JingPin> list = jingPinRepository.findByCname(dto.getCname());
//		List<JingPin> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("rows", list);
			JSONObject outList = new JSONObject();
			outList.put("page", inList);
			openEyesTarget.put("result", outList);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					jingPinRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		} catch (NullPointerException e) {
			log.debug("天眼查竞品查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				JingPin parseObject = JSONObject.parseObject(obj.toString(), JingPin.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				parseObject.setCname(dto.getCname());
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			jingPinRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getShangBiao(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.SHANGBIAO);
		List<ShangBiao> list = shangBiaoRepository.findByApplicantCn(dto.getCname());
//		List<ShangBiao> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					shangBiaoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查商标查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				ShangBiao parseObject = JSONObject.parseObject(obj.toString(), ShangBiao.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			shangBiaoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getPatents(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.PATENTS);
		List<Patents> list = patentsRepository.findByApplicantName(dto.getCname());
//		List<Patents> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					patentsRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查专利查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				Patents parseObject = JSONObject.parseObject(obj.toString(), Patents.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			patentsRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getCopyReg(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.COPYREG);
		List<CopyReg> list = copyRegRepository.findByCompanyName(dto.getCname());
//		List<CopyReg> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					copyRegRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查著作权查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				CopyReg parseObject = JSONObject.parseObject(obj.toString(), CopyReg.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			copyRegRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getIcp(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.ICP);
		dto.setParams(params);
		List<Icp> list = icpRepository.findByCompanyName(dto.getCname());
//		List<Icp> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			result.put("data", list);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					icpRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArray = openEyesTarget.getJSONArray("data");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				Icp parseObject = JSONObject.parseObject(obj.toString(), Icp.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			icpRepository.save(list);
		}else
			throw new OpeneyesException();
		return openEyesTarget;
	}


	@Override
	public JSONObject getAbnormal(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.ABNORMAL);
		dto.setParams(params);
		List<Abnormal> list = abnormalRepository.findByCompanyName(dto.getCname());
//		List<Abnormal> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("data", list);
			result.put("result", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					abnormalRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray abnormal = null;
		try {
			abnormal = openEyesTarget.getJSONObject("data").getJSONArray("result");
		} catch (NullPointerException e) {
			log.debug("天眼查经营异常查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(abnormal != null){
			abnormal.forEach(obj -> {
				Abnormal parseObject = JSONObject.parseObject(obj.toString(), Abnormal.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			abnormalRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getPunishmentInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.XINGZHENGCHUFA);
		dto.setParams(params);
		List<PunishmentInfo> list = punishmentInfoRepository.findByName(dto.getCname());
//		List<PunishmentInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					punishmentInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查行政处罚数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				PunishmentInfo parseObject = JSONObject.parseObject(obj.toString(), PunishmentInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			punishmentInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getIllegalinfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.YANZHONGWEIFA);
		dto.setParams(params);
		List<Illegalinfo> list = illegalinfoRepository.findByCompanyName(dto.getCname());
//		List<Illegalinfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					illegalinfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查严重违法查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Illegalinfo parseObject = JSONObject.parseObject(obj.toString(), Illegalinfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			illegalinfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getOwnTax(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.QIANSHUIGONGGAO);
		dto.setParams(params);
		List<OwnTax> list = ownTaxRepository.findByName(dto.getCname());
//		List<OwnTax> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					ownTaxRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查欠税公告查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				OwnTax parseObject = JSONObject.parseObject(obj.toString(), OwnTax.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			ownTaxRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getNews(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.NEWS);
		dto.setParams(params);
		List<News> list = newsRepository.findByCompanyName(dto.getCname());
//		List<News> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			result.put("result", list);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					newsRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONArray("result");
			if(jsonArr == null){
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			log.debug("天眼查新闻查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				News parseObject = JSONObject.parseObject(obj.toString(), News.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			newsRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getDishonest(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.SHIXINREN);
		dto.setParams(params);
		List<Dishonest> list = dishonestRepository.findByIname(dto.getCname());
//		List<Dishonest> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					dishonestRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查失信人查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Dishonest parseObject = JSONObject.parseObject(obj.toString(), Dishonest.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			dishonestRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getRiskInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.QIYEFENGXIAN);
		dto.setParams(params);
		List<RiskInfo> list = riskInfoRepository.findByCname(dto.getCname());
//		List<RiskInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		List<RiskInfo> internal = null;
		List<RiskInfo> external = null;
		if (list.size() > 0) {
			internal = list.stream().filter((riskInfo ->riskInfo.getRiskType().equals("internalList"))).collect(Collectors.toList());
			external = list.stream().filter((riskInfo ->riskInfo.getRiskType().equals("externalList"))).collect(Collectors.toList());
			JSONObject inList = new JSONObject();
			inList.put("internalList", internal);
			inList.put("externalList", external);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					riskInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray internalList = null;
		try {
			internalList = openEyesTarget.getJSONObject("data").getJSONArray("internalList");
		} catch (NullPointerException e) {
			log.debug("天眼查企业风险查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(internalList != null){
			internalList.forEach(obj -> {
				RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setRiskType("internalList");
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
		}
		JSONArray externalList = null;
		try {
			externalList = openEyesTarget.getJSONObject("data").getJSONArray("externalList");
		} catch (NullPointerException e) {
			log.debug("天眼查企业风险查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(externalList != null){
			externalList.forEach(obj -> {
				RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setRiskType("externalList");
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
		}
		riskInfoRepository.save(list);
		return openEyesTarget;
	}

	@Override
	//-----------------------
	public JSONObject getHumanRiskInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("humanName", dto.getHumanName());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.RENFENGXIAN);
		dto.setParams(params);
		List<HumanRiskInfo> list = humanRiskInfoRepository.findByCompanyNameAndHumanName(dto.getCname(),dto.getHumanName());
//		List<HumanRiskInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("externalList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					humanRiskInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("externalList");
		} catch (NullPointerException e) {
			log.debug("天眼查人风险查询数据为空",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				HumanRiskInfo parseObject = JSONObject.parseObject(obj.toString(), HumanRiskInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setHumanName(dto.getHumanName());
				list.add(parseObject);
			});
			humanRiskInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getRiskDetail(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("id", dto.getId());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.FENGXIANXINXI);
		dto.setParams(params);
		List<RiskDetail> list = riskDetailRepository.findBySearchId(dto.getId());
//		List<RiskDetail> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("result", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					riskDetailRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
			if(jsonArr == null){
				throw new NullPointerException();
			}
		} catch (Exception e) {
			log.debug("天眼查风险信息查询出现问题",e.getMessage());
			return openEyesTarget;
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				RiskDetail parseObject = JSONObject.parseObject(obj.toString(), RiskDetail.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCompanyName(dto.getCname());
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				parseObject.setSearchId(dto.getId());
				list.add(parseObject);
			});
			riskDetailRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTaxCredit(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.SHUIWU);
		dto.setParams(params);
		List<TaxCredit> list = taxCreditRepository.findByName(dto.getCname());
//		List<TaxCredit> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					taxCreditRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查税务评级查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				TaxCredit parseObject = JSONObject.parseObject(obj.toString(), TaxCredit.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			taxCreditRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getSousuoCompanyList(OpeneyesDTO dto) {
		Map<String, Object> params = new HashMap<>();
		params.put("word", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.SOUSUO);
		dto.setParams(params);
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		return openEyesTarget;
	}

	@Override
	public JSONObject getHolder(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.HOLDER);
		dto.setParams(params);
		List<Holder> list = holderRepository.findByCname(dto.getCname());
//		List<Holder> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("result", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					holderRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("result");
		} catch (NullPointerException e) {
			log.debug("天眼查股东信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				JSONObject target = JSONObject.parseObject(obj.toString());
				Holder holder = new Holder();
				holder.setCreationTime(System.currentTimeMillis());
				holder.setAmount(target.getDouble("amount"));
				holder.setId(target.getLong("id"));
				holder.setLogo(target.getString("logo"));
				holder.setName(target.getString("name"));
				holder.setToco(target.getIntValue("toco"));
				holder.setType(target.getIntValue("type"));
				holder.setCapital(target.getString("capital"));
				holder.setCname(dto.getCname());
				list.add(holder);
			});
			holderRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getChangeInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.CHANGEINFO);
		dto.setParams(params);
		List<ChangeInfo> list = changeInfoRepository.findByCname(dto.getCname());
//		List<ChangeInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("result", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					changeInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("result");
		} catch (NullPointerException e) {
			log.debug("天眼查变更记录信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				ChangeInfo parseObject = JSONObject.parseObject(obj.toString(), ChangeInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			changeInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getInverst(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.INVERST);
		dto.setParams(params);
		List<Inverst> list = inverstRepository.findByCname(dto.getCname());
//		List<Inverst> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("result", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					inverstRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("result");
		} catch (NullPointerException e) {
			log.debug("天眼查对外投资信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Inverst parseObject = JSONObject.parseObject(obj.toString(), Inverst.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			inverstRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getBids(OpeneyesDTO dto) throws OpeneyesException {//从这里开始
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.BIDS);
		dto.setParams(params);
		List<Bids> list = bidsRepository.findByCname(dto.getCname());
//		List<Bids> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					bidsRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查招投标信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				JSONObject parseObject = JSONObject.parseObject(obj.toString());
				Bids bid = new Bids();
				bid.setCreationTime(System.currentTimeMillis());
				bid.setAbs(parseObject.getString("abs"));
				bid.setCname(dto.getCname());
				bid.setContent(parseObject.getString("content"));
				bid.setIntro(parseObject.getString("intro"));
				bid.setLink(parseObject.getString("link"));
				bid.setProxy(parseObject.getString("proxy"));
				bid.setPublishTime(parseObject.getLong("publishTime"));
				bid.setPurchaser(parseObject.getString("purchaser"));
				bid.setTitle(parseObject.getString("title"));
				bid.setUuid(parseObject.getString("uuid"));
				list.add(bid);
			});
			bidsRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getBond(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.BOND);
		dto.setParams(params);
		List<Bond> list = bondRepository.findByCname(dto.getCname());
//		List<Bond> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("bondList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					bondRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("bondList");
			if(jsonArr.size() == 0){
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			log.debug("天眼查债券信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		jsonArr.forEach(obj -> {
			Bond parseObject = JSONObject.parseObject(obj.toString(), Bond.class);
			parseObject.setCreationTime(System.currentTimeMillis());
			parseObject.setCname(dto.getCname());
			list.add(parseObject);
		});
		bondRepository.save(list);
		return openEyesTarget;
	}

	@Override
	public JSONObject getPurchaseland(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.PURCHASELAND);
		dto.setParams(params);
		List<Purchaseland> list = purchaselandRepository.findByCname(dto.getCname());
//		List<Purchaseland> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("companyPurchaseLandList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					purchaselandRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("companyPurchaseLandList");
			if(jsonArr.size() == 0){
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			log.debug("天眼查购地信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				JSONObject parseObject = JSONObject.parseObject(obj.toString());
				Purchaseland pl = new Purchaseland();
				pl.setCreationTime(System.currentTimeMillis());
				pl.setAdminRegion(parseObject.getString("adminRegion"));
				pl.setAssignee(parseObject.getString("assignee"));
				pl.setCname(dto.getCname());
				pl.setCreateTime(parseObject.getLong("createTime"));
				pl.setDealPrice(parseObject.getString("dealPrice"));
				pl.setElecSupervisorNo(parseObject.getString("elecSupervisorNo"));
				pl.setEndTime(parseObject.getLong("endTime"));
				pl.setId(parseObject.getLong("id"));
				pl.setLinkUrl(parseObject.getString("linkUrl"));
				pl.setLocation(parseObject.getString("location"));
				pl.setMaxVolume(parseObject.getString("maxVolume"));
				pl.setMinVolume(parseObject.getString("minVolume"));
				pl.setParentCompany(parseObject.getString("parentCompany"));
				pl.setPurpose(parseObject.getString("purpose"));
				pl.setSignedDate(parseObject.getLong("signedDate"));
				pl.setStartTime(parseObject.getLong("startTime"));
				pl.setSupplyWay(parseObject.getString("supplyWay"));
				pl.setTotalArea(parseObject.getString("totalArea"));
				pl.setUpdateTime(parseObject.getLong("updateTime"));
				pl.setCname(dto.getCname());
				list.add(pl);
			});
			purchaselandRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getEmployment(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.EMPLOYMENTS);
		dto.setParams(params);
		List<Employment> list = employmentRepository.findByCompanyName(dto.getCname());
//		List<Employment> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					employmentRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查招聘信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Employment parseObject = JSONObject.parseObject(obj.toString(), Employment.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				list.add(parseObject);
			});
			employmentRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getCheckInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.CHECKINFO);
		dto.setParams(params);
		List<CheckInfo> list = checkInfoRepository.findByCname(dto.getCname());
//		List<CheckInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					checkInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查抽查检查信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				CheckInfo parseObject = JSONObject.parseObject(obj.toString(), CheckInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			checkInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getAppbkInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.APPBKINFO);
		dto.setParams(params);
		List<AppbkInfo> list = appbkInfoRepository.findByCname(dto.getCname());
//		List<AppbkInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					appbkInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查产品信息查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				AppbkInfo parseObject = JSONObject.parseObject(obj.toString(), AppbkInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			appbkInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getLawsuit(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.LAWSUIT);
		dto.setParams(params);
		List<LawSuit> list = lawSuitRepository.findByCname(dto.getCname());
//		List<LawSuit> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					lawSuitRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查法律诉讼查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				LawSuit parseObject = JSONObject.parseObject(obj.toString(), LawSuit.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			lawSuitRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getGonggao(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.COURTANNOUNCEMENT);
		dto.setParams(params);
		List<CourtAnnouncement> list = courtAnnouncementRepository.findByCname(dto.getCname());
//		List<CourtAnnouncement> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			result.put("courtAnnouncements", list);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					courtAnnouncementRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONArray("courtAnnouncements");
		} catch (NullPointerException e) {
			log.debug("天眼查法院公告查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				CourtAnnouncement parseObject = JSONObject.parseObject(obj.toString(), CourtAnnouncement.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			courtAnnouncementRepository.save(list);
		}else{
			throw new OpeneyesException();
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getZhixingInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.ZHIXINGINFO);
		dto.setParams(params);
		List<ZhixingInfo> list = zhixingInfoRepository.findByCname(dto.getCname());
//		List<ZhixingInfo> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					zhixingInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (NullPointerException e) {
			log.debug("天眼查被执行人查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				ZhixingInfo parseObject = JSONObject.parseObject(obj.toString(), ZhixingInfo.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			zhixingInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getVolatility(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.VOLATILITY);
		dto.setParams(params);
		List<Volatility> list = volatilityRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			result.put("data", list.get(0));
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					volatilityRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONObject jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data");
			if("无数据".equals(jsonArr.getString("message"))){
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			log.debug("天眼查股票行情查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			Volatility parseObject = JSONObject.parseObject(jsonArr.toString(), Volatility.class);
			parseObject.setCreationTime(System.currentTimeMillis());
			parseObject.setCname(dto.getCname());
			String id = getGeneratedId(jsonArr);
			parseObject.setId(id);
			volatilityRepository.save(parseObject);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getCompanyInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.COMPANYINFO);
		dto.setParams(params);
		List<CompanyInfo> list = companyInfoRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("data", list.get(0));
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					companyInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONObject jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data");
			if("无数据".equals(jsonArr.getString("message"))){
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			log.debug("天眼查企业简介查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			CompanyInfo parseObject = JSONObject.parseObject(jsonArr.toJSONString(), CompanyInfo.class);
			parseObject.setCreationTime(System.currentTimeMillis());
			String id = getGeneratedId(jsonArr);
			parseObject.setId(id);
			companyInfoRepository.save(parseObject);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getSeniorExecutive(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.SENIOREXECUTIVE);
		dto.setParams(params);
		List<SeniorExecutive> list = seniorExecutiveRepository.findByCname(dto.getCname());
//		List<SeniorExecutive> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					seniorExecutiveRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查公司高管查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				SeniorExecutive parseObject = JSONObject.parseObject(obj.toString(), SeniorExecutive.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			seniorExecutiveRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getHoldingCompany(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.HOLDINGCOMPANY);
		dto.setParams(params);
		List<HoldingCompany> list = holdingCompanyRepository.findByCname(dto.getCname());
//		List<HoldingCompany> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					holdingCompanyRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查公司参股控股查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				HoldingCompany parseObject = JSONObject.parseObject(obj.toString(), HoldingCompany.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			holdingCompanyRepository.save(list);
		}
		return openEyesTarget;
	}

	
	@Override
	public JSONObject getCertificate(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.CERTIFICATE);
		dto.setParams(params);
		List<Certificate> list = certificateRepository.findByCname(dto.getCname());
//		List<Certificate> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("resultList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					certificateRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("resultList");
		} catch (NullPointerException e) {
			log.debug("天眼查公司参股控股查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Certificate parseObject = JSONObject.parseObject(obj.toString(), Certificate.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			certificateRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getAnnouncement(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.ANNOUNCEMENT);
		dto.setParams(params);
		List<Announcement> list = announcementRepository.findByCname(dto.getCname());
//		List<Announcement> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					announcementRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查上市公告（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Announcement parseObject = JSONObject.parseObject(obj.toString(), Announcement.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			announcementRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getShareholder(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("type", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.SHAREHOLDER);
		dto.setParams(params);
		List<ShareHolder> list = shareHolderRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("holderList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					shareHolderRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("holderList");
		} catch (NullPointerException e) {
			log.debug("天眼查⼗⼤股东（⼗⼤流通股东）（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				ShareHolder parseObject = JSONObject.parseObject(obj.toString(), ShareHolder.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			shareHolderRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getIssueRelated(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.ISSUERELATED);
		dto.setParams(params);
		List<IssueRelated> list = issueRelatedRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			result.put("data", list.get(0));
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					issueRelatedRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONObject jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data");
			if("无数据".equals(jsonArr.getString("message"))){
				throw new NullPointerException();
				
			}
		} catch (NullPointerException e) {
			log.debug("天眼查发⾏相关（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			IssueRelated parseObject = JSONObject.parseObject(jsonArr.toJSONString(), IssueRelated.class);
			parseObject.setCreationTime(System.currentTimeMillis());
			String id = getGeneratedId(parseObject);
			parseObject.setCname(dto.getCname());
			parseObject.setId(id);
			issueRelatedRepository.save(parseObject);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getShareStructure(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.SHARESTRUCTURE);
		dto.setParams(params);
		List<ShareStructure> list = shareStructureRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					shareStructureRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查发⾏相关（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				ShareStructure parseObject = JSONObject.parseObject(obj.toString(), ShareStructure.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			shareStructureRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getEquityChange(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.SHARESTRUCTURE);
		dto.setParams(params);
		List<EquityChange> list = equityChangeRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					equityChangeRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查【web版】股本变动（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				EquityChange parseObject = JSONObject.parseObject(obj.toString(), EquityChange.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			equityChangeRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getBonusInfo(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.BONUSINFO);
		dto.setParams(params);
		List<BonusInfo> list = bonusInfoRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					bonusInfoRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查【web版】分红情况（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				BonusInfo parseObject = JSONObject.parseObject(obj.toString(), BonusInfo.class);
				parseObject.setCname(dto.getCname());
				parseObject.setCreationTime(System.currentTimeMillis());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			bonusInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getAllotmen(OpeneyesDTO dto) throws OpeneyesException {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		params.put("pageSize", dto.getPageSize());
		dto.setSpec(KeyConstan.URL.ALLOTMEN);
		dto.setParams(params);
		List<Allotmen> list = allotmenRepository.findByCname(dto.getCname());
//		List<Allotmen> newList = list.stream().skip((dto.getPageNumber()-1) * dto.getPageSize()).limit(dto.getPageSize()).collect(Collectors.toList());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("dataList", list);
			result.put("data", inList);
			//当用户查询时，如果存储时间超过1个月就重新查询
			try {
				long time = System.currentTimeMillis() - list.get(0).getCreationTime();
				if(time >= DateUtil.FAILURE_TIME){
					allotmenRepository.delete(list);
				}
			} catch (Exception e) {
				log.info("删除过期数据出错:",e.getMessage());
			}
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams(), dto.getFrom(), dto.getUserId());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
		} catch (NullPointerException e) {
			log.debug("天眼查【web版】配股情况（股票）查询出现问题",e.getMessage());
			throw new OpeneyesException();
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Allotmen parseObject = JSONObject.parseObject(obj.toString(), Allotmen.class);
				parseObject.setCreationTime(System.currentTimeMillis());
				parseObject.setCname(dto.getCname());
				String id = getGeneratedId(obj);
				parseObject.setId(id);
				list.add(parseObject);
			});
			allotmenRepository.save(list);
		}
		return openEyesTarget;
	}
	
	
	

}

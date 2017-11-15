package com.huishu.ZSServer.service.openeyes.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.openeyes.Abnormal;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.entity.openeyes.Branch;
import com.huishu.ZSServer.entity.openeyes.CopyReg;
import com.huishu.ZSServer.entity.openeyes.Dishonest;
import com.huishu.ZSServer.entity.openeyes.HistoryRongZi;
import com.huishu.ZSServer.entity.openeyes.HumanRiskInfo;
import com.huishu.ZSServer.entity.openeyes.Icp;
import com.huishu.ZSServer.entity.openeyes.Illegalinfo;
import com.huishu.ZSServer.entity.openeyes.JingPin;
import com.huishu.ZSServer.entity.openeyes.News;
import com.huishu.ZSServer.entity.openeyes.OwnTax;
import com.huishu.ZSServer.entity.openeyes.Patents;
import com.huishu.ZSServer.entity.openeyes.ProductInfo;
import com.huishu.ZSServer.entity.openeyes.PunishmentInfo;
import com.huishu.ZSServer.entity.openeyes.RiskDetail;
import com.huishu.ZSServer.entity.openeyes.RiskInfo;
import com.huishu.ZSServer.entity.openeyes.ShangBiao;
import com.huishu.ZSServer.entity.openeyes.Staff;
import com.huishu.ZSServer.entity.openeyes.TaxCredit;
import com.huishu.ZSServer.entity.openeyes.TeamMember;
import com.huishu.ZSServer.entity.openeyes.TouZi;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.openeyes.AbnormalRepository;
import com.huishu.ZSServer.repository.openeyes.BaseInfoRepository;
import com.huishu.ZSServer.repository.openeyes.BranchRepository;
import com.huishu.ZSServer.repository.openeyes.CopyRegRepository;
import com.huishu.ZSServer.repository.openeyes.DishonestRepository;
import com.huishu.ZSServer.repository.openeyes.HistoryRongZiRepository;
import com.huishu.ZSServer.repository.openeyes.HumanRiskInfoRepository;
import com.huishu.ZSServer.repository.openeyes.IcpRepository;
import com.huishu.ZSServer.repository.openeyes.IllegalinfoRepository;
import com.huishu.ZSServer.repository.openeyes.JingPinRepository;
import com.huishu.ZSServer.repository.openeyes.NewsRepository;
import com.huishu.ZSServer.repository.openeyes.OwnTaxRepository;
import com.huishu.ZSServer.repository.openeyes.PatentsRepository;
import com.huishu.ZSServer.repository.openeyes.ProductInfoRepository;
import com.huishu.ZSServer.repository.openeyes.PunishmentInfoRepository;
import com.huishu.ZSServer.repository.openeyes.RiskDetailRepository;
import com.huishu.ZSServer.repository.openeyes.RiskInfoRepository;
import com.huishu.ZSServer.repository.openeyes.SearchRepository;
import com.huishu.ZSServer.repository.openeyes.ShangBiaoRepository;
import com.huishu.ZSServer.repository.openeyes.StaffRepository;
import com.huishu.ZSServer.repository.openeyes.TaxCreditRepository;
import com.huishu.ZSServer.repository.openeyes.TeamMemberRepository;
import com.huishu.ZSServer.repository.openeyes.TouZiRepository;
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

	@Override
	public JSONObject getStaffInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.STAFF);
		dto.setParams(params);
		List<Staff> list = staffRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONArray("result");
		} catch (Exception e) {
			log.debug("天眼查主要人员查询出现问题",e.getMessage());
		}
		List<Staff> newList = new ArrayList<>();
		jsonArray.forEach((obj) -> {
			Staff parseObject = JSONObject.parseObject(obj.toString(), Staff.class);
			parseObject.setCname(dto.getCname());
			newList.add(parseObject);
		});
		staffRepository.save(newList);
		return openEyesTarget;
	}

	@Override
	public JSONObject getBaseInfo(OpeneyesDTO dto) {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.BASEINFO);
		List<BaseInfo> list = baseInfoRepository.findByName(dto.getCname());
		if (list.size() > 0) {
			openEyesTarget.put("result", list);
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		BaseInfo parseObject = null;
		if(null != openEyesTarget){
			JSONObject jsonObj = openEyesTarget.getJSONObject("result");
			parseObject = JSONObject.parseObject(jsonObj.toString(), BaseInfo.class);
			list.add(parseObject);
			getJingPin(dto);//此处调用获取竞品信息的功能，存入数据库
			Company company = companyRepository.findByCompanyName(dto.getCname());
			if(null != company){
				company.setAddress(parseObject.getRegLocation());
				company.setBoss(parseObject.getLegalPersonName());
				company.setEngageState(parseObject.getRegStatus());
//			company.setFinancingAmount(parseObject.get);
//			company.setFinancingDate(financingDate);
				company.setOpenIndustry(parseObject.getIndustry());
//			company.setInvest(parseObject.get);
//			company.setInvestor(parseObject.get);
				company.setLogo(parseObject.getWebsiteList());
				company.setRegisterCapital(Double.valueOf(parseObject.getRegCapital().substring(0, parseObject.getRegCapital().indexOf("万"))));
//			company.setRegisterDate(parseObject.get);
//			company.setScale(scale);
				company.setOpenActualCapital(parseObject.getActualCapital());
				company.setOpenBase(parseObject.getBase());
				company.setOpenBusinessScope(parseObject.getBusinessScope());
				company.setOpenCategoryScore(parseObject.getCategoryScore());
				company.setOpenCompanyOrgType(parseObject.getCompanyOrgType());
				company.setOpenCorrectCompanyId(parseObject.getCorrectCompanyId());
				company.setOpenCreditCode(parseObject.getCreditCode());
				company.setOpenEstiblishTime(parseObject.getEstiblishTime());
				company.setOpenFromTime(parseObject.getFromTime());
				company.setOpenLegalPersonId(parseObject.getLegalPersonId());
				company.setOpenLegalPersonName(parseObject.getLegalPersonName());;
				company.setOpenOrgApprovedInstitute(parseObject.getOrgApprovedInstitute());
				company.setOpenOrgNumber(parseObject.getOrgNumber());
				company.setOpenPercentileScore(parseObject.getPercentileScore());
				company.setOpenPhoneNumber(parseObject.getPhoneNumber());
				company.setOpenRegInstitute(parseObject.getRegInstitute());
				company.setOpenRegNumber(parseObject.getRegNumber());
				company.setOpenToTime(parseObject.getToTime());
				company.setOpenType(parseObject.getType());
			}
			baseInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getBranch(OpeneyesDTO dto) {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.BRANCH);
		List<Branch> list = branchRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("result", list);
			openEyesTarget.put("data", inList);
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		if(null != openEyesTarget){
			JSONArray jsonArray = null;
			try {
				jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("result");
			} catch (Exception e) {
				log.debug("天眼查分支机构查询出现问题",e.getMessage());
			}
			if(jsonArray != null){
				jsonArray.forEach(obj -> {
					Branch parseObject = JSONObject.parseObject(obj.toString(), Branch.class);
					parseObject.setCompanyName(dto.getCname());
					list.add(parseObject);
				});
				branchRepository.save(list);
			}
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getHistoryRongZi(OpeneyesDTO dto) {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.HISTORYRONGZI);
		List<HistoryRongZi> list = historyRongZiRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			openEyesTarget.put("result", inList);
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		if(null != openEyesTarget){
			JSONArray jsonArray = null;
			try {
				jsonArray = openEyesTarget.getJSONObject("result").getJSONArray("items");
			} catch (Exception e) {
				log.debug("天眼查历史融资查询出现问题",e.getMessage());
			}
			if(jsonArray != null){
				jsonArray.forEach(obj -> {
					HistoryRongZi parseObject = JSONObject.parseObject(obj.toString(), HistoryRongZi.class);
					list.add(parseObject);
				});
				historyRongZiRepository.save(list);
			}
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTeamMember(OpeneyesDTO dto) {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.TEAMMEMBER);
		List<TeamMember> list = teamMemberRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			openEyesTarget.put("result", inList);
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查核心团队查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				TeamMember parseObject = JSONObject.parseObject(obj.toString(), TeamMember.class);
				list.add(parseObject);
			});
			teamMemberRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getProductInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.PRODUCTINFO);
		List<ProductInfo> list = productInfoRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查企业业务查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				ProductInfo parseObject = JSONObject.parseObject(obj.toString(), ProductInfo.class);
				list.add(parseObject);
			});
			productInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTouZi(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.TZANLI);
		List<TouZi> list = touZiRepository.findByCompany(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		} catch (Exception e) {
			log.debug("天眼查投资案例查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				TouZi parseObject = JSONObject.parseObject(obj.toString(), TouZi.class);
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			touZiRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getJingPin(OpeneyesDTO dto) {
		JSONObject openEyesTarget = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.JINGPIN);
		List<JingPin> list = jingPinRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("rows", list);
			JSONObject outList = new JSONObject();
			outList.put("page", inList);
			openEyesTarget.put("result", outList);
			return openEyesTarget;
		}
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		} catch (Exception e) {
			log.debug("天眼查竞品查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				JingPin parseObject = JSONObject.parseObject(obj.toString(), JingPin.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			jingPinRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getShangBiao(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.SHANGBIAO);
		List<ShangBiao> list = shangBiaoRepository.findByApplicantCn(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查商标查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				ShangBiao parseObject = JSONObject.parseObject(obj.toString(), ShangBiao.class);
				list.add(parseObject);
			});
			shangBiaoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getPatents(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.PATENTS);
		List<Patents> list = patentsRepository.findByApplicantName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查专利查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				Patents parseObject = JSONObject.parseObject(obj.toString(), Patents.class);
				list.add(parseObject);
			});
			patentsRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getCopyReg(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.COPYREG);
		List<CopyReg> list = copyRegRepository.findByAuthorNationality(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = null;
		try {
			jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查著作权查询出现问题",e.getMessage());
		}
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				CopyReg parseObject = JSONObject.parseObject(obj.toString(), CopyReg.class);
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			copyRegRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getIcp(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.ICP);
		dto.setParams(params);
		List<Icp> list = icpRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("data", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("data");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				Icp parseObject = JSONObject.parseObject(obj.toString(), Icp.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			icpRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTargetInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		List<Icp> list = icpRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("data");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				Icp parseObject = JSONObject.parseObject(obj.toString(), Icp.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			icpRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getCompanyInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setParams(params);
		dto.setSpec(KeyConstan.URL.ABNORMAL);
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray abnormal = openEyesTarget.getJSONObject("result").getJSONArray("items");
		dto.setSpec(KeyConstan.URL.XINGZHENGCHUFA);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray punishmentInfo = openEyesTarget.getJSONObject("result").getJSONArray("items");
		dto.setSpec(KeyConstan.URL.YANZHONGWEIFA);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray illegalinfo = openEyesTarget.getJSONObject("result").getJSONArray("items");
		dto.setSpec(KeyConstan.URL.QIANSHUIGONGGAO);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray ownTax = openEyesTarget.getJSONObject("result").getJSONArray("items");
		dto.setSpec(KeyConstan.URL.NEWS);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray news = openEyesTarget.getJSONArray("result");
		dto.setSpec(KeyConstan.URL.SHIXINREN);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray dishonest = openEyesTarget.getJSONObject("result").getJSONArray("items");
		dto.setSpec(KeyConstan.URL.QIYEFENGXIAN);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONObject riskInfo = openEyesTarget.getJSONObject("result");
		dto.setSpec(KeyConstan.URL.RENFENGXIAN);
		params.put("humanName", dto.getHumanName());
		dto.setParams(params);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONObject humanRiskInfo = openEyesTarget.getJSONObject("result");
		dto.setSpec(KeyConstan.URL.FENGXIANXINXI);
		openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray riskDetail = openEyesTarget.getJSONObject("result").getJSONArray("dataList");
		result.put("abnormal", abnormal);
		result.put("punishmentInfo", punishmentInfo);
		result.put("illegalinfo", illegalinfo);
		result.put("ownTax", ownTax);
		result.put("news", news);
		result.put("dishonest", dishonest);
		result.put("riskInfo", riskInfo);
		result.put("humanRiskInfo", humanRiskInfo);
		result.put("riskDetail", riskDetail);
		return openEyesTarget;
	}

	@Override
	public JSONObject getKeyWords(OpeneyesDTO dto) {
		Map<String, Object> params = new HashMap<>();
		params.put("word", dto.getWord());
		dto.setParams(params);
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = new JSONArray();
		try {
			jsonArray = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查关键字查询出现问题",e.getMessage());
		}
		log.info("查询到关键词的信息:"+jsonArray.toJSONString());
		return openEyesTarget;
	}

	@Override
	public JSONObject getAbnormal(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.ABNORMAL);
		dto.setParams(params);
		List<Abnormal> list = abnormalRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray abnormal = null;
		try {
			abnormal = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查经营异常查询出现问题",e.getMessage());
		}
		if(abnormal != null){
			abnormal.forEach(obj -> {
				Abnormal parseObject = JSONObject.parseObject(obj.toString(), Abnormal.class);
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
	public JSONObject getPunishmentInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.XINGZHENGCHUFA);
		dto.setParams(params);
		List<PunishmentInfo> list = punishmentInfoRepository.findByName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查行政处罚查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				PunishmentInfo parseObject = JSONObject.parseObject(obj.toString(), PunishmentInfo.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			punishmentInfoRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getIllegalinfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.YANZHONGWEIFA);
		dto.setParams(params);
		List<Illegalinfo> list = illegalinfoRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查严重违法查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Illegalinfo parseObject = JSONObject.parseObject(obj.toString(), Illegalinfo.class);
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
	public JSONObject getOwnTax(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.QIANSHUIGONGGAO);
		dto.setParams(params);
		List<OwnTax> list = ownTaxRepository.findByName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查欠税公告查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				OwnTax parseObject = JSONObject.parseObject(obj.toString(), OwnTax.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			ownTaxRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getNews(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.NEWS);
		dto.setParams(params);
		List<News> list = newsRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONArray("result");
		} catch (Exception e) {
			log.debug("天眼查新闻查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				News parseObject = JSONObject.parseObject(obj.toString(), News.class);
				String id = getGeneratedId(parseObject);
				parseObject.setId(id);
				list.add(parseObject);
			});
			newsRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getDishonest(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.SHIXINREN);
		dto.setParams(params);
		List<Dishonest> list = dishonestRepository.findByIname(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查失信人查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				Dishonest parseObject = JSONObject.parseObject(obj.toString(), Dishonest.class);
				list.add(parseObject);
			});
			dishonestRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getRiskInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.QIYEFENGXIAN);
		dto.setParams(params);
		List<RiskInfo> list = riskInfoRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray internalList = null;
		try {
			internalList = openEyesTarget.getJSONObject("result").getJSONArray("internalList");
		} catch (Exception e) {
			log.debug("天眼查企业风险查询出现问题",e.getMessage());
		}
		if(internalList != null){
			internalList.forEach(obj -> {
				RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
				parseObject.setRiskType("internalList");
				list.add(parseObject);
			});
		}
		JSONArray externalList = null;
		try {
			externalList = openEyesTarget.getJSONObject("result").getJSONArray("externalList");
		} catch (Exception e) {
			log.debug("天眼查企业风险查询出现问题",e.getMessage());
		}
		if(externalList != null){
			externalList.forEach(obj -> {
				RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
				parseObject.setRiskType("externalList");
				list.add(parseObject);
			});
		}
		riskInfoRepository.save(list);
		return openEyesTarget;
	}

	@Override
	public JSONObject getHumanRiskInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		dto.setSpec(KeyConstan.URL.RENFENGXIAN);
		dto.setParams(params);
		List<HumanRiskInfo> list = humanRiskInfoRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("externalList");
		} catch (Exception e) {
			log.debug("天眼查人风险查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				HumanRiskInfo parseObject = JSONObject.parseObject(obj.toString(), HumanRiskInfo.class);
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
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.FENGXIANXINXI);
		dto.setParams(params);
		List<RiskDetail> list = riskDetailRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("dataList");
		} catch (Exception e) {
			log.debug("天眼查风险信息查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				RiskDetail parseObject = JSONObject.parseObject(obj.toString(), RiskDetail.class);
				parseObject.setCompanyName(dto.getCname());
				list.add(parseObject);
			});
			riskDetailRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTaxCredit(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		dto.setSpec(KeyConstan.URL.SHUIWU);
		dto.setParams(params);
		List<TaxCredit> list = taxCreditRepository.findByName(dto.getCname());
		if (list.size() > 0) {
			JSONObject inList = new JSONObject();
			inList.put("items", list);
			result.put("result", inList);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArr = null;
		try {
			jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
		} catch (Exception e) {
			log.debug("天眼查税务评级查询出现问题",e.getMessage());
		}
		if(jsonArr != null){
			jsonArr.forEach(obj -> {
				TaxCredit parseObject = JSONObject.parseObject(obj.toString(), TaxCredit.class);
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
//		List<TaxCredit> list = searchRepository.findByName(dto.getCname());
//		if (list.size() > 0) {
//			JSONObject inList = new JSONObject();
//			inList.put("items", list);
//			result.put("result", inList);
//			return result;
//		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
//		JSONArray jsonArr = openEyesTarget.getJSONArray("data");
//		if(jsonArr != null){
//			jsonArr.forEach(obj -> {
//				Company parseObject = JSONObject.parseObject(obj.toString(), Company.class);
//				String id = getGeneratedId(obj);
//				parseObject.setId(id);
//				list.add(parseObject);
//			});
//			searchRepository.save(list);
//		}
		return openEyesTarget;
	}
	

}

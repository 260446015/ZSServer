package com.huishu.ZSServer.service.openeyes.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.entity.openeyes.Branch;
import com.huishu.ZSServer.entity.openeyes.CopyReg;
import com.huishu.ZSServer.entity.openeyes.HistoryRongZi;
import com.huishu.ZSServer.entity.openeyes.Icp;
import com.huishu.ZSServer.entity.openeyes.JingPin;
import com.huishu.ZSServer.entity.openeyes.Patents;
import com.huishu.ZSServer.entity.openeyes.ProductInfo;
import com.huishu.ZSServer.entity.openeyes.ShangBiao;
import com.huishu.ZSServer.entity.openeyes.Staff;
import com.huishu.ZSServer.entity.openeyes.TeamMember;
import com.huishu.ZSServer.entity.openeyes.TouZi;
import com.huishu.ZSServer.repository.openeyes.BaseInfoRepository;
import com.huishu.ZSServer.repository.openeyes.BranchRepository;
import com.huishu.ZSServer.repository.openeyes.CopyRegRepository;
import com.huishu.ZSServer.repository.openeyes.HistoryRongZiRepository;
import com.huishu.ZSServer.repository.openeyes.IcpRepository;
import com.huishu.ZSServer.repository.openeyes.JingPinRepository;
import com.huishu.ZSServer.repository.openeyes.PatentsRepository;
import com.huishu.ZSServer.repository.openeyes.ProductInfoRepository;
import com.huishu.ZSServer.repository.openeyes.ShangBiaoRepository;
import com.huishu.ZSServer.repository.openeyes.StaffRepository;
import com.huishu.ZSServer.repository.openeyes.TeamMemberRepository;
import com.huishu.ZSServer.repository.openeyes.TouZiRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

@Service
public class OpeneyesServiceImpl extends AbstractService implements OpeneyesService {

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

	@Override
	public JSONObject getStaffInfo(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		List<Staff> list = staffRepository.findByCname(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("result");
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
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		List<BaseInfo> list = baseInfoRepository.findByName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("result");
		jsonArray.forEach(obj -> {
			BaseInfo parseObject = JSONObject.parseObject(obj.toString(), BaseInfo.class);
			list.add(parseObject);
		});
		baseInfoRepository.save(list);
		return openEyesTarget;
	}

	@Override
	public JSONObject getBranch(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		List<Branch> list = branchRepository.findByName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONArray("result");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				Branch parseObject = JSONObject.parseObject(obj.toString(), BaseInfo.class);
				parseObject.setCname(dto.getCname());
				list.add(parseObject);
			});
			branchRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getHistoryRongZi(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		List<HistoryRongZi> list = historyRongZiRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				HistoryRongZi parseObject = JSONObject.parseObject(obj.toString(), HistoryRongZi.class);
				list.add(parseObject);
			});
			historyRongZiRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getTeamMember(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		List<TeamMember> list = teamMemberRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
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
		List<ProductInfo> list = productInfoRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
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
		List<TouZi> list = touZiRepository.findByCompany(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				TouZi parseObject = JSONObject.parseObject(obj.toString(), TouZi.class);
				list.add(parseObject);
			});
			touZiRepository.save(list);
		}
		return openEyesTarget;
	}

	@Override
	public JSONObject getJingPin(OpeneyesDTO dto) {
		JSONObject result = new JSONObject();
		Map<String, Object> params = new HashMap<>();
		params.put("name", dto.getCname());
		params.put("pageNum", dto.getPageNumber());
		List<JingPin> list = jingPinRepository.findByCompanyName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				JingPin parseObject = JSONObject.parseObject(obj.toString(), JingPin.class);
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
		List<ShangBiao> list = shangBiaoRepository.findByApplicantCn(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
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
		List<Patents> list = patentsRepository.findByApplicantName(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
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
		List<CopyReg> list = copyRegRepository.findByAuthorNationality(dto.getCname());
		if (list.size() > 0) {
			result.put("result", list);
			return result;
		}
		JSONObject openEyesTarget = getOpenEyesTarget(dto.getSpec(), dto.getParams());
		JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
		if(jsonArray != null){
			jsonArray.forEach(obj -> {
				CopyReg parseObject = JSONObject.parseObject(obj.toString(), CopyReg.class);
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
//		List<Icp> list = icpRepository.findByCompanyName(dto.getCname());
//		if (list.size() > 0) {
//			result.put("result", list);
//			return result;
//		}
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
	

}

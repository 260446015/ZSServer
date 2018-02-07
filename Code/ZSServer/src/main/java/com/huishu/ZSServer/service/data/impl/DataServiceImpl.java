//package com.huishu.ZSServer.service.data.impl;
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.forget.analysis.Analysis;
//import com.huishu.ZSServer.entity.Enterprise;
//import com.huishu.ZSServer.entity.IndusCompany;
//import com.huishu.ZSServer.entity.Institutional;
//import com.huishu.ZSServer.repository.company.EnterPriseRepository;
////import com.huishu.ZSServer.repository.company.IndusCompanyRepository;
//import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
//import com.huishu.ZSServer.service.data.DataService;
//
//import ch.qos.logback.core.joran.action.Action;
//
///**
// * @author hhy
// * @date 2017年11月15日
// * @Parem
// * @return
// * 
// */
//@Service
//public class DataServiceImpl implements DataService {
//	@Autowired
//	private InstitutionalRepostitory repository;
////	@Autowired
////	private IndusCompanyRepository rep;
//	@Autowired
//	private EnterPriseRepository ep;
//
//	/**
//	 * 转换机构库数据
//	 */
//	@Override
//	public Institutional transformData1(String value) {
//		String[] split = value.split("---");
//		Institutional info = new Institutional();
//		info.setLaboratoryName(split[0]);
//		info.setInstitutionalCategory(split[1]);
//		info.setIndustry(split[2]);
//		info.setArea(split[3]);
//		info.setSupportUnit(split[4]);
//		info.setAcademicLeader(split[5]);
//		info.setUrl(split[6]);
//		return info;
//	}
//
//	/**
//	 * 保存数据到数据库
//	 */
//	@Override
//	public boolean addData1(Institutional info) {
//		Institutional save = repository.save(info);
//		if (save != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 公司详情数据
//	 */
//	@Override
//	public JSONObject rederData(String value) {
//		String[] split = value.split("---");
//		JSONObject obj = new JSONObject();
//		obj.put("company", split[0]);
//		obj.put("boss", split[1]);
//		obj.put("money", split[2]);
//		obj.put("time", split[3]);
//		obj.put("address", split[4]);
//		obj.put("data", split[6]);
//		obj.put("name", split[10]);
//		obj.put("zero", split[7]);
//		obj.put("industry", split[8]);
//		obj.put("label", split[9]);
//		return obj;
//	}
//
//	@SuppressWarnings("null")
//	@Override
//	public boolean saveData(JSONObject obj) {
//		IndusCompany ind = new IndusCompany();
//		Enterprise enter = new Enterprise();
//		String company = obj.getString("company");
//		String boss = obj.getString("boss");
//		String money = obj.getString("money");
//		String time = obj.getString("time");// 注册时间
//		String address = obj.getString("address");
//		String name = obj.getString("name");// 全称
//		String zero = obj.getString("zero");// 所属产业
//		String industry = obj.getString("industry");
//		String label = obj.getString("label");
//		String data = obj.getString("data");// 上市公司
//
//		if (name.equals("暂无")) {
//			enter.setCompany(company);// 全称
//			ind.setCompany(company);
//			try {
//				JSONObject json = Analysis.getInitCompanyAbbr(company);
//				if (json.getBoolean("status")) {
//					Set<String> set = (Set<String>) json.get("result");
//					set.forEach(str -> {
//						int i = str.length();
//						switch (i) {
//						case 5:
//							ind.setCompanyName(str);
//							break;
//						case 4:
//							ind.setCompanyName(str);
//							break;
//						case 3:
//							ind.setCompanyName(str);
//							break;
//						case 2:
//							ind.setCompanyName(str);
//							break;
//						default:
//						}
//						
//					});
//
//				} else {
//					ind.setCompanyName("");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} // 分析算法取得简称
//
//		} else {
//			enter.setCompany(name);
//			ind.setCompany(name);// 全称
//			ind.setCompanyName(company);// 简称
//		}
////		IndusCompany indus = rep.findByCompanyName(company);
////		if (indus == null) {
////			indus = new IndusCompany();
////			String ss = ind.getCompany();
////			indus.setCompany(ss);
////			indus.setCompanyName(ind.getCompanyName());
////			indus.setIndustry(industry);
////			indus.setIndustryLabel(label);
////			indus.setInduszero(zero);
////		} else {
////			indus.setIndustryLabel(label);
////			indus.setInduszero(zero);
////		}
//		Enterprise save1=null;
//		Enterprise ent = ep.findByCompany(enter.getCompany());
//		if(ent==null){
//			ent = new Enterprise();
//			ent.setAddress(address);
//			ent.setCompany(enter.getCompany());
//			ent.setBoss(boss);
//			ent.setCompanyType(industry);
//			ent.setIndustry(industry);
//			ent.setPublicCompany(data);
//			ent.setRegisterCapital(money);
//			ent.setRegisterTime(time);
//			save1 = ep.save(ent);
//		}
////		IndusCompany save = rep.save(indus);
////		if(save!=null&&save1!=null){
////			return true;
////		}else{
////			return false;
////		}
//		return false;
//	}
//
//	/**
//	 * 对生物产业数据进行维护
//	 */
//	@Override
//	public JSONObject redData(String value) {
//		String[] split = value.split("---");
//		JSONObject obj = new JSONObject();
//		obj.put("company", split[0]);
//		obj.put("area", split[1]);
//		obj.put("sorce", split[2]);
//		obj.put("state", split[3]);
//		obj.put("boss", split[5]);
//		obj.put("money", split[6]);
//		obj.put("time", split[7]);
//		obj.put("rang", split[8]);
//		obj.put("address", split[9]);
//		obj.put("industry", split[10]);
//		return obj;
//	}
//	
//	@Override
//	public boolean updateData(JSONObject obj) {
//		Enterprise entity = new Enterprise();
//		IndusCompany indus = new  IndusCompany();
//		String company = obj.getString("company");
//		String area = obj.getString("area");
//		String sorce = obj.getString("sorce");
//		String state = obj.getString("state");
//		String boss = obj.getString("boss");
//		String money = obj.getString("money");
//		String time = obj.getString("time");
//		String rang = obj.getString("rang");
//		String address = obj.getString("address");
//		String industry = obj.getString("industry");
//		entity.setAddress(address);
//		entity.setArea(area);
//		entity.setBoss(boss);
//		entity.setCompany(company);
//		entity.setIndustry(industry);
//		String replace = sorce.replace("'", "");
//		System.out.println(replace);
//		entity.setScoring(replace);
//		entity.setIndustryType(industry);
//		entity.setEngageState(state);
//		entity.setRegisterCapital(money);
//		entity.setOperateScope(rang);
////		 time = time.replaceAll("/", "-");
////		 time= time+"00:00:00";
//		 entity.setRegisterTime(time);
//		 try {
//			
//			 Enterprise ent = ep.findByCompany(company);
//			 if(ent == null){
//				 ep.save(entity);
//			 }else{
//				 ent.setScoring(sorce);
//				 ent.setOperateScope(rang);
//				 ent.setEngageState(state);
//				 System.out.println(ent.toString());
//				 ep.save(ent);
//			 }
//			 return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//
//}

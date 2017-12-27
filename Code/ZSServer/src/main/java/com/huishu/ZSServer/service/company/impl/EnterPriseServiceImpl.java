package com.huishu.ZSServer.service.company.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.ConcersUtils.DateUtil;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.Enterprise;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.repository.company.EnterPriseRepository;
import com.huishu.ZSServer.repository.company.IndusCompanyRepository;
import com.huishu.ZSServer.repository.openeyes.BaseInfoRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.company.EnterPriseService;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 关于公司的相关操作
 */
@Service
public class EnterPriseServiceImpl extends AbstractService implements EnterPriseService {
	
	@Autowired
	private EnterPriseRepository rep;
	@Autowired
	private BaseInfoRepository repository;
	@Autowired
	private IndusCompanyRepository indrepository;
	@Autowired
	private CompanyRepository cpository;
	@Override
	public List<String> findCompanyName(String area, String industry) {
		int count = rep.getCount(area, industry);
		int i = (int)(Math.random()*count);
		while(i>43){
			i = (int)(Math.random()*count+1);
		}
		List<String> list = rep.getCompanyNameByIndustryAndArea(area, industry,i);
		return list;
	}

	/**
	 * 查看公司全部信息
	 */
	@Override
	public JSONObject getCompanyInfoByCompany(String company) {
		Enterprise enter = null;
		JSONObject obj = new JSONObject();
		try {
			enter = rep.findByCompany(company);
			if(enter != null){
				obj.put("boss",enter.getBoss());
				obj.put("name", enter.getCompany());
				obj.put("address", enter.getAddress());
				obj.put("state", enter.getEngageState());
				obj.put("time", enter.getRegisterTime());
				obj.put("money", enter.getRegisterCapital());
				obj.put("industry", enter.getIndustry());
			}else{
				BaseInfo list = repository.findByName(company);
				if (list != null) {
					BaseInfo baseInfo = list;
				}else{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", company);
					JSONObject obj1 = null;
					try {
						obj1 = getOpenEyesTarget(KeyConstan.URL.BASEINFO, map, KeyConstan.From.CUSTOM);
						
						JSONObject object = obj1.getJSONObject("result");
						if(object.isEmpty()){
							JSONObject object1 = obj1.getJSONObject("data");
							if(object1.isEmpty()){
								return null;
							}else{
								BaseInfo info = object.parseObject(object.toJSONString(), BaseInfo.class);
								BaseInfo save = repository.save(info);
								obj.put("boss",save.getLegalPersonName());
								obj.put("name", save.getName());
								obj.put("address", save.getRegLocation());
								obj.put("state", save.getRegStatus());
								obj.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date(save.getEstiblishTime())));
								obj.put("money",  save.getRegCapital());
								obj.put("industry", save.getIndustry());
							}
						}else{
							BaseInfo info = object.parseObject(object.toJSONString(), BaseInfo.class);
							BaseInfo save = repository.save(info);
							obj.put("boss",save.getLegalPersonName());
							obj.put("name", save.getName());
							obj.put("address", save.getRegLocation());
							obj.put("state", save.getRegStatus());
							obj.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date(save.getEstiblishTime())));
							obj.put("money",  save.getRegCapital());
							obj.put("industry", save.getIndustry());
						}
					
					} catch (Exception e) {
						return null;
					}
				}
				
			}
		} catch (Exception e) {
			return null;
		}
		
		return obj;
	}

	
	@Override
	public List<IndusCompany> getCompanyList(String industry, String[] moneys, String[] times, String area) {
		List<Enterprise> list = new ArrayList<>();
		if(times[0].equals("全部")){
			list = rep.findByIndustryLikeAndAreaLike(industry,area);
		}else{
			for (String time : times) {
				time = time.trim();
				JSONObject obj = initTime(time);
				list.addAll(rep.findByIndustryLikeAndAreaLikeAndRegisterTimeBetween(industry,area,obj.getString("startTime"),obj.getString("endTime")));
			}
		}
		List<IndusCompany> li = new ArrayList<IndusCompany>();
		List<Enterprise> List = new ArrayList<Enterprise>();
		if(list.size()>=10){
			int i = list.size();
			int si = updateData(i);
			for(int n = 0;i <= 9;i++){
				Enterprise enter = list.get(n+si);
				 List.add(enter);
			}
			if(moneys[0].equals("全部")){
				 List.forEach(action->{
					IndusCompany ind = indrepository.findByCompany(action.getCompany());
					if(ind==null){
						try {
							JSONObject json = Analysis.getInitCompanyAbbr(action.getCompany());
							if(json.getBoolean("status")){
								Set<String> set = (Set<String>) json.get("result");
								String ss = set.iterator().next();
								IndusCompany indus = new IndusCompany();
								indus.setCompany(action.getCompany());
								indus.setCompanyName(ss);
								indus.setIndustry(action.getIndustry());
								IndusCompany save = indrepository.save(indus);
								li.add(save);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						li.add(ind);
					}
				});
				return li;
			}else{
				for (String money : moneys) {
					money = money.trim();
					List<IndusCompany> el = new ArrayList<>();
					List<Integer> ll = StringUtil.initMoney(money);
					List<JSONObject>  companyName= getName( list,ll);
					if(companyName.size()<10){
						companyName.forEach(action->{
							IndusCompany ind = indrepository.findByCompany(action.getString("company"));
							if(ind==null){
								try {
									getCompanyName(el, action);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								el.add(ind);
							}
						});
					}else{
						int size = companyName.size();
						
						 si = updateData(size);
						List<JSONObject>  o= new ArrayList<JSONObject>() ;
						for(int j = 0; j<=9 ; j++){
							JSONObject obj = companyName.get(j+si);
							o.add(obj);
						}
						o.forEach(action->{
							IndusCompany ind = indrepository.findByCompany(action.getString("company"));
							if(ind==null){
								try {
									getCompanyName(el, action);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								el.add(ind);
							}
						});
					}
					li.addAll(el);
				}
				return li;
			}
		}else{
			return null;
		}
		
	}

	private void getCompanyName(List<IndusCompany> li, JSONObject action) throws Exception {
		JSONObject json = Analysis.getInitCompanyAbbr(action.getString("company"));
		if(json.getBoolean("status")){
			Set<String> set = (Set<String>) json.get("result");
			String ss = set.iterator().next();
			IndusCompany indus = new IndusCompany();
			indus.setCompany(action.getString("company"));
			indus.setCompanyName(ss);
			indus.setIndustry(action.getString("industry"));
			IndusCompany save = indrepository.save(indus);
			li.add(save);
		}
	}

	private int updateData(int size) {
		int si = (int)(Math.random()*size);
		while(si>(size-10)){
			si=(int)(Math.random()*size+1);
		}
		return si;
	}

	/**
	 * @param list
	 * @param ll
	 * @return
	 */
	private List<JSONObject> getName(List<Enterprise> list, List<Integer> ll) {
		List<JSONObject> l1 = new ArrayList<JSONObject>();
		
		if(ll.size()==1){
			list.forEach(action->{
				String reg = action.getRegisterCapital();
				double i = ll.get(0);
				if(reg.indexOf("万")>=0){
					 String[] split = reg.split("万");
					 String mm = split[0];
					 double i1 = Double.parseDouble(mm); 
					if(i1>=i){
						JSONObject obj = new JSONObject();
						obj.put("company", action.getCompany());
						obj.put("industry", action.getIndustry());
						l1.add(obj);
					}
				}
				
			});
		}else{
			list.forEach(action->{
				
				String reg = action.getRegisterCapital();
				double i = ll.get(0);
				double i1 = ll.get(1);
				if(reg.indexOf("万")>=0){
					 String[] split = reg.split("万");
					 String mm = split[0];
					 double i2 = Double.parseDouble(mm); 
					if(i<=i2&&i2<=i1){
						JSONObject obj = new JSONObject();
						obj.put("company", action.getCompany());
						obj.put("industry", action.getIndustry());
						l1.add(obj);
					}
				}
			});
		}
		return l1;
	}

	/**
	 * @param time
	 * @return
	 */
	private JSONObject initTime(String time) {
		String startTime = null;
		String endTime=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(time.equals("1-5年")){
			Calendar now = Calendar.getInstance(); 
			 now.add(Calendar.YEAR, -5); //现在时间的5年后
			Date t1 =  now.getTime();
			startTime=sdf.format(t1);
			now.add(Calendar.YEAR, +5);
			Date t2 =  now.getTime();
			endTime=sdf.format(t2);
		}else if(time.equals("5-10年")){
			Calendar now = Calendar.getInstance(); 
			now.add(Calendar.YEAR, -10); //现在时间的10年后
			Date t1 =  now.getTime();
			startTime=sdf.format(t1);
			now.add(Calendar.YEAR, +5);
			Date t2 =  now.getTime();
			endTime=sdf.format(t2);
		}else if(time.equals("10-20年")){
			Calendar now = Calendar.getInstance(); 
			now.add(Calendar.YEAR, -20); //现在时间的20年后
			Date t1 =  now.getTime();
			startTime=sdf.format(t1);
			now.add(Calendar.YEAR, +10);
			Date t2 =  now.getTime();
			endTime=sdf.format(t2);
		}else if(time.equals("20年以上")){
			Calendar now = Calendar.getInstance(); 
			now.add(Calendar.YEAR, -20); //现在时间的20年后
			Date t1 =  now.getTime();
			endTime=sdf.format(t1);
			startTime="1991-01-01";
		}
		JSONObject obj = new JSONObject();
		obj.put("startTime", startTime);
		obj.put("endTime", endTime);
		return obj;
	}
	public static void main(String[] args) throws Exception {
		String action ="贵州块数大数据有限公司";
		JSONObject json = Analysis.getInitCompanyAbbr(action);
		System.out.println(json);
	}
}

package com.huishu.ZSServer.service.company.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.Enterprise;
import com.huishu.ZSServer.entity.dto.IndusCompanyDTO;
import com.huishu.ZSServer.repository.company.EnterPriseRepository;
import com.huishu.ZSServer.repository.company.IndusCompanyDTORepository;
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
	private IndusCompanyDTORepository indrepository;
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
	public JSONObject getCompanyInfoByCompany(String companyName) {
		Enterprise enter = null;
		JSONObject obj = new JSONObject();
		try {
			enter = rep.findByCompanyName(companyName);
			if(enter != null){
				obj.put("boss",enter.getBoss());
				obj.put("name", enter.getCompany());
				obj.put("address", enter.getAddress());
				obj.put("state", enter.getEngageState());
				obj.put("time", enter.getRegisterTime());
				obj.put("money", enter.getRegisterCapital());
				obj.put("industry", enter.getIndustry());
			}
		} catch (Exception e) {
			return null;
		}
		
		return obj;
	}

	
	@Override
//	public List<IndusCompany> getCompanyList(String industry, String[] moneys, String[] times, String area) {
	public List<IndusCompanyDTO> getCompanyList(String industry, String[] moneys, String[] times, String area) {
		List<Enterprise> list = new ArrayList<>();
		//如果时间为空
		if(times[0].equals("全部")){
			list = rep.findByIndustryLikeAndAreaLike(industry,area);
		}else{
			for (String time : times) {
				time = time.trim();
				JSONObject obj = initTime(time);
				list.addAll(rep.findByIndustryAndAreaAndRegisterTimeBetween(industry,area,obj.getString("startTime"),obj.getString("endTime")));
			}
		}
		
//		List<IndusCompany> li = new ArrayList<IndusCompany>();
		List<IndusCompanyDTO> li = new ArrayList<IndusCompanyDTO>();
		List<Enterprise> List = new ArrayList<Enterprise>();
		if(list.size()>=20){
			int i = list.size();
			int si = updateData(i);
			for(int n = 0;i <= 9;i++){
				Enterprise enter = list.get(n+si);
				 List.add(enter);
			}
			if(moneys[0].equals("全部")){
				 List.forEach(action->{
					 //	IndusCompany ind = indrepository.findByCompany(action.getCompany());
					 IndusCompanyDTO ind = new IndusCompanyDTO();
					 ind.setCompany(action.getCompany());
					 ind.setCompanyName(action.getCompanyName());
					 ind.setCreateTime(action.getCreateTime());
					 ind.setIndustry(action.getIndustry());
					 ind.setIndustryLabel(action.getIndustryLabel());
					 ind.setInduszero(action.getIndustryZero());
					 ind.setUpdateTime(action.getUpdateTime());
					 li.add(ind);
					 
				});
				return li;
			}else{
				for (String money : moneys) {
					money = money.trim();
					List<IndusCompanyDTO> el = new ArrayList<IndusCompanyDTO>();
					List<Integer> ll = StringUtil.initMoney(money);
					List<JSONObject>  companyName= getName( list,ll);
					if(companyName.size()<10){
						companyName.forEach(action->{
							IndusCompanyDTO dto = new IndusCompanyDTO();
							dto.setCompany(action.getString("company"));
							dto.setCompanyName(action.getString("companyName"));
							dto.setCreateTime(action.getString("createTime"));
							dto.setIndustry(action.getString("industry"));
							dto.setIndustryLabel(action.getString("industryLabel"));
							dto.setInduszero(action.getString("industryZero"));
							dto.setUpdateTime(action.getString("updateTime"));
							el.add(dto);
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
							IndusCompanyDTO dto = new IndusCompanyDTO();
							dto.setCompany(action.getString("company"));
							dto.setCompanyName(action.getString("companyName"));
							dto.setCreateTime(action.getString("createTime"));
							dto.setIndustry(action.getString("industry"));
							dto.setIndustryLabel(action.getString("industryLabel"));
							dto.setInduszero(action.getString("industryZero"));
							dto.setUpdateTime(action.getString("updateTime"));
							el.add(dto);
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

	/*private void getCompanyName(List<IndusCompany> li, JSONObject action) throws Exception {
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
	}*/

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
						obj.put("companyName", action.getCompanyName());
						obj.put("industryLabel", action.getIndustryLabel());
						obj.put("industryZero", action.getIndustryZero());
						obj.put("createTime", action.getCreateTime());
						obj.put("updateTime", action.getUpdateTime());
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
						obj.put("companyName", action.getCompanyName());
						obj.put("industryLabel", action.getIndustryLabel());
						obj.put("industryZero", action.getIndustryZero());
						obj.put("createTime", action.getCreateTime());
						obj.put("updateTime", action.getUpdateTime());
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

	/**
	 * 多选操作查询公司详情
	 */
	@Override
//	public List<IndusCompany> findCompanyList(String industry, String area, String[] money, String[] time) {
	public List<IndusCompanyDTO> findCompanyList(String industry, String area, String[] money, String[] time) {
		List<Enterprise> list = new ArrayList<Enterprise>();
		List<IndusCompanyDTO> li = new ArrayList<IndusCompanyDTO>();
		//第一步，根据筛选条件查询出数据
		if(StringUtil.isEmpty(industry)&&StringUtil.isEmpty(area)){
			//产业地域都为空
			if(time[0].equals("全部")){
				list = (List<Enterprise>) rep.findAll();
			}else{
				for(String tim:time){
					tim = tim.trim();
					JSONObject obj = initTime(tim);
					list.addAll(rep.findByRegisterTimeBetween(obj.getString("startTime"),obj.getString("endTime")));
				}
			}
		}else if(StringUtil.isEmpty(industry)&&StringUtil.isNotEmpty(area)){
			//产业为空，地域不为空
			if( time[0].equals("全部") ){
				list = rep.findByArea(area);
			}else{
				for(String tim:time){
					tim = tim.trim();
					JSONObject obj = initTime(tim);
					list.addAll(rep.findByAreaAndRegisterTimeBetween(area,obj.getString("startTime"),obj.getString("endTime")));
				}
			}
		}else if(StringUtil.isEmpty(area)&&StringUtil.isNotEmpty(industry)){
			//地域为空，产业不为空
			if( time[0].equals("全部") ){
				list = rep.findByIndustry(industry);
			}else{
				for(String tim:time){
					tim = tim.trim();
					JSONObject obj = initTime(tim);
					list.addAll(rep.findByIndustryAndRegisterTimeBetween(industry,obj.getString("startTime"),obj.getString("endTime")));
				}
			}
		}else{
			//产业不为空,地域不为空
			if( time[0].equals("全部") ){
				list = rep.findByIndustryAndArea(industry,area);
			}else{
				for(String tim:time){
					tim = tim.trim();
					JSONObject obj = initTime(tim);
					list.addAll(rep.findByIndustryAndAreaAndRegisterTimeBetween(industry,area,obj.getString("startTime"),obj.getString("endTime")));
				}
			}
		}
		//第二步,根据查询到的数据判断他的注册资本
		if(money[0].equals("全部")){
			//取到所得数据的前十条数据
			if(list.size()<10){
				return null;
			}else{
				if(list.size()<=20){
					list.forEach(action->{
						IndusCompanyDTO dto = new IndusCompanyDTO();
						dto.setCompany(action.getCompany());
						dto.setCompanyName(action.getCompanyName());
						dto.setCreateTime(action.getCreateTime());
						dto.setIndustry(action.getIndustry());
						dto.setIndustryLabel(action.getIndustryLabel());
						dto.setInduszero(action.getIndustryZero());
						dto.setUpdateTime(action.getUpdateTime());
						li.add(dto);
					});
				}else{
					
					while(li.size()<10){
						Enterprise action =null;
						int si = (int)(Math.random()*list.size()+1);
						if(si==list.size()){
							
							action = list.get(si-1);
						}else{
							action = list.get(si);
						}
						IndusCompanyDTO dto = new IndusCompanyDTO();
						dto.setCompany(action.getCompany());
						dto.setCompanyName(action.getCompanyName());
						dto.setCreateTime(action.getCreateTime());
						dto.setIndustry(action.getIndustry());
						dto.setIndustryLabel(action.getIndustryLabel());
						dto.setInduszero(action.getIndustryZero());
						dto.setUpdateTime(action.getUpdateTime());
						li.add(dto);
					}
				}
			}
		}else{
			for( String mon : money ){
				mon = mon.trim();
				List<Integer> ll = StringUtil.initMoney(mon);
				List<JSONObject> name = getName(list, ll);
				name.forEach(act->{
					if(li.size()<=10){
						IndusCompanyDTO dto = new IndusCompanyDTO();
						dto.setCompany(act.getString("company"));
						dto.setCompanyName(act.getString("companyName"));
						dto.setCreateTime(act.getString("createTime"));
						dto.setIndustry(act.getString("industry"));
						dto.setIndustryLabel(act.getString("industryLabel"));
						dto.setInduszero(act.getString("industryZero"));
						dto.setUpdateTime(act.getString("updateTime"));
						li.add(dto);
					}else{
						return;
					}
				});
			}
		}
			return li;
		
		
	}

	/**
	 * 根据产业获取地域信息
	 */
	@Override
	public List<String> getAareaByIndustry(String industry) {
		List<String> list =null;
		if(industry.equals("全部")){
			list = rep.getArea();
		}else{
			list= rep.getAreaByIndustry(industry);
		}
		for(int i=0;i<list.size();i++){
			String area = list.get(i);
			if(StringUtil.isEmpty(area)){
				list.remove(i);
			}
		}
		return list;
	}
}

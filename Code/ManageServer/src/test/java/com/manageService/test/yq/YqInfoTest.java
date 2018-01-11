package com.manageService.test.yq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanlp.Keyword;
import com.huishu.ManageServer.app.Application;
import com.huishu.ManageServer.common.util.ReadExcelUtil;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dbSecond.KeyInfoEntity;
import com.huishu.ManageServer.entity.dbSecond.YQInfoEntity;
import com.huishu.ManageServer.service.second.company.CompanyService;
import com.huishu.ManageServer.service.second.keyword.KeyInfoService;
import com.huishu.ManageServer.service.second.yq.YQInfoService;


/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 测试数据
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class YqInfoTest {
	
	@Autowired
	private CompanyService cservice;
	
	@Autowired
	private YQInfoService yservice;
	
	@Autowired
	private KeyInfoService kservice;
	
	//E:/慧数平台/招商平台/精筛产业关键词.xlsx
	@SuppressWarnings("unused")
	@Test
	public void getInfo(){
		//第一步获取公司名称
		System.out.println("===================测试开始===================");
		List<CompanyEntity> list = cservice.findAllCompany();
		JSONArray array = new JSONArray();
		//获取关键词集合
		List<String> keyWord2 = kservice.getKeyWord();
		//获取所有的结果
		list.forEach(act->{
			//文章集合
			List<String> li = new ArrayList<String>();
			//关键词集合
			List<String> ll = new ArrayList<String>();
			JSONObject obj = new JSONObject();
			//第二步，根据公司名称获取舆情的相关文章内容
			 List<YQInfoEntity> info = yservice.getInfoBykid(act.getId());
			 if(info.size()==0){
				 
			 }else{
				 info.forEach( action -> {
					String replaceHtml = StringUtil.replaceHtml(action.getContent());
					String replace = StringUtil.replaceHtml(action.getTitle());
				 //获取文章集合元素
				 li.add(replace);
				 li.add(replaceHtml);
				 //获取关键词集合
				 if(ll.size()==0){
					 String keywords = action.getKeywords();
					 String[] split = keywords.split("\\+");
					 ll.add(split[0]);
					 ll.add(split[1]);
				 }
				 //获取主体姓名信息
				JSONArray asList = StringUtil.replaceString(action.getName());
				 //获取主体地域信息
				JSONArray asList2 = StringUtil.replaceString( action.getArea());
				 //获取主体公司信息
				JSONArray asList3 = StringUtil.replaceString( action.getOrganization());
				if(obj.isEmpty()){
					obj.put("主体姓名", asList); 
					obj.put("主体地域", asList2); 
					obj.put("主体公司", asList3); 
				}else{
					updateData(obj,asList,"主体姓名");
					updateData(obj,asList2,"主体地域");
					updateData(obj,asList3,"主体公司");
				}
			 });
			//第三步根据取得的数据获取关键词入库。
			
			Map<String, Integer> keyword = Keyword.keyword(li, 50, keyWord2);
			System.out.println(li.size());
			JSONArray arr = new JSONArray();
			/*for(String entry:keyword.keySet()){
				arr.add(entry);
			}*/
			int sum = 0;
			for( Entry<String,Integer> entry: keyword.entrySet()){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("key", entry.getKey());
				jsonObject.put("value",entry.getValue() );
				sum +=entry.getValue();
				arr.add(jsonObject);
			}
			double sum1 = 0.0;//人工智能
			double sum2 = 0.0;//物联网
			double sum3 = 0.0;//大数据
			double sum4 = 0.0 ;//生物技术
			for(int i =0; i<arr.size();i++){
				JSONObject jsonObject = arr.getJSONObject(i);
				Integer integer = jsonObject.getInteger("value");
				double d =(integer*1.0)/sum;
				//获取关键词的所占比
				jsonObject.put("value", d);
				//获取当前关键词
				String keyname = jsonObject.getString("key");
				//根据关键词得到产业信息
			   List<KeyInfoEntity> list1 =kservice.findInfoByKeyName(keyname);
			   System.out.println(list1.size());
			   //获取每个公司的产业百分比
			   if(list1.size() != 0){
					for(int j = 0;j<list1.size();j++){
						KeyInfoEntity ent = list1.get(j);
						String industry = ent.getIndustry();
						if(industry.equals("人工智能")){
							sum1 += d;
						}else if(industry.equals("物联网")){
							sum2 +=d;
						}else if(industry.equals("大数据")){
							sum3 +=d;
						}else{
							sum4 += d;
						}
					}
					
					obj.put("人工智能所占比", sum1);
					obj.put("物联网所占比", sum2);
					obj.put("大数据所占比", sum3);
					obj.put("生物技术所占比", sum4);
				}
			}
			
			//获取关键词集合
			obj.put("关键词",arr);
			
			//获取公司名称
			obj.put("企业名称", act.getKeyword());
			obj.put("相关关键词", ll.toString());
			array.add(obj);
			 }
		});
		//进行存库处理
		ReadExcelUtil.saveKeyWord(array,"E:/慧数平台/招商平台/关键词云提取/产业标签关键词.xls");
		System.out.println("===================测试结束===================");
	}
	/**
	 * @param obj
	 * @param asList
	 */
	private void updateData(JSONObject obj, JSONArray asList,String string) {
		JSONArray json = obj.getJSONArray(string);
		obj.remove(string);
		asList.forEach(action->{
			String str = action.toString();
			if(!json.contains(str)){
				if(json.size()<32700){
					json.add(str);
				}
				
			}
		});
		System.out.println(json.size());
		obj.put(string, json);
	}
	/*public static void main(String[] args) {
		System.out.println("==========测试开始===========");
		List<String> li = new ArrayList<String>();
		li.add("北京市");
		li.add("天津市");
		li.add("江苏省");
		String string = li.toString();
		System.out.println(string);
		JSONArray arr = JSONArray.parseArray(string);
		System.out.println(arr.toString());
		JSONArray ll = StringUtil.replaceString(string);
		System.out.println(ll.toJSONString());
		System.out.println("==========测试结束==========");
	}*/
}

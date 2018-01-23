package com.huishu.aitanalysis.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.category.CategoryModel;
import com.forget.findAddress.FindAddress;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.common.Constants.Info;

/**
 * @author hhy
 * @date 2017年8月23日
 * @Parem
 * @return 验证json的内容
 */

public abstract class Util {

	public static String getIndustry(JSONObject json, String industryInfo) {
		JSONArray jrr = json.getJSONArray(industryInfo);
		if (jrr != null && !jrr.isEmpty()) {
			return getIndustryByIndestryLeabl(jrr);
		}else{
			return "";
		}
	}
	
	public static String getIndustryLeabl(JSONObject json, String industryInfo) {
		
		JSONArray jrr = json.getJSONArray(industryInfo);
		if (jrr != null && !jrr.isEmpty()) {
				return jrr.getJSONObject(0).getString("Name");
			
		}else{
			return "";
		}
	}
	
	public static String getIndustryInfo(JSONObject json ){
		if(json.getString("$产业资讯") != null){
			return "$产业资讯";
		} else if(json.getString("$产业政策")!= null){
			return "$产业政策";
		} else if(json.getString("$招商政策")!= null){
			return json.getString("$招商政策");
		}else if(json.getString("$行业峰会")!= null){
			return "$行业峰会";
		}else if(json.getString("$科学研究")!= null){
			return "$科学研究";
		} else if(json.getString("$企业排行")!= null){
			return  "$企业排行";
		} 
		else if(json.getString("$专家观点")!= null){
			return  "$专家观点";
		} 
		else if(json.getString("$园区政策")!= null){
			return  "$园区政策";
		} 
		else if(json.getString("$雄安政策")!= null){
			return  "$雄安政策";
		} 
		{
			return ""; 
		}
	}
	//获取地域的方法
	public static Set<String>  getArea(String title ,String content){
		Set<String> findAddress = FindAddress.findAddress(title + ";" + content);
		return findAddress;
	}
	
	
	//获取公司的方法
	@SuppressWarnings("unchecked")
	public static List<String>   getBusiness(String title ,String content){

				// JSONObject findCompany = Analysis.findCompany(title, content);
				JSONObject findCompany = null;
				try {
					findCompany = Analysis.getCompany(title, content);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<String> set = new ArrayList<String>();
				if (findCompany != null && findCompany.getBooleanValue("status")) {
					Map<String,CategoryModel> finder = (Map<String, CategoryModel>) findCompany.get("result");
					
					for(Map.Entry<String, CategoryModel>  entry: finder.entrySet()){
						
//						LOGGER.info("获取的公司名称为：" + entry.getKey());
//						LOGGER.info("对应情感为 ：" + entry.getValue().getCategory());
						set.add(entry.getKey());
					}
//					List<String> set = (List<String>) findCompany.get("result");
					return set;
				}
				return null;
			
	}
		
	//
	public static String getDimension (JSONObject json){
		if(json.getString("$产业资讯")!= null){
			return "产业头条";
		}else if(json.getString("$产业政策")!= null){
			return "政策解读";
		}else if(json.getString("$招商政策")!=null){
			return "政策解读";
		}else if(json.getString("$行业峰会")!= null){
			return "高峰论坛";
		}else if(json.getString("$科学研究")!= null){
			return "科学研究";
		}else if(json.getString("$企业排行")!= null){
			return "企业排行";
		}else if(json.getString("$专家观点")!= null){
			return  "百家论";
		} 
		else if(json.getString("$园区政策")!= null){
			return  "园区政策";
		} else if(json.getString("$园区动态")!= null){
			return "园区动态";
		}
		else if(json.getString("$雄安政策")!= null){
			return  "$雄安政策";
		} else if(json.getString("$融资快讯")!= null){
			return "融资快讯";
		} else {
			return "";
		}
		
	}
	public static String getArea(JSONObject json) {
		JSONArray jrr = json.getJSONArray("$地域");
		if (jrr != null && !jrr.isEmpty()) {
			return jrr.getJSONObject(0).getString("Name");
		}
		return "";
	}
	public static String getKeyWord(JSONObject json) {
		String jrr = json.getString("$关键词");
		return jrr == null ? "" : jrr;
	}

	public static String getSite(JSONObject json) {
		String jrr = json.getString("网站名称");
		return jrr == null ? "" : jrr;
	}

	public static String getVector(JSONObject json) {
		JSONArray jrr = json.getJSONArray("$载体");
		if (jrr != null && !jrr.isEmpty()) {
			return jrr.getJSONObject(0).getString("Name");
		}
		return "";
	}
	
	public static String getConfigName(JSONObject json) {
		String jrr = json.getString("$配置名称");
		return jrr == null ? "" : jrr;
	}
	public static String getBaseInfo(JSONObject json) {
		String jrr = json.getString("详情");
		return jrr == null ? "" : jrr;
	}
	//融资方
	public static String getFinanciers(JSONObject json) {
		String jrr = json.getString("融资方");
		return jrr == null ? "" : jrr;
	}
	//轮次
	public static String getInvest(JSONObject json) {
		String jrr = json.getString("轮次");
		return jrr == null ? "" : jrr;
	}
	//融资额
	public static String getFinancingAmount(JSONObject json) {
		String jrr = json.getString("融资额");
		return jrr == null ? "" : jrr;
	}
	//所属产业
	public static String getIndustry(JSONObject json) {
		String jrr = json.getString("所属产业");
		if(StringUtil.isEmpty(jrr)){
			jrr = json.getString("所属行业");
		}
		return jrr == null ? "" : jrr;
	}
	//投资方
	public static String getInvestor(JSONObject json) {
		String jrr = json.getString("投资方");
		return jrr == null ? "" : jrr;
	}
	//所属公司
	public static String getFinancingCompany(JSONObject json) {
		String jrr = json.getString("所属公司");
		return jrr == null ? "" : jrr;
	}
	//融资时间
	public static String getFinancingDate(JSONObject json) {
		String jrr = json.getString("时间");
		if(StringUtil.isNotEmpty(jrr)){
			jrr = jrr.replace(".", "-");
			jrr.replace("年", "-");
			jrr.replace("月", "-");
			jrr.replace("日", "");
		}
		
		 return jrr == null ? "" : jrr;
	}
	public static String getArticleLink(JSONObject json) {
		String jrr = json.getString("$原文原始网址");
		return jrr == null ? "" : jrr;
	}

	public static String getAdress(JSONObject json) {
		String jrr = json.getString("举办地点");
		return jrr == null ? "" : jrr;
	}
	//会展时间
	public static String getExhibitiontime(JSONObject json) {
		String jrr = json.getString("会展时间");
		if(StringUtil.isEmpty(jrr)){
			String str = json.getString("展会时间");
			return str == null ?"":str;
		}else{
			return jrr;
		}
//		return jrr == null ? "" : jrr;
	}
	public static String getBus(JSONObject json) {
		String jrr = json.getString("涉及行业");
		return jrr == null ? "" : jrr;
	}
	public static String getTitle(JSONObject json) {
		String jrr = json.getString("标题");
		return jrr == null ? "" : jrr;
	}

	public static String getContent(JSONObject json) {
		String jrr = json.getString("内容");
		jrr = jrr.replaceAll("\\n", "<br/>");
		return jrr == null ? "" : jrr;
	}

	public static String getAuthor(JSONObject json) {
		String jrr = json.getString("作者");
		return jrr == null ? "" : jrr;
	}

	public static String getSummary(JSONObject json) {
		String jrr = json.getString("摘要");
		return jrr == null ? "" : jrr;
	}

	public static String getSource(JSONObject json) {
		String jrr = json.getString("来源");
		return jrr == null ? "" : jrr;
	}
	public static String getLogo(JSONObject json) {
		String jrr = json.getString("logo");
		return jrr == null ? "" : jrr;
	}

	/** yyyy-mm-dd HH:MM:ss */
	public static String getPublishDate(JSONObject json) {
		String datetime = json.getString("发布时间");
		if (datetime == null) {
			return "";
		}
		datetime = datetime.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", " ");
		if (datetime.length() == 10) {
			datetime += "00:00:00";
		}else if(datetime.length() == 16) {
			datetime += ":00";
		}
		return datetime;
	}
	/** yyyy-mm-dd HH:MM:ss */
	public static String getGatherTime(JSONObject json) {
		String datetime = json.getString("$采集时间");
		if (datetime == null) {
			return "";
		}
		datetime = datetime.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
		if (datetime.length() == 10) {
			datetime += " 00:00:00";
		}else if(datetime.length() == 16) {
			datetime += ":00";
		}
		return datetime;
	}
	
	public static String getPublishTime(String datetime) {
		if (datetime == null) {
			return "";
		}
		return datetime.split(" ")[0];
	}

	public static Long getHitCount(JSONObject oss) {
		String str = String.valueOf(oss.get("点击数")).replace("次", "");
		return str.equals("null") || "".equals(str.trim()) ? 0 : Long.valueOf(str);
	}

	public static Long getReplyCount(JSONObject oss) {
		String str = String.valueOf(oss.get("评论数")).replace("次", "");
		return str.equals("null") || "".equals(str.trim()) ? 0 : Long.valueOf(str);
	}

	public static Long getSupportCount(JSONObject oss) {
		String str = String.valueOf(oss.get("评论数")).replace("次", "");
		return str.equals("null") || "".equals(str.trim()) ? 0 : Long.valueOf(str);
	}
	/**年份*/
	public static String getPublishYear(String datetime) {
		if (datetime == null) {
			return "";
		}
		String[] arr = datetime.split(" ");
		if (arr.length == 2) {
			return arr[0].split("-")[0];
		}
		return "";
	}
	public static String getIndustryByIndestryLeabl(JSONArray jrr){
		String industry = jrr.getJSONObject(0).getString("Name");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("互联网+", Info.HL);
		map.put("高科技", Info.GK);
		map.put("文化创意", Info.WH);
		map.put("精英配套", Info.JP);
		map.put("滨海旅游", Info.BW);
		map.put("港口物流", Info.GW);
		String  css = getLeabl(map,industry);
		return css;
	}
	
	
	/**
	 * @param map
	 * @param industry
	 * @return
	 */
	private static String getLeabl(Map<String, Object> map, String industry) {
		String str = "";
		for(String key:map.keySet()){
			String value = map.get(key).toString();
			String[] split = value.split(",");
			for (String string : split) {
				if(string.equals(industry)){
					return key;
				}
			}
			
		}
		return str;
	}
	public static String getBusiness1(List<String> str){
		String ss = "";
		for( int i=0;i<str.size();i++){
			ss = str.get(i)+",";
		}
		return ss;
	}
	public static void main(String[] args) {
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("Name","人工智能");
		json.add(obj);
		String sssss = getIndustryByIndestryLeabl(json);
		System.out.println(sssss);
		/*String str = "2017-11-23 15:32:00";
		String publishTime = getPublishTime(str);
		System.out.println(publishTime);*/
		
	}
	public static String getLamu(JSONObject jsonObject){
		
		String jrr =jsonObject.getString("$栏目");
		return jrr == null ? "" : jrr;
	}

	public static String getAreaBy(JSONObject jsonObject) {
		String jrr = jsonObject.getString("所在区域");
		return jrr == null ? "" : jrr;
	}
	
	public static Long getSiteId(JSONObject jsonObject) {
		String jrr = jsonObject.getString("$配置ID");
		long lo = Long.parseLong(jrr);
		return lo;
	}
	public static Long getDimensionId(JSONObject jsonObject,String str){
		JSONArray arr = jsonObject.getJSONArray(str);
		if( arr  != null && ! arr.isEmpty()){
			return arr.getJSONObject(0).getLong("ID");
		}
		return (long) 0;
	}

	public static String getIndustry1(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}
	}

package com.huishu.ManageServer.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.category.CategoryModel;

/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 关于分析的所有方法
 */
public class AnalysisUtil {
	
	
	/**
	 * 根据企业文章集合给企业打产业标签
	 * @param list  文章集合
	 * @param industryColl 产业关键词集合
	 * @return
	 */
	public static String getindustry(List<String> list, Map<String,Set<String>> industryColl){
		JSONObject obj = null;
		String result ="";
		try {
			obj = Analysis.industryChain(list, industryColl);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(obj.getBoolean("status")){
			 result = obj.getString("result");
		}
		return result;
	}
	
	/**
	 * 获取文章中提及的企业名称及情感对应,地域信息
	 * @param title 文章标题
	 * @param content 内容
	 */
	@SuppressWarnings("unchecked")
	public static void getAreaAndIndustry(String title,String content){
		JSONObject obj = null;
		try {
			 obj= Analysis.getCompany(title, content);
		} catch (Exception e) {
			e.printStackTrace();
//			retur null;
		}
		//企业以及情感
		if(obj.getBoolean("status")){
			Map<String,CategoryModel> find = (Map<String,CategoryModel>)obj.get("result");
			for(Map.Entry<String, CategoryModel> entry : find.entrySet()){
				System.out.println("企业名称："+entry.getKey());
				System.out.println("对应情感："+entry.getValue().getCategory());
			}
			
		}
		//省份
		Set<String> set = (Set<String>)obj.get("address");
		System.out.println("省份名称："+set);
	}
	/**
	 * 更新丰富企业库(生成简称，用于企业提及判断) 
	 * @param company 企业全称
	 * @return
	 */
	public static boolean  updateCompany(String company){
		JSONObject obj = null;
		try {
			obj = Analysis.getInitCompanyAbbr(company);
			if(obj.getBoolean("status")){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 获取企业全称对应简称
	 * @param company 企业全称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getCompanyInfo(String company){
		JSONObject obj = null;
		try {
			obj = Analysis.getInitCompanyAbbr(company);
			if(obj.getBoolean("status")){
				JSONObject json = (JSONObject) obj.get("result");
				return json;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 提取内容中是否出现关键词及出现的关键词列表
	 * @param content 文章内容+标题
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> getKeyWord(String content){
		JSONObject obj = null;
		try {
			obj = Analysis.findKeyword(content);
			if(obj.getBoolean("status")){
				Set<String> set = (Set<String>)obj.get("result");
				return set;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 丰富关键词
	 * @param keywordColl 关键词集合
	 * @return
	 */
	public static boolean addKeyWord(Set<String> keywordColl){
		boolean info = false;
		try {
			 info = Analysis.addKeyword(keywordColl);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return info;
	}
	
}

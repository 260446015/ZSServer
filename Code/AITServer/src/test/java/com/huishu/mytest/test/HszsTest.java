package com.huishu.mytest.test;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;

public class HszsTest {

	public static void main(String[] args) {
		JSONObject findCompany=null;
		try {
			findCompany = Analysis.getCompany("我在你哈还安徽公司  兼职工作，", "吃人太少人给贡放上皇冠饭？？？？");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(findCompany);
		if (findCompany != null && findCompany.getBooleanValue("status")) {
			@SuppressWarnings("unchecked")
			Set<String> set = (Set<String>) findCompany.get("result");
			System.out.println("公司名称集合:" + set);
		}

	}
}

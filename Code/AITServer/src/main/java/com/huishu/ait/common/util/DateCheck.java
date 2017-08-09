package com.huishu.ait.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yxq
 * @date 2017年8月7日
 * @功能描述：获取前台传递的时间标识 进行解析成字符串
 */
public abstract class DateCheck {
	
	public static String dateCheck(String str) {
		if (StringUtils.isNotBlank(str)) {
			String endDate = DateUtils.getTodayDate1();
			String startDate=null;
			if ("今日".equals(str)) {
				startDate = DateUtils.getTodayDate1();
			}
			if ("昨日".equals(str)) {
				startDate = DateUtils.getYesterdayDate();
			}
			if ("近7天".equals(str)) {
				startDate = DateUtils.getLast7Date();
			}
			if ("1个月".equals(str)) {
				startDate = DateUtils.getLast30Date();
			}
			if ("近30天".equals(str)) {
				startDate = DateUtils.getLast30Date();
			}
			if ("半年".equals(str)) {
				startDate = DateUtils.getHalfYearDate();
			}
			if ("一年".equals(str)) {
				startDate = DateUtils.getLast365Date();
			}
			return  startDate+"@"+endDate;
		}
		return null;
	}
}

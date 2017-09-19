package com.huishu.ait.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.forget.findAddress.FindAddress;

/**
 * 校验工具类
 */
public abstract class CheckUtils {

	public static boolean match(String str, String regex) {
		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(regex)) {
			return false;
		}
		return str.matches(regex);
	}

	/**
	 * 校验日期格式 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return false;
		}
		if (date.length() != 10) {
			return false;
		}
		try {
			LocalDate.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 校验日期时间格式 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static boolean checkDateTime(String date) {
		if (StringUtils.isEmpty(date)) {
			return false;
		}
		if (date.length() != 19) {
			return false;
		}
		if (date.contains("T")) {
			return false;
		}
		try {
			LocalDateTime.parse(date.replaceAll(" ", "T"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判断两个日期是否是同一天<br/>
	 * 这个方法的参数必须是已经通过校验的参数
	 * @param d1 yyyy-MM-dd HH:mm:ss 格式
	 * @param d2 yyyy-MM-dd HH:mm:ss 格式
	 * @return
	 */
	public static boolean isOneDay(String d1, String d2) {
		return d1.split(" ")[0].equals(d2.split(" ")[0]);
	}

}

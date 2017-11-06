package com.huishu.ZSServer.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huishu.ZSServer.common.util.ConcersUtils;



	/**
	 * 时间处理工具类
	 * 
	 * @createDate 2017-10-31
	 */
	public  class DateUtil {

		public static final String FORMAT_DATE = "yyyy-MM-dd";

		public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

		public static String getFormatDate(Date date, String format) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}

		public static Date parseStrToDate(String str, String format) {
			if (StringUtils.isEmpty(str) || StringUtils.isEmpty(format)) {
				return null;
			} else {
				SimpleDateFormat sdf = null;
				if (str.length() == format.length()) {
					sdf = new SimpleDateFormat(format);
				} else if (str.length() <= 10) {
					sdf = new SimpleDateFormat(FORMAT_DATE);
				} else {
					sdf = new SimpleDateFormat(FORMAT_TIME);
				}
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

		public static Date getYesterAgoStartTime(Date date) {
			Calendar todayStart = Calendar.getInstance();
			todayStart.setTime(date);
			todayStart.set(Calendar.HOUR, 0);
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			todayStart.add(Calendar.DATE, -1);
			return todayStart.getTime();
		}

		public static Date getWeekAgoStartTime(Date date) {
			Calendar todayStart = Calendar.getInstance();
			todayStart.setTime(date);
			todayStart.set(Calendar.HOUR, 0);
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			todayStart.set(Calendar.DATE, todayStart.get(Calendar.DATE) - 6);
			return todayStart.getTime();
		}

		public static Date getMonthAgoStartTime(Date date) {
			Calendar todayStart = Calendar.getInstance();
			todayStart.setTime(date);
			todayStart.set(Calendar.HOUR, 0);
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			todayStart.add(Calendar.MONTH, -1);
			return todayStart.getTime();
		}

		public static Date getHalfYearStartTime(Date date) {
			Calendar todayStart = Calendar.getInstance();
			todayStart.setTime(date);
			todayStart.set(Calendar.HOUR, 0);
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			todayStart.add(Calendar.MONTH, -6);
			return todayStart.getTime();
		}

		public static Date getYearStartTime(Date date) {
			Calendar todayStart = Calendar.getInstance();
			todayStart.setTime(date);
			todayStart.set(Calendar.HOUR, 0);
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			todayStart.add(Calendar.YEAR, -1);
			return todayStart.getTime();
		}

		public static List<String> getWeekAgoAllStr(String endTime) {
			Date parseStrToDate = parseStrToDate(endTime, FORMAT_TIME);
			List<String> list = new ArrayList<String>();
			for (int x = 6; x >= 0; x--) {
				list.add(ConcersUtils.DateUtil.getFormatDate(
						new Date(parseStrToDate.getTime() - 1000 * 60 * 60 * 24 * x),
						ConcersUtils.DateUtil.FORMAT_DATE));
			}
			return list;
		}

		public static Date getStartTime() {
			Calendar todayStart = Calendar.getInstance();
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.HOUR, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			return todayStart.getTime();
		}

		public static Date getEndTime() {
			Calendar todayEnd = Calendar.getInstance();
			todayEnd.set(Calendar.HOUR, 23);
			todayEnd.set(Calendar.HOUR_OF_DAY, 23);
			todayEnd.set(Calendar.MINUTE, 59);
			todayEnd.set(Calendar.SECOND, 59);
			todayEnd.set(Calendar.MILLISECOND, 999);
			return todayEnd.getTime();
		}

		public static boolean validateDate(String dateStr) {
			if (StringUtils.isEmpty(dateStr)) {
				return true;
			}
			if (dateStr.length() >= 14 && dateStr.length() <= 19) {
				Date parseStrToDate = ConcersUtils.DateUtil.parseStrToDate(dateStr, ConcersUtils.DateUtil.FORMAT_TIME);
				if (parseStrToDate != null) {
					return true;
				} else {
					return false;
				}
			} else if (dateStr.length() >= 8 && dateStr.length() <= 10) {
				Date parseStrToDate = ConcersUtils.DateUtil.parseStrToDate(dateStr, ConcersUtils.DateUtil.FORMAT_DATE);
				if (parseStrToDate != null) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
}

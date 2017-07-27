package com.huishu.ait.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huishu.ait.exception.DateParseException;


public abstract class DateUtils {

	private static Logger log = LoggerFactory.getLogger(DateUtils.class);

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String START_TIME_SUFFIX = " 00:00:00";
	public static final String END_TIME_SUFFIX = " 23:59:59";

	private static ThreadLocal<DateFormat> DATE_SECOND_FORMATER = new ThreadLocal<DateFormat>() {
		public DateFormat initialValue() {
			return new SimpleDateFormat(DATE_SECOND_FORMAT);
		}
	};

	/**
	 * 一天内所有的小时
	 */
	public static final String[] ALL_TIME = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };

	/**
	 * 获取一天所有的小时
	 * 
	 * @return
	 */
	public static List<String> getAllTime() {
		return Arrays.asList(ALL_TIME);
	}

	/**
	 * 获取一天当中所有的小时
	 * 
	 * @return
	 */
	public static List<Integer> getAllHour() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 24; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * 获取两个日期之间所有的日期
	 * 
	 * @param startStr
	 * @param endStr
	 * @return
	 */
	public static List<String> getAllDate(String startStr, String endStr) {

		List<String> result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			result = new ArrayList<String>();
			Date startDate = sdf.parse(startStr);
			Date endDate = sdf.parse(endStr);

			result.add(sdf.format(startDate));
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			Calendar temp = (Calendar) start.clone();
			temp.add(Calendar.DAY_OF_YEAR, 1);
			while (temp.before(end)) {
				result.add(sdf.format(temp.getTime()));
				temp.add(Calendar.DAY_OF_YEAR, 1);
			}
			result.add(sdf.format(endDate));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 转为yyyy-MM-dd格式的字符串 如果Number小于 946656000000 (2000年)就直接返回数字
	 * 
	 * @param value
	 *            Date|Number 类型
	 * @return
	 */
	public static String toDateStr(Object value) {
		if (value == null) {
			return "";
		}
		if (value instanceof Date) {
			return new SimpleDateFormat("yyyy-MM-dd").format((Date) value);
		}
		if (value instanceof Number) {
			long longValue = ((Number) value).longValue();
			if (longValue < 946656000000L) {
				return Long.toString(longValue + 1);
			}
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date(longValue));
		}
		if (value instanceof String) {
			return value.toString();
		} else {
			throw new RuntimeException("can not cast to date, value : " + value);
		}
	}

	/**
	 * 获取截止到某日期的指定数量的日期集合(格式为yyyy-MM-dd)
	 */
	public static List<String> getSpecifyNumDaysToDate(String date, int num) {
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		if (date == null) {
			return null;
		}
		try {
			c.setTime(sdf.parse(date));
			for (int i = num - 1; i >= 0; i--) {
				Calendar temp = (Calendar) c.clone();
				temp.add(Calendar.DATE, -i);
				result.add(sdf.format(temp.getTime()));
			}
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取近一个月的日期列表
	 */
	public static List<String> getNearlyMonthDateList(String date) {
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		if (date == null) {
			return null;
		}
		try {
			c.setTime(sdf.parse(date));
			c.add(Calendar.MONTH, -1);
			result = getAllDate(date, sdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Date parse(String str, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			throw new DateParseException(str + " 转换成 " + format + " 格式的日期错误");
		}
	}

	/**
	 * FIXME 既然已经转为long了为什么不直接比较long的大小
	 * 
	 * 判断结束日期是否大于开始日期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isEndDateMoreStartDate(String startDate, String endDate) {
		long start = new Date(Long.parseLong(startDate)).getTime();
		long end = new Date(Long.parseLong(endDate)).getTime();
		if (end > start) {
			return true;
		}
		return false;
	}

	/**
	 * 根据日期范围类型获取日期范围
	 * 
	 * @param type
	 * @return
	 */
	public static DateRange getDateRange(DateRangeType type) {
		DateRange range = new DateRange();
		String endDate = null;
		String startDate = null;
		String now = LocalDate.now().toString();
		switch (type) {
		case TODAY:
			startDate = now + START_TIME_SUFFIX;
			endDate = now + END_TIME_SUFFIX;
			break;
		case YESTERDAY:
			String lastDay = LocalDate.now().minusDays(1).toString();
			startDate = lastDay + START_TIME_SUFFIX;
			endDate = lastDay + END_TIME_SUFFIX;
			break;
		case LASTWEEK:
			String lastWeek = LocalDate.now().minusDays(6).toString();
			startDate = lastWeek + START_TIME_SUFFIX;
			endDate = now + END_TIME_SUFFIX;
			break;
		case LASTMONTH:
			String lastMonth = LocalDate.now().minusDays(29).toString();
			startDate = lastMonth + START_TIME_SUFFIX;
			endDate = now + END_TIME_SUFFIX;
			break;
		default:
			break;
		}
		range.setStartDate(startDate);
		range.setEndDate(endDate);
		return range;
	}

	public static class DateRange {

		private String startDate;

		private String endDate;

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		
		public Date getObjectStartDate() {
			try {
				return getDateTimeFormat().parse(startDate);
			} catch (ParseException e) {
				//ignore
				return null;
			}
		}
		
		public Date getObjectEndDate() {
			try {
				return getDateTimeFormat().parse(endDate);
			} catch (ParseException e) {
				//ignore
				return null;
			}
		}

		@Override
		public String toString() {
			return "DateRange [startDate=" + startDate + ", endDate=" + endDate + "]";
		}

	}
	
	public static Date parseDateTime(String dateTime) {
		try {
			return getDateTimeFormat().parse(dateTime);
		} catch (ParseException e) {
			throw new IllegalArgumentException(dateTime + " can not parse datetime"); 
		}
	}

	public static DateFormat getDateTimeFormat() {
		return DATE_SECOND_FORMATER.get();
	}

	public enum DateRangeType {
		TODAY, YESTERDAY, LASTWEEK, LASTMONTH
	}

	/**
	 * 获取5天后
	 * @return
	 */
	public static Date getSevenDaysAfter() {
		long now = System.currentTimeMillis();
		long oneDayMills = 1000 * 60 * 60 * 24;
		return new Date(now + oneDayMills * 7);
	}
	
	
	
	public static Date toDate(LocalDateTime datetime) {
		ZoneId zone = ZoneId.systemDefault();
		return Date.from(datetime.atZone(zone).toInstant());
	}
	
}

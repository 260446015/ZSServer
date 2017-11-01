package com.huishu.ZSServer.common.util;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * 
 */

public class ConcersUtils {

	public final static String LIB_ADD_USER = "system";

	public final static DecimalFormat df = new DecimalFormat("######0.00");

	public final static Integer ES_MAX_AGG_SIZE = 1000;

	public final static Integer ES_MAX_PAGENUMBER = 1000;

	public final static Integer ES_MIN_PAGENUMBER = 0;

	public final static String ALL = "全部";

	public final static Integer PAGE_SIZE = 10;

	public final static String OPINION = "舆情";

	public final static String PERSON = "人物";

	public final static String MICROBLOG = "微博";

	public final static String WECHAT = "微信";

	public final static String TOUTIAO = "今日头条";

	public final static String BAIDUNEWS = "百度新闻";
	// 载体
	public final static String ES_VECTOR = "vector";
	// 作者
	public final static String ES_AUTHOR = "author";

	public final static String ES_PUBLISHDATE = "publishDate";
	// hitCount 点击量/阅读量
	public final static String ES_HITCOUNT = "hitCount";
	// supportCount 点赞量/支持数
	public final static String ES_SUPPORTCOUNT = "supportCount";
	// replyCount 回复量/评论数
	public final static String ES_REPLYCOUNT = "replyCount";

	public final static String VECTOR_WECHAT = "微信";

	public final static String VECTOR_MICROBLOG = "微博";

	public final static Map<String, String> MAP_EMOTION;
	static {
		MAP_EMOTION = new HashMap<String, String>();
		MAP_EMOTION.put("正面", "positive");
		MAP_EMOTION.put("负面", "negative");
		MAP_EMOTION.put("positive", "正面");
		MAP_EMOTION.put("negative", "负面");
	}

	public static class CommonUtil {
		// 根据中文获取标识
		public String getConcernsType(String typeZn) {
			String temp = "1";
			if (typeZn.equals(OPINION)) {
				temp = "1";
			} else if (typeZn.equals(PERSON)) {
				temp = "2";
			} else if (typeZn.equals(WECHAT)) {
				temp = "3";
			} else if (typeZn.equals(MICROBLOG)) {
				temp = "4";
			} else {

			}
			return temp;
		}

		/**
		 * 将str 按照，切分为list 并且去重
		 * 
		 * @param str
		 * @return
		 */
		public static Set<String> getStrList(String str) {
			String[] split = str.split(",");
			List<String> asList = Arrays.asList(split);
			Set<String> set = new HashSet<String>(asList);
			return set;
		}

		/**
		 * 校验字符串是否为电话格式
		 * 
		 * @param str
		 * @return
		 */
		public static boolean isPhone(String str) {
			if (StringUtils.isEmpty(str)) {
				return true;
			}
			// return
			// !str.matches("^((13[0-9])|(14[57])|(15[0-9])|(17[01678])|(18[0-9]))\\d{8}$");
			// return !str.matches("^1(3|4|5|7|8)\\d{9}$");
			if (str.length() == 11) {
				return !str.matches("^1(3|4|5|7|8)\\d{9}$");
			} else {
				return !str.matches("^0\\d{2,3}-\\d{7,8}(-\\d{1,6})?$");
			}
		}

		/**
		 * 校验用户的邮箱
		 * 
		 * @param email
		 * @return cmx
		 */
		public static boolean checkEmail(String email) {
			if (StringUtils.isEmpty(email)) {
				return true;
			}
			String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(email);
			return !m.matches();
		}

		public static void main(String[] args) {
			System.out.println(checkEmail("@."));
		}
	}

	public static class SortUtil {

		public static boolean getSortType(String type) {
			if (type.toUpperCase().equals("ASC") || type.equals("升序")) {
				return false;
			} else {
				return true;
			}
		}
	}

	public static class ESUtil {
		/**
		 * es should
		 * 
		 * @param fiedName
		 * @param more
		 * @return
		 */
		public static BoolQueryBuilder getShouldQueryBuilder(String fiedName, Set<String> more) {
			BoolQueryBuilder or = new BoolQueryBuilder();
			if (more != null && more.size() > 0) {
				for (String str : more) {
					or.should(QueryBuilders.termQuery(fiedName, str));
				}
			}
			return or;
		}

		/**
		 * es must
		 * 
		 * @param fiedName
		 * @param more
		 * @return
		 */
		public static BoolQueryBuilder getMustQueryBuilder(String fiedName, Set<String> more) {
			BoolQueryBuilder and = new BoolQueryBuilder();
			if (more != null && more.size() > 0) {
				for (String str : more) {
					and.must(QueryBuilders.termQuery(fiedName, str));
				}
			}
			return and;
		}

		/**
		 * es mustnot
		 * 
		 * @param fiedName
		 * @param more
		 * @return
		 */
		public static BoolQueryBuilder getMustNotQueryBuilder(String fiedName, Set<String> more) {
			BoolQueryBuilder and = new BoolQueryBuilder();
			if (more != null && more.size() > 0) {
				for (String str : more) {
					and.mustNot(QueryBuilders.termQuery(fiedName, str));
				}
			}
			return and;
		}
	}

	/**
	 * 时间处理工具类
	 * 
	 * @createDate 2017-7-28
	 */
	public static class DateUtil {

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
}

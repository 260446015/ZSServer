package com.huishu.ManageServer.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 字符串工具类
 * 
 * @author gzg
 */
public class StringUtil {

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_html1 = "<[^>]+"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
	private final static String[] industry_info = {"大数据","人工智能","物联网","生物医药","生物技术"};
	private final static int[] LI_SECPOSVALUE = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212,
			3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };
	private final static String[] LC_FIRSTLETTER = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "w", "x", "y", "z" };

	/**
	 * 取得给定汉字的首字母,即声母
	 * 
	 * @param chinese
	 *            给定的汉字
	 * @return 给定汉字的声母
	 */
	public static String getFirstLetter(String chinese) {
		if (chinese == null || chinese.trim().length() == 0) {
			return "";
		}
		chinese = conversionStr(chinese, "GB2312", "ISO8859-1");
		// 判断是不是汉字
		if (chinese.length() > 1) {
			int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
			int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
			li_SectorCode = li_SectorCode - 160;
			li_PositionCode = li_PositionCode - 160;
			int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
			if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
				for (int i = 0; i < 23; i++) {
					if (li_SecPosCode >= LI_SECPOSVALUE[i] && li_SecPosCode < LI_SECPOSVALUE[i + 1]) {
						chinese = LC_FIRSTLETTER[i];
						break;
					}
				}
			} else {
				// 非汉字字符,如图形符号或ASCII码
				chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
				chinese = chinese.substring(0, 1);
			}
		}

		return chinese;
	}

	/**
	 * 字符串编码转换
	 * 
	 * @param str
	 *            要转换编码的字符串
	 * @param charsetName
	 *            原来的编码
	 * @param toCharsetName
	 *            转换后的编码
	 * @return 经过编码转换后的字符串
	 */
	private static String conversionStr(String str, String charsetName, String toCharsetName) {
		try {
			str = new String(str.getBytes(charsetName), toCharsetName);
		} catch (UnsupportedEncodingException ex) {
			System.out.println("字符串编码转换异常：" + ex.getMessage());
		}
		return str;
	}

	/**
	 * 根据用户请求判断用户使用的是否是IE浏览器
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent").toUpperCase();
		boolean isIE = ((agent != null && agent.indexOf("MSIE") != -1)
				|| (null != agent && -1 != agent.indexOf("LIKE GECKO"))); // 判断版本,后边是判断IE11的

		return isIE;
	}

	/**
	 * 校验字符串是否为空或为""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	
	public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	/**
	 * 校验字符串是否为电话格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches("^((13[0-9])|(14[57])|(15[0-9])|(17[01678])|(18[0-9]))\\d{8}$");
	}

	/**
	 * 校验字符串是否为电话格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPhones(String str) {
		if (isEmpty(str)) {
			return false;
		}
		if (str.indexOf(",") >= 0) {
			String[] split = str.split(",");
			for (int x = 0; x < split.length; x++) {
				boolean flag = split[x].matches("^((13[0-9])|(15[0-9])|(18[0,2,5-9]))\\d{8}$");
				// ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
				if (!flag) {
					return false;
				}
			}
			return true;
		}
		return str.matches("^((13[0-9])|(15[0-9])|(18[0,2,5-9]))\\d{8}$");
	}

	/**
	 * 判断字符串是否包含中文
	 */
	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 校验字符串是否为日期格式 yyyy-MM-dd
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$");
	}

	/**
	 * 校验字符串是否为日期格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDateTime(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01]) (\\d{2}):(\\d{2}):(\\d{2})$");
	}

	/**
	 * 校验用户的邮箱
	 * 
	 * @param email
	 * @return cmx
	 */
	public static boolean checkEmail(String email) {
		String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 校验密码
	 */
	public static boolean checkPassword(String password) {
		String pattern = "^[a-zA-Z]\\w{5,17}$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(password);
		return m.matches();
	}

	/**
	 * 验证字符串是否数字（包含有符号或者无符号的整数和浮点数）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\+?-?\\d+\\.?\\d+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串去掉html标签
	 */
	public static String replaceHtml(String htmlStr) {
		if(StringUtil.isEmpty(htmlStr))
			return "";
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
		Matcher m_html1 = p_html1.matcher(htmlStr);
		htmlStr = m_html1.replaceAll(""); // 过滤html标签

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim(); // 返回文本字符串
	}
	public static List<Integer> initMoney(String money){
		List<Integer> list = new ArrayList<Integer>();
		if(StringUtil.isEmpty(money)){
			return null;
		}
		if(money.indexOf("万")>=0){
			String[] split = money.split("万");
			String str = split[0];
			if(str.indexOf("-")>=0){
				String[] ss = str.split("-");
				int i1 = Integer.parseInt(ss[0]);
				int i2 = Integer.parseInt(ss[1]);
				list.add(i1);
				list.add(i2);
			}else{
				int i = Integer.parseInt(str);
				list.add(i);
			}
		}
		
		return list;
	}
	public static String getIndustryInfo(String keyWord){
		
		JSONObject obj = KeyWordInfo();
		String s1 = obj.getString("生物技术");
		String s2 =obj.getString("人工智能");
		String s3 =obj.getString("大数据");
		String s4 = obj.getString("物联网");
		if(s1.indexOf(keyWord)>0){
			return "生物医药";
		}
		if(s2.indexOf(keyWord)>0){
			return "人工智能";
		}
		if(s3.indexOf(keyWord)>=0){
			return "大数据";
		}
		if(s4.indexOf(keyWord)>=0){
			return "物联网";
		}else{
			return "";
		}
		
	}
	public static JSONObject getIndustry(JSONObject json){
		int i = industry_info.length;
		int i1 = (int)(Math.random()*i);
		JSONArray array = new JSONArray();
		for(int j = 0; j<i1;j++){
			JSONObject obj = new JSONObject();
			String industry = industry_info[j];
			obj.put("value", industry);
			array.add(obj);
		}
		json.put("industryLabel", array);
		return json;
	}
	public static JSONObject KeyWordInfo(){
		String s1="人工智能,智能算法,深度学习,机器学习,自然语言处理,语义分析,传感器,量子计算机,芯片,无人驾驶,自动驾驶,智能家居,智能设备,无人机,机器人,识别,生物识别,语音识别,图像识别";
		String s2="生物药品,生物制药,食品制造,生物燃料制造,生物农业用品制造,生物化工品制造,生物制品,医疗设备制造,生物设备制造,生物仪器制造,生物技术,基因技术";
		JSONObject obj= new JSONObject();
		obj.put("人工智能", s1);
		obj.put("生物技术", s2);
		obj.put("大数据", "大数据");
		obj.put("物联网", "物联网");
		return obj;
	}
	public static JSONArray replaceString(String str){
		JSONArray json = new JSONArray();
		String rep = str.replace("[", "");
		String result = rep.replace("]", "");
		String[] split = result.split(",");
		for(int i=0;i<split.length;i++){
			String ss = split[i];
			String sy = ss.replace(" ", "");
			json.add(sy);
		}
		return json;
	}

	/**
	 * @param string
	 */
	public static boolean checkString(String header) {
		String[] split = header.split("---");
		if(split.length!=4){
			return false;
		}
		if(split[0].equals("序号")&&split[1].equals("关键词")&&split[2].equals("词性")
				&&split[3].equals("描述")){
			return true;
		}
		return false;
	}
    	 
     
	
}

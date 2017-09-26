package com.huishu.ait.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.huishu.ait.exception.IllegalFieldException;

/**
 * 工具类
 */
public abstract class UtilsHelper {

	/**
	 * 字符串首字母转大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirstOne(String str) {
		if (Character.isUpperCase(str.charAt(0))) {
			return str;
		}
		return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
	}

	/**
	 * 获取属性的get方法名
	 * 
	 * @param field
	 * @return
	 */
	public static String getGetMethodName(String field) {
		return "get" + toUpperCaseFirstOne(field);
	}

	/**
	 * 获取属性的set方法名
	 * 
	 * @param field
	 * @return
	 */
	public static String getSetMethodName(String field) {
		return "set" + toUpperCaseFirstOne(field);
	}

	/**
	 * 反射通过属性名获取属性值, 改属性必须有get方法
	 * 
	 * @param o
	 * @param field
	 * @return
	 */
	public static Object getValueByFieldName(Object o, String field) {
		Objects.requireNonNull(o);
		try {
			Method method = o.getClass().getMethod(getGetMethodName(field));
			return method.invoke(o);
		} catch (Exception e) {
			throw new IllegalFieldException(o.getClass() + " 找不到 [" + field + "]属性 get方法");
		}
	}

	/**
	 * 反射调用set方法
	 * 
	 * @param o
	 * @param field
	 * @param value
	 * @return
	 */
	public static void setValueByFieldName(Object o, String field, Object value) {
		Objects.requireNonNull(o);
		try {
			Method method = null;
			if (value instanceof List) {
				method = o.getClass().getMethod(getSetMethodName(field), List.class);
			} else {
				method = o.getClass().getMethod(getSetMethodName(field), value.getClass());
			}
			method.invoke(o, value);
		} catch (Exception e) {
			throw new IllegalFieldException(o.getClass() + " 找不到 [" + field + "]属性 set 方法");
		}
	}

	/**
	 * 四舍五入保留两位小数
	 * 
	 * @param value
	 * @return
	 */
	public static Double getRound(Double value) {
		return getRound(value, 2);
	}

	/**
	 * 四舍五入
	 * 
	 * @param value
	 * @return
	 */
	public static Double getRound(Double value, int scale) {
		BigDecimal decimal = new BigDecimal(value);
		return Double.valueOf(decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	// 将map转换为bean
	public static <T> T transMapToBean(Map<String, Object> map, Class<T> clazz) {
		T instance = null;
		if (map == null) {
			return instance;
		}
		try {
			instance = clazz.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(instance, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

}

package com.huishu.aitanalysis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间的工具类
 * @author Administrator
 *
 */
public class DateUtil {
	public static int compareDate(String date1,String date2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			if(d1.getTime()>d2.getTime()){
				System.out.println("d1===="+date1+" 在d2=="+date2+"前");
                return 1;
			} else if (d1.getTime() < d2.getTime()) {
				System.out.println("d1===="+date1+" 在d2=="+date2+"后");
                return -1;
            } else {
                return 0;
            }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}

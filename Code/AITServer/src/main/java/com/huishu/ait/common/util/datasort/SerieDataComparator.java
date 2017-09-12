package com.huishu.ait.common.util.datasort;

import java.util.Comparator;

import com.huishu.ait.echart.series.Serie.SerieData;

/**
 * @author hhy
 * @date 2017年9月8日
 * @Parem
 * @return 
 * 
 */
@SuppressWarnings("rawtypes")
public class SerieDataComparator  implements Comparator<SerieData>{

	//重写比较方法
	public int compare(SerieData o1, SerieData o2) {
		Long d1 = (Long) o1.getValue();
		Long d2 = (Long) o2.getValue();
		
		return d2.compareTo(d1);
	}


	

}

package com.huishu.ZSServer.common.util;

import java.util.Comparator;

import com.huishu.ZSServer.es.entity.AITInfo;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 
 */
public class MyComparator implements Comparator {

	/** 
     * 实现compare 
     */
	@Override
	public int compare(Object o1, Object o2) {
		AITInfo i1 = (AITInfo)o1;
		AITInfo i2 = (AITInfo)o2;
		int n1 = i1.getHitCount().compareTo(i2.getHitCount());
		int n2 = i1.getPublishTime().compareTo(i2.getPublishTime());
		// 第一个相等，比较第二个 
		if(n1 == 0 ){
			if(n2 == 0){
				return 0;
			}else {
				return n2 ;
			}
		}else{
			return n1;
		}
	}
	public int compare2(Object o1, Object o2){
		AITInfo i1 = (AITInfo)o1;
		AITInfo i2 = (AITInfo)o2;
		int i = i1.getHitCount().compareTo(i2.getHitCount());
		
		return i;
	}
}


package com.huishu.aitanalysis.common.analyzer.clean;



/**
 * 此接口是对字符串清空接口
 * 1. 清空字符串中HTML标签
 * 2. 替换内容中
 * 		包含下列内容做替换成空字符串
 * 		(转载)|【转载】|（转载）|[转载]
 * @author LvDapeng
 * @date 2015年9月15日 下午1:35:17
 */
public interface Clean {
	
	public abstract String cleaning(String str);
	
}

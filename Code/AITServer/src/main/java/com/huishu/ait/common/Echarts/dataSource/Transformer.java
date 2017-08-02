package com.huishu.ait.common.Echarts.dataSource;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 
 * 转换器接口，用于将指定的数据转换为客户端指定格式的数据
 */
public interface Transformer {
	Object transform(String data, String... params);
}

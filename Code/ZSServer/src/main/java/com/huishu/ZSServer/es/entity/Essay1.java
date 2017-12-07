package com.huishu.ZSServer.es.entity;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE1;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 文章详情展示
 * 
 * @author yindq
 * @date 2017年12月6日
 */
@Document(indexName = INDEX, type = TYPE1)
public class Essay1 extends Essay{
}

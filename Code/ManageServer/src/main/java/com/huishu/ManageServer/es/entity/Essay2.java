package com.huishu.ManageServer.es.entity;

import static com.huishu.ManageServer.common.conf.DBConstant.EsConfig.INDEX2;
import static com.huishu.ManageServer.common.conf.DBConstant.EsConfig.TYPE2;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 文章详情展示
 * 
 * @author yindq
 * @date 2017年12月6日
 */
@Document(indexName = INDEX2, type = TYPE2)
public class Essay2 extends Essay {
}

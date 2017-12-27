package com.huishu.ManageServer.es.entity;

import static com.huishu.ManageServer.common.conf.DBConstant.EsConfig.INDEX3;
import static com.huishu.ManageServer.common.conf.DBConstant.EsConfig.TYPE2;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 文章详情展示
 * 
 * @author yindq
 * @date 2017年12月6日
 */
@Document(indexName = INDEX3, type = TYPE2)
public class Essay3 extends Essay {
}

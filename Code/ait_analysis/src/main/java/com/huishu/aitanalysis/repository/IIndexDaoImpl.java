/*package com.huishu.aitanalysis.repository;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;

import com.huishu.aitanalysis.common.ConfigManager;
import com.huishu.aitanalysis.entity.Es;
import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.util.EmptyUtils;

import net.sf.json.JSONObject;



*//**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 *//*
public class IIndexDaoImpl implements IIndexDao{
	private static IIndexDao indexDao;
	
	protected static final ConfigManager config = ConfigManager.getInstance();
	
	static {
		indexDao = new IIndexDaoImpl();
	}
	
	private TransportClient client;
	
	private IIndexDaoImpl() {
		try {
			Settings settings = Settings.settingsBuilder().put(config.get(Es.ES_TCLIENT), true).build();
			client = TransportClient.builder().settings(settings).build();
			client.addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(Es.ES_IP), 9300));
		} catch (UnknownHostException e) {
			//ignore
		}
	}
	
	public static IIndexDao getInstance(){
		return indexDao;
	}
	
	@Override
	public void insert(String info) {
		createIndexResponse(info);
	}
	
	IndexResponse createIndexResponse(String info) {
		
		IndexResponse response = client.prepareIndex(Es.ES_INDEX, 
				Es.ES_TYPE).setSource(info).execute().actionGet();
		return response;
	}

	@Override
	public Index queryByID(String documentId , Logger log) {
		try {
			if(typeExists()){
				if(!EmptyUtils.isEmpty(documentId)){
					
					StringQuery stringQuery = new StringQuery(termQuery("id", documentId).toString());
//					Index index = elasticsearchTemplate.queryForObject(stringQuery, Index.class);
					
					log.info("IndexDao.queryByID, query document by documentId: [" + documentId + "]");
					return null;
				}
			}
		} catch (Exception e) {
			log.error("IndexDao.queryByID: " + e.getMessage());
			return null;
		}
		return null;
	}
	@Override
	public void index(Index index, Logger log) {
		try {
			//当前索引下type如果不存在,就新建索引
			if(!typeExists()){
				log.info("IndexDao.index --> index and type not exists!");
				createIndex(Index.class);
			}
			
			//新增文档到ES索引库
			IndexQuery indexQuery = getIndexQuery(Es.ES_INDEX, Es.ES_TYPE, index);
		
//			elasticsearchTemplate.index(indexQuery);
//			elasticsearchTemplate.refresh(Index.class, true);
		} catch (Exception e) {
			log.warn("IndexDao.index -- IllegalArgumentException" + e);
		}
	}

	private IndexQuery getIndexQuery(String indexName, String indexType, Index index) {
		return new IndexQueryBuilder().
				withId(index.getId()).
				withIndexName(indexName).
				withType(indexType).
				withObject(index).
				build();
	}
	
	public void createIndex(Class<?> c) {
//		elasticsearchTemplate.createIndex(c);
		
		//
//		Object mapping = new Object();
//		elasticsearchTemplate.putMapping(elastics.getIndex(), elastics.getType(), mapping);
	}

	*//**
	 * 批量构建索引
	 *//*
	@Override
	public void bulkIndex(List<Index> indexs, Logger log) {
		//当前索引下type如果不存在,就新建索引
		if(!typeExists()){
			log.info("IndexDao.bulkIndex --> index and type not exists!");
			createIndex(Index.class);
		}
		
		//新增文档到ES索引库
		List<IndexQuery> indexQueries = getIndexQueries(indexs);
		try {
//			elasticsearchTemplate.bulkIndex(indexQueries);
//			elasticsearchTemplate.refresh(Index.class, true);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("IndexDao.bulkIndex -- IllegalArgumentException" + e);
		}
		
	}
	private List<IndexQuery> getIndexQueries(List<Index> indexs){
		List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();
		for (Index index : indexs) {
			indexQueries.add(
					new IndexQueryBuilder().
					withId(index.getId()).
					withIndexName(Es.ES_INDEX).
					withType(Es.ES_TYPE).
					withObject(index).build());
		}
		return indexQueries;
	}
	*//**
	 * 根据文章ID修改文章属性
	 * 针对IndexRequest的设置有很多种, source 有 object map class等设置方式,设置的方式不同,更新的索引效果不同
	 * 此处需要着重注意, 调试消耗事件很长, 后续研究下 IndexRequest	
	 * 最后研究可行的方案是, 设置 source 的源为 String好使
	 *//*
	@Override
	public boolean modifyByID(String documentId, JSONObject source, Logger log) {
		boolean flag = true;
		try {
			if(typeExists()){
				if(!source.isEmpty()){
					IndexRequest indexRequest = new IndexRequest();
					indexRequest.source(source.toString());
					
					UpdateQuery updateQuery = new UpdateQueryBuilder().
							withIndexName(Es.ES_INDEX).
							withType(Es.ES_TYPE).
							withDoUpsert(true).
							withId(documentId).
							withClass(String.class).
							withIndexRequest(indexRequest).
							build();

//					UpdateResponse response = elasticsearchTemplate.update(updateQuery);
//					long version = response.getVersion();
//					if(!(version >= 0)){
//						flag = false;
//						
//					}
//					log.info("IndexDao.modifyByID, modify document by documentId: [" + documentId + "], The success of ["+flag+"], Modify version: " + version);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("IndexDao.modifyByID: " + e.getMessage());
			return flag;
		}
		return flag;
	}

	
	public boolean typeExists(){
		return typeExists(null, null);
	}
	
	
	public boolean typeExists(String indexName, String indexType) {
		//typeExists
		boolean exists = true;
		try {
			//index properties info
			if(EmptyUtils.isEmpty(indexName)){
				indexName = Es.ES_INDEX;
			}
			if(EmptyUtils.isEmpty(indexType)){
				indexType = Es.ES_TYPE;
			}
			
//			exists = elasticsearchTemplate.typeExists(indexName, indexType);
		} catch (Exception e) {
			exists = false;
		}
		return exists;
	}

}
*/
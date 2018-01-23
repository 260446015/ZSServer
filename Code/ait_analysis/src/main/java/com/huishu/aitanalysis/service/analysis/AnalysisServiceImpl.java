package com.huishu.aitanalysis.service.analysis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.forget.articleCategory.POMPArticleCategory;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.es.entity.Index2;
import com.huishu.aitanalysis.es.entity.Index3;
import com.huishu.aitanalysis.service.index.IIndexService;
import com.huishu.aitanalysis.util.DateUtil;
import com.huishu.aitanalysis.util.Util;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 */
@Service
public class AnalysisServiceImpl extends AbstractPostService  implements AnalysisService{
	
	private Logger log = Logger.getLogger("analysis");
	@Autowired
	private IIndexService indexService;
	
	/**
	 * 分析入es库
	 */
	@SuppressWarnings("unused")
	@Override
	public void analysis(String dataJson) {
		Index index = null;
		Index2 index2 = null;
		Index3 index3 = null;
		JSONObject jsonObject = JSONObject.parseObject(dataJson);
		String title = Util.getTitle(jsonObject);
		String articleLink = Util.getArticleLink(jsonObject);
		String dimension2 = Util.getDimension(jsonObject);
		//去重
		boolean info = indexService.filterByDimensionAndJSON(dimension2,jsonObject);
		
		
		log.info("去重的结果为:》》》》"+info);
		if(!info){
			log.info("数据存在重复的可能====="+info);
			
		}else{
			
			//关键词
			String keyWord = Util.getKeyWord(jsonObject);
			
			if(StringUtil.isEmpty(keyWord)){
				String dimension = Util.getDimension(jsonObject);
				if(dimension.equals("园区政策")||dimension.equals("园区动态")){
					
					index = getIndex2(jsonObject);
					log.info("园区政策分析出来的数据为：》》》》"+index);
				}else if(dimension.equals("高峰论坛")){
						index = getIndex(jsonObject);
						index2 = getIndex3(jsonObject);
						log.info("高峰论坛分析出的数据为：》》》》"+index);
						log.info("高峰论坛分析出的数据为：》》》》"+index2);
				} else if(dimension.equals("融资快讯")){
					index3 = getIndex4(jsonObject);
					log.info("融资快讯分析出的数据为：》》》》"+index2);
				}else
				{	
					
					index = getIndex(jsonObject);			
				}
				
			}else{
				
				index = getIndex1(jsonObject);
			}
			
			if(index==null){
				log.info("index索引信息为空，不能入库");
			}else{
				String content = index.getContent();
				if(content.length()>4000){
					log.info("文章长度大于4000字[" + content.length() + "]");
					//截取前4000字，存到es中。
					content = content.substring(0, 4000);
					index.setContent(content);
				}
				//分析情感
				sentimentNew(index);
				//保存数据
				long s = System.currentTimeMillis();
				indexService.index(index, log);
				long e = System.currentTimeMillis();
				long ecs = e - s;
				log.info("中文索引到es用时[" + ecs + "ms]");
			}
			if(index2== null){
				log.info("index2索引信息为空，不能入库");
			}else{
				String content = index2.getContent();
				if(content.length()>6000){
					log.info("文章长度大于10000字[" + content.length() + "]");
					//截取前4000字，存到es中。
					content = content.substring(0, 6000);
					index.setContent(content);
				}
				//分析情感
				sentEmotion(index2);
				log.info("分析情感结束");
				//获取产业分类
				
				//保存数据
				long s = System.currentTimeMillis();
				indexService.indexInfo(index2, log);
				long e = System.currentTimeMillis();
				long ecs = e - s;
				log.info("中文索引到es用时[" + ecs + "ms]");
				
			}
			if(index3 == null){
				log.info("index3的索引信息为空，不能入库");
			}else{
				//分析情感
				//保存数据
				long s = System.currentTimeMillis();
				indexService.savaIndex3(index3,log);
				long e = System.currentTimeMillis();
				long ecs = e - s;
				log.info("中文索引到es用时[" + ecs + "ms]");
			}
			
		}
		
		}
	
	

	/**
	 * 情感分析新版本
	 */
	private void sentimentNew(Index index) {

		String content = index.getContent();
		String title = index.getTitle();
		String articleCategory = POMPArticleCategory.articleCategory(title, content);

		if ("negative".equals(articleCategory)) {
			// 负面
			index.setEmotion(articleCategory);
		} else if ("positive".equals(articleCategory)) {
			// 正面
			index.setEmotion(articleCategory);
		} else {
			// 中性
			index.setEmotion(articleCategory);
		}
	}
	/**
	 * 情感分析新版本
	 */
	private void sentEmotion(Index2 index) {

		String content = index.getContent();
		String title = index.getTitle();
		String articleCategory = POMPArticleCategory.articleCategory(title, content);

		if ("negative".equals(articleCategory)) {
			// 负面
			index.setEmotion(articleCategory);
		} else if ("positive".equals(articleCategory)) {
			// 正面
			index.setEmotion(articleCategory);
		} else {
			// 中性
			index.setEmotion(articleCategory);
		}
	}
}

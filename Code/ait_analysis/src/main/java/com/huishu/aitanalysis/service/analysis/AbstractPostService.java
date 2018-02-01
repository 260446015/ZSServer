package com.huishu.aitanalysis.service.analysis;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.common.SimpleSummariserAlgorithm;
import com.huishu.aitanalysis.common.analyzer.clean.CleanAlgorithm;
import com.huishu.aitanalysis.common.analyzer.clean.CleanFactory;
import com.huishu.aitanalysis.entity.IndustryInfo;
import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.es.entity.Index2;
import com.huishu.aitanalysis.es.entity.Index3;
import com.huishu.aitanalysis.service.indus.IndustryInfoService;
import com.huishu.aitanalysis.service.park.ParkAnalysisService;
import com.huishu.aitanalysis.util.Digests;
import com.huishu.aitanalysis.util.Encodes;
import com.huishu.aitanalysis.util.MD5Util;
import com.huishu.aitanalysis.util.StringUtils;
import com.huishu.aitanalysis.util.Util;


/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 */
public abstract class AbstractPostService {

	private Logger log = Logger.getLogger("analysis");
	private final String abstractkeyword = "网络游戏,智能硬件,移动阅读,网络视听,电子商务,节能环保,新材料,新能源,生物技术,生物医药,生物制药,大数据,人工智能";
	/**
	 * 全网采集，根据关键词查找文章内容
	 * 
	 * 封装索引需要信息
	 * TODO 这个多线程调用是否存在问题
	 * @param jsonObject
	 * @return
	 * 搜索设置分析程序
	 */
	public synchronized Index getIndex1(JSONObject jsonObject,ParkAnalysisService service){
		Index index = new Index();
		try {
			String url = null == jsonObject.get("id") ? "" : String.valueOf(jsonObject.get("id")); 
			String id = "";
			if(StringUtil.isEmpty(url)){
				byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
				byte[] hashPassword = Digests.sha1("123456".getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
						Encodes.HASH_INTERATIONS);
				 id =  Encodes.encodeHex(hashPassword);
			}
			
				
				//关键词
				String keyWord = Util.getKeyWord(jsonObject);
				//根据关键词判断是否是园区
				boolean info = service.isParkName(keyWord);
				System.out.println("查询的结果为》》》》》》》"+info);
				if(info){
					boolean warning = StringUtils.isWarning(keyWord);
					System.out.println(warning);
					if(warning){
						String park ="";
						if(keyWord.equals("雄安新区")){
							 park ="雄安新区";
							
						}else{
							
							/**根据公司名称获取公司全称mysql*/
							//园区
							 park ="天津中新生态城";
						}
						
						String _dimension = "疑似外流";
						String business = StringUtils.getBusiness(keyWord);
						//载体
						String vector = Util.getVector(jsonObject);
						//网站名称（来源）
						String source = Util.getSite(jsonObject);

						if(StringUtil.isEmpty( vector)){
							vector = source;
						}
						/*//标题
						String title = Util.getTitle(jsonObject);*/
						//原文链接
						String _articleLink = Util.getArticleLink(jsonObject);
						//作者
						String _author = Util.getAuthor(jsonObject);
						//内容
						String _content = Util.getContent(jsonObject);
						//点击数
						Long _hitCount = Util.getHitCount(jsonObject);
//						//采集时间
//						String gatherTime = Util.getGatherTime(jsonObject);
						//发布时间 (yyyy-MM-dd HH:mm:ss)
						String publishDate = Util.getPublishDate(jsonObject);
//						if(StringUtil.isEmpty(publishDate)){
//							//如果发布时间为空，将采集时间赋值给发布时间
//							publishDate=gatherTime;
//						}
						//发布时间 (yyyy-mm-dd)
						String publishTime = Util.getPublishTime(publishDate);
						//发布时间  年份
						String publishYear = Util.getPublishYear(publishDate);
						//评论数
						Long _replyCount = Util.getReplyCount(jsonObject);
						//来源
						String _source = Util.getSource(jsonObject);
						if(_source==null||StringUtil.isEmpty(_source)){
							_source = source;
						}
						//文章摘要
						String _summary = Util.getSummary(jsonObject);
						if(StringUtil.isEmpty(_summary)){
							_summary = SimpleSummariserAlgorithm.summarise(StringUtils.rmHtmlTag(_content), 3);
					}

						//去除content内容的HTML标签
						//标题
						String _title = Util.getTitle(jsonObject);
						//支持数
						Long _supportCount = Util.getSupportCount(jsonObject);
						//采集logo
						String _logo = Util.getLogo(jsonObject);
						index.setId(id);
						index.setEngageState("");
						index.setIdentity("");
						index.setRegisterCapital("");
						index.setRegisterData("");
						index.setAddress("");
						index.setEstablishTime("");
						index.setPark(park);
						index.setAcreage("");
						index.setBusiness(business);
						index.setIndustryType("");
						index.setUpdateAttribute("");
						index.setBoss("");
						index.setBusinessType("");
						index.setHasWarn(true);
						index.setLogo(_logo);
						index.setArea("天津");
						index.setIndustry("");
						index.setIndustryLabel("");
						index.setArticleLink(_articleLink);
						index.setAuthor(_author);
						index.setContent(_content);
						index.setSource(_source);
						index.setDimension(_dimension);
						index.setVector(vector);
						index.setTitle(_title);
						index.setSummary(_summary);
						index.setSourceLink(_articleLink);
						index.setPublishDate(publishDate);
						index.setPublishTime(publishTime);
						index.setPublishYear(publishYear);
						index.setHitCount(_hitCount);
						index.setReplyCount(_replyCount);
						index.setSupportCount(_supportCount);
						index.setIstop(false);
					}	else {
						String park ="";
						if(keyWord.equals("雄安新区")){
							 park ="雄安新区";
							
						}else{
							
							/**根据公司名称获取公司全称mysql*/
							//园区
							 park =keyWord;
//							 park ="天津中新生态城";
						}
//						String _dimension = "企业动态";
						String _dimension = "园区动态";
						
						//载体
						String vector = Util.getVector(jsonObject);
						//网站名称（来源）
						String source = Util.getSite(jsonObject);
						
						if(StringUtil.isEmpty( vector)){
							vector = source;
						}
						/*//标题
						String title = Util.getTitle(jsonObject);*/
						//原文链接
						String _articleLink = Util.getArticleLink(jsonObject);
						//作者
						String _author = Util.getAuthor(jsonObject);
						//内容
						String _content = Util.getContent(jsonObject);
						//去除content内容的HTML标签
						//String content = StringUtils.rmHtmlTag(_content);
						//点击数
						Long _hitCount = Util.getHitCount(jsonObject);
						//采集时间
//						String gatherTime = Util.getGatherTime(jsonObject);
						//发布时间 (yyyy-MM-dd HH:mm:ss)
						String publishDate = Util.getPublishDate(jsonObject);
//						if(StringUtil.isEmpty(publishDate)){
//							//如果发布时间为空，将采集时间赋值给发布时间
//							publishDate=gatherTime;
//						}
						//发布时间 (yyyy-mm-dd)
						String publishTime = Util.getPublishTime(publishDate);
						//发布时间  年份
						String publishYear = Util.getPublishYear(publishDate);
						//评论数
						Long _replyCount = Util.getReplyCount(jsonObject);
						//来源
						String _source = Util.getSource(jsonObject);
						if(_source==null||StringUtil.isEmpty(_source)){
							_source = source;
						}
						//文章摘要
						String _summary = Util.getSummary(jsonObject);
						if(StringUtil.isEmpty(_summary)){
							String content = StringUtils.rmHtmlTag(_content);
							//截取内容的前100个字做摘要的内容
							_summary = content.substring(0, 300);
						}
						//标题
						String _title = Util.getTitle(jsonObject);
						//支持数
						Long _supportCount = Util.getSupportCount(jsonObject);
						//采集logo
						String _logo = Util.getLogo(jsonObject);
						index.setId(id);
						index.setEngageState("");
						index.setIdentity("");
						index.setRegisterCapital("");
						index.setRegisterData("");
						index.setAddress("");
						index.setEstablishTime("");
						index.setPark(park);
						index.setAcreage("");
						index.setBusiness(keyWord);
						index.setIndustryType("");
						index.setUpdateAttribute("");
						index.setBoss("");
						index.setBusinessType("");
						index.setHasWarn(false);
						index.setLogo(_logo);
						index.setArea("");
						index.setIndustry("");
						index.setIndustryLabel("");
						index.setArticleLink(_articleLink);
						index.setAuthor(_author);
						index.setContent(_content);
						index.setSource(_source);
						index.setDimension(_dimension);
						index.setVector(vector);
						index.setTitle(_title);
						index.setSummary(_summary);
						index.setSourceLink(_articleLink);
						index.setPublishDate(publishDate);
						index.setPublishTime(publishTime);
						index.setPublishYear(publishYear);
						index.setHitCount(_hitCount);
						index.setReplyCount(_replyCount);
						index.setSupportCount(_supportCount);
						index.setIstop(false);
					}
				}else{
					//非园区数据，判断是否为产业动态全网搜数据
					if(abstractkeyword.contains(keyWord)){
						//包含关键词，进行产业头条数据维护
						//网站名称
						String site = Util.getSite(jsonObject);
						//地域
						String _area = Util.getArea(jsonObject);
						//产业标签  -- 关键词
						JSONObject obje = new JSONObject();
						JSONArray array = new JSONArray();
						obje.put("Name", keyWord);
						array.add(obje);
						//产业
						String industry = Util.getIndustryByIndestryLeabl(array);
						//维度
						String _dimension = "产业头条";
						
						//原文链接
						String _articleLink = Util.getArticleLink(jsonObject);
						//作者
						String _author = Util.getAuthor(jsonObject);
					
						//内容
						String _content = Util.getContent(jsonObject);
						//去除content内容的HTML标签
						//String content = StringUtils.rmHtmlTag(_content);
						
						//点击数
						Long _hitCount = Util.getHitCount(jsonObject);
						
						//采集时间
//						String gatherTime = Util.getGatherTime(jsonObject);
						
						//发布时间 (yyyy-MM-dd HH:mm:ss)
						String publishDate = Util.getPublishDate(jsonObject);
						
//						if(StringUtil.isEmpty(publishDate)){
//							//如果发布时间为空，将采集时间赋值给发布时间
//							publishDate=gatherTime;
//						}
						
						//发布时间 (yyyy-mm-dd)
						String publishTime = Util.getPublishTime(publishDate);
						
						//发布时间  年份
						String publishYear = Util.getPublishYear(publishDate);
						//评论数
						Long _replyCount = Util.getReplyCount(jsonObject);
						//载体
						String _vector = Util.getSite(jsonObject);
						//来源
						String _source = Util.getSource(jsonObject);
						if(_source == null){
							//如果来源不存在，将网站名称赋予
							_source = site;
						}
						//文章摘要
						String _summary = Util.getSummary(jsonObject);
						if(StringUtil.isEmpty(_summary)){
							_summary = SimpleSummariserAlgorithm.summarise(StringUtils.rmHtmlTag(_content), 3);
					}

						//标题
						String _title = Util.getTitle(jsonObject);
						//取出文章标题并将标题中的特殊字符去掉
//						String cleanTitle = CleanFactory.getAnalysis(CleanAlgorithm.CleanTitleChars).cleaning(_title);
						if(_area.equals("全国")||_area.equals("")){
							Set<String> area = Util.getArea(_title, _content);
							
							
							Object[] a= area.toArray();
							if(a.length>0){
								_area = a[0].toString();
							}else{
								_area="全国";
							}
							
						}
						
						//支持数
						Long _supportCount = Util.getSupportCount(jsonObject);
						//采集logo
						String _logo = Util.getLogo(jsonObject);
						index.setId(id);
						index.setEngageState("");
						index.setIdentity("");
						index.setRegisterCapital("");
						index.setRegisterData("");
						index.setAddress("");
						index.setEstablishTime("");
						index.setPark("");
						index.setAcreage("");
						index.setBusiness("");
						index.setIndustryType("");
						index.setUpdateAttribute("");
						index.setBoss("");
						index.setBusinessType("");
						index.setHasWarn(false);
						index.setLogo(_logo);
						index.setArea(_area);
						index.setIndustry(industry);
						index.setIndustryLabel(keyWord);
						index.setArticleLink(_articleLink);
						index.setAuthor(_author);
						index.setContent(_content);
						index.setSource(_source);
						index.setDimension(_dimension);
						index.setVector(_vector);
						index.setTitle(_title);
						index.setSummary(_summary);
						index.setSourceLink(_articleLink);
						index.setPublishDate(publishDate);
						index.setPublishTime(publishTime);
						index.setPublishYear(publishYear);
						index.setHitCount(_hitCount);
						index.setReplyCount(_replyCount);
						index.setSupportCount(_supportCount);
						index.setIstop(false);
						return index;
					}else{
						return null;
					}
					
				}
				
				
		} catch (Exception e) {

//			e.printStackTrace();
			log.error("AbstractService.getIndex: "+e.getMessage());
			return null;
		}	
		
		
		return index;
	}
	
	/**
	 * 园区政策，园区动态 分析程序
	 * 封装索引需要信息
	 * TODO 这个多线程调用是否存在问题
	 * @param jsonObject
	 * @return
	 * 
	 */
	public synchronized Index getIndex2(JSONObject jsonObject){
		Index index = new Index();
		try {
			
			String url = null == jsonObject.get("id") ? "" : String.valueOf(jsonObject.get("id")); 
			String id = "";
			if(StringUtil.isEmpty(url)){
				byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
//				System.out.println(Encodes.encodeHex(salt));
				byte[] hashPassword = Digests.sha1("123456".getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
						Encodes.HASH_INTERATIONS);
//				System.out.println(Encodes.encodeHex(hashPassword));
				
				 id =  Encodes.encodeHex(hashPassword);
			}
			String site = Util.getSite(jsonObject);
			String industryInfo = Util.getIndustryInfo(jsonObject);
			//地域
			String _area = Util.getArea(jsonObject);
			//产业
			String industry = Util.getIndustry(jsonObject, industryInfo);
			
			//产业标签
			String industryLabel = Util.getIndustryLeabl(jsonObject, industryInfo);
			//维度
			String _dimension = Util.getDimension(jsonObject);
			
			
			//原文链接
			String _articleLink = Util.getArticleLink(jsonObject);
			//作者
			String _author = Util.getAuthor(jsonObject);
		
			//内容
			String _content = Util.getContent(jsonObject);
			//去除content内容的HTML标签
			//String content = StringUtils.rmHtmlTag(_content);
			
			//点击数
			Long _hitCount = Util.getHitCount(jsonObject);
			
			//采集时间
//			String gatherTime = Util.getGatherTime(jsonObject);
			
			//发布时间 (yyyy-MM-dd HH:mm:ss)
			String publishDate = Util.getPublishDate(jsonObject);
			
//			if(StringUtil.isEmpty(publishDate)){
//				//如果发布时间为空，将采集时间赋值给发布时间
//				publishDate=gatherTime;
//			}
			
			//发布时间 (yyyy-mm-dd)
			String publishTime = Util.getPublishTime(publishDate);
			
			//发布时间  年份
			String publishYear = Util.getPublishYear(publishDate);
			//评论数
			Long _replyCount = Util.getReplyCount(jsonObject);
			//载体
			String _vector = Util.getSite(jsonObject);

			//来源
			String _source = Util.getSource(jsonObject);
			if(_source == null){
				_source = _vector;
			}
			//文章摘要
			String _summary = Util.getSummary(jsonObject);
			if(StringUtil.isEmpty(_summary)){
				//截取内容的前100个字做摘要的内容
				_summary = _content.substring(0, 300);
			}
			//标题
			String _title = Util.getTitle(jsonObject);
			//取出文章标题并将标题中的特殊字符去掉
//			String cleanTitle = CleanFactory.getAnalysis(CleanAlgorithm.CleanTitleChars).cleaning(_title);
			
			//支持数
			Long _supportCount = Util.getSupportCount(jsonObject);
			//采集logo
			String _logo = Util.getLogo(jsonObject);
			index.setId(id);
			index.setEngageState("");
			index.setIdentity("");
			index.setRegisterCapital("");
			index.setRegisterData("");
			index.setAddress("");
			index.setEstablishTime("");
			index.setPark(site);
			index.setAcreage("");
			index.setBusiness("");
			index.setIndustryType("");
			index.setUpdateAttribute("");
			index.setBoss("");
			index.setBusinessType("");
			index.setHasWarn(false);
			index.setLogo(_logo);
			index.setArea(_area);
			index.setIndustry(industry);
			index.setIndustryLabel(industryLabel);
			index.setArticleLink(_articleLink);
			index.setAuthor(_author);
			index.setContent(_content);
			index.setSource(_source);
			index.setDimension(_dimension);
			index.setVector(_vector);
			index.setTitle(_title);
			index.setSummary(_summary);
			index.setSourceLink(_articleLink);
			index.setPublishDate(publishDate);
			index.setPublishTime(publishTime);
			index.setPublishYear(publishYear);
			index.setHitCount(_hitCount);
			index.setReplyCount(_replyCount);
			index.setSupportCount(_supportCount);
			index.setIstop(false);
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("AbstractService.getIndex: "+e.getMessage());
			return null;
		}	
		
		
		return index;
		
	}
	/**
	 * 封装索引需要信息
	 * TODO 这个多线程调用是否存在问题
	 * @param jsonObject
	 * @return
	 * 
	 */
	public synchronized Index getIndex(JSONObject jsonObject){
		Index index = new Index();
		try {
			
			String url = null == jsonObject.get("id") ? "" : String.valueOf(jsonObject.get("id")); 
			String id = "";
			if(StringUtil.isEmpty(url)){
				byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
//				System.out.println(Encodes.encodeHex(salt));
				byte[] hashPassword = Digests.sha1("123456".getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
						Encodes.HASH_INTERATIONS);
//				System.out.println(Encodes.encodeHex(hashPassword));
				
				 id =  Encodes.encodeHex(hashPassword);
			}
			
			//网站名称
			String site = Util.getSite(jsonObject);
			String industryInfo = Util.getIndustryInfo(jsonObject);
			//地域
			String _area = Util.getArea(jsonObject);
			//产业
			String industry = Util.getIndustry(jsonObject, industryInfo);
			//产业标签
			String industryLabel = Util.getIndustryLeabl(jsonObject, industryInfo);
			//维度
			String _dimension = Util.getDimension(jsonObject);
			
			
			//原文链接
			String _articleLink = Util.getArticleLink(jsonObject);
			//作者
			String _author = Util.getAuthor(jsonObject);
		
			//内容
			String _content = Util.getContent(jsonObject);
			//去除content内容的HTML标签
			//String content = StringUtils.rmHtmlTag(_content);
			
			//点击数
			Long _hitCount = Util.getHitCount(jsonObject);
			
		/*	//采集时间
			String gatherTime = Util.getGatherTime(jsonObject);*/
			
			//发布时间 (yyyy-MM-dd HH:mm:ss)
			String publishDate = Util.getPublishDate(jsonObject);
			
			/*if(StringUtil.isEmpty(publishDate)){
				//如果发布时间为空，将采集时间赋值给发布时间
				publishDate=gatherTime;
			}*/
			
			//发布时间 (yyyy-mm-dd)
			String publishTime = Util.getPublishTime(publishDate);
			if(publishTime.compareTo("2016-12-31") <= 0){
				return null;
			}
			
			//发布时间  年份
			String publishYear = Util.getPublishYear(publishDate);
			//评论数
			Long _replyCount = Util.getReplyCount(jsonObject);
			//载体
			String _vector = Util.getSite(jsonObject);
			//来源
			String _source = Util.getSource(jsonObject);
			if(_source == null){
				//如果来源不存在，将网站名称赋予
				_source = site;
			}
			//文章摘要
			String _summary = Util.getSummary(jsonObject);
			if(StringUtil.isEmpty(_summary)){
				_summary = SimpleSummariserAlgorithm.summarise(StringUtils.rmHtmlTag(_content), 3);
		}

			//标题
			String _title = Util.getTitle(jsonObject);
			//取出文章标题并将标题中的特殊字符去掉
//			String cleanTitle = CleanFactory.getAnalysis(CleanAlgorithm.CleanTitleChars).cleaning(_title);
			if(_area.equals("全国")||_area.equals("")){
				Set<String> area = Util.getArea(_title, _content);
				
				
				Object[] array = area.toArray();
				if(array.length>0){
					_area = array[0].toString();
				}else{
					_area="全国";
				}
				
			}
			
			//支持数
			Long _supportCount = Util.getSupportCount(jsonObject);
			//采集logo
			String _logo = Util.getLogo(jsonObject);
			index.setId(id);
			index.setEngageState("");
			index.setIdentity("");
			index.setRegisterCapital("");
			index.setRegisterData("");
			index.setAddress("");
			index.setEstablishTime("");
			index.setPark("");
			index.setAcreage("");
			index.setBusiness("");
			index.setIndustryType("");
			index.setUpdateAttribute("");
			index.setBoss("");
			index.setBusinessType("");
			index.setHasWarn(false);
			index.setLogo(_logo);
			index.setArea(_area);
			index.setIndustry(industry);
			index.setIndustryLabel(industryLabel);
			index.setArticleLink(_articleLink);
			index.setAuthor(_author);
			index.setContent(_content);
			index.setSource(_source);
			index.setDimension(_dimension);
			index.setVector(_vector);
			index.setTitle(_title);
			index.setSummary(_summary);
			index.setSourceLink(_articleLink);
			index.setPublishDate(publishDate);
			index.setPublishTime(publishTime);
			index.setPublishYear(publishYear);
			index.setHitCount(_hitCount);
			index.setReplyCount(_replyCount);
			index.setSupportCount(_supportCount);
			index.setIstop(false);
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("AbstractService.getIndex: "+e.getMessage());
			return null;
		}	
		
		
		return index;
		
	}
	/**
	 * 封装索引需要信息
	 * TODO 这个多线程调用是否存在问题
	 * @param jsonObject
	 * @return
	 * 
	 */
	public synchronized Index2 getIndex3(JSONObject jsonObject,IndustryInfoService indusService) {
		Index2 index2 = new Index2();
		try {
			String url = null == jsonObject.get("id") ? "" : String.valueOf(jsonObject.get("id")); 
			String id = "";
			if(StringUtil.isEmpty(url)){
				byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
//				System.out.println(Encodes.encodeHex(salt));
				byte[] hashPassword = Digests.sha1("123456".getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
						Encodes.HASH_INTERATIONS);
//				System.out.println(Encodes.encodeHex(hashPassword));
				
				 id =  Encodes.encodeHex(hashPassword);
			}
			Long sid = Util.getSiteId(jsonObject);
			/*if(sid<20209||sid>20268){
				return null;
			}*/
			//地域
			String area = Util.getArea(jsonObject);
			//作者
			String articleLink = Util.getArticleLink(jsonObject);
			//原文链接
			String author = Util.getAuthor(jsonObject);
			//举办地点
			String adress = Util.getAdress(jsonObject);
			
			//涉及行业
			String bus = Util.getBus(jsonObject);
			//会展时间
			String exhibitiontime = Util.getExhibitiontime(jsonObject);
			//内容
			String content = Util.getContent(jsonObject);
			if(StringUtil.isEmpty(area)){
				Set<String> set = Util.getArea(adress, "");
				if(set.size()<=0){
					area = adress;
				}else{
					area = set.iterator().next();
				}
				
			}
			//维度
			String dimension = Util.getDimension(jsonObject);
			//点击数
			Long hitCount = Util.getHitCount(jsonObject);
			//logo
			String logo = Util.getLogo(jsonObject);
			Long replyCount = Util.getReplyCount(jsonObject);
			Long supportCount = Util.getSupportCount(jsonObject);
			String source = Util.getSource(jsonObject);
			String vector = Util.getVector(jsonObject);
			String site = Util.getSite(jsonObject);
			if(StringUtil.isEmpty(source)){
				source = site;
			}
			if(StringUtil.isEmpty(vector)||vector.equals("新闻")){
				vector = site;
			}
//			String gatherTime = Util.getGatherTime(jsonObject);
			String publishDate = Util.getPublishDate(jsonObject);
			
			if(StringUtil.isEmpty(publishDate)){
				//如果发布时间为空，将采集时间赋值给发布时间
				String[] split = exhibitiontime.split("-");
				if(split.length!=0){
					publishDate=split[0];
				}

			}
			
			String publishTime = Util.getPublishTime(publishDate);
			String publishYear = Util.getPublishYear(publishDate);
			String title = Util.getTitle(jsonObject);
			if(StringUtil.isEmpty(area)||area.equals("全国")){
				Set<String> area2 = Util.getArea(title, content);
				Object[] array = area2.toArray();
				if(array.length>0){
					area = array[0].toString();
				}else{
					area="全国";
				}
			}
			List<String> business = Util.getBusiness(title, content);
			String business1 = Util.getBusiness1(business);
			String summary = Util.getSummary(jsonObject);
			if(StringUtil.isEmpty(summary)){
					summary = SimpleSummariserAlgorithm.summarise(StringUtils.rmHtmlTag(content), 3);
			}
			String industryInfo = Util.getIndustryInfo(jsonObject);
			String industryLeabl = Util.getIndustryLeabl(jsonObject, industryInfo);
			if(StringUtil.isEmpty(exhibitiontime)&&StringUtil.isEmpty(adress)){
				return null;
			}
			IndustryInfo indus = null;
			if(StringUtil.isEmpty(industryLeabl)){
				return null;
			}else{
				
				indus = indusService.getIndusbyIndustryLabel(industryLeabl);
			}
			if(indus.getIndustryThree() == null||StringUtil.isEmpty(indus.getIndustryThree())){
				log.info("查找产业分类信息失败！");
				return null;
			}else{
//				index2.setIdustryThree(indus.getIndustryThree());
				index2.setIdustryTwice(indus.getIndustryTwo());
				index2.setIdustryZero(indus.getIndustryOne());
			}
			index2.setId(id);
			index2.setAddress(adress);
			index2.setArea(area);
			index2.setArticleLink(articleLink);
			index2.setAuthor(author);
			index2.setBus(bus);
			index2.setBusiness(business1);
			index2.setContent(content);
			index2.setDimension(dimension);
			index2.setEmotion("");
			index2.setExhibitiontime(exhibitiontime);
			index2.setHasWarn(false);
			index2.setHitCount(hitCount);
			index2.setIdustryThree(industryLeabl);
			
			index2.setIstop(false);
			index2.setLogo(logo);
			index2.setPublishDate(publishDate);
			index2.setPublishTime(publishTime);
			index2.setPublishYear(publishYear);
			index2.setReplyCount(replyCount);
			index2.setSource(source);
			index2.setSummary(summary);
			index2.setSupportCount(supportCount);
			index2.setTitle(title);
			index2.setVector(vector);
			
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("AbstractService.getIndex: "+e.getMessage());
			return null;
		}
		
		return index2;
	}
	@SuppressWarnings("unchecked")
	public synchronized Index3 getIndex4(JSONObject jsonObject,IndustryInfoService indusService) {
		
		Index3 index3 = new Index3();
		//去重
		boolean info = indusService.removalInfo(jsonObject);
		if(!info){
		}
		try {
			String url = null == jsonObject.get("id") ? "" : String.valueOf(jsonObject.get("id")); 
			String id = "";
			if(StringUtil.isEmpty(url)){
				byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
//				System.out.println(Encodes.encodeHex(salt));
				byte[] hashPassword = Digests.sha1("123456".getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
						Encodes.HASH_INTERATIONS);
//				System.out.println(Encodes.encodeHex(hashPassword));
				
				 id =  Encodes.encodeHex(hashPassword);
			}
//			String gatherTime = Util.getGatherTime(jsonObject);
			String publishDate = Util.getPublishDate(jsonObject);
			
//			if(StringUtil.isEmpty(publishDate)){
//				//如果发布时间为空，将采集时间赋值给发布时间
//				publishDate=gatherTime;
//			}
			String lamu = Util.getLamu(jsonObject);
			String publishTime = Util.getPublishTime(publishDate);
			String logo = Util.getLogo(jsonObject);
			String title = Util.getTitle(jsonObject);
			String content = Util.getContent(jsonObject);
			String dimension = Util.getDimension(jsonObject);
			String site = Util.getSite(jsonObject);
			String articleLink = Util.getArticleLink(jsonObject);
			String articleInfo = Util.getBaseInfo(jsonObject);
			String industry = Util.getIndustry(jsonObject);
			
			String invest = Util.getInvest(jsonObject);
			String investor = Util.getInvestor(jsonObject);
			String financiers = Util.getFinanciers(jsonObject);
			String financingAmount = Util.getFinancingAmount(jsonObject);
			String financingCompany = Util.getFinancingCompany(jsonObject);
			String financingDate = Util.getFinancingDate(jsonObject);
			if(content.length()>4000){
				content = content.substring(0, 4000);
			}
		
			if(industry.equals("大数据")){
				industry=industry;
			}else{
				int i = lamu.indexOf("大数据");
				if(i>=0){
					industry="大数据";
				}else{
					int j = site.indexOf("大数据");
					if(j>=0){
						industry="大数据";
					}else{
						String config=Util.getConfigName(jsonObject);
						if(config.indexOf("大数据")>=0){
							industry="大数据";
						}
					}
				}
			}
			if(StringUtil.isEmpty(financiers)&&StringUtil.isEmpty(financingCompany)){
//				return null;
			}else if(StringUtil.isEmpty(financiers)){
				/**
				 * 如果融资方为空，将所属公司的数据存入
				 */
				financiers = financingCompany;
			}else if(StringUtil.isEmpty(financingCompany)){
				 financingCompany = financiers ;
			}
			String area = Util.getAreaBy(jsonObject);
			
			if(dimension.equals("融资快讯")){
				String str = "$融资快讯";
				Long dId = Util.getDimensionId(jsonObject, str);
				if(35002001<=dId && dId <=35002005){
					dimension = "融资动态";
				}
				if(StringUtil.isEmpty(industry)){
					JSONObject keyword = Analysis.findKeyword(title);
					if(keyword.getBooleanValue("status")){
						Set<String> set = (Set<String>) keyword.get("result");
						if(set.isEmpty()){
							return null ; 
						}
						industry = set.iterator().next();
					}else{
						keyword = Analysis.findKeyword(lamu);
						if(keyword.getBooleanValue("status")){
							Set<String> set = (Set<String>) keyword.get("result");
							industry = set.iterator().next();
						}else{
							return null;
						}
					}
				} 
				
			}
			
			index3.setId(id);
			index3.setPublishDate(publishDate);
			index3.setPublishTime(publishTime);
			index3.setLogo(logo);
			index3.setTitle(title);
			index3.setContent(content);
			index3.setDimension(dimension);
			index3.setVector(site);
			index3.setArticleInfo(articleInfo);
			index3.setArticleLink(articleLink);
			//融资方
			index3.setFinanciers(financiers);
			//投资金额
			index3.setFinancingAmount(financingAmount);
			//融资公司
			index3.setFinancingCompany(financingCompany);
			//融资时间
			index3.setFinancingDate(financingDate);
			//投资方
			index3.setInvestor(investor);
			//所属产业
			index3.setIndustry(industry);
			//融资轮次
			index3.setInvest(invest);
			index3.setArea(area);
			
		} catch (Exception e) {
			return null;
		}
		return index3;
	}
}

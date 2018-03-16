package com.huishu.aitanalysis.service.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.common.AcquisitionConstant;
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
	 * 封装索引需要信息
	 * TODO 这个多线程调用是否存在问题
	 * @param jsonObject
	 * @return
	 * 搜索设置分析程序
	 */
	public synchronized Index getIndex1(JSONObject jsonObject,ParkAnalysisService service){
		Index index = new Index();
		try {
			Map<String, Object> map = getIndexInfo(jsonObject);
			//关键词
			String keyWord = Util.getKeyWord(jsonObject);
			//根据关键词判断是否是园区
			boolean info = service.isParkName(keyWord);
			if(info){
				//预警信息
				boolean warning = StringUtils.isWarning(keyWord);
				if(warning){
					String park ="";
					if(keyWord.equals("雄安新区")){
						 park ="雄安新区";
					}else{
						//**根据公司名称获取公司全称mysql*//*
						 park ="天津中新生态城";
					}
					String _dimension = "疑似外流";
					//根据关键词返回公司名称
					String business = StringUtils.getBusiness(keyWord);
					map.put("dimension", _dimension);
					map.put("business", business);
					map.put("park", park);
				}else{
					String park ="";
					if(keyWord.equals("雄安新区")){
						 park ="雄安新区";
					}else{
						/**根据公司名称获取公司全称mysql*/
						 park = keyWord;
					}
					String _dimension = "园区动态";
					map.put("dimension", _dimension);
					map.put("business", "");
					map.put("park", park);
				}
			}else{
				//非园区数据，判断是否为产业动态全网搜数据
				if(abstractkeyword.contains(keyWord)){
					//包含关键词，进行产业头条数据维护
					//维度
					String _dimension = "产业头条";
					//产业标签  -- 关键词
					JSONObject obje = new JSONObject();
					JSONArray array = new JSONArray();
					obje.put("Name", keyWord);
					array.add(obje);
					//产业
					String industry = Util.getIndustryByIndestryLeabl(array);
					
					map.put("dimension", _dimension);
					map.put("business", "");
					map.put("park", "");
					map.put("industry", industry);
					map.put("industryLabel", keyWord);
				}else{
					return null;
				}
			}
			index = addIndexInfo(map,index);
			
		} catch (Exception e) {
			log.error("AbstractService.getIndex1: "+e.getMessage());
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
			Map<String, Object> map = getIndexInfo(jsonObject);
			index = addIndexInfo(map, index);
		} catch (Exception e) {
			log.error("AbstractService.getIndex2: "+e.getMessage());
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
			Map<String, Object> map = getIndexInfo(jsonObject);
			index = addIndexInfo(map, index);
			if(map.get("dimension").equals("园区政策")||map.get("dimension").equals("园区动态")){
				index.setPark(map.get("site").toString());
			}
		} catch (Exception e) {
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
	 * 2.0版本峰会信息
	 */
	public synchronized Index2 getIndex3(JSONObject jsonObject,IndustryInfoService indusService) {
		Index2 index2 = new Index2();
		try {
			Map<String, Object> map = getIndexInfo(jsonObject);
			index2 = addIndex2Info(map,index2, indusService);
		} catch (Exception e) {
			log.error("AbstractService.getIndex: "+e.getMessage());
			return null;
		}
		
		return index2;
	}
	
	/**
	 * 获取融资数据
	 * @param jsonObject
	 * @param indusService
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized Index3 getIndex4(JSONObject jsonObject,IndustryInfoService indusService) {
		Index3 index3 = new Index3();
		try {
			Map<String, Object> map = getIndexInfo(jsonObject);
			String industry = map.get("industry").toString();
			String lamu = map.get("lamu").toString();
			String site = map.get("site").toString();
			String financingCompany = map.get("financingCompany").toString();
			String financiers = map.get("financiers").toString();
			String title = map.get("title").toString();
			if(industry.equals("大数据")){
				map.put("industry", industry);
			}else{
				int i = lamu.indexOf("大数据");
				if(i>=0){
					map.put("industry", "大数据");
				}else{
					int j = site.indexOf("大数据");
					if(j>=0){
						map.put("industry", "大数据");
					}else{
						String config=Util.getConfigName(jsonObject);
						if(config.indexOf("大数据")>=0){
							map.put("industry", "大数据");
						}
					}
				}
			}
			if(StringUtil.isEmpty(financiers)&&StringUtil.isEmpty(financingCompany)){
			}else if(StringUtil.isEmpty(financiers)){
				/**
				 * 如果融资方为空，将所属公司的数据存入
				 */
				map.put("financiers", financingCompany);
			}else if(StringUtil.isEmpty(financingCompany)){
				map.put("financingCompany", financiers);
			}
		
			if( map.get("dimendion").toString().equals("融资快讯")){
				Long dId = Util.getDimensionId(jsonObject, Util.Financial_news);
				if(35002001<=dId && dId <=35002005){
					map.put("dimension", "融资动态");
				}
				if(StringUtil.isEmpty(industry)){
					JSONObject keyword = Analysis.findKeyword(title);
					if(keyword.getBooleanValue("status")){
						Set<String> set = (Set<String>) keyword.get("result");
						if(set.isEmpty()){
						}
						map.put("industry", set.iterator().next());
					}else{
						keyword = Analysis.findKeyword(lamu);
						if(keyword.getBooleanValue("status")){
							Set<String> set = (Set<String>) keyword.get("result");
							map.put("industry", set.iterator().next());
						}else{
						}
					}
				} 
				
			}
			index3 = addIndex4Info(map,index3);
		} catch (Exception e) {
			return null;
		}
		return index3;
	}
	

	public Map<String,Object> getIndexInfo(JSONObject jsonObject){
		Map<String,Object> map = new HashMap<String,Object>();
		//生成id
		String url = null == jsonObject.get("id") ? "" : String.valueOf(jsonObject.get("id")); 
		String id = "";
		if(StringUtil.isEmpty(url)){
			byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
			byte[] hashPassword = Digests.sha1(AcquisitionConstant.PASSWORD.getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
					Encodes.HASH_INTERATIONS);
			 id =  Encodes.encodeHex(hashPassword);
		}
		//网站名称
		String site = Util.getSite(jsonObject);
		//产业信息
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
		//点击数
		Long _hitCount = Util.getHitCount(jsonObject);
		//发布时间 (yyyy-MM-dd HH:mm:ss)
		String publishDate = Util.getPublishDate(jsonObject);
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
		//文章摘要
		String _summary = Util.getSummary(jsonObject);
		//标题
		String _title = Util.getTitle(jsonObject);		
		//获取公司名称
		List<String> business = Util.getBusiness(_title, _content);
		String business1 = Util.getBusiness1(business);
		
		//支持数
		Long _supportCount = Util.getSupportCount(jsonObject);
		//采集logo
		String _logo = Util.getLogo(jsonObject);
		//举办地点
		String _adress = Util.getAdress(jsonObject);
		//涉及行业
		String _bus = Util.getBus(jsonObject);
		//会展时间
		String _exhibitiontime = Util.getExhibitiontime(jsonObject);
		//栏目
		String _lamu = Util.getLamu(jsonObject);
		//轮次
		String invest = Util.getInvest(jsonObject);
		//投资方
		String investor = Util.getInvestor(jsonObject);
		//融资方
		String financiers = Util.getFinanciers(jsonObject);
		//融资额
		String financingAmount = Util.getFinancingAmount(jsonObject);
		//所属公司
		String financingCompany = Util.getFinancingCompany(jsonObject);
		//融资时间
		String financingDate = Util.getFinancingDate(jsonObject);
		//详情信息
		String _baseInfo = Util.getBaseInfo(jsonObject);
		if(StringUtil.isEmpty(_source)){
			_source = site;
		}
		if(StringUtil.isEmpty(_vector)||_vector.equals("新闻")){
			_vector = site;
		}
		if(StringUtil.isEmpty(_summary)){
			//截取内容的前100个字做摘要的内容
			_summary = SimpleSummariserAlgorithm.summarise(StringUtils.rmHtmlTag(_content), 3);
		}
		//文本过长，截取一部分展示
		if(_content.length()>4000){
			_content = _content.substring(0, 4000);
		}
		if(_area.equals("全国")||_area.equals("")){
			Set<String> area = null;
			//高峰论坛
			if(_dimension.equals(AcquisitionConstant.SUMMIT)){
				area = Util.getArea(_title+_adress, _content);
			}else if(_dimension.equals(AcquisitionConstant.FINAACIAL_NEWS)){
				area = Util.getArea(_title+financingCompany+financiers, _content);
			}else{
				area = Util.getArea(_title, _content);
			}
			Object[] a= area.toArray();
			if(a.length>0){
				_area = a[0].toString();
			}else{
				_area="全国";
			}
		}
		map.put("id", id);
		map.put("site", site);
		map.put("industryInfo",industryInfo);
		map.put("area", _area);
		map.put("industry", industry);
		map.put("industryLabel", industryLabel);
		map.put("dimension", _dimension);
		map.put("articleLink", _articleLink);
		map.put("sourceLink", _articleLink);
		map.put("author",  _author);
		map.put("hitCount", _hitCount);
		map.put("content", _content);
		map.put("publishDate", publishDate);
		map.put("publishTime", publishTime);
		map.put("publishYear", publishYear);
		map.put("replyCount", _replyCount);
		map.put("vector", _vector);
		map.put("source", _source);
		map.put("summary", _summary);
		map.put("title", _title);
		map.put("supportCount",_supportCount);
		map.put("address", _adress);
		map.put("bus", _bus);
		map.put("exhibitiontime",_exhibitiontime);
		map.put("lamu", _lamu);
		map.put("invest", invest);
		map.put("investor", investor);
		map.put("financiers", financiers);
		map.put("financingAmount", financingAmount);
		map.put("financingCompany", financingCompany);
		map.put("financingDate", financingDate);
		map.put("baseInfo", _baseInfo );
		map.put("logo", _logo );
		//涉及公司
		map.put("business",business1);
		map.put("park","");
		return map;
	}
	/**
	 * @param map
	 * @param index
	 * @return
	 */
	private Index addIndexInfo(Map<String, Object> map, Index index) {
		index.setId(map.get("id").toString());
		index.setEngageState("");
		index.setIdentity("");
		index.setRegisterCapital("");
		index.setRegisterData("");
		index.setAddress(map.get("address").toString());
		index.setEstablishTime("");
		index.setPark(map.get("park").toString());
		index.setAcreage("");
		index.setBusiness(map.get("business").toString());
		index.setIndustryType("");
		index.setUpdateAttribute("");
		index.setBoss("");
		index.setBusinessType("");
		index.setHasWarn(true);
		index.setLogo(map.get("logo").toString());
		index.setArea(map.get("area").toString());
		index.setIndustry(map.get("industry").toString());
		index.setIndustryLabel(map.get("industryLabel").toString());
		index.setArticleLink(map.get("articleLink").toString());
		index.setAuthor(map.get("author").toString());
		index.setContent(map.get("content").toString());
		index.setSource(map.get("source").toString());
		index.setDimension(map.get("dimension").toString());
		index.setVector(map.get("vector").toString());
		index.setTitle(map.get("title").toString());
		index.setSummary(map.get("summary").toString());
		index.setSourceLink(map.get("sourceLink").toString());
		index.setPublishDate(map.get("publishDate").toString());
		index.setPublishTime(map.get("publishTime").toString());
		index.setPublishYear(map.get("publishYear").toString());
		index.setHitCount((Long) map.get("hitCount"));
		index.setReplyCount((Long) map.get("replyCount"));
		index.setSupportCount((Long) map.get("supportCount"));
		index.setIstop(false);
		return index;
	}
	/**
	 * @param map
	 * @param index2
	 * @return
	 * 为index2赋值
	 */
	private Index2 addIndex2Info(Map<String, Object> map, Index2 index2,IndustryInfoService indusService) {
		IndustryInfo indus = null;
		if(StringUtil.isEmpty(map.get("industryLeabl").toString())){
			return null;
		}else{
			if(map.get("industryLeabl").toString().equals("物联网")||map.get("industryLeabl").toString().equals("生物医药")){
				System.out.println(map.get("industryLeabl").toString());
			}else{
				if(StringUtil.isEmpty(map.get("exhibitiontime").toString())&&StringUtil.isEmpty(map.get("adress").toString())){
					return null;
				}
			}
			indus = indusService.getIndusbyIndustryLabel(map.get("industryLeabl").toString());
		}
		if(indus.getIndustryThree() == null||StringUtil.isEmpty(indus.getIndustryThree())){
			log.info("查找产业分类信息失败！");
			return null;
		}else{
			index2.setIdustryThree(indus.getIndustryThree());
			index2.setIdustryTwice(indus.getIndustryTwo());
			index2.setIdustryZero(indus.getIndustryOne());
		}
		index2.setId(map.get("id").toString());
		index2.setAddress(map.get("address").toString());
		index2.setArea(map.get("area").toString());
		index2.setArticleLink(map.get("articleLink").toString());
		index2.setAuthor(map.get("author").toString());
		index2.setBus(map.get("bus").toString());
		index2.setBusiness(map.get("business").toString());
		index2.setContent(map.get("content").toString());
		index2.setDimension(map.get("dimension").toString());
		index2.setEmotion("");
		index2.setExhibitiontime(map.get("exhibitiontime").toString());
		index2.setHasWarn(false);
		index2.setHitCount((Long)map.get("hitCount"));
//		index2.setIdustryThree(industryLeabl);
		index2.setIstop(false);
		index2.setLogo(map.get("logo").toString());
		index2.setPublishDate(map.get("publishDate").toString());
		index2.setPublishTime(map.get("publishTime").toString());
		index2.setPublishYear(map.get("publishYear").toString());
		index2.setReplyCount((Long)map.get("replyCount"));
		index2.setSource(map.get("source").toString());
		index2.setSummary(map.get("summary").toString());
		index2.setSupportCount((Long)map.get("supportCount"));
		index2.setTitle(map.get("title").toString());
		index2.setVector(map.get("vector").toString());
		return index2;
	}
	/**
	 * @param map
	 * @param index3
	 * @return
	 */
	private Index3 addIndex4Info(Map<String, Object> map, Index3 index3) {
		index3.setId(map.get("id").toString());
		index3.setPublishDate(map.get("publishDate").toString());
		index3.setPublishTime( map.get("publishTime").toString());
		index3.setLogo( map.get("logo").toString());
		index3.setTitle( map.get("title").toString());
		index3.setContent( map.get("content").toString());
		index3.setDimension( map.get("dimension").toString());
		index3.setVector( map.get("site").toString());
		index3.setArticleInfo( map.get("baseInfo").toString());
		index3.setArticleLink( map.get("articleLink").toString());
		//融资方
		index3.setFinanciers( map.get("financiers").toString());
		//投资金额
		index3.setFinancingAmount( map.get("financingAmount").toString());
		//融资公司
		index3.setFinancingCompany( map.get("financingCompany").toString());
		//融资时间
		index3.setFinancingDate( map.get("financingDate").toString());
		//投资方
		index3.setInvestor( map.get("investor").toString());
		//所属产业
		index3.setIndustry( map.get("industry").toString());
		//融资轮次
		index3.setInvest( map.get("invest").toString());
		index3.setArea(map.get("area").toString());
		return index3;
	}
}

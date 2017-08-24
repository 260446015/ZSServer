package com.huishu.ait.service;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.UtilsHelper;
import com.huishu.ait.echart.series.Serie.SerieData;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.security.Digests;
import com.huishu.ait.security.Encodes;
import com.huishu.ait.security.RSAUtils;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;


/**
 * @author hhy
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public abstract class AbstractService {

	@Autowired
	private Client client;
	
	@Autowired
	protected ElasticsearchTemplate template;

	/**
	 * 媒体聚焦--获取载体分布统计环形图
	 * 
	 * @param p
	 * @return
	 */
	protected JSONArray getVectorDistribution(QueryBuilder queryBuilder) {
		
		TermsBuilder vectorBuilder = AggregationBuilders.terms("vector").field("vector");

		TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink");
		articleBuilder.subAggregation(vectorBuilder).size(1000);
		SearchQuery query = getSearchQueryBuilder().addAggregation(articleBuilder).withQuery(queryBuilder).build();
		JSONArray result = template.query(query, res -> {
			Terms articleLink = res.getAggregations().get("articleLink");
			String key = null;
			long count = 0;
			JSONArray  json = new JSONArray();
			Map<String ,Object> map = new HashMap<String, Object>();
			if (articleLink != null) {
				for (Terms.Bucket e1 : articleLink.getBuckets()) {
					Terms vector = e1.getAggregations().get("vector");
					for (Bucket bucket : vector.getBuckets()) {
					    key = bucket.getKeyAsString();
					    count = bucket.getDocCount();
					    if(map.get(key) != null ){
					    	map.put(key, (long)map.get(key)+count);
					    }else{
					    	map.put(key, count);
					    }
					}	
				}
				for(Entry<String,Object> entry : map.entrySet()){
					SerieData<Long> data = new SerieData<>(entry.getKey(),(Long)entry.getValue());
					json.add(data);
				}
			}
			return json;
		});
		return result;
	}
    /**
     *  产业头条--关键词云
     */
	@SuppressWarnings("static-access" )
	protected JSONArray getCloudWordList(QueryBuilder queryBuilder){
		List<String> contentList = new ArrayList<String>();
		JSONArray data = new JSONArray();
		SearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).build();
		data =  template.query(query, res ->{
			JSONArray jsonArray = new JSONArray();
			Integer  wordCloudNum = 50;
			SearchHits hits = res.getHits();
			if( hits != null){
				SearchHit[] hitsList = hits.getHits();
				for(SearchHit  h : hitsList){
				    if(h.getSource()!= null){
						contentList.add(h.getSource().get("content")+"");
					}
				}
			}
			contentList.add("'<img alt=\'\\\" src=\"http://news.edianshang.com/uploadfile/2017/0111/20170111094927651.jpg\" />\n马云与宗庆后两位零售大佬之间&ldquo;虚实之争&rdquo;的硝烟还未散尽，国务院总理李克强对实体经济的再定位让各界人士有了答案。在2017年第一次国务院常务会议上李克强表示，网店、快递业都是新经济，它们既拉动了消费也促进了生产。&ldquo;这些典型的新经济行业，实际上都是&lsquo;生产性服务业&rsquo;，都是在为实体经济服务，也是实体经济的一部分。&rdquo;当新经济与实体经济的界限被打破，市场的增量空间再度被放大。\n对新经济身份的明确，让以电商为代表的新经济体也有了全新的定位与目标。按照总理所说，&ldquo;实体经济&rdquo;是一个相对于&ldquo;虚拟经济&rdquo;的概念，不仅包含制造业，更涵盖着一二三产业。纵观实体经济与电商等商业新模式之间走过的不同发展阶段，从最初的对立冲击，但如今的合二为一。互联网的工具属性越发明显，这也意味着，2017年电商行业的发展将越加回归经济的本质，而更加透彻的理解新经济，将有助于电商企业在未来的发展中更好地把握市场风向。\n关键词：适应\n要用新经济、新业态、新动能的蓬勃力量，推动传统产业尤其是实体经济更好地适应经济转型，从而提高竞争力，更好地满足人民群众不断提升的消费需求。\n壮大新经济、发展新动能是对传统动能改造的过程。李克强指出，近年来不少新经济、新业态的出现让传统产业的生产、流通和经营模式上都发生了变化。放在零售行业而言，不论是传统经济还是电商经济，在新的市场环境下都需要一个适应的过程。\n可以注意到，在当前的线上零售格局中，越来越多的传统企业开始露出峥嵘。从苏宁、国美等家电卖场的纷纷转型，到王府井、海尔、步步高、大润发等一批零售企业加速拓展自己的线上渠道。尽管从市场份额上看，阿里和京东依旧占据着网络零售额的绝大多数，但对于传统零售企业自身而言，却是适应新经济过程的体现。\n传统的电商企业也需要适应新经济。电子商务被马云形容为一只&ldquo;摆渡船&rdquo;，而融入大数据、人工智能的新模式在未来将取而代之。多位零售业专家曾表示，当前的新模式放在不远后的将来也会成为旧模式，这需要企业自身做出改变。就以阿里而言，从2016年底到2017年初，阿里对自身的零售体系做了两次内部架构调整，一次是将聚划算并入天猫，创新营销体系;一次是调整淘宝架构，建立产品、数据技术和运营相结合的&ldquo;三位一体&rdquo;运营方式，强化淘宝卖家的原创属性。\n&ldquo;大数据融合、技术叠加让企业运营效率得到提升，观察当前规模企业的发展历程，都曾在产品质量、营销方式等方面打破常规才有了当前的市场地位。&rdquo;在零售业专家、灵兽传媒创始人陈岳峰看来，大的市场环境正在进入转型期，而企业也会遇到转型中的一些问题，这是一个需要适应的过程。\n关键词：带动\n部分依靠传统动能的实体经济产业，确实面临不适应市场变化的问题。怎么破解这一问题?还要让传统产业插上新经济的&ldquo;翅膀&rdquo;，用新动能带动传统动能转型升级。\n&ldquo;流通带动生产&rdquo;的革命性变化在零售业发展中不乏案例。李克强表示，沃尔玛等大超市就曾改变过去零售商店的采购模式，直接向厂家低价格采购大量订单，带动了实体经济发展。而现在，很多网店直接向工厂下订单、定制化生产，同样带动了大量制造业工厂的发展。\n珠宝首饰供应链平台款多多创始人王文钢表示，新经济的本质没有太多创新，无论是电商还是传统零售的本质都是卖货，把合适的商品卖到消费者手中，关键在于信息流的改变。&ldquo;传统零售业都说顾客是上帝，但是很难知道顾客到底需要什么，而通过信息流的反向操作，用互联网原有的前端需求引导生产，将有效提升企业的经营效率。&rdquo;王文钢称，新经济与当前倡导的供给侧改革有相通之处，都是要按需求解决供给。据了解，王文钢在创办款多多之前的上一个项目是家纺领域的品牌电商优曼家纺，但在他看来，互联网已经不是单纯的电商销售问题，而是渗透到了生产、加工、服务等流通的各环节，数据传输、精准化管理、高效沟通机制的应用对实体经济来说都将适用。\n当新经济与实体经济的界限被打破，互联网的工具属性凸显。&ldquo;一瓶矿泉水原来的采购价是1元，最终零售价可能是2元，但如何让消费者认识到这瓶矿泉水是来自优质的水源地、富含矿物质营养、对身体有好处，最终可能会为此支付3元甚至更高的价格。&rdquo;海尔电商总裁兼顺逛微店CEO宋宝爱表示，如果说在原来的商品流通中品牌商只是简单的卖货，现在海尔会把消费者的需求进行汇总，反馈到研发团队指导生产。\n关键词：监管\n要适应新动能加速成长的需要，探索包容创新的审慎监管制度，对新产业、新业态采取既具弹性又有规范的管理措施。监管要有规范，同时也要有一定的灵活度。\n新经济的快速发展成为中国经济发展的新引擎，但在推动经济发展的同时也有诸如假货、漏税等问题随之产生。\n去年底，淘宝网再度被列入美国&ldquo;恶名市场&rdquo;名单，尽管有声音提出这背后受到国际贸易氛围因素的影响，但也从一定程度上反映了国内电商企业的现状。陈岳峰表示，新经济的概念是伴随电商在国内经济地位的愈发稳定而生，随着电商发展进入成熟期，诸如商品品质、偷漏税等问题的监管都在被逐步完善。\n根据阿里此前披露的2016年度纳税数据显示，阿里巴巴集团以及蚂蚁金服集团2016年度合计纳税238亿元，带动平台纳税至少2000亿元，创造了超过3000万的就业机会。陈岳峰认为，在电商飞速发展、实体经济低迷之际，有观点提出中国经济&ldquo;脱实入虚&rdquo;的现象愈演愈烈，需要警惕其中的资产泡沫。但可以注意到，电商行业在走出最初的萌芽期之后确实带动了就业，促进了实体经济的发展。政府的导向相当于给电商企业送来了一颗定心丸，通过构建更好的商业环境，让企业可以尽情发展。\n在零售业的发展历史中，连锁商业的出现是对消费品质的一次升级，而电商的普及是对零售业的又一次提升。新经济新在何处?仅就网店而言，在经过最近十年的快速发展后，很难再说它是新鲜事物。但放到国内整体的商业结构中，不论从促进经济增长、带动就业还是引导生产等方面来看，都有其新颖之处。陈岳峰表示，新经济的概念是伴随电商在国内经济地位的愈发稳定而生。\n<img src=\"http://img.edianshang.com/images/code.jpg\">\n第一时间获取电商行业新鲜资讯和深度商业报道，请在微信公众账号中搜索「E电商」或者「Edianshang123」，或用手机扫描左方二维码，即可获得E电商每日精华内容推送和最优搜索体验，并参与编辑活动。");
			JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, wordCloudNum);
			if(keywordCloud.getBooleanValue("status")){
				jsonArray.add((List<KeywordModel>)keywordCloud.get("result"));
			}
			
			return  jsonArray;
		});
		return data;
	}
	
	
	/**
	 * 查询es库，获取更多条件查询
	 * 
	 * @return
	 */
	protected NativeSearchQueryBuilder getSearchQueryBuilder() {
		return new NativeSearchQueryBuilder().withIndices(INDEX).withTypes(TYPE);
	}

	/**
	 * 建立查询条件筛选
	 */
	protected BoolQueryBuilder getIndustryContentBuilder(HeadlinesDTO headlinesDTO) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		/** 产业 */
		String industry = headlinesDTO.getIndustry();
		if (StringUtils.isNotEmpty(industry)) {
			bq.must(QueryBuilders.termQuery("industry", industry));
		}
		
		/** 产业标签 */
		String industryLabel = headlinesDTO.getIndustryLabel();
		if(StringUtils.isNotEmpty(industryLabel)){
			bq.must(QueryBuilders.termQuery("industryLabel",industryLabel));
		}

		
		/** 载体 */
		String vector = headlinesDTO.getVector();
		if (StringUtils.isNotEmpty(vector)) {
			bq.must(QueryBuilders.termQuery("vector", vector));
		}
		String dimension = headlinesDTO.getDimension();
		if(StringUtils.isNotEmpty(dimension)){
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		/**关键词*/
		String keyword = headlinesDTO.getKeyWord();
		if(StringUtils.isNotEmpty(keyword)){
			bq.must(QueryBuilders.wildcardQuery("content", "*"+keyword+"*"));
//			bq.must(QueryBuilders.fuzzyQuery("content", keyword));
		}
		/** 时间 */
		String startDate = headlinesDTO.getStartDate();
		String endDate = headlinesDTO.getEndDate();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startDate).to(endDate));
		}
		return bq;
	}
	
	/**
	 * 今日头条，排行前十
	 * @param bq
	 * @param object
	 * @param pageable
	 * @return
	 */
	protected Page<HeadlinesArticleListDTO> getArticleRank(BoolQueryBuilder bq, Object object,
			Pageable pageable) {
		// 第一步：按照声量排序取前500个文章
		TermsBuilder articleLinkBuilder = AggregationBuilders.terms("articleLink").field("articleLink")
				.order(Order.count(false)).size(500);
		
		SearchQuery authorQuery = getSearchQueryBuilder().withQuery(bq).addAggregation(articleLinkBuilder)
				.build();

		List<HeadlinesArticleListDTO> jsonArray = template.query(authorQuery, res -> {
			List<HeadlinesArticleListDTO> list = new ArrayList<HeadlinesArticleListDTO>();
			
		    	Map<String, Object> Source= null;
		    	SearchHits hits = res.getHits();
		    	for(SearchHit hit :hits){
		    		Source = hit.getSource();
		    		Source.put("_id", hit.getId());
		    		 getDtoInfo(list,Source);
		    	}
		    	
			return list;
		});

		// 第二步：对结果进行排序，按照热度排序，分页取十条数据
		                    
				int total = jsonArray.size();
				int pageNumber = pageable.getPageNumber();
				int pageSize = pageable.getPageSize();

				pageable.getSort().forEach(order -> {
					String property = order.getProperty();
					Direction direction = order.getDirection();
					
					jsonArray.sort((o1, o2) -> {
						Double v1 = (Double) UtilsHelper.getValueByFieldName(o1, property);
						Double v2 = (Double) UtilsHelper.getValueByFieldName(o2, property);

						if (Direction.ASC.equals(direction)) {
							return v1.compareTo(v2);
						}
						return v2.compareTo(v1);
					});
				});

				for (int i = 0; i < jsonArray.size(); i++) {
					jsonArray.get(i).setRank(i + 1);
				}

				List<HeadlinesArticleListDTO> newList = new ArrayList<>();
				jsonArray.stream().skip(pageNumber * pageSize).limit(pageSize).forEach(newList::add);

				Page<HeadlinesArticleListDTO> results = new PageImpl<>(newList, pageable, total);
				return results;
			}
	/**
	 * @param source
	 * @param dto
	 * @return
	 */
	private void  getDtoInfo( List<HeadlinesArticleListDTO> list,Map<String, Object> source) {
		HeadlinesArticleListDTO dto = new HeadlinesArticleListDTO();
		dto.setId(source.get("_id").toString());
		dto.setArticleLink(source.get("articleLink").toString());
		dto.setContent(source.get("content").toString());
		dto.setPublishDate(source.get("publishDate").toString());
		dto.setSource(source.get("source").toString());
		dto.setSourceLink(source.get("sourceLink").toString());
		dto.setTitle(source.get("title").toString());
		Integer hitCount  = (Integer) source.get("hitCount");
		Integer supportCount  = (Integer) source.get("supportCount");
		
		dto.setHitCount( (int) source.get("hitCount"));
		dto.setReplyCount( (int) source.get("replyCount"));
		dto.setSupportCount( (int) source.get("supportCount"));
		dto.setHot(UtilsHelper.getRound(supportCount/hitCount*1.0));
		list.add(dto);
	}
	
	/**
	 * ES的分页查询数据方法
	 * @param searchModel  查询Model
	 * @param termField    查询条件集合
	 * @param notTermField    不包含条件集合
	 * @param orderField   需要排序的字段集合。  PS：请注意先后顺序
	 * @param dataField    返回的数据存在的字段集合。  PS：ID属性默认有
	 * @param isPage    是否分页
	 * @return
	 */
	protected JSONArray getEsData(SearchModel searchModel,Map<String,String> termField,Map<String,String> notTermField,List<String> orderField,List<String> dataField,boolean isPage){
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		//查询条件
		if(null!=termField&&termField.size()!=0){
			for (String key : termField.keySet()) {
				bq.must(QueryBuilders.termQuery(key,termField.get(key)));
			}
		}
		//不包含条件
		if(null!=notTermField&&notTermField.size()!=0){
			for (String key : termField.keySet()) {
				bq.mustNot(QueryBuilders.termQuery(key,termField.get(key)));
			}
		}
		SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
		//按orderField包含的字段降序排列
		if(null!=orderField&&orderField.size()!=0){
			orderField.forEach((string) -> srb.addSort(SortBuilders.fieldSort(string).order(SortOrder.DESC)));
		}
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize*pageNumber).execute().actionGet();
		
		JSONArray rows=new JSONArray();
		JSONArray data=new JSONArray();
		Long total=null; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			total = hits.getTotalHits();
			hits.forEach((searchHit)->{
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
				//获取所需要的字段值
				if(null!=dataField&&dataField.size()!=0){
					for (String string : dataField) {
						obj.put(string,map.get(string));
					}
				}
				rows.add(obj);
			});
		}
		if(isPage){
			searchModel.setTotalSize(Integer.valueOf(total.toString()));
			for (int i = searchModel.getPageFrom(); i < rows.size(); i++) {
				data.add(rows.get(i));
			}
			return data;
		}
		return rows;
		
	}
	
	/**
	 * 获取固定查询条件DEMO
	 * @param gardenName   园区名字
	 * @return
	 */
	protected AreaSearchDTO getAreaSearchDTODemo(String gardenName){
		AreaSearchDTO searchModel = new AreaSearchDTO();
		searchModel.setPark(gardenName);
		searchModel.setPageSize(5);
		return searchModel;
	}
	
	/**
	 * 获取经过解密，加密处理后的密码
	 * @param password
	 * @param salt
	 * @return
	 */
	protected String getPasswordDB(String password,String salt){
		// 获取当前用户的私钥
		Object priKey = SecurityUtils.getSubject().getSession().getAttribute("privateKey");
		String newPassword = null;
		try {
			newPassword = RSAUtils.decryptByPrivateKey(password, (RSAPrivateKey) priKey);
		} catch (Exception e) {
			return null;
		}
		newPassword = new StringBuilder(newPassword).reverse().toString();
		byte[] saltByte = Encodes.decodeHex(salt);
		byte[] passwordByte = Digests.sha1(newPassword.getBytes(), saltByte, Encodes.HASH_INTERATIONS);
		String passwordTrue = Encodes.encodeHex(passwordByte);
		return passwordTrue;
		
	}
}

package com.huishu.ManageServer.service.third.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.entity.dto.dbThird.AttributeDTO;
import com.huishu.ManageServer.entity.dto.dbThird.AttributeInfo;
import com.huishu.ManageServer.entity.dto.dbThird.RelatedDTO;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.WordDataDTO;
import com.huishu.ManageServer.entity.dto.dbThird.addKeyWordDTO;
import com.huishu.ManageServer.repository.third.AttributeRepository;
import com.huishu.ManageServer.repository.third.KeyWordInfoRepository;
import com.huishu.ManageServer.repository.third.KeyWordRelatedRepository;
import com.huishu.ManageServer.repository.third.KeyWordRelatetionRepository;
import com.huishu.ManageServer.repository.third.KeywordTypeRepository;
import com.huishu.ManageServer.repository.third.LogRepository;
import com.huishu.ManageServer.repository.third.ThesaurusRepository;
import com.huishu.ManageServer.service.third.ThesaurusService;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.AttributeEntity;
import com.huishu.ManageServer.entity.dbThird.KeyWordRelatedEntity;
import com.huishu.ManageServer.entity.dbThird.KeywordInfoEntity;
import com.huishu.ManageServer.entity.dbThird.KeywordTypeEntity;
import com.huishu.ManageServer.entity.dbThird.Log;
import com.huishu.ManageServer.entity.dbThird.RelatedWordEntity;
/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 
 */
@Service
@Transactional
public class ThesaurusServiceImpl implements ThesaurusService {
	private static final Logger LOGGER = Logger.getLogger(ThesaurusServiceImpl.class);
	
	@Resource  
	private  ThesaurusRepository rep;
	@Resource
	private KeyWordRelatedRepository krp;
	@Resource
	private LogRepository lrp;
	@Resource
	private AttributeRepository arp;
	@Resource
	private KeywordTypeRepository ktp;
	@Resource
	private KeyWordInfoRepository kip;
	@Resource
	private KeyWordRelatetionRepository kwp;
	
	@Override
	public JSONArray findAllKeyWord() {
		JSONArray obj = new JSONArray();
		
		return obj;
	}

	/**
	 * 查看关键词信息--列表查看
	 */
	@Override
	public Page<WordDataDTO> findByPage(TKeyWordDTO dto) {
		Page<WordDataDTO> page = null;
		List<ThesaurusEntity> list = new ArrayList<ThesaurusEntity>();
		List<WordDataDTO> alist = new ArrayList<WordDataDTO>();
		List<Integer> ids = null;
		Long count = null;
		try {
			//总数，分页的数量集、数据
			Long number = (long) (dto.getPageNumber()*(dto.getPageSize()));
			Pageable pageable = new PageRequest(dto.getPageNumber() ,dto.getPageSize());
			if(dto.getType().equals("0")){
				switch(dto.getSort()){
					case("1"):
						//按照添加时间倒序
						ids = kip.getKeyWordListDESCByTime( number, dto.getPageSize());
						ListRepInfo(list, ids);
						//获取相应的总数，
						count = rep.count();
					  break;
					case("2"):
						//按照添加时间顺序
						ids = kip.getKeyWordListByInsertTime( number, dto.getPageSize());
						ListRepInfo(list, ids);
						//获取相应的总数，
						count = rep.count();
						break;
					case("3"):
						//按照词性热度
						//需要配合调试
					  break;
					case("4"):
						//按照词性复杂度
						//先获取所有属性复杂度高的关键词id
						ids = arp.getKeyWordId(number,dto.getPageSize());
						if(ids.size()==0){
							//按照添加时间倒序
							list = rep.getKeyWordListDESC(number,dto.getPageSize());
						}else{
							ListRepInfo(list, ids);
							pageable = addOrUpdata(dto, list, pageable, ids);
							
						}
						count = rep.count();
					   break;
					default :
						//词性关系复杂度
						List<Integer> all = krp.getKeyWordIdDESC(number, dto.getPageSize());
						if(all.size()==0){
							//按照添加时间倒序
							list = rep.getKeyWordListDESC(number,dto.getPageSize());
						}else{
							ListRepInfo(list, all);
							pageable = addOrUpdata(dto, list, pageable, all);
						}
						count = rep.count();
						break;
				};
				
			}else{
				Long typeId = 	Long.parseLong(dto.getType());
				switch(dto.getSort()){
				case("1"):
					//按照添加时间倒序
					List<Integer> _ids = kip.getKeyWordListDESCByType(typeId , number, dto.getPageSize());
					ListRepInfo(list, _ids);
					//获取相应的总数，
					 count = kip.getCount(typeId);
					 break;
				case("2"):
					//按照添加时间顺序
					ids = kip.getKeyWordListByType(typeId , number, dto.getPageSize());
					ListRepInfo(list, ids);	
					//获取相应的总数，
					count = rep.count();
				  break;
				case("3"):
					//按照词性热度
					//需要配合调试
				  break;
				case("4"):
					//按照词性复杂度
					List<Integer> ll = kip.getWordIdByTypeId(typeId);
					if(ll.size()==0){
						//按照添加时间倒序
						list = rep.getKeyWordListDESC(number,dto.getPageSize());
						
					}else{
						if(dto.getPageNumber()==0){
							//先存词性比较复杂的数据
							ListRepInfo(list, ll);
							pageable = addOrUpdata(dto, list, pageable, ll);
						}else{
								//如果不是第一页，则需要判断词性复杂度的数值多少
								list = rep.getKeyInfoList(ll,number.intValue(), dto.getPageSize().intValue());
						}
					}
					//获取总数即可
					count = rep.count();
				   break;
				default :
					//词性关系复杂度
					List<Integer> info = kip.getWordIdByTypeId(typeId);
						if(info.size()==0){
							//按照添加时间倒序
							list = rep.getKeyWordListDESC(number,dto.getPageSize());
						}else{
							//根据关键词id集合查询所有数据
							List<Integer> into = krp.getKeyWordIdByTypeId(info);
							ListRepInfo(list, into);
							pageable = addOrUpdata(dto, list, pageable, into);
						}
					count = rep.count();
				  break;
			   }
			}
			list.forEach(action->{
				WordDataDTO dtp = new WordDataDTO();
				dtp.setEntntity(action);
				KeywordInfoEntity ent = kip.findByWordId(action.getId());
				KeywordTypeEntity one = ktp.findOne(ent.getTypeId());
				dtp.setKeywordNumber(ent.getWordNumber());
				dtp.setTypeWord(one.getTypeWord());
				alist.add(dtp);
			});
			 page = new PageImpl<>(alist, pageable, count);
			 return page;
		} catch (Exception e) {
			LOGGER.debug("列表查看词库关键词失败！原因是：",e);
			return null;
		}
	}
	
	
	private Pageable addOrUpdata(TKeyWordDTO dto, List<ThesaurusEntity> list, Pageable pageable, List<Integer> ll) {
		//先获取所有的id
		if(list.size()<dto.getPageSize()){
			//在获取剩下部分的数据，存入数据库
			int it = dto.getPageSize().intValue()-ll.size();
			List<ThesaurusEntity> li = rep.getKeyInfoList(ll,0,it);
			list.addAll(li);
		}else{
			pageable = new PageRequest(dto.getPageNumber() ,ll.size());
		}
		return pageable;
	}
	
	private void ListRepInfo(List<ThesaurusEntity> list, List<Integer> ids) {
		for(int i =0;i<ids.size();i++){
			list.add(rep.findOne(ids.get(i).longValue()));
		}
	}

	/**
	 * 获取关联关系
	 */
	@Override
	public JSONObject findRelatedById(String id) {
		JSONObject obj = new JSONObject();
		try{
			//词语id
			Long _id = Long.parseLong(id);
			JSONArray arr = new JSONArray();
			 krp.findByWordId(_id).forEach(action->{
				 JSONObject ent = new JSONObject();
				 RelatedWordEntity findOne = kwp.findOne(action.getRelateId());
				 ThesaurusEntity findOne2 = rep.findOne( action.getRelateWordId());
				 KeywordInfoEntity findOne3 = kip.findByWordId(action.getRelateWordId());
				 ent.put("related", findOne.getRelatetion());//关联关系
				 ent.put("relate_keyword", findOne2.getKeyword());//关键词
				 ent.put("relate_keyword_id", findOne3.getWordNumber());
				 arr.add(ent);
			});
			 obj.put("result", arr);
		} catch (Exception e) {
			LOGGER.debug("根据id查看关联关系失败！原因是：",e);
			obj.put("result", "false");
		}
		return obj;
	}

	/**
	 * 删除词信息
	 */
	@Override
	public boolean deleteRelatedById(String id) {
		try {
			Long _id = Long.parseLong(id);
			//根据id删除所有信息
			//删除关键词的关联关系信息
			kip.delete(kip.findByWordId(_id));
			//删除关键词属性
			arp.delete(arp.findByWordId(_id));
			KeywordInfoEntity ent = kip.findByWordId(_id);
			if(ent!=null){
				//删除关键词信息
				kip.delete(ent);
			}
			//删除关键词
			rep.delete(_id);
		} catch (Exception e) {
			LOGGER.debug("根据id删除关联关系失败！原因是：",e);
			return false;
		}
		return true;
	}

	
	@SuppressWarnings("unused")
	@Override
	public boolean saveOrUpdateInfo(addKeyWordDTO dto) {
		ThesaurusEntity ent = new ThesaurusEntity();
		try {
			  return true;
		} catch (Exception e) {
			LOGGER.error("新增或保存词失败,原因是：",e);
			return false;
		}
		
	}

	
	@Override
	public boolean deleteRelatedInfoById(String id) {
		try {
			long _id = Long.parseLong(id);
			krp.delete(_id);
			return true;
		} catch (Exception e) {
			LOGGER.error("根据id删除关联关系失败,原因是：",e);
			return false;
		}
	}

	@Override
	public boolean printLog(String originalFilename, String message) {
		Log log = new Log();
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdate = sdf.format(date);
		log.setCreateTime(createdate);
		log.setMessage(message);
		log.setName(originalFilename);
		Log save = lrp.save(log);
		if (save == null) {
			return false;
		}
		return true;
	}

	
	@Override
	public boolean addDataInfo(String value) {
		try {
			String[] split = value.split("---");
			String keyword = split[0];
			String type = split[1];
			boolean info = saveOrUpdateData(keyword, type);
			return info;
		} catch (Exception e) {
			LOGGER.error("添加一个实体数据失败,原因是：",e);
			return false;
		}
	}

	/**
	 * 获取词性分类标签信息
	 */
	@Override
	public List<KeywordTypeEntity> getLableInfo() {
		return (List<KeywordTypeEntity>) ktp.findAll();
	}

	/**
	 * 根据id获取词的属性信息
	 * @param id
	 * @return
	 */
	@Override
	public JSONObject findAttributeInfoById(String id) {
		try {
			JSONObject obj = new JSONObject();
			long _id = Long.parseLong(id);
			List<AttributeEntity> list = arp.findByWordId(_id);
			obj.put("attr",list);
			return obj;
		} catch (Exception e) {
			LOGGER.error("根据id查看词属性失败,原因是：",e);
			return null;
		}
	}

	/**
	 * 更新词性信息
	 */
	@Override
	public boolean updateTypeWord(String typeWord) {
		try {
			KeywordTypeEntity ent = new KeywordTypeEntity();
			ent.setTypeWord(typeWord);
			ktp.save(ent);
			return true;
		} catch (Exception e) {
			LOGGER.error("添加词性失败,原因是：",e);
			return  false;
		}
	}
	/**
	 * 保存第一页数据
	 */
	@Override
	public boolean saveOrUpAttributeData(AttributeDTO dto) {
		Long typeId = dto.getTypeId();
		String 	_wordNumber ="";
		try {
			if(typeId==0){
				String typeWord = dto.getTypeWord();
				if(StringUtil.isEmpty(typeWord)){
					return false;
				}
				KeywordTypeEntity ent = new KeywordTypeEntity();
				ent.setTypeWord(typeWord);
				KeywordTypeEntity save = ktp.save(ent);
				
				//根据类型id,判断分类编号属于A,B,C,D,E。。。。。等
				//不是新增类型，则一定存在，只需获取数量值就行
				updateDataSave(dto, save.getId(), _wordNumber);
				return true;
			}else{
				updateDataSave(dto, typeId, _wordNumber);
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("添加词时报错,原因是：",e);
			return false;
		}
	}
	
	private void updateDataSave(AttributeDTO dto,Long typeId,String  _wordNumber) {
		//根据类型id获取当前类型的数量
		Long count = kip.getCount(typeId);
		if(typeId>26){
			//随机取两个值进行匹对
			String ss = StringUtil.LC_FIRST[(typeId.intValue()-1)/10];//取整
			String s = StringUtil.LC_FIRST[(typeId.intValue()-1)%10];//取余
			_wordNumber = ss+ s+(count+1);
		}else{
			String ss = StringUtil.LC_FIRST[(typeId.intValue()-1)];
			_wordNumber = ss+(count+1);
		}
		//增加词
		ThesaurusEntity emt = rep.findByKeyword(dto.getKeyword());
		if(emt == null){
			ThesaurusEntity tity = new ThesaurusEntity();
			tity.setKeyword(dto.getKeyword());
			/*tity.setDescribe(dto                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ());
			tity.setInsertTime(new Date());*/
			emt = rep.save(tity);
		}
		Long keywordId = emt.getId();
		//增加编号
		KeywordInfoEntity info = kip.findByWordId(emt.getId());
		if(info == null){
			KeywordInfoEntity entity = new KeywordInfoEntity();
			entity.setInsertTime(new Date());
			entity.setTypeId(typeId);
			entity.setWordId(emt.getId());
			entity.setWordNumber(_wordNumber);
			kip.save(entity);
		}
		//增加属性值		
		List<AttributeInfo> msg = dto.getMsg();
		List<AttributeEntity> list = new ArrayList<AttributeEntity>();
		msg.forEach(action->{
			AttributeEntity into = new AttributeEntity();
			into.setAttributeName(action.getAttributeName());
			into.setAttributeValue(action.getAttributeValue());
			into.setWordId(keywordId);
			list.add(into);
		});
		arp.save(list);
	}

	/**
	 * 根据类型词获取所有的关键词
	 * @param typeWord
	 */
	@Override
	public List<ThesaurusEntity> findKeyWordByType(String typeWord) {
		try {
			long _typeId = Long.parseLong(typeWord);
			List<Integer> _ids = kip.getWordIdByTypeId(_typeId);
			List<ThesaurusEntity> list = new ArrayList<ThesaurusEntity>();
			_ids.forEach(action->{
				ThesaurusEntity one = rep.findOne(action.longValue());
				list.add(one);
			});
			return list;
		} catch (Exception e) {
			LOGGER.error("根据类型词获取所有的关键词失败,原因是：",e);
			return null;
		}
	}

	/**
	 * 新增关系词
	 */
	@Override
	public boolean updateRelatedWord(String relatedWord) {
		RelatedWordEntity ent = new RelatedWordEntity();
		ent.setRelatetion(relatedWord);
		try {
			RelatedWordEntity entity = kwp.findByRelatetion(relatedWord);
			if(entity==null){
				kwp.save(ent);
			}
			return true;
		} catch (Exception e) {
			LOGGER.error("新增关系词失败,原因是：",e);
			return false;
		}
		
	}

	/**
	 * 获取所有的关系词
	 */
	@Override
	public List<RelatedWordEntity> getAllRelatedInfo() {
		try {
			Iterable<RelatedWordEntity> findAll = kwp.findAll();
			
			return (List<RelatedWordEntity>)findAll ;
		} catch (Exception e) {
			LOGGER.error("获取所有的关系词失败,原因是：",e);
			return null;
		}
	}

	/**
	 * 新增词的关系属性
	 */
	@Override
	public JSONArray addOrUpdate(RelatedDTO dto) {
		try {
			JSONArray arr = new JSONArray();
			String keyword = dto.getKeyword();
			Long relatedId = dto.getRelatedId();
			ThesaurusEntity one = rep.findByKeyword(keyword);
			List<Long> msg = dto.getMsg();
			List<KeyWordRelatedEntity> list = new ArrayList<KeyWordRelatedEntity>();
			for(int i=0;i<msg.size();i++){
				KeyWordRelatedEntity ent = new KeyWordRelatedEntity();
				Long wordId = msg.get(i);
				ent.setRelateWordId(wordId);
				ent.setRelateId(relatedId);
				ent.setWordId(one.getId());
				list.add(ent);
			}
			List<KeyWordRelatedEntity> save = krp.save(list);
			for(int i =0;i<save.size();i++){
				JSONObject  obj = new JSONObject();
				KeyWordRelatedEntity entity = save.get(i);
				//编号
				obj.put("relateId",entity.getId());
				//关联词id
				//获取关联词名
				ThesaurusEntity ent = rep.findOne(entity.getRelateWordId());
				String word = ent.getKeyword();
				obj.put("relateword", word);
				//获取关联词编号
				String number = kip.getKeywordNumberByWordId(entity.getRelateWordId());
				obj.put("number", number);
				//关系词id
				RelatedWordEntity findOne = kwp.findOne(entity.getRelateId());
				obj.put("related", findOne.getRelatetion());
				arr.add(obj);
			}
			return arr;
		} catch (Exception e) {
			LOGGER.error("新增词的关系属性失败,原因是：",e);
			return null;
		}
		
	}

	
	@Override
	@Transactional(value = Transactional.TxType.NOT_SUPPORTED)
	public boolean saveOrUpdateData(JSONArray jsonArray) {
		if(jsonArray.size()==0){
			return false;
		}else{
			try {
				for (int i = 0; i <jsonArray.size() ; i++) {
					ThesaurusEntity one = null;
					JSONObject json = (JSONObject) JSONObject.toJSON(jsonArray.get(i));
					String subject  =   json.getString("subject");//实体
					String object 	=   json.getString("object"); //关系实体
					String relation =   json.getString("relation");//关系
					one = rep.findByKeyword(subject);
					if(one==null){
						ThesaurusEntity ent = new ThesaurusEntity();
						ent.setKeyword(subject);
						one = rep.save(ent);
					}
					ThesaurusEntity save = rep.findByKeyword(object);
					if(save == null){
						ThesaurusEntity ent = new ThesaurusEntity();
						ent.setKeyword(object);
						save = rep.save(ent);
					}
					RelatedWordEntity rel = null;
					try {
						rel = kwp.findByRelatetion(relation);
					} catch (Exception e) {
						throw new Exception("当前对象是:"+i+relation);
					}
					if(rel == null){
						RelatedWordEntity ent = new RelatedWordEntity();
						ent.setRelatetion(relation);
						rel = kwp.save(ent);
					}
					KeyWordRelatedEntity findOne = new  KeyWordRelatedEntity();
					findOne.setRelateId( rel.getId());
					findOne.setWordId(one.getId());
					findOne.setRelateWordId(save.getId());
					try {
						krp.save(findOne);
					} catch (Exception e) {
						LOGGER.debug("t_word_related already having"+findOne);
					}
				}
				/*jsonArray.forEach(action->{
					ThesaurusEntity one = null;
					JSONObject json = (JSONObject) JSONObject.toJSON(action);
					String subject  =   json.getString("subject");//实体
					String object 	=   json.getString("object"); //关系实体
					String relation =   json.getString("relation");//关系
					one = rep.findByKeyword(subject);
					 if(one==null){
						 ThesaurusEntity ent = new ThesaurusEntity();
						 ent.setKeyword(subject);
						 one = rep.save(ent);
					 }
					 ThesaurusEntity save = rep.findByKeyword(object);
					 if(save == null){
						 ThesaurusEntity ent = new ThesaurusEntity();
						 ent.setKeyword(object);
						 save = rep.save(ent);
					 }
					 RelatedWordEntity rel = kwp.findByRelatetion(relation);
					 if(rel == null){
						 RelatedWordEntity ent = new RelatedWordEntity();
						 ent.setRelatetion(relation);
						 rel = kwp.save(ent);
					 }
					 KeyWordRelatedEntity findOne = new  KeyWordRelatedEntity();
					 findOne.setRelateId( rel.getId());
					 findOne.setWordId(one.getId());
					 findOne.setRelateWordId(save.getId());
					 krp.save(findOne);
				});*/
				return true;
			} catch (Exception e) {
				LOGGER.error("更新关系实体信息失败,原因是："+e.getMessage(),e);
				return false;
			}
			
		}
	}

	/**
	 * 获取所有关键词
	 */
	@Override
	@TargetDataSource(name="third")
	public JSONObject findAllInfoByKeyWord(String keyword) {
			JSONObject obj = new JSONObject();
		try {
			ThesaurusEntity ent = rep.findByKeyword(keyword);
			if(ent==null){
				return null;
			}else{
				obj.put("keyword", ent.getKeyword());
				Long wordId = ent.getId();//获取关键词id
				//词类型
				KeywordInfoEntity tity = kip.findByWordId(wordId);
				KeywordTypeEntity one = ktp.findOne(tity.getTypeId());
				obj.put("wordtype", one.getTypeWord());//词类型
				//词属性
				List<AttributeEntity> list = arp.findByWordId(wordId);
				obj.put("info", list);
				//词关系
				JSONArray arr = new JSONArray();
				List<KeyWordRelatedEntity> list2 = krp.findByWordId(wordId);
				if(list2.size()==0){
					 obj.put("relatetion", null);
				}else{
					list2.forEach(action->{
						 JSONObject obje = new JSONObject();
						 obje.put("relateword", rep.findOne( action.getRelateWordId()).getKeyword());
						 obje.put("number", kip.findByWordId(action.getRelateWordId()).getWordNumber());
						 obje.put("related",kwp.findOne(action.getRelateId()).getRelatetion());
						 arr.add(obje);
					});
					
					obj.put("relatetion", arr);
				}
			}
			return obj;
		} catch (Exception e) {
			LOGGER.error("获取新增词的全部信息失败,原因是：",e);
			return null;
		}
		
	}

	
	@Override
	@TargetDataSource(name="third")
	public boolean saveOrUpdateData(String keyword, String typeWord) {
		try {
			ThesaurusEntity ent = rep.findByKeyword(keyword);
			if(ent == null){
				ThesaurusEntity one = new ThesaurusEntity();
				one.setKeyword(keyword);
				rep.save(one);
			}
			//获取词性id
			KeywordTypeEntity tity = ktp.findByTypeWord(typeWord);
			if(tity==null){
				KeywordTypeEntity enti = new KeywordTypeEntity();
				enti.setTypeWord(typeWord);
				tity = ktp.save(enti);
			}
			Long count = kip.getCount(tity.getId());//查看当前类型的数量
			String _wordNumber ="";
			if(tity.getId()>26){
				//随机取两个值进行匹对
				String ss = StringUtil.LC_FIRST[(tity.getId().intValue()-1)/10];//取整
				String s = StringUtil.LC_FIRST[(tity.getId().intValue()-1)%10];//取余
				_wordNumber = ss+ s+(count+1);
			}else{
				String ss = StringUtil.LC_FIRST[(tity.getId().intValue()-1)];
				_wordNumber = ss+(count+1);
			}
			KeywordInfoEntity entity = new KeywordInfoEntity();
			entity.setInsertTime(new Date());
			entity.setTypeId(tity.getId());
			entity.setWordNumber(_wordNumber);
			entity.setWordId(ent.getId());
			kip.save(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error("保存词信息失败,原因是：",e);
			return false;
		}
	}

	/**
	 * 添加属性表
	 */
	@Override
	@TargetDataSource(name="third")
	public boolean addDataInfo(List<String> subList, String value) {
		try {
			//获取每一行的属性值
			String[] split = subList.get(0).split("---");
			
			String[] split2 = value.split("---");
			for(int i=0;i<split.length;i++){
				String ss = split[i];
				String s2 = split2[i];
//				System.out.println("表名1：》》》"+ss+"\n属性2：》》》"+s2);
				if(ss != s2){
					//获取词
					if(ss.equals("关键词")){
						//获取公司名录
						String companyName = split2[3];
						if(!companyName.equals("企业名称")){
							System.out.println("关键词:>>>>"+ss+"\n公司名录：>>>>>"+companyName);
							//将企业词与产业词入库，
							//关键词表
							//产业词
							ThesaurusEntity one = rep.findByKeyword(s2);
							if(one == null){
								one = new ThesaurusEntity();
								one.setKeyword(ss);
								one = rep.saveAndFlush(one);
							}
							//企业词
							ThesaurusEntity two = rep.findByKeyword(companyName);
							if(two == null){
								two = new ThesaurusEntity();
								two.setKeyword(companyName);
								two = rep.saveAndFlush(two);
							}
							Long typeId = (long) 4;
							KeywordInfoEntity ent = kip.findByWordId(two.getId());
							if(ent == null){
								 ent = new KeywordInfoEntity();
								 ent.setTypeId(typeId);
									ent.setInsertTime(new Date());
									ent.setWordId(two.getId());
									Long count = kip.getCount(typeId);
									String num = StringUtil.LC_FIRST[(typeId.intValue()-1)];
									String _wordNumber = num+(count+1);
									ent.setWordNumber(_wordNumber);
									//编号表
									kip.save(ent);
							}
							
							//关系表
							KeyWordRelatedEntity tity =	krp.findByWordIdAndRelateWordId(two.getId(),one.getId());
							if(tity ==null){
								tity = new KeyWordRelatedEntity();
								tity.setRelateId((long) 2);
								tity.setWordId(one.getId());
								tity.setRelateWordId(two.getId());
								krp.save(tity);
							}else{
								tity.setRelateWordId(two.getId());
								tity.setWordId(one.getId());
								krp.save(tity);
							}
							
							}
						}
					}
				}
			//获取每一行的值
			//获取公司名称,
			//获取类型词
			//
			return true;
		} catch (Exception e) {
			LOGGER.error("保存属性词失败,原因是：",e);
			return false;
		}
		
	}	
	
	
}

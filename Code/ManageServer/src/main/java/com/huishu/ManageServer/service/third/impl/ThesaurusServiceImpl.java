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
import com.huishu.ManageServer.entity.dto.dbThird.Serizal;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.WordDataDTO;
import com.huishu.ManageServer.entity.dto.dbThird.addKeyWordDTO;
import com.huishu.ManageServer.repository.third.AttributeRepository;
import com.huishu.ManageServer.repository.third.KeyWordInfoRepository;
import com.huishu.ManageServer.repository.third.KeyWordRelatedRepository;
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
	
	
	@TargetDataSource(name="third")
	@Override
	public JSONArray findAllKeyWord() {
		JSONArray obj = new JSONArray();
		Iterable<ThesaurusEntity> all = rep.findAll();
		all.forEach(action->{
			JSONObject o = new JSONObject();
			JSONArray arr = new JSONArray();
			Long id = action.getId();
			String keyword = action.getKeyword();//关键词
			String type = null;//词类型
			String desc=action.getDescribe();//描述
		
			List<KeyWordRelatedEntity> list = krp.findByWordId(id);
			list.forEach(act->{
				JSONObject oj = new JSONObject();
				String related = act.getRelated();//关联关系
				Long wordId =act.getRelateId();//关联词id
				if(wordId!=0){
					ThesaurusEntity one = rep.findOne(wordId);
					oj.put("keyName", one.getKeyword());
					oj.put("relatedName",related );
					oj.put("keyType",null);
					arr.add(oj);
				}
			});
			
			o.put("desc", desc);			
					
			o.put("keyword", keyword);			
			o.put("type", type);
			o.put("relate", arr);
			obj.add(o);
		});
		return obj;
	}

	/**
	 * 查看关键词信息--列表查看
	 */
	@Override
	@TargetDataSource(name="third")
	public Page<WordDataDTO> findByPage(TKeyWordDTO dto) {
		Page<WordDataDTO> page = null;
		List<ThesaurusEntity> list  = null;
		List<WordDataDTO> alist = new ArrayList<WordDataDTO>();
		Long count = null;
		try {
			Long number = (long) (dto.getPageNumber()*(dto.getPageSize()));
			Pageable pageable = new PageRequest(dto.getPageNumber() ,dto.getPageSize());
			if(dto.getType().equals("全部")){
				switch(dto.getSort()){
					case("1"):
						//按照添加时间倒序
						list = rep.getKeyWordListDESC(number,dto.getPageSize());
						//获取相应的总数，
						count = rep.count();
					  break;
					case("2"):
						//按照添加时间顺序
						list = rep.getKeyInfoList(number,dto.getPageSize());
						//获取相应的总数，
//						count = rep.countByType(dto.getType());
					  break;
					case("3"):
						//按照词性热度
						//需要配合调试
					  break;
					case("4"):
						//按照词性复杂度
						//先获取所有属性复杂度高的关键词id
						List<Long> ids = arp.getKeyWordId(number,dto.getPageSize());
						//根据关键词id集合查询所有数据
						list = rep.findAll(ids);
						count = arp.getKeyWordIdCount();
					   break;
					default :
						//词性关系复杂度
						List<Long> all = krp.getKeyWordIdDESC(number, dto.getPageSize());
						//根据关键词id集合查询所有数据
						list = rep.findAll(all);
						count = arp.getKeyWordIdCount();
						break;
				};
				
			}else{
				Long typeId = 	Long.parseLong(dto.getType());
				switch(dto.getSort()){
				case("1"):
					//按照添加时间倒序
					//list = rep.getKeyWordListDESC(number,dto.getPageSize());
					List<Long> _ids = kip.getKeyWordListDESCByType(typeId , number, dto.getPageSize());
					list = rep.findAll(_ids);
					//获取相应的总数，
					 count = kip.getCount(typeId);
					 break;
				case("2"):
					//按照添加时间顺序
					List<Long> ids = kip.getKeyWordListByType(typeId , number, dto.getPageSize());
					list = rep.findAll(ids);
					//获取相应的总数，
					count = kip.getCount(typeId);
				  break;
				case("3"):
					//按照词性热度
					//需要配合调试
				  break;
				case("4"):
					//按照词性复杂度
					
					//先获取所有的id
					List<Long> ll = kip.getWordIdByTypeId(typeId);
					
					//先获取所有属性复杂度高的关键词id
					
					//根据关键词id集合查询所有数据
					
				   break;
				default :
					//词性关系复杂度
				
					//根据关键词id集合查询所有数据
				
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

	/**
	 * 获取关联关系
	 */
	@Override
	@TargetDataSource(name="third")
	public JSONObject findRelatedById(String id) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		try {
			long _id = Long.parseLong(id);
			ThesaurusEntity one = rep.findOne(_id);
			if(one==null){
				obj.put("result", false);
			}else{
				//获取关联关系
				List<KeyWordRelatedEntity> list = krp.findByWordId(_id);
				if(list.size()==0){
					obj.put("relate", false);
				}else{
					list.forEach(action->{
						JSONObject o = new JSONObject();
						if(action.getRelateId()!=0){
							ThesaurusEntity ent = rep.findOne(action.getRelateId());
							o.put("rkeyword", ent.getKeyword());
							o.put("rtype", null);
							o.put("ralate_id", action.getRelateId());//关联词id
						}
						
						o.put("r_id", action.getId());//主键id
						o.put("related", action.getRelated());
						arr.add(o);
					});
					obj.put("relate", arr);
				}
				//获取属性值
				List<AttributeEntity> alist = arp.findByWordId(_id);
				
				if(alist.size()==0){
					obj.put("attribute", false);
				}else{
					obj.put("attribute", alist);
				}
				obj.put("id", id);			
				obj.put("desc", one.getDescribe());			
				obj.put("keyword", one.getKeyword());
				obj.put("type", null);
				obj.put("result", true);
			}
		} catch (Exception e) {
			LOGGER.debug("根据id查看关联关系失败！原因是：",e);
			obj.put("result", "false");
		}
		return obj;
	}

	/**
	 * 删除关联关系
	 */
	@Override
	@TargetDataSource(name="third")
	public boolean deleteRelatedById(String id) {
		try {
			long _id = Long.parseLong(id);
			//删除关键词
			rep.delete(_id);
			//删除关联关系
			List<KeyWordRelatedEntity> list = krp.findByWordId(_id);
			if(list.size()>0){
				krp.delete(list);
			}
		} catch (Exception e) {
			LOGGER.debug("根据id删除关联关系失败！原因是：",e);
			return false;
		}
		return true;
	}

	
	@Override
	@TargetDataSource(name="third")
	public boolean saveOrUpdateInfo(addKeyWordDTO dto) {
		ThesaurusEntity ent = new ThesaurusEntity();
		try {
			if(dto.getId()!=0){
				ent.setId(dto.getId());
			}
			ent.setDescribe(dto.getDescrip());
			ent.setKeyword(dto.getName());
			ThesaurusEntity save = rep.save(ent);
			List<Serizal> msg = dto.getMsg();
			List<KeyWordRelatedEntity> list = new ArrayList<KeyWordRelatedEntity>();
			msg.forEach(action->{
				  KeyWordRelatedEntity en = new KeyWordRelatedEntity();
				  Long options =action.getOptions();
				  String describe = action.getDescribe();
				  en.setRelated(describe);
				  en.setRelateId(options);
				  en.setWordId(save.getId());
				  KeyWordRelatedEntity tit =   krp.findByWordIdAndRelateId(save.getId(),options);
				  if(tit==null){
					  list.add(en);
				  }else{
					  tit.setRelated(describe);
					  krp.save(tit);
				  }
			  });
			 krp.save(list);
			  return true;
		} catch (Exception e) {
			LOGGER.error("新增或保存词失败,原因是：",e);
			return false;
		}
		
	}

	
	@Override
	@TargetDataSource(name="third")
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
	@TargetDataSource(name="third")
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
	@TargetDataSource(name="third")
	public boolean addDataInfo(String value) {
		ThesaurusEntity ent = null;
		ThesaurusEntity save =  null;
		Long wordId = null;
		Long count = (long) 0;
		try {
			String[] split = value.split("---");
			String keyword = split[0];
			String type = split[1];
			if(split.length<=2){
//				 rep.countByType(type);
				Long typeId = null;
				if(count==0){
					Long keyWordId = rep.getKeyWordId();
					typeId = keyWordId+1;
				}else{
//					typeId = rep.getTypeIdByType(type);
				}
				ent =new ThesaurusEntity();
				ent.setKeyword(keyword);
				rep.save(ent);
			}else{
				String describe = split[2];
//				Long count = rep.countByType(type);
				ent = new ThesaurusEntity();
				ent.setDescribe(describe);
				ent.setKeyword(keyword);
				//如果count==0,说明此类型词没有出现，进行添加
				if( count == 0){
					//获取产业的最大值id
					Long _id = rep.getKeyWordId();
					Long id = _id+1;
					ent.setId(id);
				}else{
					//获取typeid
//					Long typeId = rep.getTypeIdByType(type);
					Long mid = rep.getMaxId();
					//如果mid处于0~20之间，说明产业数据太小，需要扩大
					if(mid>0&&mid<20){
						ent.setId((long) 20);
					}else{
						ent.setId(mid+1);
					}
				}
				//判断关键词是否存在
				save = rep.findByKeyword(keyword);
				//如果通过关键词查找为空，则进行新增
				if(save == null){
					save = rep.save(ent);
					wordId = save.getId();
				}else{
					//如果不为空，则直接获取数据
					wordId = save.getId();
				}
				List<AttributeEntity> list = new ArrayList<AttributeEntity>();
				for(int i=3;i<split.length;i++){
					String info = split[i];
					int i1 = info.indexOf("(");
					int i2 = info.indexOf(")");
					String name = info.substring(0, i1);
					String val = info.substring(i1+1, i2);
					AttributeEntity ant = new AttributeEntity();
//					ant.setEntity(save);
					ant.setWordId(wordId);
					ant.setAttributeName(name);
					ant.setAttributeValue(val);
					list.add(ant);
				}
				arp.save(list);
			}
			
			return true;
		} catch (Exception e) {
			LOGGER.error("添加一个实体数据失败,原因是：",e);
			return false;
		}
		
	}

	/**
	 * 获取词性分类标签信息
	 */
	@Override
	@TargetDataSource(name="third")
	public List<KeywordTypeEntity> getLableInfo() {
		return (List<KeywordTypeEntity>) ktp.findAll();
	}

	/**
	 * 根据id获取词的属性信息
	 * @param id
	 * @return
	 */
	@Override
	@TargetDataSource(name="third")
	public JSONObject findAttributeInfoById(String id) {
		try {
			long _id = Long.parseLong(id);
			
			return null;
		} catch (Exception e) {
			LOGGER.error("根据id查看词属性失败,原因是：",e);
			return null;
		}
	}

	/**
	 * 更新词性信息
	 */
	@Override
	@TargetDataSource(name="third")
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
		if(typeId==0){
			String typeWord = dto.getTypeWord();
			if(StringUtil.isEmpty(typeWord)){
				return false;
			}
			
			KeywordTypeEntity ent = new KeywordTypeEntity();
			ent.setTypeWord(typeWord);
			KeywordTypeEntity save = ktp.save(ent);
			
			//根据类型id,判断分类编号属于A,B,C,D,E。。。。。等
			if(save.getId()>26){
				//随机取两个值进行pi'dui
			}else{
				
			}
			//查询info数据库，判断当前类型数据的数量值，
		}else{
			//不是新增类型，则一定存在，只需获取数量值就行
			
		}
		return false;
	}
}

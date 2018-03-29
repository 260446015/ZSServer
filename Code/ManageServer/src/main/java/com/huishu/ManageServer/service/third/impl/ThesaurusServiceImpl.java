package com.huishu.ManageServer.service.third.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.entity.dto.dbThird.AttributeInfo;
import com.huishu.ManageServer.entity.dto.dbThird.Serizal;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.WordDataDTO;
import com.huishu.ManageServer.entity.dto.dbThird.addKeyWordDTO;
import com.huishu.ManageServer.repository.third.AttributeRepository;
import com.huishu.ManageServer.repository.third.KeyWordRelatedRepository;
import com.huishu.ManageServer.repository.third.LogRepository;
import com.huishu.ManageServer.repository.third.ThesaurusRepository;
import com.huishu.ManageServer.service.third.ThesaurusService;
import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.AttributeEntity;
import com.huishu.ManageServer.entity.dbThird.KeyWordRelatedEntity;
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
			String type = action.getType();//词类型
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
					oj.put("keyType", one.getType());
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
	 * 查看关键词信息
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
			//获取相应的总数，
			if(dto.getType().equals("全部")){
				list  = rep.getKeyInfoList(number,dto.getPageSize());
				count = rep.count();
			}else{
				list  = rep.getKeyWordListByType(dto.getType(),number,dto.getPageSize());
				count = (long) rep.countByType(dto.getType());
			}
			list.forEach(action->{
				Long wordId = action.getId();
				List<AttributeEntity> arr = arp.getByWordId(wordId);
				List<AttributeInfo> array = new ArrayList<AttributeInfo>();
				arr.forEach(act->{
					AttributeInfo info = new AttributeInfo();
					info.setAttributeName(act.getAttributeName());
					info.setAttributeValue(act.getAttributeValue());
					array.add(info);
				});
				WordDataDTO data = new WordDataDTO();
				data.setEntntity(action);
				data.setInfo(array);
				alist.add(data);
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
				List<KeyWordRelatedEntity> list = krp.findByWordId(_id);
				if(list.size()==0){
					obj.put("relate", false);
				}else{
					list.forEach(action->{
						JSONObject o = new JSONObject();
						if(action.getRelateId()!=0){
							ThesaurusEntity ent = rep.findOne(action.getRelateId());
							o.put("rkeyword", ent.getKeyword());
							o.put("rtype", ent.getType());
							o.put("ralate_id", action.getRelateId());//关联词id
						}
						
						o.put("r_id", action.getId());//主键id
						o.put("related", action.getRelated());
						arr.add(o);
					});
					obj.put("relate", arr);
				}
				obj.put("id", id);			
				obj.put("desc", one.getDescribe());			
				obj.put("keyword", one.getKeyword());
				obj.put("type", one.getType());
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
			ent.setType(dto.getType());
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
		try {
			String[] split = value.split("---");
			String keyword = split[0];
			String type = split[1];
			if(split.length<=2){
				int count = rep.countByType(type);
				Long typeId = null;
				if(count==0){
					Long keyWordId = rep.getKeyWordId();
					typeId = keyWordId+1;
				}else{
					typeId = rep.getTypeIdByType(type);
				}
				ent =new ThesaurusEntity();
				ent.setKeyword(keyword);
				ent.setType(type);
				ent.setTypeId(typeId);
				rep.save(ent);
			}else{
				String describe = split[2];
				int count = rep.countByType(type);
				ent = new ThesaurusEntity();
				ent.setDescribe(describe);
				ent.setKeyword(keyword);
				ent.setType(type);
				//如果count==0,说明此类型词没有出现，进行添加
				if( count == 0){
					//获取产业的最大值id
					Long _id = rep.getKeyWordId();
					Long id = _id+1;
					ent.setId(id);
					ent.setTypeId(id);
				}else{
					//获取typeid
					Long typeId = rep.getTypeIdByType(type);
					ent.setTypeId(typeId);
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
}

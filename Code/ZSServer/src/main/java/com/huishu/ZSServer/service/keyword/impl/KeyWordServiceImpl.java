package com.huishu.ZSServer.service.keyword.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.entity.KeyWordEntity;
import com.huishu.ZSServer.repository.keyword.KeyWordRepository;
import com.huishu.ZSServer.service.keyword.KeyWordService;

/**
 * @author hhy
 * @date 2017年12月19日
 * @Parem
 * @return 
 * 关键词云实现方法
 */
@Service
public class KeyWordServiceImpl implements KeyWordService {
	
	@Autowired
	private KeyWordRepository rep;
	
	/**
	 * 根据时间查询所有的关键词
	 */
	@Override
	public List<KeyWordEntity> findKeyWordList(String str) {
		return rep.findByTime(str);
	}

	/**
	 * 保存关键词
	 */
	@Override
	public boolean saveKeyWord(List<KeyWordEntity> li) {
		Iterable<KeyWordEntity> save = rep.save(li);
		if(save!= null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteData(List<KeyWordEntity> list) {
		try {
			rep.delete(list);
			return true;	
		} catch (Exception e) {
			list.forEach(action->{
				rep.delete(action);
			});
			return true;
		}
	}

	@Override
	public List<KeyWordEntity> findAllKeyWord() {
		List<KeyWordEntity> all = (List<KeyWordEntity>) rep.findAll();
		return all;
	}

}

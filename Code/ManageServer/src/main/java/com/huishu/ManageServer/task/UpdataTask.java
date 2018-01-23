package com.huishu.ManageServer.task;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.es.entity.SummitInfo;
import com.huishu.ManageServer.es.repository.SummitElasticsearch;

/**
 * @author hhy
 * @date 2018年1月9日
 * @Parem
 * @return 
 * 更新峰会数据
 */
@Component
public class UpdataTask {
	private static final Logger log = LoggerFactory.getLogger(UpdataTask.class);
	@Autowired
	private SummitElasticsearch rep;
	/*@Autowired
	private Client client;*/
	
	@Scheduled(fixedDelay = 1000 * 60 * 60 * 12 )
	public void updataData(){
		//查询所有，进行数据更新，删除，维护
		log.info("==============高峰论坛数据更新开始======================");
		Iterable<SummitInfo> all = rep.findAll();
		Map<Integer,SummitInfo> map = new HashMap<Integer,SummitInfo>();
		all.forEach(action ->{
			log.info("查询到的数据为："+action.toString());
			//排除不符合条件的数据，没有展会时间
			String exhibitiontime = action.getExhibitiontime();
			String id = action.getId();
			//删除展会时间为空的数据
			if(StringUtil.isEmpty(exhibitiontime)){
				rep.delete(id);
			}else{
				//删除logo为空的数据
				if(StringUtil.isEmpty(action.getLogo())){
					rep.delete(id);
				}else{
				//判重
				int i = action.getArticleLink().hashCode();
				log.info("原网址的哈希值"+i);
				if(map.containsKey(i)){
					//哈希值重复删除数据
					rep.delete(id);
				}else{
					map.put(i, action);
					//进行数据更新
					String publishTime = action.getPublishTime();
					String replace = publishTime.replace("年", "-").replace("月", "-").replace("日", " ");
					action.setPublishTime(replace);
					//判重之后更新数据
					/*int index = exhibitiontime.indexOf("~");
					if(index>0){
						String replace = exhibitiontime.replace("~", "至");
						action.setExhibitiontime(replace);
						String[] split = exhibitiontime.split("~");
						boolean info = ifPublishTime(split);
						if(info){
							action.setPublishTime(split[0]);
						}else{
							String string = split[0];
							String[] split2 = string.split(" ");
							action.setPublishTime(split2[0]);
						}
						
					}*/
					/*else{
						int index2 = exhibitiontime.indexOf("-");
						if(index2>0){
							String replace = exhibitiontime.replace("-", "至");
							action.setExhibitiontime(replace);
							String[] split = exhibitiontime.split("-");
							boolean info = ifPublishTime(split);
							if(info){
								action.setPublishTime(split[0]);
							}else{
								String string = split[0];
								String[] split2 = string.split(" ");
								action.setPublishTime(split2[0]);
							}
						}
					}*/
					
					
				//存储数据
				log.info("此时的数据为：》》》》"+action.toString());
				rep.save(action);
				}
			  }
			}
		});
	}

	/**
	 * @param split
	 * @return
	 */
	private boolean ifPublishTime(String[] split) {
		String spl = split[0];
		String[] split2 = spl.split(" ");
		if(split2.length>=2){
			return false;
		}else{
			return true;
		}
	}
	
	
}

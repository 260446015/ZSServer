package com.huishu.ZSServer.service.indus.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.entity.UserInstitutionalEntity;
import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
import com.huishu.ZSServer.repository.user.UserInstitutionalRepository;
import com.huishu.ZSServer.service.indus.InstitutionalService;

/**
 * @author hhy
 * @date 2017年11月22日
 * @Parem
 * @return
 * 
 */
@Service
public class InstitutionalServiceImpl implements InstitutionalService {
	private Logger LOGGER = LoggerFactory.getLogger(InstitutionalServiceImpl.class);
	@Autowired
	private InstitutionalRepostitory rep;
	@Autowired
	private UserInstitutionalRepository uir;
	
	@Override
	public List<Institutional> getInstutionalInfo(String area, String industry) {
		if(StringUtil.isEmpty(industry)||StringUtil.isEmpty(area)){
			LOGGER.debug("参数异常:area>>>>>>>>>"+area+"产业信息:>>>>>>>>>>"+industry);
			return null;
		}
		List<Institutional> list = rep.findByAreaAndIndustry(area, industry);
		if(list.size()==0){
			return null;
		}else{
			list.forEach(act ->{
				String url = act.getUrl();
				int i1 = url.indexOf("%");
				int i = url.indexOf("?");
				if(i1>0){
					act.setUrl(url.substring(0, i1));
					
				}
				if(i>0){
					act.setUrl(url.substring(0, i));
				 }
			 
			});
			return list;
		}
	}


	/**
	 * 关注实验室
	 */
	@Override
	public String saveLaboratoryInfoById(Long id, String name) {
		
		UserInstitutionalEntity uie = null;
		try {
			
			uie = uir.getInfoByInsIdAndName(id,name);
			if(uie!= null){
				return "已关注";
			}
		} catch (Exception e) {
			return "关注失败！";
		}
		UserInstitutionalEntity entity = new UserInstitutionalEntity();
		entity.setInsId(id);
		entity.setName(name);
		try {
			uir.save(entity);
			Institutional one = rep.findOne(id);
			one.setLabel(true);
			Institutional save = rep.save(one);
			
		} catch (Exception e) {
			return "关注失败！";
		}
		return "关注成功 ！";
	}
	
}

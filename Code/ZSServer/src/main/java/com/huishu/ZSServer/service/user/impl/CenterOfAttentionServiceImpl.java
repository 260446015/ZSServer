package com.huishu.ZSServer.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.common.conf.PageConstant;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.repository.instituton.InstitutionalRepostitory;
import com.huishu.ZSServer.repository.user.UserInstitutionalRepository;
import com.huishu.ZSServer.service.user.CenterOfAttentionService;

@Service
public class CenterOfAttentionServiceImpl implements CenterOfAttentionService {
	
	@Autowired
	private InstitutionalRepostitory institutionalRepostitory;
	@Autowired
	private UserInstitutionalRepository userInstitutionalRepository;
	
	@Override
	public Page<Institutional> findOrganizationList(String industry, Long userId,Integer pageNum) {
		List<Institutional> list = institutionalRepostitory.findByIdAndIndustry(userId, industry, pageNum*PageConstant.FIVE, PageConstant.FIVE);
		PageRequest request = new PageRequest(pageNum,PageConstant.FIVE);
		if(list.size()==0){
			return new PageImpl<>(null, request, 0);
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
		}
		Integer total = institutionalRepostitory.findCountByIdAndIndustry(userId, industry);
		Page<Institutional> impl = new PageImpl<>(list, request, total);
		return impl;
	}

}

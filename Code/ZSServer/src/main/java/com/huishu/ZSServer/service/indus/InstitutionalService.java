package com.huishu.ZSServer.service.indus;

import java.util.List;

import com.huishu.ZSServer.entity.Institutional;

/**
 * @author hhy
 * @date 2017年11月22日
 * @Parem
 * @return 
 * 重点实验室相关service 
 */
public interface InstitutionalService {

	/**
	 * @param area
	 * @param industry
	 * @return
	 */
	List<Institutional> getInstutionalInfo(String area, String industry);

	/**
	 * @param id
	 * @param name
	 * @return
	 */
	String saveLaboratoryInfoById(Long id, Long userId);

}

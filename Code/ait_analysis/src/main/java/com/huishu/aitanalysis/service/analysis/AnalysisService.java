package com.huishu.aitanalysis.service.analysis;

import com.huishu.aitanalysis.service.index.IIndexService;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 */
public interface AnalysisService {

	/**
	 * POST请求分析
	 */
	public void analysis(String dataJson,IIndexService indexService);

}

package com.huishu.aitanalysis.common.analyzer.clean;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * @author hhy
 * @date 2017年8月31日
 * @Parem
 * @return 
 * 
 */
public class CleanFactory {
	private static final Logger log = Logger.getLogger(CleanFactory.class);
    private static final Map<String, Clean> pool = new HashMap<String, Clean>();
    private CleanFactory(){}
    
    public static synchronized Clean getAnalysis(CleanAlgorithm cleanAlgorithm){
    	String clazz = "com.zkdj.analysis.analyzer.clean.impl."+cleanAlgorithm.name();
    	Clean clean = pool.get(clazz);
    	if(clean == null){
    		log.info("构造清空实现类: " + clazz);
    		try {
    			clean = (Clean)Class.forName(clazz).newInstance();
				pool.put(clazz, clean);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				log.error("构造清空实现类失败：", e);
			}
    	}
    	return clean;
    }
}



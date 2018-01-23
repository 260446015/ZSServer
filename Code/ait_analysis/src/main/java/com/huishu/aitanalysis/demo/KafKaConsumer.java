package com.huishu.aitanalysis.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.huishu.aitanalysis.service.analysis.AnalysisService;

/**
 * @author hhy
 * @date 2018年1月23日
 * @Parem
 * @return 
 * 
 */
@Component
public class KafKaConsumer {
	@Autowired
	private AnalysisService analysisService;
	
	
	    @KafkaListener(topics = {"pomp"})
	    public void receive(String message){
	    	System.out.println("+++++++++++测试消费端开始++++++++++++++++");
	        System.out.println("pomp--消费消息:" + message);
	        analysisService.analysis(message);
	        System.out.println("+++++++++++测试结束+++++++++++++++");
	    }
	
}

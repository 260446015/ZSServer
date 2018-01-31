package com.huishu.aitanalysis.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.huishu.aitanalysis.service.analysis.AnalysisService;
import com.huishu.aitanalysis.service.analysis.AnalysisServiceImpl;
import com.huishu.aitanalysis.service.index.IIndexService;
import com.huishu.aitanalysis.service.indus.IndustryInfoService;
import com.huishu.aitanalysis.service.park.ParkAnalysisService;
import com.huishu.aitanalysis.threadpool.KafkaThreadPool;

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
	@Autowired
	private IIndexService indexService;
	@Autowired
	private IndustryInfoService indusService;
	@Autowired
	private ParkAnalysisService  service;
	
	
	@KafkaListener(topics = { "pomp" })
	public void receive(String message) {
		KafkaThreadPool.getInstance();
		KafkaThreadPool.getExecutorService().execute(new KafkaRunner(message));
	}
	
	class KafkaRunner implements Runnable{

		private String message;

		public KafkaRunner(String message) {
			super();
			this.message = message;
			analysisService = new AnalysisServiceImpl();
		}
		
		@Override
		public void run() {
			System.out.println("+++++++++++测试消费端开始++++++++++++++++");
			System.out.println("----当前线程为:" + Thread.currentThread().getName() + "-------");
			System.out.println("pomp--消费消息:" + message);
			analysisService.analysis(message,indexService,indusService,service);
			System.out.println("+++++++++++测试结束+++++++++++++++");
		}
		
	}

}

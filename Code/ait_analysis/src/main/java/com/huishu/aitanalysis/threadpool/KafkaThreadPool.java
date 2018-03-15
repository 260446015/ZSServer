package com.huishu.aitanalysis.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

public class KafkaThreadPool {

	private static KafkaThreadPool pool = null;

	private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20000);

	private static ThreadPoolExecutor executor;

	private KafkaThreadPool() {
		synchronized (this) {
//			executor = new ThreadPoolExecutor(50, 200, 60, TimeUnit.SECONDS, queue);
			executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, queue);
		}
		System.out.println("创建线程池成功!!!!!!!!!!!!");
	}

	public static KafkaThreadPool getInstance() {
		if (pool == null) {
			synchronized (KafkaThreadPool.class) {
				if (pool == null) {
					pool = new KafkaThreadPool();
				}
			}
		}
		return pool;
	}

	public static ThreadPoolExecutor getExecutorService() {
		return executor;
	}

}

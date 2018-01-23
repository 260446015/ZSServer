package com.test.util;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.huishu.aitanalysis.entity.Es;

public class ESTools {
public final static Client client =  build();
	
	/**
	 * 创建一次
	 * @return
	 */
	private static Client build(){
		if(null != client){
			return client;
		}
		Client client = null;
		try {
			System.out.println("创建Elasticsearch Client 开始");
			Settings settings = Settings.settingsBuilder()
					.put("cluster.name","elasticsearch")   //设置集群名字,与已经有的不一致会报错  NoNode````
						.put("client.transport.sniff", true)    //开启嗅探
 						//.put("es.tclient", true)
							.build();
			client = TransportClient.builder().settings(settings).build()
			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.16"), 9300));
			System.out.println("创建Elasticsearch Client 结束");
		} catch (Exception e) {
			System.out.println("创建Elasticsearch Client 异常");
		}
		return client;
	}
	
	/**
	 * 关闭
	 */
	public static void close(){
		if(null != client){
			try {
				client.close();
			} catch (Exception e) {
				
			}
		}
	}
}

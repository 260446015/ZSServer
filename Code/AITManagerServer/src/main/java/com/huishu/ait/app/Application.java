package com.huishu.ait.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by hhy on 2017/7/26
 */
@Configuration
@ComponentScan(basePackages = "com.huishu.ait")
@EntityScan(basePackages = "com.huishu.ait.entity")
@EnableJpaRepositories(basePackages = "com.huishu.ait.repository")
@EnableElasticsearchRepositories(basePackages = "com.huishu.ait.es.repository")
@EnableAutoConfiguration
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

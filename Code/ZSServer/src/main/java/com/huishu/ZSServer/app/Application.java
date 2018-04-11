package com.huishu.ZSServer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by hhy on 2017/10/26
 */
@Configuration
@ComponentScan(basePackages = "com.huishu.ZSServer")
@EntityScan(basePackages = "com.huishu.ZSServer.entity")
@EnableJpaRepositories(basePackages = "com.huishu.ZSServer.repository")
@EnableElasticsearchRepositories(basePackages = "com.huishu.ZSServer.es.repository")
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

}

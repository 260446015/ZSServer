package com.huishu.ManageServer.app;

import com.huishu.ManageServer.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by hhy on 2017/10/26
 */
@Configuration
@Import(DynamicDataSourceRegister.class)
@ComponentScan(basePackages = "com.huishu.ManageServer")
@EntityScan(basePackages = "com.huishu.ManageServer.entity")
@EnableJpaRepositories(basePackages = "com.huishu.ManageServer.repository")
@EnableElasticsearchRepositories(basePackages = "com.huishu.ManageServer.es.repository")
@EnableTransactionManagement
@EnableAutoConfiguration
//@EnableScheduling
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

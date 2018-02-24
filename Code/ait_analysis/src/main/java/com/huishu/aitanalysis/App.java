package com.huishu.aitanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Created by yuwei on 2017/04/16
 */
@Configuration
@ComponentScan(basePackages = "com.huishu.aitanalysis")
@EntityScan(basePackages = "com.huishu.aitanalysis.entity")
@EnableElasticsearchRepositories(basePackages = "com.huishu.aitanalysis.es.repository")
@EnableAutoConfiguration
public class App {
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
}

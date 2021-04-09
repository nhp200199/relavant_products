package com.phucnguyen.khoaluan.webservice.productrelevance.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.phucnguyen.khoaluan.webservice.productrelevance.demo.repository.mongo.tiki",
mongoTemplateRef = "mongoTikiTemplate")
public class MongoTikiConfig {
    
}

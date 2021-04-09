package com.phucnguyen.khoaluan.webservice.productrelevance.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.phucnguyen.khoaluan.webservice.productrelevance.demo.repository.mongo.shopee",
mongoTemplateRef = "mongoShopeeTemplate")
public class MongoShopeeConfig {
    
}

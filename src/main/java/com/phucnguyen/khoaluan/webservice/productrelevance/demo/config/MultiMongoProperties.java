package com.phucnguyen.khoaluan.webservice.productrelevance.demo.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mongodb")
public class MultiMongoProperties {
    private MongoProperties tiki;
    private MongoProperties shopee;

    public MongoProperties getTikiProperties() {
        return tiki;
    }

    public MongoProperties getShopeeProperties() {
        return shopee;
    }
}

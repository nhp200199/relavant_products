package com.phucnguyen.khoaluan.webservice.productrelevance.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {

    @Primary
    @Bean(name = "mongoTiki")
    @ConfigurationProperties(prefix = "mongodb.tiki")
    public MongoProperties getMongoTiki() {
        return new MongoProperties();
    }

    @Bean(name = "mongoShopee")
    @ConfigurationProperties(prefix = "mongodb.shopee")
    public MongoProperties getMongoShopee() {
        return new MongoProperties();
    }

    @Bean(name = "mongoTikiTemplate")
    @Primary
    public MongoTemplate mongoTiki() {
        return new MongoTemplate(tikiDbFactory(getMongoTiki()));
    }

    @Bean(name = "mongoShopeeTemplate")
    public MongoTemplate mongoShopee() {
        return new MongoTemplate(shopeeDbFactory(getMongoShopee()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory tikiDbFactory(final MongoProperties mongo) {
        String uri = mongo.getUri();
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }

    @Bean
    public MongoDatabaseFactory shopeeDbFactory(final MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory(mongo.getUri());
    }
}

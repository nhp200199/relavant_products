package com.phucnguyen.khoaluan.webservice.productrelevance.demo.config;

import com.phucnguyen.khoaluan.webservice.productrelevance.demo.RootCategory;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
            org.springframework.web.servlet.config.annotation.CorsRegistry cors) {
        config.exposeIdsFor(RootCategory.class);
    }
}

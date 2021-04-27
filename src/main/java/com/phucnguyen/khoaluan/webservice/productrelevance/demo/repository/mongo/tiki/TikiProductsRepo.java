package com.phucnguyen.khoaluan.webservice.productrelevance.demo.repository.mongo.tiki;

import java.util.List;

import com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.mongo.tiki.TikiProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

@Repository
public class TikiProductsRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TikiProduct> findProductsByRelavantName(String name, String platform) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(name);
        Query textQuery = TextQuery.queryText(criteria).sortByScore().limit(100);
        return mongoTemplate.find(textQuery, TikiProduct.class);
    }

}

package com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.mongo.tiki;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ItemsTiki")
public class Product {
    @Id
    private String objectId;
    @Field("id")
    private long id;
    @TextIndexed
    String name;
    private int currentPrice;
    private String thumbnailUrl;
    private int categoryId;
    private String productUrl;
    private String platform;

    public Product(String objectId, long id, String name, int currentPrice, String thumbnailUrl, int categoryId, String productUrl,
            String platform) {
        this.objectId = objectId;
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.thumbnailUrl = thumbnailUrl;
        this.categoryId = categoryId;
        this.productUrl = productUrl;
        this.platform = platform;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}

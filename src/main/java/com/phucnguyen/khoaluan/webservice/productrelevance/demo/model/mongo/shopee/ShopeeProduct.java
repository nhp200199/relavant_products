package com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.mongo.shopee;

import com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.CommonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document(collection = "ItemsShopee")
public class ShopeeProduct implements CommonProperty {
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
    private float rating;
    private int totalReview; 
    @TextScore
    Float score;

    public ShopeeProduct(String objectId, long id, String name, int currentPrice, String thumbnailUrl, int categoryId,
            String productUrl, String platform, float rating, int totalReview) {
        this.id = id;
        this.objectId = objectId;
        this.name = name;
        this.currentPrice = currentPrice;
        this.thumbnailUrl = thumbnailUrl;
        this.categoryId = categoryId;
        this.productUrl = productUrl;
        this.platform = platform;
        this.rating = rating;
        this.totalReview = totalReview;
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

    public void setCurrentPrice(int price) {
        this.currentPrice = price;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    @Override
    public float getCommonScoreProperty() {
        return score;
    }
}

package com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.mongo.shopee;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ItemsShopee")
public class ShopeeProduct {
    @Id
    private String id;
    @TextIndexed
    String name;
    private int currentPrice;
    private String thumbnailUrl;
    private int categoryId;
    private String productUrl;
    private String platform;

    public ShopeeProduct(String id, String name, int currentPrice, String thumbnailUrl, int categoryId,
            String productUrl, String platform) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.thumbnailUrl = thumbnailUrl;
        this.categoryId = categoryId;
        this.productUrl = productUrl;
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}

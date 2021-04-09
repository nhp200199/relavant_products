package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.Map;

public class Product {
    private String id;
    private String name;
    private int price;
    private String thumbnailUrl;
    private String seller;
    private String categoryId;
    private String url;
    private String platform;
    private Map<String, String> productAttribute;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Map<String, String> getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(Map<String, String> productAttribute) {
        this.productAttribute = productAttribute;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return id + "\n" + name + "\n" + price + "\n" + thumbnailUrl + "\n" + seller + "\n" + categoryId + "\n" + url
                + "\n" + productAttribute.toString();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}

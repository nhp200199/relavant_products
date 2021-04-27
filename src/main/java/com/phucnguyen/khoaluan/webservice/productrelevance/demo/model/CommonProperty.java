package com.phucnguyen.khoaluan.webservice.productrelevance.demo.model;
//this interface is used to make a common (maybe call a super class) between
//all class implemeting it. The purpose is to compare different class based on
//common property(ies) 
public interface CommonProperty{
    float getCommonScoreProperty();
}

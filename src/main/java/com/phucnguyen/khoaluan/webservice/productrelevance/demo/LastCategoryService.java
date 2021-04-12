package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LastCategoryService {
    @Autowired
    private LastCategoryRepo repo;

    public List<MappedLastCategory> getCategoriesBasedOnTikiCate(String category){
        return repo.findByTikiIdContaining(category);
    }

    public List<MappedLastCategory> getCategoriesBasedOnShopeeCate(String category){
        return repo.findByShopeeIdContaining(category);
    }
}

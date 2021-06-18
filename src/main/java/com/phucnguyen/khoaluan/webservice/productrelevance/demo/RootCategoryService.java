package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootCategoryService {
    @Autowired
    RootCategoryRepo rootCategoryRepo;

    public RootCategory getRootCategoryById(int rootId) {
        return rootCategoryRepo.findById(rootId).get();
    }
    
    public List<String> getAllRootCategories(){
        List<String> allCategories = new ArrayList<String>();
        rootCategoryRepo.findAll().forEach(rootCate -> allCategories.add(rootCate.getRootName()));
        return allCategories;
    }
}

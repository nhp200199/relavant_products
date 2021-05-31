package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootCategoryService {
    @Autowired
    RootCategoryRepo rootCategoryRepo;

    public RootCategory getRootCategoryById(int rootId) {
        return rootCategoryRepo.findById(rootId).get();
    }
    
}

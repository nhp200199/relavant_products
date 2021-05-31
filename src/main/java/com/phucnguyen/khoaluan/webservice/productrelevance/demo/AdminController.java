package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@RestController
public class AdminController {
    @Autowired
    private StopwordService stopwordService;
    @Autowired RootCategoryService rootCategoryService;
    
    @PostMapping("/api/v1/admin/relevant-products/root-category/{rootCategory}/stopwords")
    public void addOneNewStopword(@PathVariable int rootCategory, @RequestBody String stopwordsString){
        stopwordService.addOneNewStopword(rootCategory, stopwordsString);
    }

    @DeleteMapping("/api/v1/admin/relevant-products/root-category/{categoryId}/stopwords/{stopword}")
    public void deleteOneStopword(@PathVariable int categoryId, @PathVariable String stopword){
        StopwordId id = new StopwordId(stopword, categoryId);
        stopwordService.deleteStopwordById(id);
    }

    @DeleteMapping("/api/v1/admin/relevant-products/root-category/{categoryId}/stopwords")
    public void deleteStopwordsInCategory(@PathVariable int categoryId){
        stopwordService.deleteStopwordsInCategory(categoryId);
    }

    @GetMapping("/api/v1/admin/relevant-products/stopwords")
    public List<Stopword> getAllStopwords(){
        return stopwordService.getAllStopwords();
    }

    @GetMapping("/api/v1/admin/relevant-products/root-category/{rootId}")
    public RootCategory getRootCategoryById(@PathVariable int rootId){
        return rootCategoryService.getRootCategoryById(rootId);
    }
}

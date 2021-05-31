package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StopwordService {
    @Autowired
    private StopwordRepo stopwordRepo;
    @Autowired
    private RootCategoryRepo rootCategoryRepo;

    public List<Stopword> getAllStopwords() {
        List<Stopword> resultList= new ArrayList<Stopword>();
        stopwordRepo.findAll().forEach(resultList::add);
        return resultList;
    
    }

    public void addOneNewStopword(int cateId, String name) {
        RootCategory rootCate = rootCategoryRepo.findById(cateId).get();
        
        // ArrayList<Stopword> stopwords = new ArrayList<Stopword>();
        // for (String stopword : name.split("\\r\\n")) {
        //     Stopword element = new Stopword();
        //     element.setName(stopword);
        //     element.setRootCate(rootCate);
        //     stopwords.add(element);
        // }
        // stopwordRepo.saveAll(stopwords);
        Stopword stopword = new Stopword();
        stopword.setName(name);
        stopword.setRootCate(rootCate);
        stopwordRepo.save(stopword);
    }

    public void UpdateStopword(Stopword Stopword){
        stopwordRepo.save(Stopword);
    }

    public void deleteStopwordById(StopwordId stopwordId){
        stopwordRepo.deleteById(stopwordId);;
    }
    public List<String> getStopwordName(){
        return stopwordRepo.getStopwordName();
    }
    public List<String> getStopwordsByTikiCate(String category){
        return stopwordRepo.getStopwordsByTikiCate(category);
    }
    public List<String> getStopwordsByShopeeCate(String category){
        return stopwordRepo.getStopwordsByShopeeCate(category);
    }

    public void deleteStopwordsInCategory(int categoryId) {
        stopwordRepo.deleteStopwordsInCategory(categoryId);
    }
}

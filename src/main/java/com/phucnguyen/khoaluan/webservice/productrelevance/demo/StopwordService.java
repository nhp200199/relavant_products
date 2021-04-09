package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StopwordService {
    @Autowired
    private StopwordRepo repo;

    public List<Stopword> getAllStopwords() {
        List<Stopword> resultList= new ArrayList<Stopword>();
        repo.findAll().forEach(resultList::add);
        return resultList;
    
    }

    public void AddNewStopword(Stopword Stopword) {
        repo.save(Stopword);
    }

    public void UpdateStopword(Stopword Stopword){
        repo.save(Stopword);
    }

    public void DeleteStopword(Integer id){
        repo.deleteById(id);;
    }
    public List<String> getStopwordName(){
        return repo.getStopwordName();
    }
    public List<String> getStopwordsByRootCate(String category){
        return repo.getStopwordsByRootCate(category);
    }
    public List<String> getStopwordsByShopeeCate(String category){
        return repo.getStopwordsByShopeeCate(category);
    }
}

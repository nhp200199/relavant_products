package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepo repo;

    public List<Evaluation> getEvaluationByShopeeCateId(String cateId) {
        return repo.getEvaluationByShopeeCateId(cateId);
    }
    public List<Evaluation> getEvaluationByTikiCateId(String cateId) {
        return repo.getEvaluationByTikiCateId(cateId);
    }
}

package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EvaluationRepo extends CrudRepository<Evaluation, Integer>{
    
    @Query(value = "select criteria.* " +
    "from standard_last_node_category category " +
    "join last_cate_evaluation_criteria mid " +
    "on category.id = mid.cate_id " +
    "join evaluation_criteria criteria " +
    "on mid.criteria_id = criteria.id " + 
    "where category.shopee_equi_cate_id like %:cateId%", nativeQuery = true)
    public List<Evaluation> getEvaluationByShopeeCateId(@Param("cateId")String cateId);

    @Query(value = "select criteria.* " +
    "from standard_last_node_category category " +
    "join last_cate_evaluation_criteria mid " +
    "on category.id = mid.cate_id " +
    "join evaluation_criteria criteria " +
    "on mid.criteria_id = criteria.id " + 
    "where category.tiki_equi_cate_id like %:cateId%", nativeQuery = true)
    public List<Evaluation> getEvaluationByTikiCateId(@Param("cateId")String cateId);
}

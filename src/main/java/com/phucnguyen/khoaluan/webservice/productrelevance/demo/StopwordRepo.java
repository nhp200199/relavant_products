package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StopwordRepo extends CrudRepository<Stopword, StopwordId>{
    
    @Query("select name from stop_word")
    public List<String> getStopwordName();

    @Query(value = "select sw.name " + 
    "from stop_word sw " + 
    "join root_category rc " +
    "on sw.root_cate_id = rc.id " +
    "join standard_last_node_category lc " +
    "on lc.root_id = rc.id " +
    "where lc.tiki_equi_cate_id like %:category%", nativeQuery = true)
    public List<String> getStopwordsByTikiCate(@Param("category") String category);

    @Query(value = "select sw.name " + 
    "from stop_word sw " + 
    "join root_category rc " +
    "on sw.root_cate_id = rc.id " +
    "join standard_last_node_category lc " +
    "on lc.root_id = rc.id " +
    "where lc.shopee_equi_cate_id like %:category%", nativeQuery = true)
    public List<String> getStopwordsByShopeeCate(@Param("category") String category);

    @Modifying
    @Transactional
    @Query(value = "delete from stop_word " +
    "where root_cate_id = :rootCateId", nativeQuery = true)
    public void deleteStopwordsInCategory(@Param("rootCateId") int rootCateId);
}

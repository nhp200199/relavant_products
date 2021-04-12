package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LastCategoryRepo extends CrudRepository<MappedLastCategory, Integer>{

    List<MappedLastCategory> findByTikiIdContaining(String category);
    List<MappedLastCategory> findByShopeeIdContaining(String category);
}

package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "standard_last_node_category")
public class MappedLastCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(name = "tiki_equi_cate_id")
    private String tikiId;
    @Column(name = "shopee_equi_cate_id")
    private String shopeeId;
    @ManyToMany()
    @JoinTable(
      name = "last_cate_evaluation_criteria",
      joinColumns = @JoinColumn(name = "cate_id"), 
      inverseJoinColumns = @JoinColumn(name = "criteria_id")
    ) 
    private Set<Evaluation> evaluations;
    @ManyToOne()
    @JoinColumn(name = "root_id")
    private RootCategory rootCate;

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getTikiId() {
      return tikiId;
    }
    public void setTikiId(String tikiId) {
      this.tikiId = tikiId;
    }
    public String getShopeeId() {
      return shopeeId;
    }
    public void setShopeeId(String shopeeId) {
      this.shopeeId = shopeeId;
    }
    public Set<Evaluation> getEvaluations() {
      return evaluations;
    }
    public void setEvaluations(Set<Evaluation> evaluations) {
      this.evaluations = evaluations;
    }
    public RootCategory getRootCate() {
      return rootCate;
    }
    public void setRootCate(RootCategory rootCate) {
      this.rootCate = rootCate;
    }

}

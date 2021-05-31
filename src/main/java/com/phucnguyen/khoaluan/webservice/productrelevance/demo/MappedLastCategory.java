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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
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
    public RootCategory getRootCate() {
      return rootCate;
    }
    public void setRootCate(RootCategory rootCate) {
      this.rootCate = rootCate;
    }

}

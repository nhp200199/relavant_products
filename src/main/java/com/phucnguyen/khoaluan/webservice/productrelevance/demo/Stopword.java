package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "stop_word")
@IdClass(StopwordId.class)
public class Stopword {
    private int id;
    @Id
    private String name;
    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "root_cate_id")
    private RootCategory rootCate;

    public Stopword(){}

    public Stopword(int id, String name, RootCategory rootCate) {
        this.id = id;
        this.name = name;
        this.rootCate = rootCate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public RootCategory getRootCate() {
        return rootCate;
    }
    public void setRootCate(RootCategory rootCate) {
        this.rootCate = rootCate;
    }

}

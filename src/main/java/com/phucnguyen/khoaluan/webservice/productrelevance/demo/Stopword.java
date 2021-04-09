package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import javax.persistence.*;

@Entity(name = "stop_word")
public class Stopword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String pos;
    @ManyToOne
    @JoinColumn(name = "root_cate_id")
    private RootCategory rootCate;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPos() {
        return pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
    public RootCategory getRootCate() {
        return rootCate;
    }
    public void setRootCate(RootCategory rootCate) {
        this.rootCate = rootCate;
    }

}

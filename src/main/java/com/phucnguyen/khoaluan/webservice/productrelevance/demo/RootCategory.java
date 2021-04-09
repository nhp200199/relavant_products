package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="root_category")
public class RootCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "root_name")
    private String rootName;
    @OneToMany(mappedBy = "rootCate")
    private Set<Stopword> stopwords;
    @OneToMany(mappedBy = "rootCate")
    private Set<MappedLastCategory> subCategories;
    
    public String getRootName() {
        return rootName;
    }
    public void setRootName(String rootName) {
        this.rootName = rootName;
    }
    public Set<Stopword> getStopwords() {
        return stopwords;
    }
    public void setStopwords(Set<Stopword> stopwords) {
        this.stopwords = stopwords;
    }
    public Set<MappedLastCategory> getSubCategories() {
        return subCategories;
    }
    public void setSubCategories(Set<MappedLastCategory> subCategories) {
        this.subCategories = subCategories;
    }
    
}

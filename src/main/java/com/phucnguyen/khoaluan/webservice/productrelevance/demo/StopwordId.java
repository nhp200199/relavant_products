package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.io.Serializable;

public class StopwordId implements Serializable{
    private String name;
    private int rootCate;

    public StopwordId() {
    }

    public StopwordId(String name, int rootCate) {
        this.name = name;
        this.rootCate = rootCate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + rootCate;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StopwordId other = (StopwordId) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (rootCate != other.rootCate)
            return false;
        return true;
    }
}

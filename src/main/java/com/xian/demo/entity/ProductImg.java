package com.xian.demo.entity;

import java.io.Serializable;

public class ProductImg implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iid;
    private Integer pid;
    private String name;

    public ProductImg() {
    }

    public ProductImg(Integer iid, Integer pid, String name) {
        this.iid = iid;
        this.pid = pid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "iid=" + iid +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

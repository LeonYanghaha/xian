package com.xian.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "xian",type = "ProductImg")
public class ProductImg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer iid;
    @Field(type = FieldType.Integer)
    private Integer pid;
    @Field(type = FieldType.Text)
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

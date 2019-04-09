package com.xian.demo.entity;

public class Slide {

    private Integer id;
    private String name;
    private String desc;
    private String url;
    private String src;

    public Slide() {
    }

    public Slide(Integer id, String name, String desc, String url, String src) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.src = src;
    }

    @Override
    public String toString() {
        return "Slide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", src='" + src + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}



package com.xian.demo.entity;

public class Create {
    private Integer cid;
    private String cname;
    private String caddress;
    private String cdesc;

    @Override
    public String toString() {
        return "Create{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", caddress='" + caddress + '\'' +
                ", cdesc='" + cdesc + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public Create() {
    }

    public Create(Integer cid) {
        this.cid = cid;
    }

    public Create(Integer cid, String cname, String caddress, String cdesc) {
        this.cid = cid;
        this.cname = cname;
        this.caddress = caddress;
        this.cdesc = cdesc;
    }
}

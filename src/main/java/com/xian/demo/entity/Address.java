package com.xian.demo.entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
public class Address {

    private Integer aid;
    private Integer uid;
    private Date time;
    @NotNull(message = "用户ID不能为空")
    private String aname;
    private String aadderss;
    private String atag;
    @NotNull(message = "收件人电话不能为空")
    private String aphone;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", time=" + time +
                ", aname='" + aname + '\'' +
                ", aadderss='" + aadderss + '\'' +
                ", atag='" + atag + '\'' +
                ", aphone='" + aphone + '\'' +
                '}';
    }

    public Address(Integer aid, Integer uid, Date time, String aname, String aadderss, String atag, String aphone) {
        this.aid = aid;
        this.uid = uid;
        this.time = time;
        this.aname = aname;
        this.aadderss = aadderss;
        this.atag = atag;
        this.aphone = aphone;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAadderss() {
        return aadderss;
    }

    public void setAadderss(String aadderss) {
        this.aadderss = aadderss;
    }

    public String getAtag() {
        return atag;
    }

    public void setAtag(String atag) {
        this.atag = atag;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }
}

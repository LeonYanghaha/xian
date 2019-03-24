package com.xian.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class V_user_order_detial {

    private Integer oid;
    private Integer uid;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date submitTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date payTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date pushTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date ReceivedTime;
    private Integer aid;
    private Double totalPrice;
    private String meta;
    private List<OrderDetial> orderDetial;
    private Short status;
    private String name;

    private String aname;
    private String aadderss;
    private String atag;
    private String aphone;

    public V_user_order_detial(Integer oid, Integer uid, Date submitTime, Date payTime,
                               Date pushTime, Date receivedTime, Integer aid, Double totalPrice,
                               String meta, List<OrderDetial> orderDetial, Short status, String name,
                               String aname, String aadderss, String atag, String aphone) {
        this.oid = oid;
        this.uid = uid;
        this.submitTime = submitTime;
        this.payTime = payTime;
        this.pushTime = pushTime;
        ReceivedTime = receivedTime;
        this.aid = aid;
        this.totalPrice = totalPrice;
        this.meta = meta;
        this.orderDetial = orderDetial;
        this.status = status;
        this.name = name;
        this.aname = aname;
        this.aadderss = aadderss;
        this.atag = atag;
        this.aphone = aphone;
    }

    public V_user_order_detial() {
    }

    @Override
    public String toString() {
        return "V_user_order_detial{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", submitTime=" + submitTime +
                ", payTime=" + payTime +
                ", pushTime=" + pushTime +
                ", ReceivedTime=" + ReceivedTime +
                ", aid=" + aid +
                ", totalPrice=" + totalPrice +
                ", meta='" + meta + '\'' +
                ", orderDetial=" + orderDetial +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", aname='" + aname + '\'' +
                ", aadderss='" + aadderss + '\'' +
                ", atag='" + atag + '\'' +
                ", aphone='" + aphone + '\'' +
                '}';
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Date getReceivedTime() {
        return ReceivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        ReceivedTime = receivedTime;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public List<OrderDetial> getOrderDetial() {
        return orderDetial;
    }

    public void setOrderDetial(List<OrderDetial> orderDetial) {
        this.orderDetial = orderDetial;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

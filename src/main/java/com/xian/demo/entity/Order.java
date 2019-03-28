package com.xian.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Order {

//    oid 订单ID
//    uid 用户ID
//    submitTime  下单时间
//    payTime 付款时间
//    pushTIme 发货时间
//    ReceivedTime 收货时间
//    aid  地址ID
//    totalprice  订单总价
//    meta  用户留言
//    status 状态  10  已提交   20 未付款  30 已付款  40 待发货   50 已发货，待收货
//                 60 收货，交易成功  70 超时未付款，关闭  80 用户主动关闭

    private Integer oid;
    private User user;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date submitTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date payTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date pushTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date ReceivedTime;
    private Address address;
    private Double totalPrice;
    private String meta;
    private List<OrderDetial> orderDetial;
    private Short status;
    private String name;

    public Order(Integer oid, User user, Date submitTime, Date payTime, Date pushTime,
                 Date receivedTime, Address address, Double totalPrice, String meta,
                 List<OrderDetial> orderDetial, Short status, String name) {
        this.oid = oid;
        this.user = user;
        this.submitTime = submitTime;
        this.payTime = payTime;
        this.pushTime = pushTime;
        this.ReceivedTime = receivedTime;
        this.address = address;
        this.totalPrice = totalPrice;
        this.meta = meta;
        this.orderDetial = orderDetial;
        this.status = status;
        this.name = name;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", user=" + user +
                ", submitTime=" + submitTime +
                ", payTime=" + payTime +
                ", pushTime=" + pushTime +
                ", ReceivedTime=" + ReceivedTime +
                ", address=" + address +
                ", totalPrice=" + totalPrice +
                ", meta='" + meta + '\'' +
                ", orderDetial=" + orderDetial +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}

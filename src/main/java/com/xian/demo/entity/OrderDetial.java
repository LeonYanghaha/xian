package com.xian.demo.entity;

public class OrderDetial {
//    oid 订单表
//    pid 商品表
//    price 商品单价
//    number 数量
//    meta  其他信息
    private Integer oid;
    private Integer pid;
    private Integer number;
    private Double price;
    private String meta;

    public OrderDetial() {
    }

    @Override
    public String toString() {
        return "OrderDetial{" +
                "oid=" + oid +
                ", pid=" + pid +
                ", number=" + number +
                ", price=" + price +
                ", meta='" + meta + '\'' +
                '}';
    }

    public OrderDetial(Integer oid, Integer pid, Integer number, Double price, String meta) {
        this.oid = oid;
        this.pid = pid;
        this.number = number;
        this.price = price;
        this.meta = meta;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}

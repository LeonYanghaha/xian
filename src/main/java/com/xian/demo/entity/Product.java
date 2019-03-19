package com.xian.demo.entity;
import java.util.Date;
import java.util.List;

public class Product {

    private Integer pid;
//    价格
    private Double price;
//    商品名
    private String name;
//    描述
    private String desc;
//    库存
    private Integer stock;
//    类型
    private Integer type;
//    状态
    private Short status;
//   是否推荐
    private Boolean isRecommend;
//    上架时间
    private Date pushTime;
//    厂家编号
    private Short producerId;
//    累计销量
    private Integer sellNumber;
//    商品图
    private String imgUrl;

    public Product(Integer pid, Double price, String name, String desc, Integer stock, Integer type,
                   Short status, Boolean isRecommend, Date pushTime, Short producerId,
                   Integer sellNumber, String imgUrl) {
        this.pid = pid;
        this.price = price;
        this.name = name;
        this.desc = desc;
        this.stock = stock;
        this.type = type;
        this.status = status;
        this.isRecommend = isRecommend;
        this.pushTime = pushTime;
        this.producerId = producerId;
        this.sellNumber = sellNumber;
        this.imgUrl = imgUrl;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", stock=" + stock +
                ", type=" + type +
                ", status=" + status +
                ", isRecommend=" + isRecommend +
                ", pushTime=" + pushTime +
                ", producerId=" + producerId +
                ", sellNumber=" + sellNumber +
                ", imgUrl=" + imgUrl +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Short getProducerId() {
        return producerId;
    }

    public void setProducerId(Short producerId) {
        this.producerId = producerId;
    }

    public Integer getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(Integer sellNumber) {
        this.sellNumber = sellNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

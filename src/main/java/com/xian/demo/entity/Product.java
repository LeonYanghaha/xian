package com.xian.demo.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @describe  商品实体类
 * @param {String}
 * @return {String}
 */
@Document(indexName = "xian",type = "Product", shards = 3,replicas = 0, refreshInterval = "-1")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer pid;
    @Field
    private Double price;
    @Field
    private String name;
    @Field
    private String desc;
    @Field
    private Integer stock;
    @Field
    private ProductType productType;
    @Field
    private Short status;
    @Field
    private Boolean isRecommend;
    @Field
    private Date pushTime;
    @Field
    private Date lastUpdateTime; // 添加最后修改时间，用于es同步数据
    @Field
    private Create create;
    @Field
    private Integer sellNumber;
//    @Field
    private List<ProductImg> productImgList;

    public Product(Double price, String name, String desc,
                   Short status, Boolean isRecommend,
                   Date pushTime, Date lastUpdateTime) {
//        this.pid = pid;
        this.price = price;
        this.name = name;
        this.desc = desc;
//        this.stock = stock;
        this.status = status;
        this.isRecommend = isRecommend;
        this.pushTime = pushTime;
        this.lastUpdateTime = lastUpdateTime;
//        this.sellNumber = sellNumber;
    }
    public Product(Integer pid, Double price, String name, String desc,
                   Integer stock, ProductType productType, Short status,
                   Boolean isRecommend, Date pushTime, Date lastUpdateTime,
                   Create create, Integer sellNumber, List<ProductImg> productImgList) {
        this.pid = pid;
        this.price = price;
        this.name = name;
        this.desc = desc;
        this.stock = stock;
        this.productType = productType;
        this.status = status;
        this.isRecommend = isRecommend;
        this.pushTime = pushTime;
        this.lastUpdateTime = lastUpdateTime;
        this.create = create;
        this.sellNumber = sellNumber;
        this.productImgList = productImgList;
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
                ", productType=" + productType +
                ", status=" + status +
                ", isRecommend=" + isRecommend +
                ", pushTime=" + pushTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", create=" + create +
                ", sellNumber=" + sellNumber +
                ", productImgList=" + productImgList +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Create getCreate() {
        return create;
    }

    public void setCreate(Create create) {
        this.create = create;
    }

    public Integer getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(Integer sellNumber) {
        this.sellNumber = sellNumber;
    }

    public List<ProductImg> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImg> productImgList) {
        this.productImgList = productImgList;
    }
}

package com.xian.demo.entity;

import java.util.Date;
import java.util.List;

/**
 * @describe  新建了购物车和商品表的视图。然而必须建立一个对应的实体类。
 *             要不然查出来的数据没发做映射，只能用object接收😢😢
 */
public class CarDetial {

    private Integer carid;
    private Integer uid;
    private Date addTime;

    private Integer pid;
    private Double price;
    private String name;
    private String desc;
    private Integer stock;
    private ProductType productType;
    private Short status;
    private Boolean isRecommend;
    private Date pushTime;
    private Create create;
    private Integer sellNumber;
    private List<ProductImg> productImgList;

    public CarDetial() {
    }

    @Override
    public String toString() {
        return "CarDetial{" +
                "carid=" + carid +
                ", uid=" + uid +
                ", addTime=" + addTime +
                ", pid=" + pid +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", stock=" + stock +
                ", productType=" + productType +
                ", status=" + status +
                ", isRecommend=" + isRecommend +
                ", pushTime=" + pushTime +
                ", create=" + create +
                ", sellNumber=" + sellNumber +
                ", productImgList=" + productImgList +
                '}';
    }

    public CarDetial(Integer carid, Integer uid, Date addTime, Integer pid, Double price, String name, String desc, Integer stock, ProductType productType, Short status, Boolean isRecommend, Date pushTime, Create create, Integer sellNumber, List<ProductImg> productImgList) {
        this.carid = carid;
        this.uid = uid;
        this.addTime = addTime;
        this.pid = pid;
        this.price = price;
        this.name = name;
        this.desc = desc;
        this.stock = stock;
        this.productType = productType;
        this.status = status;
        this.isRecommend = isRecommend;
        this.pushTime = pushTime;
        this.create = create;
        this.sellNumber = sellNumber;
        this.productImgList = productImgList;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

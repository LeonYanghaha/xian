package com.xian.demo.entity;
import java.util.Date;
import java.util.List;

/**
 * @describe  商品实体类
 * @param {String}
 * @return {String}
 */
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
    private ProductType productType;
//    状态
    private Short status;
//   是否推荐
    private Boolean isRecommend;
//    上架时间
    private Date pushTime;
//    厂家编号
    private Create create;
//    累计销量
    private Integer sellNumber;
//    商品图
    private List<ProductImg> productImgList;

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
                ", create=" + create +
                ", sellNumber=" + sellNumber +
                ", productImgList=" + productImgList +
                '}';
    }

    public Product() {
    }

    public Product(Integer pid, Double price, String name, String desc, Integer stock,
                   ProductType productType, Short status, Boolean isRecommend, Date pushTime,
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
        this.create = create;
        this.sellNumber = sellNumber;
        this.productImgList = productImgList;
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

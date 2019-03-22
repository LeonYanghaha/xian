package com.xian.demo.entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Car {

    private Integer cid;
    @NotNull(message = "用户ID不能为空")
    private Integer uid;
    @NotNull(message = "商品ID不能为空")
    private Integer pid;
    private Date addTime;
    private Product product;

    public Car() {
    }

    public Car(Integer cid, @NotNull(message = "用户ID不能为空") Integer uid, @NotNull(message = "商品ID不能为空") Integer pid, Date addTime, Product product) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.addTime = addTime;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", addTime=" + addTime +
                ", product=" + product +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

package com.xian.demo.entity;

import sun.security.util.ManifestEntryVerifier;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Car {

    @NotNull(message = "用户ID不能为空")
    private Integer uid;
    @NotNull(message = "商品ID不能为空")
    private Integer pid;
    private Date addTime;

    public Car(Integer uid, Integer pid, Date addTime) {
        this.uid = uid;
        this.pid = pid;
        this.addTime = addTime;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", addTime=" + addTime +
                '}';
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
}

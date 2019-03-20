package com.xian.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String un;
    private String phone;
    @JsonIgnore
    @NotNull(message = "密码不能为空")
    private String pw;
    private String headImage;
    private Short status;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date registerTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
    private Date lastLoginTime;

    public User(Integer id, String un, String phone, String pw, String headImage,
                Short status, Date registerTime, Date lastLoginTime) {
        this.id = id;
        this.un = un;
        this.phone = phone;
        this.pw = pw;
        this.headImage = headImage;
        this.status = status;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", un='" + un + '\'' +
                ", phone='" + phone + '\'' +
                ", pw='" + pw + '\'' +
                ", headImage='" + headImage + '\'' +
                ", status=" + status +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }

    public User() {
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}

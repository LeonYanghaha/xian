package com.xian.demo.entity;


import java.util.Date;

public class Msg {
    private Integer mid;
    private Integer from;
    private Integer to;
    private String desc;
    private String detial;
    private Boolean isRead;
    private Date sendTime;
    private String type;

    public Msg() {
    }

    @Override
    public String toString() {
        return "Msg{" +
                "mid=" + mid +
                ", from=" + from +
                ", to=" + to +
                ", desc='" + desc + '\'' +
                ", detial='" + detial + '\'' +
                ", isRead=" + isRead +
                ", sendTime=" + sendTime +
                ", type='" + type + '\'' +
                '}';
    }

    public Msg(Integer mid, Integer from, Integer to, String desc, String detial,
               Boolean isRead, Date sendTime, String type) {
        this.mid = mid;
        this.from = from;
        this.to = to;
        this.desc = desc;
        this.detial = detial;
        this.isRead = isRead;
        this.sendTime = sendTime;
        this.type = type;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

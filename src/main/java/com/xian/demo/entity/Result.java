package com.xian.demo.entity;


public class Result {

    private Short code;
    private String msg;
    private Object data;

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public void setCodeAndMsg(Short code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result(Short code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Short code) {
        this.code = code;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

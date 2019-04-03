package com.xian.demo.entity;

import java.io.Serializable;

public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer count; //总数
    private Integer pageShowNumber; // 页面展示的条数
    private Integer currentPage; // 当前的页数
    private Integer totalPage; // 总页数
    private Integer startIndex;
    private Integer endIndex;  // 这个属性值的名字没弄好,不应该是endIndex,应该是size之类的。这个原因是我对于limit x,y的两个参数理解不熟悉导致的
    private Object data;

    public Page() {
    }

    public Page(Integer count, Integer pageShowNumber, Integer currentPage, Integer totalPage, Object data) {
        this.count = count;
        this.pageShowNumber = pageShowNumber;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", pageShowNumber=" + pageShowNumber +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", data=" + data +
                '}';
    }

    public void setAllProp(Integer pageShowNumber, Integer currentPage, Integer count){
        this.setPageShowNumber(pageShowNumber);
        this.setCurrentPage(currentPage);
        this.setCount(count);
        this.setStartIndexAndEndIndexAndtotalPage(pageShowNumber, currentPage, count);
    }

    public void setStartIndexAndEndIndexAndtotalPage(Integer pageShowNumber, Integer currentPage, Integer count){
        Double tempTotalPage = Math.ceil(count/pageShowNumber);
        this.totalPage = tempTotalPage.intValue();
        this.startIndex = (currentPage-1)*pageShowNumber;
        this.endIndex = pageShowNumber;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageShowNumber() {
        return pageShowNumber;
    }

    public void setPageShowNumber(Integer pageShowNumber) {
        this.pageShowNumber = pageShowNumber;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

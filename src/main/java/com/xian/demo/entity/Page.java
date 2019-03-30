package com.xian.demo.entity;

public class Page {

    private Integer count; //总数
    private Integer pageShowNumber; // 页面展示的条数
    private Integer currentPage; // 当前的页数
    private Integer totalPage; // 总页数
    private Integer startIndex;
    private Integer endIndex;
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

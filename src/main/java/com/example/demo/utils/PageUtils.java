package com.example.demo.utils;

import lombok.Data;

import java.util.Collection;

@Data
public class PageUtils<T> {
    private int currentPage;//当前页
    private int pageNo;//页码
    private int pageSize;//每页多少条
    private long totalPage = 0l;//总页数
    private long totalCount;//总条数
    private Collection<T> currentList;

    public int getPageNo() {
        return (pageNo - 1) * pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotalPage() {
        return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}

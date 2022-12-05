package com.project.shop.feature.page;

import lombok.Data;

@Data
public class Paging {
    private int startPage;
    private int endPage;
    private int countPerPage;
    private int currentPage;
    private int skip;
    private int amount;
    private int total;
    private boolean prev;
    private boolean next;

    public Paging(int currentPage, int countPerPage, int amount, int total) {
        this.currentPage = currentPage;
        this.countPerPage = countPerPage;
        this.skip = (currentPage - 1) * countPerPage;
        this.amount = amount;
        this.total = total;
        this.endPage = (int)(Math.ceil(getCurrentPage() / 10.0)) * 10;
        this.startPage = this.endPage - 9;
        int realEnd = (int)(Math.ceil(total * 1.0 / getCountPerPage()));
        if(realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}

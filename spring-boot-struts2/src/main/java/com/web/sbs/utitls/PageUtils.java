package com.web.sbs.utitls;

public class PageUtils {
    private int next;
    private int totalPages;

    public PageUtils(int page, int size, int total){
        this.totalPages = (int) Math.floor(total / size);
        if(total - totalPages* size  > 0)
            totalPages++;
        System.out.println("TOTAL PAGE: "+ totalPages);
        this.next = page * size;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

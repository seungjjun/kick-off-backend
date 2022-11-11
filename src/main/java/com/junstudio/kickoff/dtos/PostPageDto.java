package com.junstudio.kickoff.dtos;

public class PostPageDto {
    private final int startPage;

    private int lastPage;

    private final int currentPageNumber;

    private final int currentLastPage;

    private final Long totalPageNumber;

    public PostPageDto(int currentPageNumber, Long totalPageNumber) {
        this.currentPageNumber = currentPageNumber;
        this.totalPageNumber = totalPageNumber;
        this.lastPage = (int) (Math.ceil(currentPageNumber / 10.0) * 10);
        this.currentLastPage = (int) Math.ceil(totalPageNumber * 1.0 / 2);
        this.startPage = lastPage - 9;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getLastPage() {
        if (this.lastPage > currentLastPage) {
            this.lastPage = currentLastPage;
        }
        return lastPage;
    }

    public Long getTotalPageNumber() {
        return totalPageNumber;
    }

    public int getCurrentLastPage() {
        return currentLastPage;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }
}

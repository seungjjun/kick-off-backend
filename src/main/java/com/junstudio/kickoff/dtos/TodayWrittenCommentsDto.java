package com.junstudio.kickoff.dtos;

public class TodayWrittenCommentsDto {
    private final int commentsNumber;

    public TodayWrittenCommentsDto(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }
}

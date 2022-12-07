package com.junstudio.kickoff.dtos;

public class PostsByDateDto {
    private int todayPostsNumber;
    private final int aDayAgoPostsNumber;
    private final int twoDaysAgoPostsNumber;
    private final int threeDaysAgoPostsNumber;
    private final int fourDaysAgoPostsNumber;
    private final int fiveDaysAgoPostsNumber;
    private final int sixDaysAgoPostsNumber;

    public PostsByDateDto(int todayPostsNumber,
                          int aDayAgoPostsNumber,
                          int twoDaysAgoPostsNumber,
                          int threeDaysAgoPostsNumber,
                          int fourDaysAgoPostsNumber,
                          int fiveDaysAgoPostsNumber,
                          int sixDaysAgoPostsNumber) {
        this.todayPostsNumber = todayPostsNumber;
        this.aDayAgoPostsNumber = aDayAgoPostsNumber;
        this.twoDaysAgoPostsNumber = twoDaysAgoPostsNumber;
        this.threeDaysAgoPostsNumber = threeDaysAgoPostsNumber;
        this.fourDaysAgoPostsNumber = fourDaysAgoPostsNumber;
        this.fiveDaysAgoPostsNumber = fiveDaysAgoPostsNumber;
        this.sixDaysAgoPostsNumber = sixDaysAgoPostsNumber;
    }

    public int getTodayPostsNumber() {
        return todayPostsNumber;
    }

    public int getaDayAgoPostsNumber() {
        return aDayAgoPostsNumber;
    }

    public int getTwoDaysAgoPostsNumber() {
        return twoDaysAgoPostsNumber;
    }

    public int getThreeDaysAgoPostsNumber() {
        return threeDaysAgoPostsNumber;
    }

    public int getFourDaysAgoPostsNumber() {
        return fourDaysAgoPostsNumber;
    }

    public int getFiveDaysAgoPostsNumber() {
        return fiveDaysAgoPostsNumber;
    }

    public int getSixDaysAgoPostsNumber() {
        return sixDaysAgoPostsNumber;
    }
}

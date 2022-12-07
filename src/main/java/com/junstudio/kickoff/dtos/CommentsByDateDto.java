package com.junstudio.kickoff.dtos;

public class CommentsByDateDto {
    private final int todayCommentsNumber;
    private final int aDayAgoCommentsNumber;
    private final int twoDaysAgoCommentsNumber;
    private final int threeDaysAgoCommentsNumber;
    private final int fourDaysAgoCommentsNumber;
    private final int fiveDaysAgoCommentsNumber;
    private final int sixDaysAgoCommentsNumber;

    public CommentsByDateDto(int todayCommentsNumber,
                             int aDayAgoCommentsNumber,
                             int twoDaysAgoCommentsNumber,
                             int threeDaysAgoCommentsNumber,
                             int fourDaysAgoCommentsNumber,
                             int fiveDaysAgoCommentsNumber,
                             int sixDaysAgoCommentsNumber
    ) {

        this.todayCommentsNumber = todayCommentsNumber;
        this.aDayAgoCommentsNumber = aDayAgoCommentsNumber;
        this.twoDaysAgoCommentsNumber = twoDaysAgoCommentsNumber;
        this.threeDaysAgoCommentsNumber = threeDaysAgoCommentsNumber;
        this.fourDaysAgoCommentsNumber = fourDaysAgoCommentsNumber;
        this.fiveDaysAgoCommentsNumber = fiveDaysAgoCommentsNumber;
        this.sixDaysAgoCommentsNumber = sixDaysAgoCommentsNumber;
    }

    public int getTodayCommentsNumber() {
        return todayCommentsNumber;
    }

    public int getaDayAgoCommentsNumber() {
        return aDayAgoCommentsNumber;
    }

    public int getTwoDaysAgoCommentsNumber() {
        return twoDaysAgoCommentsNumber;
    }

    public int getThreeDaysAgoCommentsNumber() {
        return threeDaysAgoCommentsNumber;
    }

    public int getFourDaysAgoCommentsNumber() {
        return fourDaysAgoCommentsNumber;
    }

    public int getFiveDaysAgoCommentsNumber() {
        return fiveDaysAgoCommentsNumber;
    }

    public int getSixDaysAgoCommentsNumber() {
        return sixDaysAgoCommentsNumber;
    }
}

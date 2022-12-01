package com.junstudio.kickoff.dtos;

public class ApplicationFormDto {
    private final Long applicationPostId;

    private Long userId;

    private final String grade;

    private String reason;

    private final String userName;


    public ApplicationFormDto(Long applicationPostId,
                              String grade,
                              String userName) {
        this.applicationPostId = applicationPostId;
        this.grade = grade;
        this.userName = userName;
    }

    public Long getApplicationPostId() {
        return applicationPostId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getGrade() {
        return grade;
    }

    public String getReason() {
        return reason;
    }

    public String getUserName() {
        return userName;
    }
}

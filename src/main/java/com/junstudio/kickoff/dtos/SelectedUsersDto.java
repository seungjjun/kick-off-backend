package com.junstudio.kickoff.dtos;

import java.util.List;

public class SelectedUsersDto {
    public List<Long> usersId;
    public String grade;

    public SelectedUsersDto(List<Long> usersId, String grade) {
        this.usersId = usersId;
        this.grade = grade;
    }

    public List<Long> getUsersId() {
        return usersId;
    }

    public String getGrade() {
        return grade;
    }
}

package com.junstudio.kickoff.dtos;

public class ScheduleDto {
    private final String today;

    public ScheduleDto(String today) {
        this.today = today;
    }

    public String getToday() {
        return today;
    }
}

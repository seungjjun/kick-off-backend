package com.junstudio.kickoff.dtos;

public class RegistrationResultDto {
    private final String name;

    public RegistrationResultDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

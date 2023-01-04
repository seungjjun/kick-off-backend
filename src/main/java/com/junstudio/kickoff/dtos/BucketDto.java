package com.junstudio.kickoff.dtos;

public class BucketDto {
    private final Long availableTokens;

    public BucketDto(Long availableTokens) {

        this.availableTokens = availableTokens;
    }

    public Long getAvailableTokens() {
        return availableTokens;
    }
}

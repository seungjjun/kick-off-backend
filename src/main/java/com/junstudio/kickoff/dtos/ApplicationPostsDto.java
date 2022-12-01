package com.junstudio.kickoff.dtos;

import java.util.List;

public class ApplicationPostsDto {
    private final List<ApplicationPostDto> applicationPosts;

    public ApplicationPostsDto(List<ApplicationPostDto> applicationPosts) {
        this.applicationPosts = applicationPosts;
    }

    public List<ApplicationPostDto> getApplicationPosts() {
        return applicationPosts;
    }
}

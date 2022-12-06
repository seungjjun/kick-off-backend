package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Post;

import java.util.List;

public class TodayCreatePostsDto {
    private final List<Post> posts;

    public TodayCreatePostsDto(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}

package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Post;

import java.util.List;
import java.util.stream.Collectors;

public class TodayCreatePostsDto {
    private final List<PostDto> posts;

    public TodayCreatePostsDto(List<Post> posts) {
        this.posts = posts.stream().map(Post::toDto).collect(Collectors.toList());
    }

    public List<PostDto> getPosts() {
        return posts;
    }
}

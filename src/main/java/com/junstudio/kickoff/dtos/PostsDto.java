package com.junstudio.kickoff.dtos;

import java.util.List;

public class PostsDto {
    private final List<PostDto> posts;
    private PageDto page;

    public PostsDto(List<PostDto> posts) {
        this.posts = posts;
    }

    public PostsDto(List<PostDto> posts, PageDto page) {
        this.posts = posts;
        this.page = page;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public PageDto getPage() {
        return page;
    }
}

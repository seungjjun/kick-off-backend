package com.junstudio.kickoff.dtos;

import java.util.List;

public class PostsDto {
    private final List<PostDto> posts;
    private PostPageDto page;

    public PostsDto(List<PostDto> posts) {
        this.posts = posts;
    }

    public PostsDto(List<PostDto> posts, PostPageDto page) {
        this.posts = posts;
        this.page = page;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public PostPageDto getPage() {
        return page;
    }
}

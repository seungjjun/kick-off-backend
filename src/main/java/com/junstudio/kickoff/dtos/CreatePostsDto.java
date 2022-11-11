package com.junstudio.kickoff.dtos;

public class CreatePostsDto {
    private final PostsDto posts;
    private final PostPageDto postPageDto;

    public CreatePostsDto(PostsDto posts, PostPageDto postPageDto) {
        this.posts = posts.toDto();
        this.postPageDto = postPageDto;
    }

    public PostsDto getPosts() {
        return posts;
    }

    public PostPageDto getPostPageDto() {
        return postPageDto;
    }
}

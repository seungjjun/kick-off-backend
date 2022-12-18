package com.junstudio.kickoff.dtos;

public class PostsDto {
    private final PostPageDto page;
    private final CreatePostsDto posts;

    public PostsDto(CreatePostsDto createPostsDto, PostPageDto page) {
        this.posts = createPostsDto;
        this.page = page;
    }

    public CreatePostsDto getPosts() {
        return posts;
    }

    public PostPageDto getPage() {
        return page;
    }
}

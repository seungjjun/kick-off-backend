package com.junstudio.kickoff.dtos;

import java.util.List;

public class PostsDto {
    private PostPageDto page;
    private CreatePostsDto posts;
    private List<PostDto> searchedPosts;

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

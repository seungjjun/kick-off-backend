package com.junstudio.kickoff.dtos;

import java.util.List;

public class PostsDto {
    private PostPageDto page;
    private List<PostDto> categoryPosts;
    private List<PostDto> posts;
    private List<CommentDto> comments;
    private List<ReCommentDto> reComments;
    private List<LikeDto> likes;
    private List<CategoryDto> categories;
    private List<UserDto> users;

    public PostsDto(List<PostDto> categoryPosts, PostPageDto page) {
        this.categoryPosts = categoryPosts;
        this.page = page;
    }

    public PostsDto(List<PostDto> posts,
                    List<CommentDto> comments,
                    List<ReCommentDto> reComments,
                    List<LikeDto> likes,
                    List<CategoryDto> categories,
                    List<UserDto> users) {
        this.posts = posts;
        this.comments = comments;
        this.reComments = reComments;
        this.likes = likes;
        this.categories = categories;
        this.users = users;
    }

    public PostPageDto getPage() {
        return page;
    }

    public List<PostDto> getCategoryPosts() {
        return categoryPosts;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public List<ReCommentDto> getReComments() {
        return reComments;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public PostsDto toDto() {
        return new PostsDto(posts, comments, reComments, likes, categories, users);
    }
}

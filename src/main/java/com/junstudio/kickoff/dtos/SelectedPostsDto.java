package com.junstudio.kickoff.dtos;

import java.util.List;

public class SelectedPostsDto {
    public List<Long> postsId;

    public SelectedPostsDto(List<Long> postsId) {
        this.postsId = postsId;
    }

    public List<Long> getPostsId() {
        return postsId;
    }

    public SelectedPostsDto() {
    }
}

package com.junstudio.kickoff.dtos;

import java.util.List;

public class SelectedLikeDto {
    public List<Long> likeId;

    public SelectedLikeDto() {
    }

    public SelectedLikeDto(List<Long> likeId) {
        this.likeId = likeId;
    }

    public List<Long> getLikeId() {
        return likeId;
    }
}

package com.junstudio.kickoff.dtos;

import java.util.List;

public class SelectedRecommentDto {
    public List<Long> recommentId;

    public SelectedRecommentDto() {
    }

    public SelectedRecommentDto(List<Long> recommentId) {
        this.recommentId = recommentId;
    }

    public List<Long> getRecommentId() {
        return recommentId;
    }
}

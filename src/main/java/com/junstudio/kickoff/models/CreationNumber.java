package com.junstudio.kickoff.models;

import javax.persistence.Embeddable;

@Embeddable
public class CreationNumber {
    private Long postNumber;

    private Long commentNumber;

    public CreationNumber() {
    }

    public CreationNumber(Long postNumber, Long commentNumber) {
        this.postNumber = postNumber;
        this.commentNumber = commentNumber;
    }

    public Long getPostNumber() {
        return postNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public CreationNumber toDto() {
        return new CreationNumber(postNumber, commentNumber);
    }
}

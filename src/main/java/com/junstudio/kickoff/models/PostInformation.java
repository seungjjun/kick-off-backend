package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.PostInformationDto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PostInformation {
    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    public PostInformation() {
    }

    public PostInformation(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public PostInformationDto toDto() {
        return new PostInformationDto(title, content);
    }
}

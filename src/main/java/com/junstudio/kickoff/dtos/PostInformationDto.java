package com.junstudio.kickoff.dtos;

public class PostInformationDto {
    private final String title;

    private final String content;

    public PostInformationDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

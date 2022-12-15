package com.junstudio.kickoff.dtos;

public class ResponseDto {
    private final String sender;

    private final String content;

    public ResponseDto(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}

package com.junstudio.kickoff.dtos;

public class ChatMessageDto {
    private String roomId;

    private String writer;

    private String message;

    private String name;

    public ChatMessageDto() {
    }

    public ChatMessageDto(String roomId, String writer, String message, String name) {
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getWriter() {
        return writer;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void name(String name) {
        this.name = name;
    }
}

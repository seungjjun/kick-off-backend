package com.junstudio.kickoff.dtos;

public class ChatMessageDto {
  private String roomId;

  private String writer;

  private String message;

  public ChatMessageDto() {
  }

  public ChatMessageDto(String roomId, String writer, String message) {
    this.roomId = roomId;
    this.writer = writer;
    this.message = message;
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

  public void setMessage(String message) {
    this.message = message;
  }
}

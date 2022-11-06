package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.ChatMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final SimpMessagingTemplate template;

  public MessageController(SimpMessagingTemplate template) {
    this.template = template;
  }

  @MessageMapping("/chat/enter")
  public void enter(ChatMessageDto message) {
    message.setMessage(message.getWriter() + "님이 채팅방에 입장하였습니다.");
    template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }

  @MessageMapping("/chat/message")
  public void message(ChatMessageDto message) {
    template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}

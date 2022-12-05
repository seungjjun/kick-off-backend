package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.ChatMessageDto;
import com.junstudio.kickoff.services.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    public MessageController(SimpMessagingTemplate template,
                             MessageService messageService) {
        this.template = template;
        this.messageService = messageService;
    }

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDto message,
                      SimpMessageHeaderAccessor headerAccessor) {
        message.setMessage(message.getWriter() + "님이 채팅방에 입장하였습니다.");
        messageService.authentication(headerAccessor, message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(
        ChatMessageDto message,
        SimpMessageHeaderAccessor headerAccessor
    ) {
        messageService.authentication(headerAccessor, message);

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}

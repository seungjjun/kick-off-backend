package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.ChatMessageDto;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class MessageServiceTest {
    @MockBean
    SimpMessageHeaderAccessor simpMessageHeaderAccessor;

    @SpyBean
    JwtUtil jwtUtil;

    User user;
    UserRepository userRepository;

    @Test
    void authentication() {
        userRepository = mock(UserRepository.class);
        jwtUtil = mock(JwtUtil.class);

        user = User.fake();

        ChatMessageDto message =
            spy(new ChatMessageDto("8000", user.name(), "안녕", user.name()));

        Map<String, Object> headers = new HashMap<>();

        headers.put("nativeHeaders", "Authorization=[Bearer AccessToken]");

        MessageHeaders messageHeaders = new MessageHeaders(headers);

        simpMessageHeaderAccessor = mock(SimpMessageHeaderAccessor.class);

        given(simpMessageHeaderAccessor.getMessageHeaders())
            .willReturn(messageHeaders);

        given(userRepository.findByIdentification(any()))
            .willReturn(Optional.of(user));

        MessageService messageService = new MessageService(jwtUtil, userRepository);

        messageService.authentication(simpMessageHeaderAccessor, message);

        verify(message).name(any());
    }
}

package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.ChatMessageDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public MessageService(JwtUtil jwtUtil,
                          UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public void authentication(SimpMessageHeaderAccessor headerAccessor, ChatMessageDto message) {
        String headers = headerAccessor.getMessageHeaders().get("nativeHeaders").toString();

        String accessToken = headers.substring(
            "Authorization=[Bearer  ".length(),  headers.indexOf("]"));

        String identification = jwtUtil.decode(accessToken);

        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        message.name(user.name());
    }
}

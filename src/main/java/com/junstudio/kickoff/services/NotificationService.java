package com.junstudio.kickoff.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junstudio.kickoff.dtos.ResponseDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.SseEmitterRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class NotificationService {
    private final SseEmitterRepository sseEmitterRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(SseEmitterRepository sseEmitterRepository,
                               NotificationRepository notificationRepository,
                               UserRepository userRepository) {
        this.sseEmitterRepository = sseEmitterRepository;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public SseEmitter subscribe(String identification, String lastEventId) {
        Long userId = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new).id();

        String id = userId + "_" + System.currentTimeMillis();
        SseEmitter emitter = sseEmitterRepository.save(id, new SseEmitter(60L * 1000 * 60));

        emitter.onCompletion(() -> sseEmitterRepository.deleteById(id));
        emitter.onTimeout(() -> sseEmitterRepository.deleteById(id));

        sendToClient(emitter, id, "Connected!");

        return emitter;
    }

    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                .id(id)
                .name("sse")
                .data(data));
        } catch (IOException exception) {
            sseEmitterRepository.deleteById(id);
            System.out.println("connect error" + exception);
        }
    }

    public void sendNotification(Long receiverId, Long userId, String content) {
        String sender = userRepository.findById(userId).orElseThrow(UserNotFound::new).name();

        Notification notification = createNotification(sender, content);

        String id = String.valueOf(receiverId);

        notificationRepository.save(notification);

        Map<String, SseEmitter> sseEmitters = sseEmitterRepository.findAllStarWithById(id);

        sseEmitters.forEach(
            (key, emitter) -> {
                sseEmitterRepository.saveEventCache(key, notification);
                try {
                    sendToClient(emitter, key, from(notification));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }

    private Notification createNotification(String sender, String content) {
        return new Notification(sender, content);
    }

    public String from(Notification notification) throws JsonProcessingException {
        ResponseDto responseDto =
            new ResponseDto(notification.sender(), notification.content());

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(responseDto);
    }
}

package com.junstudio.kickoff.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junstudio.kickoff.dtos.NotificationDto;
import com.junstudio.kickoff.exceptions.NotificationNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.SseEmitterRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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

//        sendToClient(emitter, id, "Connected!");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = sseEmitterRepository.findAllEventCacheStartWithId(String.valueOf(userId));
            events.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

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

    public void sendNotification(Long receiverId, Long userId, Long postId, String content, String identification) {
        String sender = userRepository.findById(userId).orElseThrow(UserNotFound::new).name();

        User receiver = userRepository.findById(receiverId).orElseThrow(UserNotFound::new);

        if(receiver.identification().equals(identification)) {
            return;
        }

        Notification notification = createNotification(receiverId, postId, sender, content);

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

    public Notification createNotification(Long receiverId, Long postId, String sender, String content) {
        return new Notification(receiverId, postId, sender, content);
    }

    public String from(Notification notification) throws JsonProcessingException {
        NotificationDto notificationDto =
            new NotificationDto(
                notification.id(),
                notification.sender(),
                notification.content(),
                notification.postId(),
                notification.isRead(),
                notification.createdAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(notificationDto);
    }

    public void read(Long notificationId) {
        Notification notification = notificationRepository
            .findById(notificationId)
            .orElseThrow(NotificationNotFound::new);

        notification.read();
    }
}

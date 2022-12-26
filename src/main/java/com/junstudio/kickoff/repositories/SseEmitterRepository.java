package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Notification;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class SseEmitterRepository {
    public final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    public SseEmitter save(String id, SseEmitter sseEmitter) {
        emitters.put(id, sseEmitter);
        return sseEmitter;
    }

    public void deleteById(String id) {
        emitters.remove(id);
    }

    public Map<String, SseEmitter> findAllStarWithById(String id) {
        return emitters.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith(id))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void saveEventCache(String id, Notification notification) {
        eventCache.put(id, notification);
    }

    public Map<String, Object> findAllEventCacheStartWithId(String id) {
        return eventCache.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith(id))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

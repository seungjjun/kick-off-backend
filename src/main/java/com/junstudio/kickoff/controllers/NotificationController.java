package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.services.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@CrossOrigin
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(
        @RequestAttribute("identification") String identification,
        @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId
    ) {
        return notificationService.subscribe(identification, lastEventId);
    }
}

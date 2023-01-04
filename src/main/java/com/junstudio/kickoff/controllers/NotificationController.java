package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.NotificationsDto;
import com.junstudio.kickoff.exceptions.NotificationNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.services.DeleteNotificationService;
import com.junstudio.kickoff.services.GetNotificationService;
import com.junstudio.kickoff.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@CrossOrigin
public class NotificationController {
    private final NotificationService notificationService;
    private final GetNotificationService getNotificationService;
    private final DeleteNotificationService deleteNotificationService;

    public NotificationController(NotificationService notificationService,
                                  GetNotificationService getNotificationService,
                                  DeleteNotificationService deleteNotificationService) {
        this.notificationService = notificationService;
        this.getNotificationService = getNotificationService;
        this.deleteNotificationService = deleteNotificationService;
    }

    @GetMapping("/notifications")
    private NotificationsDto notifications(
        @RequestAttribute("identification") String identification
    ) {
        return getNotificationService.notifications(identification);
    }

    @GetMapping("/notification")
    private boolean checkNotification(
        @RequestAttribute("identification") String identification
    ) {
        return getNotificationService.checkNotification(identification);
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(
        @RequestAttribute("identification") String identification
    ) {
        return notificationService.subscribe(identification);
    }

    @DeleteMapping("/notifications/{notificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(
        @PathVariable Long notificationId
    ) {
        deleteNotificationService.deleteNotification(notificationId);
    }

    @DeleteMapping("/notifications")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAll(
        @RequestAttribute("identification") String identification
    ) {
        deleteNotificationService.deleteAllNotification(identification);
    }

    @DeleteMapping("/notifications/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteReadNotification(
        @RequestAttribute("identification") String identification
    ) {
        deleteNotificationService.deleteReadNotification(identification);
    }

    @PatchMapping("/notifications/{notificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void read(
        @PathVariable Long notificationId
    ) {
        notificationService.read(notificationId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private UserNotFound userNotFound() {
        return new UserNotFound();
    }

    @ExceptionHandler(NotificationNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private NotificationNotFound notificationNotFound() {
        return new NotificationNotFound();
    }
}

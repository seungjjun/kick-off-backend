package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.NotificationDto;
import com.junstudio.kickoff.dtos.NotificationsDto;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.services.DeleteNotificationService;
import com.junstudio.kickoff.services.GetNotificationService;
import com.junstudio.kickoff.services.NotificationService;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private GetNotificationService getNotificationService;

    @MockBean
    private DeleteNotificationService deleteNotificationService;

    @SpyBean
    private JwtUtil jwtUtil;

    String token;
    String identification;

    Notification notification;

    @BeforeEach
    void setup() {
        identification = "je1ly";

        token = jwtUtil.encode(identification);
        notification = Notification.fake();
    }

    @Test
    void notifications() throws Exception {
        given(getNotificationService.notifications(identification))
            .willReturn(new NotificationsDto(List.of(
                new NotificationDto(
                    notification.id(),
                    notification.sender(),
                    notification.content(),
                    notification.postId(),
                    notification.isRead(),
                    notification.createdAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                ))));

        mockMvc.perform(MockMvcRequestBuilders.get("/notifications")
            .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("" +
                    "\"sender\":\"pikachu\"," +
                    "\"content\":\"Million volt\"" +
                    "")
            ));

        verify(getNotificationService).notifications(identification);
    }

    @Test
    void notificationWithAllRead() throws Exception {
        given(getNotificationService.checkNotification(identification)).willReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/notification")
            .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("false")
            ));

        verify(getNotificationService).checkNotification(identification);
    }

    @Test
    void notReadAnyOfTheNotification() throws Exception {
        given(getNotificationService.checkNotification(identification)).willReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/notification")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("true")
            ));

        verify(getNotificationService).checkNotification(identification);
    }

    @Test
    void connect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/connect")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());

        verify(notificationService).subscribe("je1ly");
    }

    @Test
    void read() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/notifications/1"))
            .andExpect(status().isNoContent());

        verify(notificationService).read(1L);
    }

    @Test
    void deleteWithSelectedNotification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notifications/1"))
            .andExpect(status().isNoContent());

        verify(deleteNotificationService).deleteNotification(1L);
    }

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notifications")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isNoContent());

        verify(deleteNotificationService).deleteAllNotification(identification);
    }

    @Test
    void deleteWithReadNotification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notifications/read")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isNoContent());

        verify(deleteNotificationService).deleteReadNotification(identification);
    }
}

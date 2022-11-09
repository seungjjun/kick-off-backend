package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.services.CreateCommentService;
import com.junstudio.kickoff.services.GetCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@ActiveProfiles("test")
class
CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCommentService getCommentService;

    @MockBean
    private CreateCommentService createCommentService;

    @Test
    void comment() throws Exception {
        Comment comment = new Comment(1L, "reply", 1L, 1L, LocalDateTime.now());

        given(getCommentService.findComment(1L)).willReturn(List.of(comment));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/comments")
                .param("postId", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"content\":\"reply\"")
            ));
    }

    @Test
    void writeComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"content\":\"comment\"," +
                    "\"userId\":\"1\"," +
                    "\"postId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());
    }
}

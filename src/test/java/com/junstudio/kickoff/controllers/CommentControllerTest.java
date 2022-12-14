package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CommentsDto;
import com.junstudio.kickoff.dtos.SelectedCommentDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Content;
import com.junstudio.kickoff.models.PostId;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.services.CreateCommentService;
import com.junstudio.kickoff.services.DeleteCommentService;
import com.junstudio.kickoff.services.GetCommentService;
import com.junstudio.kickoff.services.PatchCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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

    @MockBean
    private PatchCommentService patchCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    void comment() throws Exception {
        Comment comment = new Comment(1L, new Content("reply"), new UserId(1L), new PostId(1L), LocalDateTime.now());

        given(getCommentService.findComment(any(Long.class), any(Pageable.class)))
            .willReturn(new CommentsDto
                (List.of(new CommentDto(comment.id(), comment.content().value(),
                    comment.userId().value(), comment.postId().value(), comment.isDeleted(),
                    comment.commentDate().toString()))));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/comments")
                .param("postId", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"content\":\"reply\"")
            ));
    }

    @Test
    void writeComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"content\":\"comment\"," +
                    "\"userId\":\"1\"," +
                    "\"postId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());
    }

    @Test
    void patchComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/comments/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"content\":\"comment\"" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(patchCommentService).patchComment(1L, "comment");
    }

    @Test
    void deleteComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/3"))
            .andExpect(status().isNoContent());

        verify(deleteCommentService).deleteComment(3L);
    }

    @Test
    void deleteSelectedComments() throws Exception {
        SelectedCommentDto selectedCommentDto =
            new SelectedCommentDto(List.of(4L, 5L, 6L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/comments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"commentId\": [4, 5, 6]" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(deleteCommentService).deleteComments(selectedCommentDto.getCommentId());
    }
}

package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.CreatePostService;
import com.junstudio.kickoff.services.DeletePostService;
import com.junstudio.kickoff.services.GetPostService;
import com.junstudio.kickoff.services.PatchPostService;
import com.junstudio.kickoff.utils.S3Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@ActiveProfiles("test")
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private PatchPostService patchPostService;

    @MockBean
    private DeletePostService deletePostService;

    @MockBean
    private S3Uploader s3Uploader;

    private Post post;

    private Comment comment;

    private Recomment recomment;

    private Like like;

    private Board board;

    private User user;

    @BeforeEach
    void setup() {
        user = new User(1L, "jel1y", "password", "son7", "image", 1L, false);

        board = Board.fake();
        post = Post.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();
        like = Like.fake();
    }

    @Test
    void postDetail() throws Exception {
        given(getPostService.findPost(any(Long.class)))
            .willReturn(
                new PostDetailDto(
                    1L, new PostInformation("World Cup", "content"),
                    1L, 1L, board, user, "2022", "imageUrl")
            );

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"title\":\"World Cup\"")
            ));
    }

    @Test
    void post() throws Exception {
        given(createPostService.write(any(String.class), any(String.class),
            any(String.class), any(Long.class), any(Long.class))).willReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"title\":\"Erik ten Hag\"," +
                    " \"content\":\"congraturation\"," +
                    " \"imageUrl\":\"imageUrl\"," +
                    " \"userId\":\"1\"," +
                    " \"boardId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createPostService)
            .write(any(String.class), any(String.class),
                any(String.class), any(Long.class), any(Long.class));
    }

    @Test
    void patch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/posts/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"title\":\"patch post\"," +
                    "\"content\":\"modify content\"," +
                    "\"imageUrl\":\"imageUrl\"," +
                    "\"categoryId\":\"1\"" +
                    "}"))
            .andExpect(status().isNoContent());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
            .andExpect(status().isNoContent());

        verify(deletePostService).delete(post.id());
    }
}

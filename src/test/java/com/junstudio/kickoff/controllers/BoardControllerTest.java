package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.BoardDto;
import com.junstudio.kickoff.dtos.BoardsDto;
import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.PostPageDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.BoardName;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.services.GetBoardService;
import com.junstudio.kickoff.services.GetPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private GetBoardService getBoardService;

    Post post;
    User user;
    Comment comment;
    Recomment recomment;
    Like like;
    Board board;

    @BeforeEach
    void setup() {
        user = new User(1L, "jel1y", "password", "son7", "image", new Grade("아마추어"), false, LocalDateTime.now());

        post = Post.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();
        like = Like.fake();
        board = Board.fake();
    }

    @Test
    void board() throws Exception {
        given(getBoardService.boards()).willReturn(new BoardsDto(
            List.of(new BoardDto(board.id(), new BoardName("EPL"), board.parentId(), false))));

        mockMvc.perform(MockMvcRequestBuilders.get("/boards"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("boardName\":{\"value\":\"EPL\"}")
            ));
    }

    @Test
    void posts() throws Exception {
        given(getPostService.posts(any(Long.class), any(Pageable.class)))
            .willReturn(new PostsDto(
                new CreatePostsDto(List.of(post.toDto()), List.of(comment.toDto()),
                    List.of(recomment.toDto()), List.of(like.toDto()),
                    List.of(user.toDto()), List.of(board.toDto())),
                new PostPageDto(1, 1L)));

        mockMvc.perform(MockMvcRequestBuilders.get("/board/1/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"title\":\"Son is EPL King\"")
            ));
    }


    @Test
    void searchPostWithTitle() throws Exception {
        Post post = new Post(
            1L, new UserId(1L), 1L,
            new PostInformation("search post", "content"),
            1L, "imageUrl", LocalDateTime.now());

        given(getPostService.search(any(), any(), any(), any()))
            .willReturn(new PostsDto(
                new CreatePostsDto(
                    List.of(post.toDto()), List.of(comment.toDto()),
                    List.of(recomment.toDto()), List.of(like.toDto()),
                    List.of(user.toDto()), List.of(board.toDto())),
                new PostPageDto(1, 1L))
            );

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"keyword\":\"search\"," +
                    "\"keywordType\":\"title\"" +
                    "}"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"title\":\"search post\"")
            ));
    }

    @Test
    void searchPostWithContent() throws Exception {
        Post post = new Post(
            1L, new UserId(1L), 1L,
            new PostInformation("almond", "almond is delicious"),
            1L, "imageUrl", LocalDateTime.now());

        given(getPostService.search(any(), any(), any(), any()))
            .willReturn(new PostsDto(
                new CreatePostsDto(
                    List.of(post.toDto()), List.of(comment.toDto()),
                    List.of(recomment.toDto()), List.of(like.toDto()),
                    List.of(user.toDto()), List.of(board.toDto())),
                new PostPageDto(1, 1L))
            );

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"keyword\":\"almond\"," +
                    "\"keywordType\":\"content\"" +
                    "}"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"content\":\"almond is delicious\"")
            ));
    }

    @Test
    void searchPostWithAuthor() throws Exception {
        given(getPostService.search(any(), any(), any(), any()))
            .willReturn(new PostsDto(
                new CreatePostsDto(
                    List.of(post.toDto()), List.of(comment.toDto()),
                    List.of(recomment.toDto()), List.of(like.toDto()),
                    List.of(user.toDto()), List.of(board.toDto())),
                new PostPageDto(1, 1L))
            );

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"keyword\":\"son7\"," +
                    "\"keywordType\":\"author\"" +
                    "}"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"title\":\"Son is EPL King\"")
            ));
    }
}

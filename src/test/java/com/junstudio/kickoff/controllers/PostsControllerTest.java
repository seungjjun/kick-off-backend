package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CategoryDto;
import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.LikeDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostPageDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.Recomment;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostsController.class)
@ActiveProfiles("test")
class PostsControllerTest {
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

    private Category category;

    @BeforeEach
    void setup() {
        category = new Category(1L, "EPL", null);

        post = Post.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();
        like = Like.fake();
    }

    @Test
    void posts() throws Exception {
        PostDto postDto = new PostDto(post.id(), post.postInformation(),
            post.categoryId(), post.userId(), post.hit(),
            post.createdAt().toString(), post.imageUrl());

        CommentDto commentDto = new CommentDto(comment.id(), comment.content(),
            comment.userId(), comment.postId(), comment.commentDate().toString());

        ReCommentDto recommentDto =
            new ReCommentDto(recomment.getCommentId(), recomment.getContent(),
                recomment.getCommentId(), recomment.getPostId(),
                recomment.getPostId(), recomment.getCommentDate().toString());

        LikeDto likeDto = new LikeDto(like.id(), like.postId(), like.userId());

        CategoryDto categoryDto = new CategoryDto(category.id(), category.name(), category.getParentId());

        given(getPostService.posts(any(Pageable.class))).willReturn(new CreatePostsDto(
            new PostsDto(List.of(postDto), List.of(commentDto), List.of(recommentDto),
                List.of(likeDto), List.of(categoryDto), any()), new PostPageDto(1, 1L)));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"title\":\"Son is EPL King\"")
            ));
    }

    @Test
    void categoryPosts() throws Exception {
        PostDto postDto = new PostDto(post.id(), post.postInformation(),
            post.categoryId(), post.userId(), post.hit(), post.createdAt().toString(), post.imageUrl());

        given(getPostService.findCategoryPosts(any(), any()))
            .willReturn(new PostsDto(List.of(postDto), new PostPageDto(1, 1L)));

        mockMvc.perform(MockMvcRequestBuilders.get("/category/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("content\":\"Son is the first Asian to score EPL")
            ));
    }

    @Test
    void postDetail() throws Exception {
        given(getPostService.findPost(any(Long.class)))
            .willReturn(new PostDetailDto(1L, new PostInformation("title", "content"), 1L,
                category, "2022", "imageUrl"));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"EPL\"")
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
                    " \"categoryId\":\"1\"" +
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

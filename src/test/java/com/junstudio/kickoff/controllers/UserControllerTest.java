package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.GetUserService;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    User user;
    String identification;

    String token;

    Post post;
    Comment comment;
    Recomment recomment;

    @BeforeEach
    void setup() {
        identification = "je1ly";

        user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", 1L, true);

        post = Post.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();

        token = jwtUtil.encode(identification);
    }

    @Test
    void user() throws Exception {
        given(getUserService.users()).willReturn(List.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("name\":\"Jun")
            ));
    }

    @Test
    void findMyInformation() throws Exception {
        given(getUserService.findMyInformation(identification)).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("jel1y")
            ));
    }

    @Test
    void findUser() throws Exception {
        given(getUserService.findUser(user.id(), identification))
            .willReturn(new UsersDto(
                user,
                List.of(post.toDto()),
                List.of(comment.toDto()),
                List.of(recomment.toDto()),
                List.of(post.toDto()))
            );

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                .header("Authorization", "Bearer " + token)
                .content("userId\":\"1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("title\":\"Son is EPL King")
            ));
    }
}

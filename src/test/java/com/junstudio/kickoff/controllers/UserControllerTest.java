package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.RegistrationRequestDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.CreateUserService;
import com.junstudio.kickoff.services.GetUserService;
import com.junstudio.kickoff.services.PatchUserService;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private PatchUserService patchUserService;

    @MockBean
    private CreateUserService createUserService;

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
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();

        identification = "je1ly";

        user = new User(1L, identification, "encodedPassword",
            "Jun", "profileImage", new Grade("아마추어"), true);

        post = Post.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();

        token = jwtUtil.encode(identification);

        RegistrationRequestDto registrationRequestDto =
            new RegistrationRequestDto("jun", "gmail", "Jun!@123", "Jun!@123");

        given(createUserService.register(
            registrationRequestDto.getName(),
            registrationRequestDto.getIdentification(),
            registrationRequestDto.getPassword(),
            registrationRequestDto.getConfirmPassword())).willReturn(user);
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
        given(getUserService.findMyInformation(identification)).willReturn(new UsersDto(
            user,
            List.of(post.toDto()),
            List.of(comment.toDto()),
            List.of(recomment.toDto()),
            List.of(post.toDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("je1ly")
            ));
    }

    @Test
    void findUser() throws Exception {
        given(getUserService.findUser(any(), any())).willReturn(new UsersDto(
            user,
            List.of(post.toDto()),
            List.of(comment.toDto()),
            List.of(recomment.toDto()),
            List.of(post.toDto()))
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"userName\":\"Jun\"" +
                    "}"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("title\":\"Son is EPL King")
            ));
    }

    @Test
    void patch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"name\":\"son\"," +
                    "\"profileImage\":\"image\"" +
                    "}")
            )
            .andExpect(status().isNoContent());
    }

    @Test
    void register() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"jun\"," +
                    "\"identification\":\"gmail\"," +
                    "\"password\":\"Jun!@123\"," +
                    "\"confirmPassword\":\"Jun!@123\"" +
                    "}"))
            .andExpect(status().isCreated());
    }

    @Test
    void registerWithWrongNameLength() throws Exception {
        RegistrationRequestDto registrationRequestDto =
            new RegistrationRequestDto("NohSeungjun", "gmail", "Jun!@123", "Jun!@123");

        given(createUserService.register(
            registrationRequestDto.getName(),
            registrationRequestDto.getIdentification(),
            registrationRequestDto.getPassword(),
            registrationRequestDto.getConfirmPassword())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"NohSeungjun\"," +
                    "\"identification\":\"gmail\"," +
                    "\"password\":\"Jun!@123\"," +
                    "\"confirmPassword\":\"Jun!@123\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("닉네임은 특수문자를 제외한 2 ~ 10자리여야 합니다.")
            ));
    }

    @Test
    void registerWithWrongIdFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"jun\"," +
                    "\"identification\":\"jun-jingmailcom\"," +
                    "\"password\":\"Jun!@123\"," +
                    "\"confirmPassword\":\"Jun!@123\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("아이디는 4 ~ 16자 영문 소문자, 숫자를 사용해야 합니다.")
            ));
    }

    @Test
    void registerWithWrongPasswordFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"jun\"," +
                    "\"identification\":\"gmail\"," +
                    "\"password\":\"123\"," +
                    "\"confirmPassword\":\"123\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
            ));
    }

    @Test
    void registerWithNameInputFieldIsBlank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"\"," +
                    "\"identification\":\"gmail\"," +
                    "\"password\":\"Jun!@123\"," +
                    "\"confirmPassword\":\"Jun!@123\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("닉네임은 특수문자를 제외한 2 ~ 10자리여야 합니다.")
            ));
    }

    @Test
    void registerWithIdInputFieldIsBlank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"jun\"," +
                    "\"identification\":\"\"," +
                    "\"password\":\"Jun!@123\"," +
                    "\"confirmPassword\":\"Jun!@123\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("아이디는 4 ~ 16자 영문 소문자, 숫자를 사용해야 합니다.")
            ));
    }

    @Test
    void registerWithConfirmPasswordInputFieldIsBlank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                    "\"name\":\"jun\"," +
                    "\"identification\":\"gmail\"," +
                    "\"password\":\"\"," +
                    "\"confirmPassword\":\"\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
            ));
    }
}

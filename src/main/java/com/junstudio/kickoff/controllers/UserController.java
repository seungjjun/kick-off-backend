package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.GetUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {
    private final GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping()
    private UsersDto users() {
        List<UserDto> users = getUserService.users()
            .stream().map(User::toDto)
            .collect(Collectors.toList());

        return new UsersDto(users);
    }

    @GetMapping("me")
    public UserDto user(
        @RequestAttribute("identification") String identification
    ) {
        User user = getUserService.findUser(identification);
        return user.toDto();
    }
}
